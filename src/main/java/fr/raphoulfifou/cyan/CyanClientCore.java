package fr.raphoulfifou.cyan;

import fr.raphoulfifou.cyan.commands.MiscellaneousCommands;
import fr.raphoulfifou.cyan.commands.TeleportationCommands;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class CyanClientCore implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(CyanServerCore.MODID);
    public static final String CLIENTMODNAME = "[CyanClient]";

    @Override
    // Initialize the differents instances (here commands) when lauched on client (used when in singleplayer)
    public void onInitializeClient() {
        // Register all the commands
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            //InventoryCommands.register(dispatcher);
            TeleportationCommands.register(dispatcher);
            MiscellaneousCommands.register(dispatcher);
        });
        CyanClientCore.LOGGER.info("{} Successfully initialized commands", CLIENTMODNAME);
        CyanClientCore.LOGGER.info("{} Successfully completed initialization", CLIENTMODNAME);
    }
}