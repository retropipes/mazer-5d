/*  FantastleReboot: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.files;

import com.puttysoftware.mazer5d.locale.LocaleLoader;

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
	return LocaleLoader.loadFileExtension(FileExtensions.SOUND);
    }

    public static String getImageExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.IMAGE);
    }

    public static String getMusicExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.MUSIC);
    }

    public static String getMazeExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.MAZE);
    }

    public static String getGameExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.GAME);
    }

    public static String getLockedMazeExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.LOCKED_MAZE);
    }

    public static String getMazeLevelExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.MAZE_LEVEL);
    }

    public static String getMazeTempExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.MAZE_TEMP);
    }

    public static String getScoresExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.SCORES);
    }

    public static String getRuleSetExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.RULE_SET);
    }

    public static String getCharacterExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.CHARACTER);
    }

    public static String getRegistryExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.REGISTRY);
    }

    public static String getPrefsExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.PREFS);
    }

    public static String getDataFileExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.DATA_FILE);
    }

    public static String getRawFileExtension() {
	return LocaleLoader.loadFileExtension(FileExtensions.RAW_FILE);
    }

    // Methods (with period)
    public static String getSoundExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.SOUND);
    }

    public static String getImageExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.IMAGE);
    }

    public static String getMusicExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.MUSIC);
    }

    public static String getMazeExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.MAZE);
    }

    public static String getGameExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.GAME);
    }

    public static String getLockedMazeExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.LOCKED_MAZE);
    }

    public static String getMazeLevelExtensionWithPeriod() {
	return LocaleLoader.loadFileExtension(FileExtensions.MAZE_LEVEL);
    }

    public static String getMazeTempExtensionWithPeriod() {
	return LocaleLoader.loadFileExtension(FileExtensions.MAZE_TEMP);
    }

    public static String getScoresExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.SCORES);
    }

    public static String getRuleSetExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.RULE_SET);
    }

    public static String getCharacterExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.CHARACTER);
    }

    public static String getRegistryExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.REGISTRY);
    }

    public static String getPrefsExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.PREFS);
    }

    public static String getDataFileExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.DATA_FILE);
    }

    public static String getRawFileExtensionWithPeriod() {
	return LocaleLoader.loadFileExtensionWithPeriod(FileExtensions.RAW_FILE);
    }
}
