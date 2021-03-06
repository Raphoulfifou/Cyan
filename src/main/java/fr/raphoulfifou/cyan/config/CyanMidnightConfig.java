package fr.raphoulfifou.cyan.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class CyanMidnightConfig extends MidnightConfig
{

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
    public static boolean useOneLanguage = true;

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
