/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.editor.MazeEditor;

public class MenuManager {
    // Fields
    private final JMenuBar mainMenuBar;
    private JMenu editorMenu;
    private JMenu editorFillSubMenu;
    private JMenuItem editorUndo, editorRedo, editorCutLevel, editorCopyLevel,
            editorPasteLevel, editorInsertLevelFromClipboard,
            editorClearHistory, editorGoToLocation, editorGoToDestination,
            editorUpOneFloor, editorDownOneFloor, editorUpOneLevel,
            editorDownOneLevel, editorAddLevel, editorRemoveLevel,
            editorResizeLevel, editorToggleLayer, editorLevelPreferences,
            editorMazePreferences, editorSetStartPoint,
            editorSetFirstMovingFinish;
    private JMenuItem editorFillFloor, editorFillLevel, editorFillFloorRandomly,
            editorFillLevelRandomly, editorFillRuleSets;
    private JCheckBoxMenuItem editorFillUseRuleSets;

    // Constructors
    public MenuManager() {
        this.mainMenuBar = new JMenuBar();
        this.createMenus();
        this.createMenuItems();
        this.attachEventHandlers();
        this.assembleMenuItems();
        this.assembleMenus();
        this.setInitialMenuState();
    }

    // Methods
    public JMenuBar getMainMenuBar() {
        return this.mainMenuBar;
    }

    public void setGameMenus() {
        this.editorUndo.setEnabled(false);
        this.editorRedo.setEnabled(false);
        this.editorCutLevel.setEnabled(false);
        this.editorCopyLevel.setEnabled(false);
        this.editorPasteLevel.setEnabled(false);
        this.editorInsertLevelFromClipboard.setEnabled(false);
        this.editorClearHistory.setEnabled(false);
        this.editorGoToLocation.setEnabled(false);
        this.editorGoToDestination.setEnabled(false);
        this.editorUpOneFloor.setEnabled(false);
        this.editorDownOneFloor.setEnabled(false);
        this.editorUpOneLevel.setEnabled(false);
        this.editorDownOneLevel.setEnabled(false);
        this.editorAddLevel.setEnabled(false);
        this.editorRemoveLevel.setEnabled(false);
        this.editorResizeLevel.setEnabled(false);
        this.editorFillFloor.setEnabled(false);
        this.editorFillLevel.setEnabled(false);
        this.editorFillFloorRandomly.setEnabled(false);
        this.editorFillLevelRandomly.setEnabled(false);
        this.editorFillRuleSets.setEnabled(false);
        this.editorFillUseRuleSets.setEnabled(false);
        this.editorToggleLayer.setEnabled(false);
        this.editorLevelPreferences.setEnabled(false);
        this.editorMazePreferences.setEnabled(false);
        this.editorSetStartPoint.setEnabled(false);
        this.editorSetFirstMovingFinish.setEnabled(false);
        this.checkFlags();
        this.checkFlags();
    }

    public void setEditorMenus() {
        this.editorCutLevel.setEnabled(true);
        this.editorCopyLevel.setEnabled(true);
        this.editorPasteLevel.setEnabled(true);
        this.editorInsertLevelFromClipboard.setEnabled(true);
        this.editorGoToLocation.setEnabled(true);
        this.editorGoToDestination.setEnabled(true);
        this.editorResizeLevel.setEnabled(true);
        this.editorFillFloor.setEnabled(true);
        this.editorFillLevel.setEnabled(true);
        this.editorFillFloorRandomly.setEnabled(true);
        this.editorFillLevelRandomly.setEnabled(true);
        this.editorFillRuleSets.setEnabled(true);
        this.editorFillUseRuleSets.setEnabled(true);
        this.editorToggleLayer.setEnabled(true);
        this.editorLevelPreferences.setEnabled(true);
        this.editorMazePreferences.setEnabled(true);
        this.editorSetStartPoint.setEnabled(true);
        this.editorSetFirstMovingFinish.setEnabled(true);
        this.checkFlags();
    }

