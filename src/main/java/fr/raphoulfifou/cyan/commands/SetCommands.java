package fr.raphoulfifou.cyan.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.raphoulfifou.cyan.config.CyanConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @since 0.2.6
 * @author Raphoulfifou
 */
public class SetCommands
{
    public static final CyanConfig CYAN_CONFIG = new CyanConfig(CyanConfig.DEFAULT_FILE_PATH);

    public static void register(@NotNull CommandDispatcher<ServerCommandSource> dispatcher)
    {
        dispatcher.register(CommandManager.literal("setAllowBed")
                .then(CommandManager.argument("bool", BoolArgumentType.bool())
                    .executes(SetCommands::setAllowBed)
                )
        );
        dispatcher.register(CommandManager.literal("setAllowKgi")
                .then(CommandManager.argument("bool", BoolArgumentType.bool())
                        .executes(SetCommands::setAllowKgi)
                )
        );
        dispatcher.register(CommandManager.literal("setAllowSurface")
                .then(CommandManager.argument("bool", BoolArgumentType.bool())
                        .executes(SetCommands::setAllowSurface)
                )
        );

        dispatcher.register(CommandManager.literal("setDistanceToEntitiesKgi")
                .then(CommandManager.argument("int", IntegerArgumentType.integer())
                        .executes(SetCommands::setDistanceToEntitiesKgi)
                )
        );
        dispatcher.register(CommandManager.literal("setRequiredOpLevelKgi")
                .then(CommandManager.argument("int", IntegerArgumentType.integer())
                        .executes(SetCommands::setRequiredOpLevelKgi)
                )
        );
    }

    /**
     * <p>Called when a player execute the command "/setAllowBed (true|false)"</p>
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
    public static int setAllowBed(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        boolean arg = BoolArgumentType.getBool(context, "bool");

        // If OP with max level (4)
        if(player.hasPermissionLevel(4)) {
            CYAN_CONFIG.setAllowBed(arg);
            try {
                CYAN_CONFIG.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage(new TranslatableText("cyan.message.setAllowBed", Boolean.toString(arg)), true);
        }
        // If not OP or not OP with max level
        else {
            source.sendFeedback(new TranslatableText("cyan.message.notOp"), true);
        }
        return Command.SINGLE_SUCCESS;
    }

    /**
     * <p>Called when a player execute the command "/setAllowKgi (true|false)"</p>
     *
     * <ul>If the player has a permission level equal to 4
     *      <li>-> Enables/disables the use of the /kgi command</li>
     * </ul>
     * <ul>Else:
     *      <li>-> The player receive a message saying that it doesn't have the required permission</li>
     * </ul>
     *
     * @throws CommandSyntaxException if the syntaxe of the command isn't correct
     */
    public static int setAllowKgi(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        boolean arg = BoolArgumentType.getBool(context, "bool");

        // If OP with max level (4)
        if(player.hasPermissionLevel(4)) {
            CYAN_CONFIG.setAllowKgi(arg);
            try {
                CYAN_CONFIG.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage(new TranslatableText("cyan.message.setAllowKgi", Boolean.toString(arg)), true);
        }
        // If not OP or not OP with max level
        else {
            source.sendFeedback(new TranslatableText("cyan.message.notOp"), true);
        }
        return Command.SINGLE_SUCCESS;
    }

    /**
     * <p>Called when a player execute the command "/setAllowSurface (true|false)"</p>
     *
     * <ul>If the player has a permission level equal to 4
     *      <li>-> Enables/disables the use of the /surface command</li>
     * </ul>
     * <ul>Else:
     *      <li>-> The player receive a message saying that it doesn't have the required permission</li>
     * </ul>
     *
     * @throws CommandSyntaxException if the syntaxe of the command isn't correct
     */
    public static int setAllowSurface(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        boolean arg = BoolArgumentType.getBool(context, "bool");

        // If OP with max level (4)
        if(player.hasPermissionLevel(4)) {
            CYAN_CONFIG.setAllowSurface(arg);
            try {
                CYAN_CONFIG.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage(new TranslatableText("cyan.message.setAllowSurface", Boolean.toString(arg)), true);
        }
        // If not OP or not OP with max level
        else {
            source.sendFeedback(new TranslatableText("cyan.message.notOp"), true);
        }
        return Command.SINGLE_SUCCESS;
    }

    /**
     * <p>Called when a player execute the command "/setDistanceToEntitiesKgi (int)"</p>
     *
     * <ul>If the player has a permission level equal to 4
     *      <li>-> Set the distance (in chunks) in which the ground items will be removed</li>
     * </ul>
     * <ul>Else:
     *      <li>-> The player receive a message saying that it doesn't have the required permission</li>
     * </ul>
     *
     * @throws CommandSyntaxException if the syntaxe of the command isn't correct
     */
    public static int setDistanceToEntitiesKgi(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException
    {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        int arg = IntegerArgumentType.getInteger(context, "int");

        // If OP with max level (4)
        if(player.hasPermissionLevel(4)) {
            CYAN_CONFIG.setDistanceToEntitiesKgi(arg);
            try {
                CYAN_CONFIG.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage(new TranslatableText("cyan.message.setDistanceToEntitiesKgi", arg), true);
        }
        // If not OP or not OP with max level
        else {
            source.sendFeedback(new TranslatableText("cyan.message.notOp"), true);
        }
        return Command.SINGLE_SUCCESS;
    }

    /**
     * <p>Called when a player execute the command "/setRequiredOpLevelKgi (int)"</p>
     *
     * <ul>If the player has a permission level equal to 4
     *      <li>-> Set the minimum OP level required to execute the /kgi command</li>
     * </ul>
     * <ul>Else:
     *      <li>-> The player receive a message saying that it doesn't have the required permission</li>
     * </ul>
     *
     * @throws CommandSyntaxException if the syntaxe of the command isn't correct
     */
    public static int setRequiredOpLevelKgi(@NotNull CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        int arg = IntegerArgumentType.getInteger(context, "int");

        // If OP with max level (4)
        if (player.hasPermissionLevel(CYAN_CONFIG.getRequiredOpLevelKgi())) {
            CYAN_CONFIG.setRequiredOpLevelKgi(arg);
            try {
                CYAN_CONFIG.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage(new TranslatableText("cyan.message.setRequiredOpLevelKgi", arg), true);
        }
        // If not OP or not OP with max level
        else {
            source.sendFeedback(new TranslatableText("cyan.message.notOp"), true);
        }
        return Command.SINGLE_SUCCESS;
    }
}