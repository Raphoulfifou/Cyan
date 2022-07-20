package fr.raphoulfifou.cyan.util;

import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class ChatConstants
{

    public static String line_start = "\n§d>> ";
    public static String line_start_error = "\n§c>> ";

    public static String notOP = "§cYou don't have the required permission to do that";

    public static Formatting green = Formatting.GREEN;
    public static Formatting red = Formatting.RED;
    public static Formatting gold = Formatting.GOLD;

    public static final Map<String, String> optionsTraductions = new HashMap<>();

    public static void generateOptionsTraductionsMap()
    {
        optionsTraductions.put("allowBed", "§6>> §3/bed allowed : %s");
        optionsTraductions.put("allowKgi", "§6>> §3/kgi allowed : %s");
        optionsTraductions.put("allowSurface", "§6>> §3/surface allowed : %s");

        optionsTraductions.put("distanceToEntitiesKgi", "§6>> §3kgi distance (in chunks) : %s");
        optionsTraductions.put("minOpLevelExeKgi", "§6>> §3Minimu OP level for /kgi : %s");

        optionsTraductions.put("useOneLanguage", "§6>> §3Use only one language : %s");
    }

    public static String getOptionTraduction(String optionName)
    {
        return optionsTraductions.get(optionName);
    }

    public static Map<String, String> getOptionsTraductionsMap()
    {
        return optionsTraductions;
    }


}
