/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.files.format;

public class Extension {
    // Constants
    private static final String _MAZE_EXTENSION = "5dxm";
    private static final String _SAVED_GAME_EXTENSION = "5dxg";
    private static final String _SCORES_EXTENSION = "5dxs";
    private static final String _RULE_SET_EXTENSION = "5dxt";

    // Methods
    public static String getMazeExtension() {
	return Extension._MAZE_EXTENSION;
    }

    public static String getMazeExtensionWithPeriod() {
	return "." + Extension._MAZE_EXTENSION;
    }

    public static String getGameExtension() {
	return Extension._SAVED_GAME_EXTENSION;
    }

    public static String getGameExtensionWithPeriod() {
	return "." + Extension._SAVED_GAME_EXTENSION;
    }

    public static String getScoresExtension() {
	return Extension._SCORES_EXTENSION;
    }

    public static String getScoresExtensionWithPeriod() {
	return "." + Extension._SCORES_EXTENSION;
    }

    public static String getRuleSetExtension() {
	return Extension._RULE_SET_EXTENSION;
    }

    public static String getRuleSetExtensionWithPeriod() {
	return "." + Extension._RULE_SET_EXTENSION;
    }
}
