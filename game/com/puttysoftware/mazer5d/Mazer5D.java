/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d;

import java.awt.desktop.PreferencesEvent;
import java.awt.desktop.PreferencesHandler;

import org.retropipes.diane.Diane;
import org.retropipes.diane.gui.MainWindow;
import org.retropipes.diane.gui.dialog.CommonDialogs;
import org.retropipes.diane.integration.Integration;

import com.puttysoftware.mazer5d.asset.LogoImageIndex;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.gui.GUIConstants;
import com.puttysoftware.mazer5d.loader.LogoImageLoader;
import com.puttysoftware.mazer5d.locale.LocalDataLoader;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class Mazer5D {
    // Constants
    private static BagOStuff bagOStuff;
    private static final String PROGRAM_NAME = Translations.load(Strings.PROGRAM_NAME);

    // Methods
    public static BagOStuff getBagOStuff() {
	return Mazer5D.bagOStuff;
    }

    public static void logError(final Throwable t) {
	Diane.handleError(t);
    }

    public static void main(final String[] args) {
	// Install error handler
	Diane.installDefaultErrorHandler(PROGRAM_NAME);
	// Integrate with host platform
	final Integration ni = new Integration();
	ni.configureLookAndFeel();
	LocalDataLoader.init();
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
