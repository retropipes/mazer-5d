/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private MainWindow guiFrame;
    private MainWindowContent guiPane;
    private JPanel logoPane, commandPane;
    private JLabel logoLabel;
    private JButton fileNew, fileOpen, fileOpenLocked, fileClose, fileSave,
            fileSaveAs, fileSaveLocked, quit, play, edit, helpAbout,
            helpObjectHelp;
    private boolean setUp;

    // Constructors
    public GUIManager() {
        this.setUp = false;
    }

    // Methods
    public void showGUI() {
        this.setUpGUI();
        Modes.setInGUI();
        this.guiFrame.attachAndSave(this.guiPane);
        this.guiFrame.setTitle("Mazer5D");
        this.checkCommandState();
    }

    public void hideGUI() {
        this.setUpGUI();
        this.guiFrame.restoreSaved();
    }

    public void closeHandler() {
        this.setUpGUI();
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

    private void checkCommandState() {
        final BagOStuff bag = Mazer5D.getBagOStuff();
        if (bag.getMazeManager().getMaze().doesPlayerExist()) {
            this.play.setEnabled(true);
        } else {
            this.play.setEnabled(false);
        }
        if (bag.getMazeManager().isLocked()) {
            this.edit.setEnabled(false);
        } else {
            this.edit.setEnabled(true);
        }
        if (bag.getMazeManager().getDirty()) {
            this.setMenusDirtyOn();
        } else {
            this.setMenusDirtyOff();
        }
        if (bag.getMazeManager().getLoaded()) {
            this.setMenusLoadedOn();
        } else {
            this.setMenusLoadedOff();
        }
    }

    private void setMenusDirtyOn() {
        this.fileSave.setEnabled(true);
    }

    private void setMenusDirtyOff() {
        this.fileSave.setEnabled(false);
    }

    private void setMenusLoadedOn() {
        if (Modes.inGUI()) {
            this.fileClose.setEnabled(false);
            this.fileSaveAs.setEnabled(false);
            this.fileSaveLocked.setEnabled(false);
        } else {
            this.fileClose.setEnabled(true);
            this.fileSaveAs.setEnabled(true);
            this.fileSaveLocked.setEnabled(true);
        }
    }

    private void setMenusLoadedOff() {
        this.fileClose.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.fileSaveLocked.setEnabled(false);
    }

    private void setUpGUI() {
        if (!this.setUp) {
            final BagOStuff bag = Mazer5D.getBagOStuff();
            this.guiFrame = MainWindow.getMainWindow();
            this.guiPane = this.guiFrame.createContent();
            this.guiPane.setLayout(new BorderLayout());
            this.logoPane = new JPanel();
            this.logoPane.setLayout(new GridLayout(1, 1));
            final BufferedImageIcon logo = LogoImageLoader
                    .load(LogoImageIndex.LOGO);
            this.logoLabel = new JLabel("", logo, SwingConstants.CENTER);
            this.logoLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
            this.logoPane.add(this.logoLabel);
            this.commandPane = new JPanel();
            this.commandPane.setLayout(
                    new BoxLayout(this.commandPane, BoxLayout.PAGE_AXIS));
            this.fileNew = new JButton("New...");
            this.fileOpen = new JButton("Open...");
            this.fileOpenLocked = new JButton("Open Locked...");
            this.fileClose = new JButton("Close");
            this.fileSave = new JButton("Save");
            this.fileSaveAs = new JButton("Save As...");
            this.fileSaveLocked = new JButton("Save Locked...");
            this.quit = new JButton("Quit");
            this.play = new JButton("Play");
            this.edit = new JButton("Edit");
            this.helpAbout = new JButton("About This Game");
            this.helpObjectHelp = new JButton("Object Help");
            this.fileNew.addActionListener(h -> bag.getEditor().newMaze());
            this.fileOpen
                    .addActionListener(h -> bag.getMazeManager().loadMaze());
            this.fileOpenLocked.addActionListener(
                    h -> bag.getMazeManager().loadLockedMaze());
            this.fileClose
                    .addActionListener(h -> bag.getGUIManager().closeHandler());
            this.fileSave
                    .addActionListener(h -> bag.getMazeManager().saveMaze());
            this.fileSaveAs
                    .addActionListener(h -> bag.getMazeManager().saveMazeAs());
            this.fileSaveLocked.addActionListener(
                    h -> bag.getMazeManager().saveLockedMaze());
            this.quit.addActionListener(h -> System.exit(0));
            this.play.addActionListener(h -> bag.getGameManager().playMaze());
            this.edit.addActionListener(h -> bag.getEditor().editMaze());
            this.helpAbout.addActionListener(
                    h -> bag.getAboutThisGame().showAboutDialog());
            this.helpObjectHelp.addActionListener(
                    h -> bag.getObjectHelpViewer().showHelp());
            this.fileNew.setEnabled(true);
            this.fileOpen.setEnabled(true);
            this.fileOpenLocked.setEnabled(true);
            this.fileClose.setEnabled(false);
            this.fileSave.setEnabled(false);
            this.fileSaveAs.setEnabled(false);
            this.fileSaveLocked.setEnabled(false);
            this.quit.setEnabled(true);
            this.helpAbout.setEnabled(true);
            this.helpObjectHelp.setEnabled(true);
            this.commandPane.add(this.fileNew);
            this.commandPane.add(this.fileOpen);
            this.commandPane.add(this.fileOpenLocked);
            this.commandPane.add(this.fileClose);
            this.commandPane.add(this.fileSave);
            this.commandPane.add(this.fileSaveAs);
            this.commandPane.add(this.fileSaveLocked);
            this.commandPane.add(this.quit);
            this.commandPane.add(this.play);
            this.commandPane.add(this.edit);
            this.commandPane.add(this.helpAbout);
            this.commandPane.add(this.helpObjectHelp);
            this.guiPane.add(this.logoPane, BorderLayout.CENTER);
            this.guiPane.add(this.commandPane, BorderLayout.EAST);
            this.setUp = true;
        }
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
