/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d;

import java.awt.desktop.PreferencesEvent;
import java.awt.desktop.PreferencesHandler;

import com.puttysoftware.integration.Integration;
import com.puttysoftware.mazer5d.assets.LogoImageIndex;
import com.puttysoftware.mazer5d.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.commondialogs.MainWindow;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.LogoImageLoader;
import com.puttysoftware.mazer5d.locale.LocaleLoader;
import com.puttysoftware.mazer5d.prefs.Prefs;
import com.puttysoftware.mazer5d.utilities.GUIConstants;

public class Mazer5D {
    // Constants
    private static BagOStuff bagOStuff;
    private static GameErrorHandler errhand;
    private static final String PROGRAM_NAME = "Mazer5D";

    // Methods
    public static BagOStuff getBagOStuff() {
	return Mazer5D.bagOStuff;
    }

    public static void logError(final Throwable t) {
	Mazer5D.errhand.uncaughtException(Thread.currentThread(), t);
    }

    public static void main(final String[] args) {
	// Install error handler
	Mazer5D.errhand = new GameErrorHandler();
	Thread.setDefaultUncaughtExceptionHandler(Mazer5D.errhand);
	// Integrate with host platform
	final Integration ni = new Integration();
	ni.configureLookAndFeel();
	LocaleLoader.init();
	MainWindow.createMainWindow(GUIConstants.GUI_WIDTH, GUIConstants.GUI_HEIGHT);
	Mazer5D.bagOStuff = new BagOStuff();
	ni.setAboutHandler(Mazer5D.bagOStuff.getAboutThisGame());
	ni.setOpenFileHandler(Mazer5D.bagOStuff.getMazeManager());
	ni.setPreferencesHandler(new PreferencesLauncher());
	ni.setQuitHandler(Mazer5D.bagOStuff.getGUIManager());
	// Set up Common Dialogs
	CommonDialogs.setDefaultTitle(Mazer5D.PROGRAM_NAME);
	CommonDialogs.setIcon(LogoImageLoader.load(LogoImageIndex.MICRO_LOGO));
	// Set default settings
	Prefs.setDefaultPrefs();
	// Launch GUI
	Mazer5D.bagOStuff.playLogoSound();
	Mazer5D.bagOStuff.getGUIManager().showGUI();
    }

    private static class PreferencesLauncher implements PreferencesHandler {
	@Override
	public void handlePreferences(final PreferencesEvent inE) {
	    Prefs.showPrefs();
	}
    }
}
