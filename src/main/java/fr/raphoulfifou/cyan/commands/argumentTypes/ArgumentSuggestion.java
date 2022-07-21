package fr.raphoulfifou.cyan.commands.argumentTypes;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ArgumentSuggestion
{

    /**
     * @param context the command context
     * @param builder the suggestion builder
     *
     * @return a suggestion with all available options
     */
    public static CompletableFuture<Suggestions> getOptions(@NotNull CommandContext<ServerCommandSource> context, @NotNull SuggestionsBuilder builder)
    {
        MinecraftServer server = context.getSource().getServer();

        List<String> exeLevels = new ArrayList<>();
        exeLevels.add("all");
        exeLevels.add("bed");
        exeLevels.add("kgi");
        exeLevels.add("surface");

        // Return the suggestion handler
        return CommandSource.suggestMatching(exeLevels, builder);
    }

    /**
     * @param context the command context
     * @param builder the suggestion builder
     *
     * @return a suggestion with all option types
     */
    public static CompletableFuture<Suggestions> getOptionTypes(@NotNull CommandContext<ServerCommandSource> context, @NotNull SuggestionsBuilder builder)
    {
        MinecraftServer server = context.getSource().getServer();

        List<String> exeLevels = new ArrayList<>();
        exeLevels.add("allow");
        exeLevels.add("minOpLevelExe");
        exeLevels.add("other");

        // Return the suggestion handler
        return CommandSource.suggestMatching(exeLevels, builder);
    }

}
