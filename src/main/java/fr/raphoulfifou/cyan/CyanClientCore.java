package fr.raphoulfifou.cyan;

import fr.raphoulfifou.cyan.commands.MiscellaneousCommands;
import fr.raphoulfifou.cyan.commands.SetCommands;
import fr.raphoulfifou.cyan.commands.TeleportationCommands;
import fr.raphoulfifou.cyan.config.CyanConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @since 0.0.1
 * @author Raphoulfifou
 */
@Environment(EnvType.CLIENT)
public class CyanClientCore implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(CyanServerCore.MODID);
    public static final String CLIENTMODNAME = "[CyanClient]";

    @Override
    // Initialize the differents parts of the mod when lauched on client (used when in singleplayer)
    public void onInitializeClient()
    {
        CyanConfig cyanConfig = new CyanConfig(FabricLoader.getInstance().getConfigDir().resolve("cyan-options.properties"));

        try {
            cyanConfig.initialize();
        } catch (IOException e) {
            LOGGER.error("Failed to initialize Cyan configuration, default values will be used instead");
            LOGGER.catching(Level.ERROR, e);
        }

        // Register all the commands
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
        {
            TeleportationCommands.register(dispatcher);
            MiscellaneousCommands.register(dispatcher);
            SetCommands.register(dispatcher);
        });
        CyanClientCore.LOGGER.info("{} Successfully initialized commands", CLIENTMODNAME);
        CyanClientCore.LOGGER.info("{} Successfully completed initialization", CLIENTMODNAME);
    }
}