    public void setPrefMenus() {
        this.editorUndo.setEnabled(false);
        this.editorRedo.setEnabled(false);
        this.editorCutLevel.setEnabled(false);
        this.editorCopyLevel.setEnabled(false);
        this.editorPasteLevel.setEnabled(false);
        this.editorInsertLevelFromClipboard.setEnabled(false);
        this.editorClearHistory.setEnabled(false);
        this.editorGoToLocation.setEnabled(false);
        this.editorGoToDestination.setEnabled(false);
        this.editorUpOneFloor.setEnabled(false);
        this.editorDownOneFloor.setEnabled(false);
        this.editorUpOneLevel.setEnabled(false);
        this.editorDownOneLevel.setEnabled(false);
        this.editorAddLevel.setEnabled(false);
        this.editorRemoveLevel.setEnabled(false);
        this.editorResizeLevel.setEnabled(false);
        this.editorFillFloor.setEnabled(false);
        this.editorFillLevel.setEnabled(false);
        this.editorFillFloorRandomly.setEnabled(false);
        this.editorFillLevelRandomly.setEnabled(false);
        this.editorFillRuleSets.setEnabled(false);
        this.editorFillUseRuleSets.setEnabled(false);
        this.editorToggleLayer.setEnabled(false);
        this.editorLevelPreferences.setEnabled(false);
        this.editorMazePreferences.setEnabled(false);
        this.editorSetStartPoint.setEnabled(false);
        this.editorSetFirstMovingFinish.setEnabled(false);
    }

    public void setHelpMenus() {
        this.editorUndo.setEnabled(false);
        this.editorRedo.setEnabled(false);
        this.editorCutLevel.setEnabled(false);
        this.editorCopyLevel.setEnabled(false);
        this.editorPasteLevel.setEnabled(false);
        this.editorInsertLevelFromClipboard.setEnabled(false);
        this.editorClearHistory.setEnabled(false);
        this.editorGoToLocation.setEnabled(false);
        this.editorGoToDestination.setEnabled(false);
        this.editorUpOneFloor.setEnabled(false);
        this.editorDownOneFloor.setEnabled(false);
        this.editorUpOneLevel.setEnabled(false);
        this.editorDownOneLevel.setEnabled(false);
        this.editorAddLevel.setEnabled(false);
        this.editorRemoveLevel.setEnabled(false);
        this.editorResizeLevel.setEnabled(false);
        this.editorFillFloor.setEnabled(false);
        this.editorFillLevel.setEnabled(false);
        this.editorFillFloorRandomly.setEnabled(false);
        this.editorFillLevelRandomly.setEnabled(false);
        this.editorFillRuleSets.setEnabled(false);
        this.editorFillUseRuleSets.setEnabled(false);
        this.editorToggleLayer.setEnabled(false);
        this.editorLevelPreferences.setEnabled(false);
        this.editorMazePreferences.setEnabled(false);
        this.editorSetStartPoint.setEnabled(false);
    }

    public void setMainMenus() {
        this.editorUndo.setEnabled(false);
        this.editorRedo.setEnabled(false);
        this.editorCutLevel.setEnabled(false);
        this.editorCopyLevel.setEnabled(false);
        this.editorPasteLevel.setEnabled(false);
        this.editorInsertLevelFromClipboard.setEnabled(false);
        this.editorClearHistory.setEnabled(false);
        this.editorGoToLocation.setEnabled(false);
        this.editorGoToDestination.setEnabled(false);
        this.editorUpOneFloor.setEnabled(false);
        this.editorDownOneFloor.setEnabled(false);
        this.editorUpOneLevel.setEnabled(false);
        this.editorDownOneLevel.setEnabled(false);
        this.editorAddLevel.setEnabled(false);
        this.editorRemoveLevel.setEnabled(false);
        this.editorFillFloor.setEnabled(false);
        this.editorFillLevel.setEnabled(false);
        this.editorFillFloorRandomly.setEnabled(false);
        this.editorFillLevelRandomly.setEnabled(false);
        this.editorFillRuleSets.setEnabled(false);
        this.editorFillUseRuleSets.setEnabled(false);
        this.editorResizeLevel.setEnabled(false);
        this.editorToggleLayer.setEnabled(false);
        this.editorLevelPreferences.setEnabled(false);
        this.editorMazePreferences.setEnabled(false);
        this.editorSetStartPoint.setEnabled(false);
        this.editorSetFirstMovingFinish.setEnabled(false);
        this.checkFlags();
    }

