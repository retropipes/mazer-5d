/*  FantastleReboot: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.file;

import com.puttysoftware.mazer5d.loader.DataFileLoader;

public class FileExtensions {
    // Constants
    private static final int SOUND = 0;
    private static final int IMAGE = 1;
    private static final int MUSIC = 2;
    private static final int MAZE = 3;
    private static final int GAME = 4;
    private static final int LOCKED_MAZE = 5;
    private static final int MAZE_LEVEL = 6;
    private static final int MAZE_TEMP = 7;
    private static final int SCORES = 8;
    private static final int RULE_SET = 9;
    private static final int CHARACTER = 10;
    private static final int REGISTRY = 11;
    private static final int PREFS = 12;
    private static final int DATA_FILE = 13;
    private static final int RAW_FILE = 14;

    // Methods (no period)
    public static String getSoundExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.SOUND);
    }

    public static String getImageExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.IMAGE);
    }

    public static String getMusicExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.MUSIC);
    }

    public static String getMazeExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.MAZE);
    }

    public static String getGameExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.GAME);
    }

    public static String getLockedMazeExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.LOCKED_MAZE);
    }

    public static String getMazeLevelExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.MAZE_LEVEL);
    }

    public static String getMazeTempExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.MAZE_TEMP);
    }

    public static String getScoresExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.SCORES);
    }

    public static String getRuleSetExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.RULE_SET);
    }

    public static String getCharacterExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.CHARACTER);
    }

    public static String getRegistryExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.REGISTRY);
    }

    public static String getPrefsExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.PREFS);
    }

    public static String getDataFileExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.DATA_FILE);
    }

    public static String getRawFileExtension() {
	return DataFileLoader.loadFileExtension(FileExtensions.RAW_FILE);
    }

    // Methods (with period)
    public static String getSoundExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.SOUND);
    }

    public static String getImageExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.IMAGE);
    }

    public static String getMusicExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.MUSIC);
    }

    public static String getMazeExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.MAZE);
    }

    public static String getGameExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.GAME);
    }

    public static String getLockedMazeExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.LOCKED_MAZE);
    }

    public static String getMazeLevelExtensionWithPeriod() {
	return DataFileLoader.loadFileExtension(FileExtensions.MAZE_LEVEL);
    }

    public static String getMazeTempExtensionWithPeriod() {
	return DataFileLoader.loadFileExtension(FileExtensions.MAZE_TEMP);
    }

    public static String getScoresExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.SCORES);
    }

    public static String getRuleSetExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.RULE_SET);
    }

    public static String getCharacterExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.CHARACTER);
    }

    public static String getRegistryExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.REGISTRY);
    }

    public static String getPrefsExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.PREFS);
    }

    public static String getDataFileExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.DATA_FILE);
    }

    public static String getRawFileExtensionWithPeriod() {
	return DataFileLoader.loadFileExtensionWithPeriod(FileExtensions.RAW_FILE);
    }
}
