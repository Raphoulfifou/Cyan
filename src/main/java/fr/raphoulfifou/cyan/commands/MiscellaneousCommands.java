package fr.raphoulfifou.cyan.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

public class MiscellaneousCommands  {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("killgrounditems")
                .executes(MiscellaneousCommands::kgi)
        );
        dispatcher.register(CommandManager.literal("kgi")
                .executes(MiscellaneousCommands::kgi)
        );
    }

    public static int kgi(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();

        // If OP with max level (4)
        if(player.hasPermissionLevel(4)) {
            source.getMinecraftServer().getCommandManager().execute(source,"/kill @e[type=item]");
            source.getPlayer().sendMessage(new TranslatableText("cyan.message.kgi"), true);
        }
        // If not OP
        else {
            source.sendFeedback(new TranslatableText("cyan.message.kgi"), true);
        }
        return Command.SINGLE_SUCCESS;
    }
}