package fr.raphoulfifou.cyan.config.options;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CyanOptions
{
    public static final String DEFAULT_FILE_NAME = "cyan-options.json";

    public final GeneralSettings generalSettings = new GeneralSettings();
    public final CommandSettings commandSettings = new CommandSettings();

    private Path configPath;
    private boolean readOnly;

    public static class GeneralSettings
    {
        public boolean allowBed = true;
        public boolean allowKgi = true;
        public boolean allowSurface = true;
    }

    public static class CommandSettings
    {
        public int distanceToEntitiesKgi = 14;
        public int requiredOpLevelKgi = 4;
    }

    public static @NotNull CyanOptions defaults() {
        var options = new CyanOptions();
        options.configPath = getConfigPath(DEFAULT_FILE_NAME);

        return options;
    }

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.PRIVATE)
            .create();

    public static @NotNull CyanOptions load() {
        return load(DEFAULT_FILE_NAME);
    }

    public static @NotNull CyanOptions load(String fileName)
    {
        Path path = getConfigPath(fileName);
        CyanOptions config;

        if (Files.exists(path)) {
            try (FileReader reader = new FileReader(path.toFile())) {
                config = GSON.fromJson(reader, CyanOptions.class);
            } catch (IOException e) {
                throw new RuntimeException("Could not parse config", e);
            }
        } else {
            config = new CyanOptions();
        }

        config.configPath = path;

        try {
            config.writeChanges();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't update config file", e);
        }

        return config;
    }

    private static @NotNull Path getConfigPath(String name) {
        return FabricLoader.getInstance()
                .getConfigDir()
                .resolve(name);
    }

    public void writeChanges() throws IOException
    {
        if (this.isReadOnly())
        {
            throw new IllegalStateException("Config file is read-only");
        }

        Path dir = this.configPath.getParent();

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        } else if (!Files.isDirectory(dir)) {
            throw new IOException("Not a directory: " + dir);
        }

        // Use a temporary location next to the config's final destination
        Path tempPath = this.configPath.resolveSibling(this.getFileName() + ".tmp");

        // Write the file to our temporary location
        Files.writeString(tempPath, GSON.toJson(this));

        // Atomically replace the old config file (if it exists) with the temporary file
        Files.move(tempPath, this.configPath, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
    }

    public boolean isReadOnly()
    {
        return this.readOnly;
    }

    public void setReadOnly()
    {
        this.readOnly = true;
    }

    public String getFileName()
    {
        return this.configPath.getFileName().toString();
    }
}
