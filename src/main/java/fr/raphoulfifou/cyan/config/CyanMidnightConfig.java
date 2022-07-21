package fr.raphoulfifou.cyan.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CyanMidnightConfig extends MidnightConfig
{

    public static Map<String, Object> allowOptionsMap = new HashMap<>();
    public static Map<String, Object> exeLevelOptionsMap = new HashMap<>();
    public static Map<String, Object> otherOptionsMap = new HashMap<>();
    public static Map<String, Map<String, Object>> optionsMap = new HashMap<>();

    @Comment
    public static Comment allowOptions;
    @Entry
    public static boolean allowBed = true;
    @Entry
    public static boolean allowKgi = true;
    @Entry
    public static boolean allowSurface = true;

    @Comment
    public static Comment intOptions;
    @Entry(min = 1, max = 64)
    public static int distanceToEntitiesKgi = 14;
    @Entry(min = 0, max = 4)
    public static int minOpLevelExe = 4;
    @Entry(min = 0, max = 4)
    public static int minOpLevelExeBed = 0;
    @Entry(min = 0, max = 4)
    public static int minOpLevelExeKgi = 4;
    @Entry(min = 0, max = 4)
    public static int minOpLevelExeSurface = 0;

    @Comment
    public static Comment otherOptions;
    @Entry
    public static boolean useOneLanguage = false;

    public static Map<String, Object> generateAllowOptionsMap()
    {
        allowOptionsMap.put("allowBed", allowBed);
        allowOptionsMap.put("allowKgi", allowKgi);
        allowOptionsMap.put("allowSurface", allowSurface);

        return allowOptionsMap;
    }

    public static Map<String, Object> generateExeLevelOptionsMap()
    {
        exeLevelOptionsMap.put("minOpLevelExeGeneral", minOpLevelExe);
        exeLevelOptionsMap.put("minOpLevelExeBed", minOpLevelExeBed);
        exeLevelOptionsMap.put("minOpLevelExeKgi", minOpLevelExeKgi);
        exeLevelOptionsMap.put("minOpLevelExeSurface", minOpLevelExeSurface);

        return exeLevelOptionsMap;
    }

    public static Map<String, Object> generateOtherOptionsMap()
    {
        otherOptionsMap.put("distanceToEntitiesKgi", distanceToEntitiesKgi);
        otherOptionsMap.put("useOneLanguage", useOneLanguage);

        return otherOptionsMap;
    }

    /**
     * Generates the map that will contain all options and their value
     *
     * @return a <code>Map</code> that contains all the options
     */
    public static Map<String, Map<String, Object>> generateOptionsMap()
    {
        allowOptionsMap = generateAllowOptionsMap();
        exeLevelOptionsMap = generateExeLevelOptionsMap();
        otherOptionsMap = generateOtherOptionsMap();
        optionsMap.put("allows", allowOptionsMap);
        optionsMap.put("minOpLevelExe", exeLevelOptionsMap);
        optionsMap.put("other", otherOptionsMap);

        return optionsMap;
    }

    public static void setBoolOption(@NotNull String optionName, boolean value)
    {
        switch (optionName)
        {
            case "all" -> allowBed = allowKgi = allowSurface = value;
            case "bed" -> allowBed = value;
            case "kgi" -> allowKgi = value;
            case "surface" -> allowSurface = value;
        }
        write("cyan");
    }

    public static void setIntOption(@NotNull String optionName, int value)
    {
        switch (optionName)
        {
            case "distanceToEntitiesKgi" -> distanceToEntitiesKgi = value;
            case "general" -> minOpLevelExe = minOpLevelExeBed = minOpLevelExeKgi = minOpLevelExeSurface = value;
            case "generalExe" -> minOpLevelExe = value;
            case "bed" -> minOpLevelExeBed = value;
            case "kgi" -> minOpLevelExeKgi = value;
            case "surface" -> minOpLevelExeSurface = value;
        }
        write("cyan");
    }

    // Allows
    public static void setAllowBed(boolean value)
    {
        allowBed = value;
        write("cyan");
    }

    public static void setAllowKgi(boolean value)
    {
        allowKgi = value;
        write("cyan");
    }

    public static void setAllowSurface(boolean value)
    {
        allowSurface = value;
        write("cyan");
    }

    // Sets
    public static void setDistanceToEntitiesKgi(int value)
    {
        distanceToEntitiesKgi = value;
        write("cyan");
    }

    public static void setMinOpLevelExe(int value)
    {
        minOpLevelExe = value;
        write("cyan");
    }

    public static void setMinOpLevelExeBed(int value)
    {
        minOpLevelExeBed = value;
        write("cyan");
    }

    public static void setMinOpLevelExeKgi(int value)
    {
        minOpLevelExeKgi = value;
        write("cyan");
    }

    public static void setMinOpLevelExeSurface(int value)
    {
        minOpLevelExeSurface = value;
        write("cyan");
    }

    // Other

    @Environment(EnvType.SERVER)
    public static void setUseOneLanguage(boolean value)
    {
        useOneLanguage = value;
        write("cyan");
    }

}
