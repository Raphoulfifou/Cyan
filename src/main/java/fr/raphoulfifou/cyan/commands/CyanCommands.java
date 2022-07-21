package fr.raphoulfifou.cyan.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.raphoulfifou.cyan.commands.argumentTypes.ArgumentSuggestion;
import fr.raphoulfifou.cyan.config.CyanMidnightConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static fr.raphoulfifou.cyan.util.ChatConstants.*;
import static fr.raphoulfifou.cyanlib.util.ChatUtil.sendPlayerMessage;

public class CyanCommands
{

    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher)
    {
        dispatcher.register(CommandManager.literal("cyan")
                .then(CommandManager.literal("set")
                        .then(CommandManager.argument("optionType", StringArgumentType.string())
                                .suggests(ArgumentSuggestion::getOptionTypes)
                                .then(CommandManager.argument("option", StringArgumentType.string())
                                        .suggests(ArgumentSuggestion::getOptions)
                                        .then(CommandManager.argument("boolValue", BoolArgumentType.bool())
                                                .executes(CyanCommands::setBoolOption)
                                        )
                                        .then(CommandManager.argument("intValue", IntegerArgumentType.integer())
                                                //.executes(CyanCommands::setIntOption)
                                        )
                                )
                        )
                )
                .executes(GetCommand::getConfigOptions)
        );
    }

    /**
     * <p>Called when a player execute the command <code>/cyan set allow [optionName] [true|false]</code></p>
     *
     * <ul>If the player has a permission level equal to 4
     *      <li>-> Enables/disables the use of the /bed command</li>
     * </ul>
     * <ul>Else:
     *      <li>-> The player receive a message saying that it doesn't have the required permission</li>
     * </ul>
     *
     * @throws CommandSyntaxException if the syntaxe of the command isn't correct
     */
    public static int setBoolOption(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();

        Map<String, Map<String, Object>> options = CyanMidnightConfig.generateOptionsMap();

        String optionType = StringArgumentType.getString(context, "optionType");
        String option = StringArgumentType.getString(context, "option");
        boolean boolValue = BoolArgumentType.getBool(context, "boolValue");

        // Used for the translationPath (we geet the word in lowercase but we need the first letter in uppercase)
        String upperCaseOptionName = String.valueOf(option.charAt(0)).toUpperCase();
        String tmpOption = option.substring(1);
        upperCaseOptionName = upperCaseOptionName.concat(tmpOption);

        /*if (Objects.equals(option, "all"))
        {
            option = "general";
        }*/

        // If OP with minimum defined level (minOpLevelExe option)
        if (player.hasPermissionLevel((Integer) options.get("minOpLevelExe").get("minOpLevelExeGeneral")))
        {
            CyanMidnightConfig.setBoolOption(option, boolValue);
            if (boolValue)
            {
                if ("all".equals(option))
                {
                    sendPlayerMessage(player,
                            line_start + "§3Allow options have been set to %s",
                            green + Boolean.toString(boolValue),
                            "cyan.message.setAllow",
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                } else
                {
                    sendPlayerMessage(player,
                            line_start + "§3setAllow%s %s".formatted(upperCaseOptionName, "option have been set to %s"),
                            green + Boolean.toString(boolValue),
                            "cyan.message.setAllow%s".formatted(upperCaseOptionName),
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                }
            } else
            {
                if ("all".equals(option))
                {
                    sendPlayerMessage(player,
                            line_start + "§3Allow options have been set to %s",
                            red + Boolean.toString(boolValue),
                            "cyan.message.setAllow",
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                } else
                {
                    sendPlayerMessage(player,
                            line_start + "§3setAllow%s %s".formatted(upperCaseOptionName, "option have been set to %s"),
                            red + Boolean.toString(boolValue),
                            "cyan.message.setAllow%s".formatted(upperCaseOptionName),
                            false,
                            CyanMidnightConfig.useOneLanguage
                    );
                }
            }
        }
        // If not OP or not OP with max level
        else
        {
            sendPlayerMessage(player,
                    notOP,
                    null,
                    "cyan.message.notOp",
                    true,
                    CyanMidnightConfig.useOneLanguage
            );
            return 0;
        }
        return Command.SINGLE_SUCCESS;
    }

}
