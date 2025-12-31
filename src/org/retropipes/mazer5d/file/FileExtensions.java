/*  FantastleReboot: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.file;

import org.retropipes.mazer5d.loader.DataLoader;

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
	return DataLoader.loadFileExtension(FileExtensions.SOUND);
    }

    public static String getImageExtension() {
	return DataLoader.loadFileExtension(FileExtensions.IMAGE);
    }

    public static String getMusicExtension() {
	return DataLoader.loadFileExtension(FileExtensions.MUSIC);
    }

    public static String getMazeExtension() {
	return DataLoader.loadFileExtension(FileExtensions.MAZE);
    }

    public static String getGameExtension() {
	return DataLoader.loadFileExtension(FileExtensions.GAME);
    }

    public static String getLockedMazeExtension() {
	return DataLoader.loadFileExtension(FileExtensions.LOCKED_MAZE);
    }

    public static String getMazeLevelExtension() {
	return DataLoader.loadFileExtension(FileExtensions.MAZE_LEVEL);
    }

    public static String getMazeTempExtension() {
	return DataLoader.loadFileExtension(FileExtensions.MAZE_TEMP);
    }

    public static String getScoresExtension() {
	return DataLoader.loadFileExtension(FileExtensions.SCORES);
    }

    public static String getRuleSetExtension() {
	return DataLoader.loadFileExtension(FileExtensions.RULE_SET);
    }

    public static String getCharacterExtension() {
	return DataLoader.loadFileExtension(FileExtensions.CHARACTER);
    }

    public static String getRegistryExtension() {
	return DataLoader.loadFileExtension(FileExtensions.REGISTRY);
    }

    public static String getPrefsExtension() {
	return DataLoader.loadFileExtension(FileExtensions.PREFS);
    }

    public static String getDataFileExtension() {
	return DataLoader.loadFileExtension(FileExtensions.DATA_FILE);
    }

    public static String getRawFileExtension() {
	return DataLoader.loadFileExtension(FileExtensions.RAW_FILE);
    }

    // Methods (with period)
    public static String getSoundExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.SOUND);
    }

    public static String getImageExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.IMAGE);
    }

    public static String getMusicExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.MUSIC);
    }

    public static String getMazeExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.MAZE);
    }

    public static String getGameExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.GAME);
    }

    public static String getLockedMazeExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.LOCKED_MAZE);
    }

    public static String getMazeLevelExtensionWithPeriod() {
	return DataLoader.loadFileExtension(FileExtensions.MAZE_LEVEL);
    }

    public static String getMazeTempExtensionWithPeriod() {
	return DataLoader.loadFileExtension(FileExtensions.MAZE_TEMP);
    }

    public static String getScoresExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.SCORES);
    }

    public static String getRuleSetExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.RULE_SET);
    }

    public static String getCharacterExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.CHARACTER);
    }

    public static String getRegistryExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.REGISTRY);
    }

    public static String getPrefsExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.PREFS);
    }

    public static String getDataFileExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.DATA_FILE);
    }

    public static String getRawFileExtensionWithPeriod() {
	return DataLoader.loadFileExtensionWithPeriod(FileExtensions.RAW_FILE);
    }
}
