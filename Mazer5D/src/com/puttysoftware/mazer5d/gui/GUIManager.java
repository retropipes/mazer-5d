/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import java.awt.GridLayout;
import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.commondialogs.MainWindow;
import com.puttysoftware.commondialogs.MainWindowContent;
import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.assets.LogoImageIndex;
import com.puttysoftware.mazer5d.files.MazeManager;
import com.puttysoftware.mazer5d.files.TempDirCleanup;
import com.puttysoftware.mazer5d.loaders.LogoImageLoader;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class GUIManager implements QuitHandler {
    // Fields
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private final MainWindow guiFrame;
    private final MainWindowContent guiPane;
    private final JLabel logoLabel;

    // Constructors
    public GUIManager() {
        MainWindow.createMainWindow(GUIManager.WIDTH, GUIManager.HEIGHT);
        this.guiFrame = MainWindow.getMainWindow();
        this.guiPane = this.guiFrame.createContent();
        this.guiPane.setLayout(new GridLayout(1, 1));
        final BufferedImageIcon logo = LogoImageLoader
                .load(LogoImageIndex.LOGO);
        this.logoLabel = new JLabel("", logo, SwingConstants.CENTER);
        this.logoLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.guiPane.add(this.logoLabel);
    }

    // Methods
    public void showGUI() {
        final BagOStuff app = Mazer5D.getBagOStuff();
        Modes.setInGUI();
        app.getMenuManager().setMainMenus();
        app.getMenuManager().checkFlags();
        this.guiFrame.attachAndSave(this.guiPane);
        this.guiFrame.setTitle("Mazer5D");
    }

    public void hideGUI() {
        this.guiFrame.restoreSaved();
    }

    public void closeHandler() {
        // Close the window
        final BagOStuff app = Mazer5D.getBagOStuff();
        if (Modes.inEditor()) {
            app.getEditor().doneEditing();
        } else if (Modes.inGame()) {
            boolean saved = true;
            int status = 0;
            if (app.getMazeManager().getDirty()) {
                status = app.getMazeManager().showSaveDialog();
                if (status == CommonDialogs.YES_OPTION) {
                    app.getMazeManager().saveMaze();
                    saved = app.getMazeManager().getDirty();
                } else if (status == CommonDialogs.CANCEL_OPTION) {
                    saved = false;
                } else {
                    app.getMazeManager().setDirty(false);
                }
            }
            if (saved) {
                app.getGameManager().endGame();
            }
        }
        app.getMenuManager().checkFlags();
    }

    @Override
    public void handleQuitRequestWith(final QuitEvent inE,
            final QuitResponse inResponse) {
        final MazeManager mm = Mazer5D.getBagOStuff().getMazeManager();
        boolean saved = true;
        int status = CommonDialogs.DEFAULT_OPTION;
        if (mm.getDirty()) {
            status = mm.showSaveDialog();
            if (status == CommonDialogs.YES_OPTION) {
                mm.saveMaze();
                saved = !mm.getDirty();
            } else if (status == CommonDialogs.CANCEL_OPTION) {
                saved = false;
            } else {
                mm.setDirty(false);
            }
        }
        if (saved) {
            Prefs.writePrefs();
            // Run cleanup task
            new TempDirCleanup().start();
            inResponse.performQuit();
        } else {
            inResponse.cancelQuit();
        }
    }
}
