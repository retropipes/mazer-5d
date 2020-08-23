/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import java.awt.BorderLayout;
import java.awt.Component;
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
import com.puttysoftware.mazer5d.utilities.GUIConstants;

public class GUIManager implements QuitHandler {
    // Fields
    private MainWindow guiFrame;
    private MainWindowContent guiPane;
    private JPanel logoPane, commandPane;
    private JLabel logoLabel;
    private JButton fileNew, fileOpen, fileOpenLocked, fileClose, fileSave,
            fileSaveAs, fileSaveLocked, quit, play, edit, helpAbout,
            helpObjectHelp, showPreferences;
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
        this.checkFlags();
    }

    public void hideGUI() {
        this.setUpGUI();
        this.guiFrame.restoreSaved();
    }

    private void checkFlags() {
        final BagOStuff bag = Mazer5D.getBagOStuff();
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
        if (!bag.getMazeManager().getMaze().doesPlayerExist()) {
            this.play.setEnabled(false);
        }
        if (bag.getMazeManager().isLocked()) {
            this.edit.setEnabled(false);
        }
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
        this.checkFlags();
    }

    private void setMenusDirtyOn() {
        this.fileSave.setEnabled(true);
    }

    private void setMenusDirtyOff() {
        this.fileSave.setEnabled(false);
    }

    private void setMenusLoadedOn() {
        this.fileClose.setEnabled(true);
        this.fileSaveAs.setEnabled(true);
        this.fileSaveLocked.setEnabled(true);
        this.play.setEnabled(true);
        this.edit.setEnabled(true);
    }

    private void setMenusLoadedOff() {
        this.fileClose.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.fileSaveLocked.setEnabled(false);
        this.play.setEnabled(false);
        this.edit.setEnabled(false);
    }

    private void setUpGUI() {
        if (!this.setUp) {
            // Create content containers
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
            // Create command buttons
            this.fileNew = new JButton("New...");
            this.fileOpen = new JButton("Open...");
            this.fileOpenLocked = new JButton("Open Locked...");
            this.fileClose = new JButton("Close");
            this.fileSave = new JButton("Save");
            this.fileSaveAs = new JButton("Save As...");
            this.fileSaveLocked = new JButton("Save Locked...");
            this.showPreferences = new JButton("Preferences...");
            this.quit = new JButton("Quit");
            this.play = new JButton("Play");
            this.edit = new JButton("Edit");
            this.helpAbout = new JButton("About This Game");
            this.helpObjectHelp = new JButton("Object Help");
            // Configure command button size
            this.fileNew.setSize(GUIConstants.COMMAND_BUTTON);
            this.fileOpen.setSize(GUIConstants.COMMAND_BUTTON);
            this.fileOpenLocked.setSize(GUIConstants.COMMAND_BUTTON);
            this.fileClose.setSize(GUIConstants.COMMAND_BUTTON);
            this.fileSave.setSize(GUIConstants.COMMAND_BUTTON);
            this.fileSaveAs.setSize(GUIConstants.COMMAND_BUTTON);
            this.fileSaveLocked.setSize(GUIConstants.COMMAND_BUTTON);
            this.showPreferences.setSize(GUIConstants.COMMAND_BUTTON);
            this.quit.setSize(GUIConstants.COMMAND_BUTTON);
            this.play.setSize(GUIConstants.COMMAND_BUTTON);
            this.edit.setSize(GUIConstants.COMMAND_BUTTON);
            this.helpAbout.setSize(GUIConstants.COMMAND_BUTTON);
            this.helpObjectHelp.setSize(GUIConstants.COMMAND_BUTTON);
            // Configure command button alignment
            this.fileNew.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileOpen.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileOpenLocked.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileClose.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileSave.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileSaveAs.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileSaveLocked.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.showPreferences.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.quit.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.play.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.edit.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.helpAbout.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.helpObjectHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.fileNew.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.fileOpen.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.fileOpenLocked.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.fileClose.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.fileSave.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.fileSaveAs.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.fileSaveLocked.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.showPreferences.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.quit.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.play.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.edit.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.helpAbout.setAlignmentY(Component.CENTER_ALIGNMENT);
            this.helpObjectHelp.setAlignmentY(Component.CENTER_ALIGNMENT);
            // Attach event handlers
            this.fileNew.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getEditor().newMaze();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.fileOpen.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getMazeManager().loadMaze();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.fileOpenLocked.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getMazeManager().loadLockedMaze();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.fileClose.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        GUIManager.this.closeHandler();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.fileSave.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getMazeManager().saveMaze();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.fileSaveAs.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getMazeManager().saveMazeAs();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.fileSaveLocked.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getMazeManager().saveLockedMaze();
                        GUIManager.this.checkFlags();
                    }
                }.start();
            });
            this.showPreferences.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        Prefs.showPrefs();
                    }
                }.start();
            });
            this.quit.addActionListener(h -> System.exit(0));
            this.play.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getGameManager().playMaze();
                    }
                }.start();
            });
            this.edit.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getEditor().editMaze();
                    }
                }.start();
            });
            this.helpAbout.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getAboutThisGame().showAboutDialog();
                    }
                }.start();
            });
            this.helpObjectHelp.addActionListener(h -> {
                new Thread() {
                    @Override
                    public void run() {
                        bag.getObjectHelpViewer().showHelp();
                    }
                }.start();
            });
            // Set initial command state
            this.fileNew.setEnabled(true);
            this.fileOpen.setEnabled(true);
            this.fileOpenLocked.setEnabled(true);
            this.fileClose.setEnabled(false);
            this.fileSave.setEnabled(false);
            this.fileSaveAs.setEnabled(false);
            this.fileSaveLocked.setEnabled(false);
            this.showPreferences.setEnabled(true);
            this.play.setEnabled(false);
            this.edit.setEnabled(false);
            this.quit.setEnabled(true);
            this.helpAbout.setEnabled(true);
            this.helpObjectHelp.setEnabled(true);
            // Assemble everything together
            this.commandPane.add(this.fileNew);
            this.commandPane.add(this.fileOpen);
            this.commandPane.add(this.fileOpenLocked);
            this.commandPane.add(this.fileClose);
            this.commandPane.add(this.fileSave);
            this.commandPane.add(this.fileSaveAs);
            this.commandPane.add(this.fileSaveLocked);
            this.commandPane.add(this.showPreferences);
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