    public void enableUpOneFloor() {
        this.editorUpOneFloor.setEnabled(true);
    }

    public void disableUpOneFloor() {
        this.editorUpOneFloor.setEnabled(false);
    }

    public void enableDownOneFloor() {
        this.editorDownOneFloor.setEnabled(true);
    }

    public void disableDownOneFloor() {
        this.editorDownOneFloor.setEnabled(false);
    }

    public void enableUpOneLevel() {
        this.editorUpOneLevel.setEnabled(true);
    }

    public void disableUpOneLevel() {
        this.editorUpOneLevel.setEnabled(false);
    }

    public void enableDownOneLevel() {
        this.editorDownOneLevel.setEnabled(true);
    }

    public void disableDownOneLevel() {
        this.editorDownOneLevel.setEnabled(false);
    }

    public void enableAddLevel() {
        this.editorAddLevel.setEnabled(true);
    }

    public void disableAddLevel() {
        this.editorAddLevel.setEnabled(false);
    }

    public void enableRemoveLevel() {
        this.editorRemoveLevel.setEnabled(true);
    }

    public void disableRemoveLevel() {
        this.editorRemoveLevel.setEnabled(false);
    }

    public void enableUndo() {
        this.editorUndo.setEnabled(true);
    }

    public void disableUndo() {
        this.editorUndo.setEnabled(false);
    }

    public void enableRedo() {
        this.editorRedo.setEnabled(true);
    }

    public void disableRedo() {
        this.editorRedo.setEnabled(false);
    }

    public void enableClearHistory() {
        this.editorClearHistory.setEnabled(true);
    }

    public void disableClearHistory() {
        this.editorClearHistory.setEnabled(false);
    }

    public void enableCutLevel() {
        this.editorCutLevel.setEnabled(true);
    }

    public void disableCutLevel() {
        this.editorCutLevel.setEnabled(false);
    }

    public void enablePasteLevel() {
        this.editorPasteLevel.setEnabled(true);
    }

    public void disablePasteLevel() {
        this.editorPasteLevel.setEnabled(false);
    }

    public void enableInsertLevelFromClipboard() {
        this.editorInsertLevelFromClipboard.setEnabled(true);
    }

    public void disableInsertLevelFromClipboard() {
        this.editorInsertLevelFromClipboard.setEnabled(false);
    }

    public void enableSetStartPoint() {
        this.editorSetStartPoint.setEnabled(true);
    }

    public void disableSetStartPoint() {
        this.editorSetStartPoint.setEnabled(false);
    }

    public void checkFlags() {
        final BagOStuff bag = Mazer5D.getBagOStuff();
        if (Modes.inEditor()) {
            if (bag.getMazeManager().getMaze().isPasteBlocked()) {
                this.disablePasteLevel();
                this.disableInsertLevelFromClipboard();
            } else {
                this.enablePasteLevel();
                this.enableInsertLevelFromClipboard();
            }
            if (bag.getMazeManager().getMaze().isCutBlocked()) {
                this.disableCutLevel();
            } else {
                this.enableCutLevel();
            }
        }
    }

    public boolean useFillRuleSets() {
        return this.editorFillUseRuleSets.getState();
    }

    private final void createMenus() {
        this.editorMenu = new JMenu("Editor");
        this.editorFillSubMenu = new JMenu("Fill");
    }

