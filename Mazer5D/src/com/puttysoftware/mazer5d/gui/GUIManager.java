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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.assets.LogoImageIndex;
import com.puttysoftware.mazer5d.dialog.MainWindow;
import com.puttysoftware.mazer5d.files.MazeManager;
import com.puttysoftware.mazer5d.files.TempDirCleanup;
import com.puttysoftware.mazer5d.loaders.LogoImageLoader;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class GUIManager implements QuitHandler {
    // Fields
    private final MainWindow guiFrame;
    private final JPanel guiPane;
    private final JLabel logoLabel;

    // Constructors
    public GUIManager() {
        this.guiFrame = MainWindow.getMainWindow();
        this.guiPane = new JPanel();
        this.guiPane.setLayout(new GridLayout(1, 1));
        final BufferedImageIcon logo = LogoImageLoader.load(
                LogoImageIndex.LOGO);
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
        this.guiFrame.pack();
    }

    public void hideGUI() {
        this.guiFrame.restoreSaved();
    }

    public boolean quitHandler() {
        final MazeManager mm = Mazer5D.getBagOStuff().getMazeManager();
        boolean saved = true;
        int status = JOptionPane.DEFAULT_OPTION;
        if (mm.getDirty()) {
            status = mm.showSaveDialog();
            if (status == JOptionPane.YES_OPTION) {
                saved = mm.saveMaze();
            } else if (status == JOptionPane.CANCEL_OPTION) {
                saved = false;
            } else {
                mm.setDirty(false);
            }
        }
        if (saved) {
            Prefs.writePrefs();
            // Run cleanup task
            new TempDirCleanup().start();
        }
        return saved;
    }

    @Override
    public void handleQuitRequestWith(final QuitEvent inE,
            final QuitResponse inResponse) {
        final boolean ok = this.quitHandler();
        if (ok) {
            inResponse.performQuit();
        } else {
            inResponse.cancelQuit();
        }
    }
}
