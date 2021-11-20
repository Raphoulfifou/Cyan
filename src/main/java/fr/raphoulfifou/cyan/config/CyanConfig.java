package fr.raphoulfifou.cyan.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class CyanConfig
{
    private static final String COMMENT = "This file stores configuration options for Cyan";

    public static final Path DEFAULT_FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve("cyan-options.properties");

    public boolean allowBed;
    public boolean allowKgi;
    public boolean allowSurface;

    public int distanceToEntitiesKgi;
    public int requiredOpLevelKgi;

    private final Path propertiesPath;

    public CyanConfig(Path propertiesPath)
    {
        allowBed = true;
        allowKgi = true;
        allowSurface = true;

        distanceToEntitiesKgi = 14;
        requiredOpLevelKgi = 4;
        this.propertiesPath = propertiesPath;
    }

    /**
     * Initializes the configuration, loading it if it is present and creating a default config otherwise.
     *
     * @throws IOException file exceptions
     */
    public void initialize() throws IOException
    {
        load();
        if (!Files.exists(propertiesPath))
        {
            save();
        }
    }

    public boolean isBedAllowed()
    {
        return allowBed;
    }
    public boolean isKgiAllowed()
    {
        return allowKgi;
    }
    public boolean isSurfaceAllowed()
    {
        return allowSurface;
    }

    public int getDistanceToEntitiesKgi()
    {
        return distanceToEntitiesKgi;
    }
    public int getRequiredOpLevelKgi()
    {
        return requiredOpLevelKgi;
    }

    public void setAllowBed(boolean value)
    {
        this.allowBed = value;
    }
    public void setAllowKgi(boolean value)
    {
        this.allowKgi = value;
    }
    public void setAllowSurface(boolean value)
    {
        this.allowSurface = value;
    }

    public void setDistanceToEntitiesKgi(int value)
    {
        this.distanceToEntitiesKgi = value;
    }
    public void setRequiredOpLevelKgi(int value)
    {
        this.requiredOpLevelKgi = value;
    }

    public void load() throws IOException
    {
        if (!Files.exists(propertiesPath))
        {
            return;
        }

        Properties properties = new Properties();
        properties.load(Files.newInputStream(propertiesPath));
        allowBed = Boolean.parseBoolean(properties.getProperty("allowBed"));
        allowKgi = Boolean.parseBoolean(properties.getProperty("allowKgi"));
        allowSurface = Boolean.parseBoolean(properties.getProperty("allowSurface"));

        distanceToEntitiesKgi = Integer.parseInt(properties.getProperty("distanceToEntitiesKgi"));
        requiredOpLevelKgi = Integer.parseInt(properties.getProperty("requiredOpLevelKgi"));
    }

    /**
     * Serializes the config into a file. Should be called whenever any config values are modified.
     *
     * @throws IOException file exceptions
     */
    public void save() throws IOException
    {
        Properties properties = new Properties();

        if (!Files.exists(propertiesPath))
        {
            return;
        }

        properties.load(Files.newInputStream(propertiesPath));
        properties.setProperty("allowBed", String.valueOf(allowBed));
        properties.setProperty("allowKgi", String.valueOf(allowKgi));
        properties.setProperty("allowSurface", String.valueOf(allowSurface));

        properties.setProperty("distanceToEntitiesKgi", String.valueOf(distanceToEntitiesKgi));
        properties.setProperty("requiredOpLevelKgi", String.valueOf(requiredOpLevelKgi));
        properties.store(Files.newOutputStream(propertiesPath), COMMENT);
    }

    public void saveBoolProperty(String property, boolean value) throws IOException
    {

    }
}