    private final void createMenuItems() {
        this.editorUndo = new JMenuItem("Undo");
        this.editorRedo = new JMenuItem("Redo");
        this.editorCutLevel = new JMenuItem("Cut Level");
        this.editorCopyLevel = new JMenuItem("Copy Level");
        this.editorPasteLevel = new JMenuItem("Paste Level");
        this.editorInsertLevelFromClipboard = new JMenuItem(
                "Insert Level From Clipboard");
        this.editorClearHistory = new JMenuItem("Clear History");
        this.editorGoToLocation = new JMenuItem("Go To Location...");
        this.editorGoToDestination = new JMenuItem("Go To Destination...");
        this.editorUpOneFloor = new JMenuItem("Up One Floor");
        this.editorDownOneFloor = new JMenuItem("Down One Floor");
        this.editorUpOneLevel = new JMenuItem("Up One Level");
        this.editorDownOneLevel = new JMenuItem("Down One Level");
        this.editorAddLevel = new JMenuItem("Add a Level...");
        this.editorRemoveLevel = new JMenuItem("Remove a Level...");
        this.editorResizeLevel = new JMenuItem("Resize Current Level...");
        this.editorFillFloor = new JMenuItem("Fill Current Floor");
        this.editorFillLevel = new JMenuItem("Fill Current Level");
        this.editorFillFloorRandomly = new JMenuItem(
                "Fill Current Floor Randomly");
        this.editorFillLevelRandomly = new JMenuItem(
                "Fill Current Level Randomly");
        this.editorFillRuleSets = new JMenuItem("Fill Rule Sets...");
        this.editorFillUseRuleSets = new JCheckBoxMenuItem(
                "Use Fill Rule Sets");
        this.editorToggleLayer = new JMenuItem("Toggle Layer");
        this.editorLevelPreferences = new JMenuItem("Level Preferences...");
        this.editorMazePreferences = new JMenuItem("Maze Preferences...");
        this.editorSetStartPoint = new JMenuItem("Set Start Point...");
        this.editorSetFirstMovingFinish = new JMenuItem(
                "Set First Moving Finish...");
    }

    private final void attachEventHandlers() {
        final BagOStuff bag = Mazer5D.getBagOStuff();
        this.editorUndo.addActionListener(h -> bag.getEditor().undo());
        this.editorRedo.addActionListener(h -> bag.getEditor().redo());
        this.editorCutLevel.addActionListener(h -> bag.getEditor().cutLevel());
        this.editorCopyLevel
                .addActionListener(h -> bag.getEditor().copyLevel());
        this.editorPasteLevel
                .addActionListener(h -> bag.getEditor().pasteLevel());
        this.editorInsertLevelFromClipboard.addActionListener(
                h -> bag.getEditor().insertLevelFromClipboard());
        this.editorClearHistory
                .addActionListener(h -> bag.getEditor().clearHistory());
        this.editorGoToLocation
                .addActionListener(h -> bag.getEditor().goToLocationHandler());
        this.editorGoToDestination.addActionListener(
                h -> bag.getEditor().goToDestinationHandler());
        this.editorUpOneFloor.addActionListener(
                h -> bag.getEditor().updateEditorPosition(0, 0, 1, 0));
        this.editorDownOneFloor.addActionListener(
                h -> bag.getEditor().updateEditorPosition(0, 0, -1, 0));
        this.editorUpOneLevel.addActionListener(
                h -> bag.getEditor().updateEditorPosition(0, 0, 0, 1));
        this.editorDownOneLevel.addActionListener(
                h -> bag.getEditor().updateEditorPosition(0, 0, 0, -1));
        this.editorAddLevel.addActionListener(h -> bag.getEditor().addLevel());
        this.editorRemoveLevel
                .addActionListener(h -> bag.getEditor().removeLevel());
        this.editorResizeLevel
                .addActionListener(h -> bag.getEditor().resizeLevel());
        this.editorFillFloor
                .addActionListener(h -> bag.getEditor().fillFloor());
        this.editorFillLevel
                .addActionListener(h -> bag.getEditor().fillLevel());
        this.editorFillFloorRandomly
                .addActionListener(h -> bag.getEditor().fillFloorRandomly());
        this.editorFillLevelRandomly
                .addActionListener(h -> bag.getEditor().fillLevelRandomly());
        this.editorFillRuleSets
                .addActionListener(h -> bag.getRuleSetPicker().editRuleSets());
        this.editorToggleLayer
                .addActionListener(h -> bag.getEditor().toggleLayer());
        this.editorLevelPreferences
                .addActionListener(h -> bag.getEditor().setLevelPrefs());
        this.editorMazePreferences
                .addActionListener(h -> bag.getEditor().setMazePrefs());
        this.editorSetStartPoint
                .addActionListener(h -> bag.getEditor().editPlayerLocation());
        this.editorSetFirstMovingFinish
                .addActionListener(h -> bag.getEditor().editTeleportDestination(
                        MazeEditor.TELEPORT_TYPE_FIRST_MOVING_FINISH));
    }

