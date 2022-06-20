/*  FantastleReboot: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.files;

public class FileExtensions {
    // Constants
    private static final String MAZE_EXTENSION = "jsonmaze.zip";
    private static final String GAME_EXTENSION = "jsongame.zip";
    private static final String LOCKED_MAZE_EXTENSION = "jsonmaze.locked.zip";
    private static final String MAZE_LEVEL_EXTENSION = "level.json";
    private static final String CHARACTER_EXTENSION = "partymember.json";
    private static final String REGISTRY_EXTENSION = "registry.json";
    private static final String PREFS_EXTENSION = "prefs.properties";
    private static final String DATA_FILE_EXTENSION = "txt";
    private static final String RAW_FILE_EXTENSION = "json";

    // Methods
    public static String getMazeExtension() {
	return FileExtensions.MAZE_EXTENSION;
    }

    public static String getMazeExtensionWithPeriod() {
	return "." + FileExtensions.MAZE_EXTENSION;
    }
    
    public static String getLockedMazeExtension() {
	return FileExtensions.LOCKED_MAZE_EXTENSION;
    }

    public static String getLockedMazeExtensionWithPeriod() {
	return "." + FileExtensions.LOCKED_MAZE_EXTENSION;
    }

    public static String getMazeLevelExtensionWithPeriod() {
	return "." + FileExtensions.MAZE_LEVEL_EXTENSION;
    }

    public static String getGameExtension() {
	return FileExtensions.GAME_EXTENSION;
    }

    public static String getGameExtensionWithPeriod() {
	return "." + FileExtensions.GAME_EXTENSION;
    }

    public static String getCharacterExtension() {
	return FileExtensions.CHARACTER_EXTENSION;
    }

    public static String getPrefsExtension() {
	return FileExtensions.PREFS_EXTENSION;
    }
    
    public static String getPrefsExtensionWithPeriod() {
	return "." + FileExtensions.PREFS_EXTENSION;
    }

    public static String getCharacterExtensionWithPeriod() {
	return "." + FileExtensions.CHARACTER_EXTENSION;
    }

    public static String getRegistryExtensionWithPeriod() {
	return "." + FileExtensions.REGISTRY_EXTENSION;
    }

    public static String getDataFileExtensionWithPeriod() {
	return "." + FileExtensions.DATA_FILE_EXTENSION;
    }
    
    public static String getRawFileExtensionWithPeriod() {
	return "." + FileExtensions.RAW_FILE_EXTENSION;
    }
}
