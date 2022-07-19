package fr.raphoulfifou.cyan.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.HashMap;
import java.util.Map;

public class CyanMidnightConfig extends MidnightConfig
{

    public static Map<String, Object> options = new HashMap<>();

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
    public static int minOpLevelExeKgi = 4;

    @Comment
    public static Comment otherOptions;
    @Entry
    @Environment(EnvType.SERVER)
    public static boolean useOneLanguage = true;

    /**
     * Generates the map that will contain all options and their value
     *
     * @return a <code>Map</code> that contains all the options
     */
    public static Map<String, Object> generateOptionsMap()
    {
        options.put("allowBed", allowBed);
        options.put("allowKgi", allowKgi);
        options.put("allowSurface", allowSurface);

        options.put("distanceToEntitiesKgi", distanceToEntitiesKgi);
        options.put("minOpLevelExeKgi", minOpLevelExeKgi);

        options.put("useOneLanguage", useOneLanguage);

        return options;
    }

    /**
     * Get the option given in parameter
     *
     * @param optionName the option name
     *
     * @return the option value (int | bool)
     */
    public static Object getOption(String optionName)
    {
        return options.get(optionName);
    }

    // Booleans
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

    @Environment(EnvType.SERVER)
    public static void setUseOneLanguage(boolean value)
    {
        useOneLanguage = value;
        write("cyan");
    }

    // Integers
    public static void setDistanceToEntitiesKgi(int value)
    {
        distanceToEntitiesKgi = value;
        write("cyan");
    }

    public static void setMinOpLevelExeKgi(int value)
    {
        minOpLevelExeKgi = value;
        write("cyan");
    }

}
