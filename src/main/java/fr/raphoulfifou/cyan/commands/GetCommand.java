package fr.raphoulfifou.cyan.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.raphoulfifou.cyan.config.CyanMidnightConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static fr.raphoulfifou.cyanlib.util.ChatUtil.sendPlayerMessage;

/**
 * @since 0.4.1
 */
public class GetCommand
{

    static Formatting a_c = Formatting.GREEN;
    static Formatting b_c = Formatting.GREEN;
    static Formatting c_c = Formatting.GREEN;
    static Formatting d_c = Formatting.GREEN;

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

        Map<String, Object> options = CyanMidnightConfig.generateOptionsMap();

        sendPlayerMessage(player,
                "§6|--> §3Options defined for the Cyan mod :",
                null,
                "cyan.message.getCfgOptions.header",
                false,
                CyanMidnightConfig.useOneLanguage
        );

        for (Map.Entry<String, Object> entry : options.entrySet())
        {
            String key = entry.getKey();
            if (entry.getValue() instanceof Boolean value)
            {
                if (value)
                {
                    sendPlayerMessage(player,
                            "%s allowed : ".formatted(key),
                            Formatting.GREEN + Boolean.toString(value),
                            "cyan.message.getCfgOptions.%s".formatted(key),
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                } else
                {
                    sendPlayerMessage(player,
                            "%s allowed : ".formatted(key),
                            Formatting.RED + Boolean.toString(value),
                            "cyan.message.getCfgOptions.%s".formatted(key),
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                }
            } else if (entry.getValue() instanceof Integer value)
            {
                sendPlayerMessage(player,
                        "%s allowed : ".formatted(key),
                        Formatting.GOLD + Integer.toString(value),
                        "cyan.message.getCfgOptions.%s".formatted(key),
                        false,
                        CyanMidnightConfig.useOneLanguage
                );
            }
        }

        return Command.SINGLE_SUCCESS;
    }

}
