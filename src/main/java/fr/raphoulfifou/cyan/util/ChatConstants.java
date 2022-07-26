package fr.raphoulfifou.cyan.util;

import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatConstants
{

    public static String line_start = "\n§d>> ";
    public static String line_start_error = "\n§c>> ";

    public static String notOP = "§cYou don't have the required permission to do that";

    public static Formatting green = Formatting.GREEN;
    public static Formatting red = Formatting.RED;
    public static Formatting gold = Formatting.GOLD;

    public static final Map<String, String> optionsTraductionsMap = new HashMap<>();
    public static final Map<String, String> commandsTraductionsMap = new HashMap<>();
    public static final Map<String, Map<String, String>> traductions = new HashMap<>();
    public static final List<String> commandsList = new ArrayList<>();

    public static void generateOptionsTraductionsMap()
    {
        optionsTraductionsMap.put("allowBed", "§6>> §3/bed allowed : %s");
        optionsTraductionsMap.put("allowKgi", "§6>> §3/kgi allowed : %s");
        optionsTraductionsMap.put("allowSurface", "§6>> §3/surface allowed : %s");

        optionsTraductionsMap.put("distanceToEntitiesKgi", "§6>> §3kgi distance (in chunks) : %s");
        optionsTraductionsMap.put("minOpLevelExe", "§6>> §3Minimu OP level for /kgi : %s");
        optionsTraductionsMap.put("minOpLevelExeBed", "§6>> §3Minimu OP level for /kgi : %s");
        optionsTraductionsMap.put("minOpLevelExeKgi", "§6>> §3Minimu OP level for /kgi : %s");
        optionsTraductionsMap.put("minOpLevelExeSurface", "§6>> §3Minimu OP level for /kgi : %s");

        optionsTraductionsMap.put("useOneLanguage", "§6>> §3Use only one language : %s");
    }

    public static void generateCommandsTraductionsMap()
    {
        commandsTraductionsMap.put("header", "\nDescription of the §e/%s command :");

        commandsTraductionsMap.put("bed", "The §e/bed §fcommand teleports you to your bed or respawn anchor");
        commandsTraductionsMap.put("kgi", "The §e/kgi §fcommand kills all item on the ground in a specific radius.\n§e/kgi §fto kill items in the default radius.\n§e/kgi [distance_in_chunks] §fto kill items in the specified radius");
        commandsTraductionsMap.put("surface", "The §e/surface §fcommand teleports you to the highest block located at your XY position");
    }

    public static Map<String, Map<String, String>> generateTraductionsMap()
    {
        generateOptionsTraductionsMap();
        generateCommandsTraductionsMap();

        traductions.put("options", optionsTraductionsMap);
        traductions.put("commands", commandsTraductionsMap);


        return traductions;
    }

    public static List<String> generatecCommandsMap()
    {
        commandsList.add("bed");
        commandsList.add("kgi");
        commandsList.add("surface");


        return commandsList;
    }

    public static String getOptionTraduction(String optionName)
    {
        return optionsTraductionsMap.get(optionName);
    }

    public static Map<String, String> getOptionsTraductionsMap()
    {
        return optionsTraductionsMap;
    }


}
