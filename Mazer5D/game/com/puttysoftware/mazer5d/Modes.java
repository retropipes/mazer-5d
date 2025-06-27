package com.puttysoftware.mazer5d;

import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class Modes {
	private Modes() {
		super();
	}

	static {
		Modes.current = Modes.NULL;
		Modes.former = Modes.NULL;
	}
	private static final int NULL = 0;
	private static final int GAME = 1;
	private static final int EDITOR = 2;
	private static final int PREFS = 3;
	private static final int ABOUT = 4;
	private static final int GUI = 5;
	private static final int HELP = 6;
	private static final int LEVEL_PREFS = 7;
	private static final int MAZE_PREFS = 8;
	private static final int TREASURE = 9;
	private static final int RULE_PICKER = 10;
	private static final int RULE_EDITOR = 11;
	private static int current;
	private static int former;

	public static void setInGUI() {
		Modes.current = Modes.GUI;
	}

	public static void setInPrefs() {
		Modes.save();
		Modes.current = Modes.PREFS;
	}

	public static void setInGame() {
		Modes.current = Modes.GAME;
	}

	public static void setInEditor() {
		Modes.current = Modes.EDITOR;
	}

	public static void setInAbout() {
		Modes.save();
		Modes.current = Modes.ABOUT;
	}

	public static void setInHelp() {
		Modes.save();
		Modes.current = Modes.HELP;
	}

	public static void setInLevelPrefs() {
		Modes.current = Modes.LEVEL_PREFS;
	}

	public static void setInMazePrefs() {
		Modes.current = Modes.MAZE_PREFS;
	}

	public static void setInTreasure() {
		Modes.current = Modes.TREASURE;
	}

	public static void setInRulePicker() {
		Modes.current = Modes.RULE_PICKER;
	}

	public static void setInRuleEditor() {
		Mazer5D.getBagOStuff().getRuleSetPicker().hideOutput();
		Modes.current = Modes.RULE_EDITOR;
	}

	public static boolean inGUI() {
		return Modes.current == Modes.GUI;
	}

	public static boolean inGame() {
		return Modes.current == Modes.GAME;
	}

	public static boolean inEditor() {
		return Modes.current == Modes.EDITOR;
	}

	private static void save() {
		Modes.former = Modes.current;
	}

	public static void restore() {
		final BagOStuff bag = Mazer5D.getBagOStuff();
		if (Modes.current == Modes.LEVEL_PREFS || Modes.current == Modes.MAZE_PREFS || Modes.current == Modes.TREASURE
				|| Modes.current == Modes.RULE_PICKER) {
			Modes.current = Modes.EDITOR;
			bag.getEditor().showOutput();
		} else if (Modes.current == Modes.RULE_EDITOR) {
			Modes.current = Modes.RULE_PICKER;
			Mazer5D.getBagOStuff().getRuleSetPicker().showOutput();
		} else {
			Modes.current = Modes.former;
			switch (Modes.current) {
				case GUI:
					bag.getGUIManager().showGUI();
					break;
				case GAME:
					bag.getGameManager().showOutput();
					break;
				case EDITOR:
					bag.getEditor().showOutput();
					break;
				case PREFS:
					Prefs.showPrefs();
					break;
				case ABOUT:
					bag.getAboutThisGame().showAboutDialog();
					break;
				case HELP:
					bag.getObjectHelpViewer().showHelp();
					break;
				default:
					break;
			}
		}
	}
}
