package fr.raphoulfifou.cyan.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.raphoulfifou.cyan.config.CyanMidnightConfig;
import fr.raphoulfifou.cyan.util.ChatConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static fr.raphoulfifou.cyan.util.ChatConstants.*;
import static fr.raphoulfifou.cyanlib.util.ChatUtil.sendPlayerMessage;

/**
 * @since 0.4.1
 */
public class GetCommand
{

    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher)
    {
        dispatcher.register(CommandManager.literal("getCyanConfigOptions")
                .executes(GetCommand::getConfigOptions)
        );
    }

    /**
     * <p>Called when a player execute the command <code>/getCyanConfigOptions</code></p>
     * <p>Send a player in the player's chat with all the mod's options and their values</p>
     *
     * @throws CommandSyntaxException if the syntaxe of the command isn't correct
     */
    public static int getConfigOptions(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();

        Map<String, Map<String, Object>> options = CyanMidnightConfig.generateOptionsMap();

        sendPlayerMessage(player,
                "§6|--> §3Options defined for the Cyan mod :",
                null,
                "cyan.message.getCfgOptions.header",
                false,
                CyanMidnightConfig.useOneLanguage
        );

        for (Map.Entry<String, Map<String, Object>> entry : options.entrySet())
        {
            Map<String, Object> key = entry.getValue();
            for (Map.Entry<String, Object> entry2 : key.entrySet())
            {
                String currentTrad = null;
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER)
                {
                    currentTrad = ChatConstants.getOptionTraduction(entry2.getKey());
                }

                if (entry2.getValue() instanceof Boolean value)
                {
                    if (value)
                    {
                        sendPlayerMessage(player,
                                currentTrad,
                                green + Boolean.toString(value),
                                "cyan.message.getCfgOptions.%s".formatted(key),
                                false,
                                CyanMidnightConfig.useOneLanguage
                        );
                    } else
                    {
                        sendPlayerMessage(player,
                                currentTrad,
                                red + Boolean.toString(value),
                                "cyan.message.getCfgOptions.%s".formatted(key),
                                false,
                                CyanMidnightConfig.useOneLanguage
                        );
                    }
                } else if (entry2.getValue() instanceof Integer value)
                {
                    sendPlayerMessage(player,
                            currentTrad,
                            gold + Integer.toString(value),
                            "cyan.message.getCfgOptions.%s".formatted(key),
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                }
            }
        }

        return Command.SINGLE_SUCCESS;
    }

}