    private final void assembleMenuItems() {
        this.editorFillSubMenu.add(this.editorFillFloor);
        this.editorFillSubMenu.add(this.editorFillLevel);
        this.editorFillSubMenu.add(this.editorFillFloorRandomly);
        this.editorFillSubMenu.add(this.editorFillLevelRandomly);
        this.editorFillSubMenu.add(this.editorFillRuleSets);
        this.editorFillSubMenu.add(this.editorFillUseRuleSets);
        this.editorMenu.add(this.editorUndo);
        this.editorMenu.add(this.editorRedo);
        this.editorMenu.add(this.editorCutLevel);
        this.editorMenu.add(this.editorCopyLevel);
        this.editorMenu.add(this.editorPasteLevel);
        this.editorMenu.add(this.editorInsertLevelFromClipboard);
        this.editorMenu.add(this.editorClearHistory);
        this.editorMenu.add(this.editorGoToLocation);
        this.editorMenu.add(this.editorGoToDestination);
        this.editorMenu.add(this.editorUpOneFloor);
        this.editorMenu.add(this.editorDownOneFloor);
        this.editorMenu.add(this.editorUpOneLevel);
        this.editorMenu.add(this.editorDownOneLevel);
        this.editorMenu.add(this.editorAddLevel);
        this.editorMenu.add(this.editorRemoveLevel);
        this.editorMenu.add(this.editorResizeLevel);
        this.editorMenu.add(this.editorFillSubMenu);
        this.editorMenu.add(this.editorToggleLayer);
        this.editorMenu.add(this.editorLevelPreferences);
        this.editorMenu.add(this.editorMazePreferences);
        this.editorMenu.add(this.editorSetStartPoint);
        this.editorMenu.add(this.editorSetFirstMovingFinish);
    }

    private final void assembleMenus() {
        this.mainMenuBar.add(this.editorMenu);
    }

    private final void setInitialMenuState() {
        this.editorUndo.setEnabled(false);
        this.editorRedo.setEnabled(false);
        this.editorCutLevel.setEnabled(false);
        this.editorCopyLevel.setEnabled(false);
        this.editorPasteLevel.setEnabled(false);
        this.editorInsertLevelFromClipboard.setEnabled(false);
        this.editorClearHistory.setEnabled(false);
        this.editorGoToLocation.setEnabled(false);
        this.editorGoToDestination.setEnabled(false);
        this.editorUpOneFloor.setEnabled(false);
        this.editorDownOneFloor.setEnabled(false);
        this.editorUpOneLevel.setEnabled(false);
        this.editorDownOneLevel.setEnabled(false);
        this.editorAddLevel.setEnabled(false);
        this.editorRemoveLevel.setEnabled(false);
        this.editorResizeLevel.setEnabled(false);
        this.editorFillFloor.setEnabled(false);
        this.editorFillLevel.setEnabled(false);
        this.editorFillFloorRandomly.setEnabled(false);
        this.editorFillLevelRandomly.setEnabled(false);
        this.editorFillRuleSets.setEnabled(false);
        this.editorFillUseRuleSets.setEnabled(false);
        this.editorToggleLayer.setEnabled(false);
        this.editorLevelPreferences.setEnabled(false);
        this.editorMazePreferences.setEnabled(false);
        this.editorSetStartPoint.setEnabled(false);
        this.editorSetFirstMovingFinish.setEnabled(false);
    }
}
