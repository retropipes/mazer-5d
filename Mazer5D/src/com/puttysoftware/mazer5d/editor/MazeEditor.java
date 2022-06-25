/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.editor;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.commondialogs.MainWindow;
import com.puttysoftware.mazer5d.commondialogs.MainWindowContent;
import com.puttysoftware.mazer5d.files.MazeManager;
import com.puttysoftware.mazer5d.game.GameManager;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.ImageConstants;
import com.puttysoftware.mazer5d.loaders.ObjectImageLoader;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.maze.Maze;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericConditionalTeleport;
import com.puttysoftware.mazer5d.objects.abc.GenericContainer;
import com.puttysoftware.mazer5d.objects.abc.GenericTeleport;
import com.puttysoftware.mazer5d.prefs.Prefs;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.picturepicker.PicturePicker;

public class MazeEditor {
    // Declarations
    private MainWindow outputFrame;
    private JPanel outputPane, secondaryPane, commandPane;
    private MainWindowContent borderPane, treasurePane;
    private JLabel messageLabel;
    private MazeObject savedMazeObject;
    private GridBagLayout gridbag;
    private GridBagConstraints c;
    private JScrollBar vertScroll, horzScroll;
    private final EventHandler mhandler;
    private final StartEventHandler shandler;
    private final TeleportEventHandler thandler;
    private final TreasureEventHandler rhandler;
    private final MetalButtonEventHandler mbhandler;
    private final ConditionalTeleportEventHandler cthandler;
    private final LevelPrefs lPrefs;
    private final MazePrefs mPrefs;
    private PicturePicker picker;
    private final PicturePicker treasurePicker;
    private final JButton pick;
    private final String[] groundNames;
    private final String[] objectNames;
    private final MazeObject[] groundObjects;
    private final MazeObject[] objectObjects;
    private final BufferedImageIcon[] groundEditorAppearances;
    private final BufferedImageIcon[] objectEditorAppearances;
    private final String[] containableNames;
    private final MazeObject[] containableObjects;
    private final BufferedImageIcon[] containableEditorAppearances;
    private int TELEPORT_TYPE;
    private GenericConditionalTeleport instanceBeingEdited;
    private int conditionalEditFlag;
    private int currentObjectIndex;
    private UndoRedoEngine engine;
    private EditorLocationManager elMgr;
    private EditorViewingWindowManager evMgr;
    private JLabel[][] drawGrid;
    private boolean mazeChanged;
    private boolean goToDestMode;
    private static final MazeObject VOID = GameObjects.createObject(MazeObjects.BOUNDS);
    private static final MazeObject DEST = GameObjects.createObject(MazeObjects.DESTINATION);
    private static final MazeObject EMPTY = GameObjects.getEmptySpace();
    private JButton editorUndo, editorRedo, editorCutLevel, editorCopyLevel, editorPasteLevel,
	    editorInsertLevelFromClipboard, editorClearHistory, editorGoToLocation, editorGoToDestination,
	    editorUpOneFloor, editorDownOneFloor, editorUpOneLevel, editorDownOneLevel, editorAddLevel,
	    editorRemoveLevel, editorResizeLevel, editorToggleLayer, editorLevelPreferences, editorMazePreferences,
	    editorSetStartPoint, editorSetFirstMovingFinish, editorFillFloor, editorFillLevel, editorFillFloorRandomly,
	    editorFillLevelRandomly, editorFillRuleSets, editorExit;
    private JToggleButton editorFillUseRuleSets;
    private static final int CEF_DEST1 = 1;
    private static final int CEF_DEST2 = 2;
    private static final int CEF_CONDITION = 3;
    private static final int CEF_TRIGGER_TYPE = 4;
    public static final int TELEPORT_TYPE_GENERIC = 0;
    public static final int TELEPORT_TYPE_INVISIBLE_GENERIC = 1;
    public static final int TELEPORT_TYPE_RANDOM = 2;
    public static final int TELEPORT_TYPE_RANDOM_INVISIBLE = 3;
    public static final int TELEPORT_TYPE_ONESHOT = 4;
    public static final int TELEPORT_TYPE_INVISIBLE_ONESHOT = 5;
    public static final int TELEPORT_TYPE_RANDOM_ONESHOT = 6;
    public static final int TELEPORT_TYPE_RANDOM_INVISIBLE_ONESHOT = 7;
    public static final int TELEPORT_TYPE_TWOWAY = 8;
    public static final int TELEPORT_TYPE_MOVING_FINISH = 9;
    public static final int TELEPORT_TYPE_FIRST_MOVING_FINISH = 10;
    public static final int TELEPORT_TYPE_CHAIN = 11;
    public static final int TELEPORT_TYPE_INVISIBLE_CHAIN = 12;
    public static final int TELEPORT_TYPE_ONESHOT_CHAIN = 15;
    public static final int TELEPORT_TYPE_INVISIBLE_ONESHOT_CHAIN = 16;
    public static final int STAIRS_UP = 0;
    public static final int STAIRS_DOWN = 1;

    public MazeEditor() {
	this.savedMazeObject = MazeEditor.EMPTY;
	this.lPrefs = new LevelPrefs();
	this.mPrefs = new MazePrefs();
	this.mhandler = new EventHandler();
	this.mbhandler = new MetalButtonEventHandler();
	this.shandler = new StartEventHandler();
	this.thandler = new TeleportEventHandler();
	this.cthandler = new ConditionalTeleportEventHandler();
	this.engine = new UndoRedoEngine();
	this.rhandler = new TreasureEventHandler();
	this.groundNames = GameObjects.getAllGroundLayerNames();
	this.objectNames = GameObjects.getAllObjectLayerNames();
	this.groundObjects = GameObjects.getAllGroundLayerObjects();
	this.objectObjects = GameObjects.getAllObjectLayerObjects();
	this.groundEditorAppearances = GameObjects.getAllGroundLayerEditorAppearances();
	this.objectEditorAppearances = GameObjects.getAllObjectLayerEditorAppearances();
	// Set up treasure picker
	this.containableNames = GameObjects.getAllContainableNames();
	this.containableObjects = GameObjects.getAllContainableObjects();
	this.containableEditorAppearances = GameObjects.getAllContainableObjectEditorAppearances();
	this.treasurePicker = new PicturePicker(this.containableEditorAppearances, this.containableNames);
	this.treasurePicker.changePickerColor(new Color(223, 223, 223));
	this.pick = new JButton("Pick!");
	this.pick.addActionListener(this.rhandler);
	final int maxSize = Prefs.getViewingWindowSize();
	this.treasurePicker.updatePickerLayout(maxSize);
	this.mazeChanged = true;
	this.goToDestMode = false;
	this.instanceBeingEdited = null;
    }

    public void mazeChanged() {
	this.mazeChanged = true;
    }

    public EditorLocationManager getLocationManager() {
	return this.elMgr;
    }

    public void updateEditorPosition(final int x, final int y, final int z, final int w) {
	this.elMgr.offsetEditorLocationW(w);
	this.evMgr.offsetViewingWindowLocationX(x);
	this.evMgr.offsetViewingWindowLocationY(y);
	this.elMgr.offsetEditorLocationZ(z);
	if (w != 0) {
	    // Level Change
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().switchLevelOffset(w);
	    this.fixLimits();
	    this.setUpGUI();
	}
	this.checkFlags();
	this.redrawEditor();
    }

    public void updateEditorPositionAbsolute(final int x, final int y, final int z, final int w) {
	final int oldW = this.elMgr.getEditorLocationW();
	this.elMgr.setEditorLocationW(w);
	this.evMgr.setViewingWindowCenterX(y);
	this.evMgr.setViewingWindowCenterY(x);
	this.elMgr.setEditorLocationZ(z);
	if (w != oldW) {
	    // Level Change
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().switchLevelOffset(w);
	    this.fixLimits();
	    this.setUpGUI();
	}
	this.checkFlags();
	this.redrawEditor();
    }

    public void updateEditorLevelAbsolute(final int w) {
	this.elMgr.setEditorLocationW(w);
	// Level Change
	Mazer5D.getBagOStuff().getMazeManager().getMaze().switchLevel(w);
	this.fixLimits();
	this.setUpGUI();
	this.checkFlags();
	this.redrawEditor();
    }

    private void checkFlags() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (Modes.inEditor()) {
	    final Maze m = app.getMazeManager().getMaze();
	    if (m.getLevels() == Maze.getMinLevels()) {
		this.disableRemoveLevel();
	    } else {
		this.enableRemoveLevel();
	    }
	    if (m.getLevels() == Maze.getMaxLevels()) {
		this.disableAddLevel();
	    } else {
		this.enableAddLevel();
	    }
	    try {
		if (app.getMazeManager().getMaze().is3rdDimensionWraparoundEnabled()) {
		    this.enableDownOneFloor();
		} else {
		    if (this.elMgr.getEditorLocationZ() == this.elMgr.getMinEditorLocationZ()) {
			this.disableDownOneFloor();
		    } else {
			this.enableDownOneFloor();
		    }
		}
		if (app.getMazeManager().getMaze().is3rdDimensionWraparoundEnabled()) {
		    this.enableUpOneFloor();
		} else {
		    if (this.elMgr.getEditorLocationZ() == this.elMgr.getMaxEditorLocationZ()) {
			this.disableUpOneFloor();
		    } else {
			this.enableUpOneFloor();
		    }
		}
		if (this.elMgr.getEditorLocationW() == this.elMgr.getMinEditorLocationW()) {
		    this.disableDownOneLevel();
		} else {
		    this.enableDownOneLevel();
		}
		if (this.elMgr.getEditorLocationW() == this.elMgr.getMaxEditorLocationW()) {
		    this.disableUpOneLevel();
		} else {
		    this.enableUpOneLevel();
		}
	    } catch (final NullPointerException npe) {
		this.disableDownOneFloor();
		this.disableUpOneFloor();
		this.disableDownOneLevel();
		this.disableUpOneLevel();
	    }
	    if (this.elMgr != null) {
		if (this.elMgr.getEditorLocationE() != Layers.GROUND) {
		    this.enableSetStartPoint();
		} else {
		    this.disableSetStartPoint();
		}
	    } else {
		this.disableSetStartPoint();
	    }
	    if (!this.engine.tryUndo()) {
		this.disableUndo();
	    } else {
		this.enableUndo();
	    }
	    if (!this.engine.tryRedo()) {
		this.disableRedo();
	    } else {
		this.enableRedo();
	    }
	    if (this.engine.tryBoth()) {
		this.disableClearHistory();
	    } else {
		this.enableClearHistory();
	    }
	    if (app.getMazeManager().getMaze().isPasteBlocked()) {
		this.disablePasteLevel();
		this.disableInsertLevelFromClipboard();
	    } else {
		this.enablePasteLevel();
		this.enableInsertLevelFromClipboard();
	    }
	    if (app.getMazeManager().getMaze().isCutBlocked()) {
		this.disableCutLevel();
	    } else {
		this.enableCutLevel();
	    }
	}
    }

    private void enableUpOneFloor() {
	this.editorUpOneFloor.setEnabled(true);
    }

    private void disableUpOneFloor() {
	this.editorUpOneFloor.setEnabled(false);
    }

    private void enableDownOneFloor() {
	this.editorDownOneFloor.setEnabled(true);
    }

    private void disableDownOneFloor() {
	this.editorDownOneFloor.setEnabled(false);
    }

    private void enableUpOneLevel() {
	this.editorUpOneLevel.setEnabled(true);
    }

    private void disableUpOneLevel() {
	this.editorUpOneLevel.setEnabled(false);
    }

    private void enableDownOneLevel() {
	this.editorDownOneLevel.setEnabled(true);
    }

    private void disableDownOneLevel() {
	this.editorDownOneLevel.setEnabled(false);
    }

    private void enableAddLevel() {
	this.editorAddLevel.setEnabled(true);
    }

    private void disableAddLevel() {
	this.editorAddLevel.setEnabled(false);
    }

    private void enableRemoveLevel() {
	this.editorRemoveLevel.setEnabled(true);
    }

    private void disableRemoveLevel() {
	this.editorRemoveLevel.setEnabled(false);
    }

    private void enableUndo() {
	this.editorUndo.setEnabled(true);
    }

    private void disableUndo() {
	this.editorUndo.setEnabled(false);
    }

    private void enableRedo() {
	this.editorRedo.setEnabled(true);
    }

    private void disableRedo() {
	this.editorRedo.setEnabled(false);
    }

    private void enableClearHistory() {
	this.editorClearHistory.setEnabled(true);
    }

    private void disableClearHistory() {
	this.editorClearHistory.setEnabled(false);
    }

    private void enableCutLevel() {
	this.editorCutLevel.setEnabled(true);
    }

    private void disableCutLevel() {
	this.editorCutLevel.setEnabled(false);
    }

    private void enablePasteLevel() {
	this.editorPasteLevel.setEnabled(true);
    }

    private void disablePasteLevel() {
	this.editorPasteLevel.setEnabled(false);
    }

    private void enableInsertLevelFromClipboard() {
	this.editorInsertLevelFromClipboard.setEnabled(true);
    }

    private void disableInsertLevelFromClipboard() {
	this.editorInsertLevelFromClipboard.setEnabled(false);
    }

    private void enableSetStartPoint() {
	this.editorSetStartPoint.setEnabled(true);
    }

    private void disableSetStartPoint() {
	this.editorSetStartPoint.setEnabled(false);
    }

    private boolean useFillRuleSets() {
	return this.editorFillUseRuleSets.isSelected();
    }

    public void toggleLayer() {
	if (this.elMgr.getEditorLocationE() == Layers.GROUND) {
	    this.elMgr.setEditorLocationE(Layers.OBJECT);
	} else {
	    this.elMgr.setEditorLocationE(Layers.GROUND);
	}
	this.updatePicker();
	this.redrawEditor();
	this.checkFlags();
    }

    public void setMazePrefs() {
	this.mPrefs.showPrefs();
    }

    public void setLevelPrefs() {
	this.lPrefs.showPrefs();
    }

    public void redrawEditor() {
	if (this.elMgr.getEditorLocationE() == Layers.GROUND) {
	    this.redrawGround();
	} else {
	    this.redrawGroundAndObjects();
	}
    }

    private void redrawGround() {
	// Draw the maze in edit mode
	final BagOStuff app = Mazer5D.getBagOStuff();
	int x, y, w;
	int xFix, yFix;
	w = this.elMgr.getEditorLocationW();
	for (x = this.evMgr.getViewingWindowLocationX(); x <= this.evMgr.getLowerRightViewingWindowLocationX(); x++) {
	    for (y = this.evMgr.getViewingWindowLocationY(); y <= this.evMgr
		    .getLowerRightViewingWindowLocationY(); y++) {
		xFix = x - this.evMgr.getViewingWindowLocationX();
		yFix = y - this.evMgr.getViewingWindowLocationY();
		try {
		    final MazeObject obj1 = app.getMazeManager().getMaze().getCell(y, x,
			    this.elMgr.getEditorLocationZ(), Layers.GROUND);
		    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.load(obj1, false));
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.load(MazeEditor.VOID, false));
		} catch (final NullPointerException np) {
		    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.load(MazeEditor.VOID, false));
		}
	    }
	}
	this.outputFrame.setTitle(
		"Editor (Object Layer) - Floor " + (this.elMgr.getEditorLocationZ() + 1) + " Level " + (w + 1));
	this.showOutput();
    }

    private void redrawGroundAndObjects() {
	// Draw the maze in edit mode
	final BagOStuff app = Mazer5D.getBagOStuff();
	int x, y, w;
	int xFix, yFix;
	w = this.elMgr.getEditorLocationW();
	for (x = this.evMgr.getViewingWindowLocationX(); x <= this.evMgr.getLowerRightViewingWindowLocationX(); x++) {
	    for (y = this.evMgr.getViewingWindowLocationY(); y <= this.evMgr
		    .getLowerRightViewingWindowLocationY(); y++) {
		xFix = x - this.evMgr.getViewingWindowLocationX();
		yFix = y - this.evMgr.getViewingWindowLocationY();
		try {
		    final MazeObject obj1 = app.getMazeManager().getMaze().getCell(y, x,
			    this.elMgr.getEditorLocationZ(), Layers.GROUND);
		    final MazeObject obj2 = app.getMazeManager().getMaze().getCell(y, x,
			    this.elMgr.getEditorLocationZ(), Layers.OBJECT);
		    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.getCompositeImage(obj1, obj2, false));
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.load(MazeEditor.VOID, false));
		} catch (final NullPointerException np) {
		    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.load(MazeEditor.VOID, false));
		}
	    }
	}
	this.outputFrame.setTitle(
		"Editor (Object Layer) - Floor " + (this.elMgr.getEditorLocationZ() + 1) + " Level " + (w + 1));
	this.showOutput();
    }

    private void redrawVirtual(final int x, final int y, final MazeObject obj3) {
	// Draw the square
	final BagOStuff app = Mazer5D.getBagOStuff();
	int xFix, yFix;
	xFix = y - this.evMgr.getViewingWindowLocationX();
	yFix = x - this.evMgr.getViewingWindowLocationY();
	try {
	    MazeObject obj1, obj2;
	    obj1 = app.getMazeManager().getMaze().getCell(y, x, this.elMgr.getEditorLocationZ(), Layers.GROUND);
	    obj2 = app.getMazeManager().getMaze().getCell(y, x, this.elMgr.getEditorLocationZ(), Layers.OBJECT);
	    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.getVirtualCompositeImage(obj1, obj2, obj3, false));
	    this.drawGrid[xFix][yFix].repaint();
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
    }

    public void editObject(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	this.currentObjectIndex = this.picker.getPicked();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int gridX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int gridY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	try {
	    this.savedMazeObject = app.getMazeManager().getMaze().getCell(gridX, gridY, this.elMgr.getEditorLocationZ(),
		    this.elMgr.getEditorLocationE());
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    return;
	}
	MazeObject[] choices = null;
	if (this.elMgr.getEditorLocationE() == Layers.GROUND) {
	    choices = this.groundObjects;
	} else {
	    choices = this.objectObjects;
	}
	final MazeObject mo = choices[this.currentObjectIndex];
	final MazeObject instance = GameObjects.createObject(mo.getUniqueID());
	this.elMgr.setEditorLocationX(gridX);
	this.elMgr.setEditorLocationY(gridY);
	mo.editorPlaceHook();
	try {
	    this.checkTwoWayTeleportPair(this.elMgr.getEditorLocationZ());
	    this.updateUndoHistory(this.savedMazeObject, gridX, gridY, this.elMgr.getEditorLocationZ(),
		    this.elMgr.getEditorLocationW(), this.elMgr.getEditorLocationE());
	    app.getMazeManager().getMaze().setCell(instance, gridX, gridY, this.elMgr.getEditorLocationZ(),
		    this.elMgr.getEditorLocationE());
	    this.checkStairPair(this.elMgr.getEditorLocationZ());
	    app.getMazeManager().setDirty(true);
	    this.checkFlags();
	    this.redrawEditor();
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    app.getMazeManager().getMaze().setCell(this.savedMazeObject, gridX, gridY, this.elMgr.getEditorLocationZ(),
		    this.elMgr.getEditorLocationE());
	    this.redrawEditor();
	}
    }

    public void probeObjectProperties(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int gridX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int gridY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	try {
	    final MazeObject mo = app.getMazeManager().getMaze().getCell(gridX, gridY, this.elMgr.getEditorLocationZ(),
		    this.elMgr.getEditorLocationE());
	    this.elMgr.setEditorLocationX(gridX);
	    this.elMgr.setEditorLocationY(gridY);
	    mo.editorProbeHook();
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    final MazeObject ev = GameObjects.createObject(MazeObjects.BOUNDS);
	    ev.determineCurrentAppearance(gridX, gridY, this.elMgr.getEditorLocationZ());
	    ev.editorProbeHook();
	}
    }

    public void editObjectProperties(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int gridX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int gridY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	try {
	    final MazeObject mo = app.getMazeManager().getMaze().getCell(gridX, gridY, this.elMgr.getEditorLocationZ(),
		    this.elMgr.getEditorLocationE());
	    this.elMgr.setEditorLocationX(gridX);
	    this.elMgr.setEditorLocationY(gridY);
	    if (!mo.defersSetProperties()) {
		final MazeObject mo2 = mo.editorPropertiesHook();
		if (mo2 == null) {
		    Mazer5D.getBagOStuff().showMessage("This object has no properties");
		} else {
		    this.checkTwoWayTeleportPair(this.elMgr.getEditorLocationZ());
		    this.updateUndoHistory(this.savedMazeObject, gridX, gridY, this.elMgr.getEditorLocationZ(),
			    this.elMgr.getEditorLocationW(), this.elMgr.getEditorLocationE());
		    app.getMazeManager().getMaze().setCell(mo2, gridX, gridY, this.elMgr.getEditorLocationZ(),
			    this.elMgr.getEditorLocationE());
		    this.checkStairPair(this.elMgr.getEditorLocationZ());
		    this.checkFlags();
		    app.getMazeManager().setDirty(true);
		}
	    } else {
		mo.editorPropertiesHook();
	    }
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    // Do nothing
	}
    }

    public void setStatusMessage(final String msg) {
	this.messageLabel.setText(msg);
    }

    private void checkStairPair(final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObject mo1 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		this.elMgr.getEditorLocationY(), z, Layers.OBJECT);
	final String name1 = mo1.getName();
	String name2, name3;
	try {
	    final MazeObject mo2 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		    this.elMgr.getEditorLocationY(), z + 1, Layers.OBJECT);
	    name2 = mo2.getName();
	} catch (final ArrayIndexOutOfBoundsException e) {
	    name2 = StaticStrings.EMPTY;
	}
	try {
	    final MazeObject mo3 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		    this.elMgr.getEditorLocationY(), z - 1, Layers.OBJECT);
	    name3 = mo3.getName();
	} catch (final ArrayIndexOutOfBoundsException e) {
	    name3 = StaticStrings.EMPTY;
	}
	if (!name1.equals("Stairs Up")) {
	    if (name2.equals("Stairs Down")) {
		this.unpairStairs(MazeEditor.STAIRS_UP, z);
	    } else if (!name1.equals("Stairs Down")) {
		if (name3.equals("Stairs Up")) {
		    this.unpairStairs(MazeEditor.STAIRS_DOWN, z);
		}
	    }
	}
    }

    private void reverseCheckStairPair(final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObject mo1 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		this.elMgr.getEditorLocationY(), z, Layers.OBJECT);
	final String name1 = mo1.getName();
	String name2, name3;
	try {
	    final MazeObject mo2 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		    this.elMgr.getEditorLocationY(), z + 1, Layers.OBJECT);
	    name2 = mo2.getName();
	} catch (final ArrayIndexOutOfBoundsException e) {
	    name2 = StaticStrings.EMPTY;
	}
	try {
	    final MazeObject mo3 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		    this.elMgr.getEditorLocationY(), z - 1, Layers.OBJECT);
	    name3 = mo3.getName();
	} catch (final ArrayIndexOutOfBoundsException e) {
	    name3 = StaticStrings.EMPTY;
	}
	if (name1.equals("Stairs Up")) {
	    if (!name2.equals("Stairs Down")) {
		this.pairStairs(MazeEditor.STAIRS_UP, z);
	    } else if (name1.equals("Stairs Down")) {
		if (!name3.equals("Stairs Up")) {
		    this.pairStairs(MazeEditor.STAIRS_DOWN, z);
		}
	    }
	}
    }

    public void pairStairs(final int type) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	switch (type) {
	case STAIRS_UP:
	    try {
		app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.STAIRS_DOWN),
			this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(),
			this.elMgr.getEditorLocationZ() + 1, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException e) {
		// Do nothing
	    }
	    break;
	case STAIRS_DOWN:
	    try {
		app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.STAIRS_UP),
			this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(),
			this.elMgr.getEditorLocationZ() - 1, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException e) {
		// Do nothing
	    }
	    break;
	default:
	    break;
	}
    }

    private void pairStairs(final int type, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	switch (type) {
	case STAIRS_UP:
	    try {
		app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.STAIRS_DOWN),
			this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), z + 1, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException e) {
		// Do nothing
	    }
	    break;
	case STAIRS_DOWN:
	    try {
		app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.STAIRS_UP),
			this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), z - 1, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException e) {
		// Do nothing
	    }
	    break;
	default:
	    break;
	}
    }

    private void unpairStairs(final int type, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	switch (type) {
	case STAIRS_UP:
	    try {
		app.getMazeManager().getMaze().setCell(MazeEditor.EMPTY, this.elMgr.getEditorLocationX(),
			this.elMgr.getEditorLocationY(), z + 1, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException e) {
		// Do nothing
	    }
	    break;
	case STAIRS_DOWN:
	    try {
		app.getMazeManager().getMaze().setCell(MazeEditor.EMPTY, this.elMgr.getEditorLocationX(),
			this.elMgr.getEditorLocationY(), z - 1, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException e) {
		// Do nothing
	    }
	    break;
	default:
	    break;
	}
    }

    private void checkTwoWayTeleportPair(final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObject mo1 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		this.elMgr.getEditorLocationY(), z, Layers.OBJECT);
	final String name1 = mo1.getName();
	String name2;
	int destX, destY, destZ;
	if (name1.equals("Two-Way Teleport")) {
	    final GenericTeleport twt = (GenericTeleport) mo1;
	    destX = twt.getDestinationRow();
	    destY = twt.getDestinationColumn();
	    destZ = twt.getDestinationFloor();
	    final MazeObject mo2 = app.getMazeManager().getMaze().getCell(destX, destY, destZ, Layers.OBJECT);
	    name2 = mo2.getName();
	    if (name2.equals("Two-Way Teleport")) {
		MazeEditor.unpairTwoWayTeleport(destX, destY, destZ);
	    }
	}
    }

    private void reverseCheckTwoWayTeleportPair(final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObject mo1 = app.getMazeManager().getMaze().getCell(this.elMgr.getEditorLocationX(),
		this.elMgr.getEditorLocationY(), z, Layers.OBJECT);
	final String name1 = mo1.getName();
	String name2;
	int destX, destY, destZ;
	if (name1.equals("Two-Way Teleport")) {
	    final GenericTeleport twt = (GenericTeleport) mo1;
	    destX = twt.getDestinationRow();
	    destY = twt.getDestinationColumn();
	    destZ = twt.getDestinationFloor();
	    final MazeObject mo2 = app.getMazeManager().getMaze().getCell(destX, destY, destZ, Layers.OBJECT);
	    name2 = mo2.getName();
	    if (!name2.equals("Two-Way Teleport")) {
		this.pairTwoWayTeleport(destX, destY, destZ);
	    }
	}
    }

    public void pairTwoWayTeleport(final int destX, final int destY, final int destZ) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getMazeManager().getMaze().setCell(
		GameObjects.createTeleportObject(MazeObjects.TWO_WAY_TELEPORT, this.elMgr.getEditorLocationX(),
			this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ()),
		destX, destY, destZ, Layers.OBJECT);
    }

    private static void unpairTwoWayTeleport(final int destX, final int destY, final int destZ) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getMazeManager().getMaze().setCell(MazeEditor.EMPTY, destX, destY, destZ, Layers.OBJECT);
    }

    public void editConditionalTeleportDestination(final GenericConditionalTeleport instance) {
	final String[] choices = new String[] { "Edit Destination 1", "Edit Destination 2", "Edit Condition Trigger",
		"Edit Trigger Type" };
	final String choice = CommonDialogs.showInputDialog("Edit What?", "Editor", choices, choices[0]);
	if (choice != null) {
	    this.instanceBeingEdited = instance;
	    this.conditionalEditFlag = 0;
	    for (int x = 0; x < choices.length; x++) {
		if (choices[x].equals(choice)) {
		    this.conditionalEditFlag = x + 1;
		    break;
		}
	    }
	    if (this.conditionalEditFlag != 0) {
		if (this.conditionalEditFlag == MazeEditor.CEF_CONDITION) {
		    final String def = Integer.toString(instance.getTriggerValue());
		    final String resp = CommonDialogs.showTextInputDialogWithDefault(
			    "Condition Trigger Value (Number of Sun/Moon Stones needed)", "Editor", def);
		    int respVal = -1;
		    try {
			respVal = Integer.parseInt(resp);
			if (respVal < 0) {
			    throw new NumberFormatException(resp);
			}
		    } catch (final NumberFormatException nfe) {
			CommonDialogs.showDialog("The value must be a non-negative integer.");
			this.instanceBeingEdited = null;
			return;
		    }
		    instance.setTriggerValue(respVal);
		} else if (this.conditionalEditFlag == MazeEditor.CEF_TRIGGER_TYPE) {
		    final int respIndex = instance.getSunMoon();
		    final String[] ttChoices = new String[] { "Sun Stones", "Moon Stones" };
		    final String ttChoice = CommonDialogs.showInputDialog("Condition Trigger Type?", "Editor",
			    ttChoices, ttChoices[respIndex - 1]);
		    if (ttChoice != null) {
			int newResp = -1;
			for (int x = 0; x < choices.length; x++) {
			    if (ttChoices[x].equals(ttChoice)) {
				newResp = x + 1;
				break;
			    }
			}
			if (newResp != -1) {
			    instance.setSunMoon(newResp);
			}
		    }
		} else {
		    this.horzScroll.removeAdjustmentListener(this.mhandler);
		    this.vertScroll.removeAdjustmentListener(this.mhandler);
		    this.secondaryPane.removeMouseListener(this.mhandler);
		    this.horzScroll.addAdjustmentListener(this.cthandler);
		    this.vertScroll.addAdjustmentListener(this.cthandler);
		    this.secondaryPane.addMouseListener(this.cthandler);
		    this.elMgr.setCameFromZ(this.elMgr.getEditorLocationZ());
		    this.disableDownOneLevel();
		    this.disableUpOneLevel();
		}
	    } else {
		this.instanceBeingEdited = null;
	    }
	}
    }

    public MazeObject editTeleportDestination(final int type) {
	String input1 = null, input2 = null;
	this.TELEPORT_TYPE = type;
	int destX = 0, destY = 0;
	switch (type) {
	case TELEPORT_TYPE_GENERIC:
	case TELEPORT_TYPE_INVISIBLE_GENERIC:
	case TELEPORT_TYPE_ONESHOT:
	case TELEPORT_TYPE_INVISIBLE_ONESHOT:
	case TELEPORT_TYPE_TWOWAY:
	case TELEPORT_TYPE_CHAIN:
	case TELEPORT_TYPE_INVISIBLE_CHAIN:
	case TELEPORT_TYPE_ONESHOT_CHAIN:
	case TELEPORT_TYPE_INVISIBLE_ONESHOT_CHAIN:
	    Mazer5D.getBagOStuff().showMessage("Click to set teleport destination");
	    break;
	case TELEPORT_TYPE_MOVING_FINISH:
	    Mazer5D.getBagOStuff().showMessage("Click to set next moving finish");
	    break;
	case TELEPORT_TYPE_FIRST_MOVING_FINISH:
	    Mazer5D.getBagOStuff().showMessage("Click to set first moving finish");
	    break;
	case TELEPORT_TYPE_RANDOM:
	case TELEPORT_TYPE_RANDOM_INVISIBLE:
	case TELEPORT_TYPE_RANDOM_ONESHOT:
	case TELEPORT_TYPE_RANDOM_INVISIBLE_ONESHOT:
	    input1 = CommonDialogs.showTextInputDialog("Random row range:", "Editor");
	    break;
	default:
	    break;
	}
	if (input1 != null) {
	    switch (type) {
	    case TELEPORT_TYPE_RANDOM:
	    case TELEPORT_TYPE_RANDOM_INVISIBLE:
	    case TELEPORT_TYPE_RANDOM_ONESHOT:
	    case TELEPORT_TYPE_RANDOM_INVISIBLE_ONESHOT:
		input2 = CommonDialogs.showTextInputDialog("Random column range:", "Editor");
		break;
	    default:
		break;
	    }
	    if (input2 != null) {
		try {
		    destX = Integer.parseInt(input1);
		    destY = Integer.parseInt(input2);
		} catch (final NumberFormatException nf) {
		    CommonDialogs.showDialog("Row and column ranges must be integers.");
		}
		switch (type) {
		case TELEPORT_TYPE_RANDOM:
		    return GameObjects.createTeleportObject(MazeObjects.RANDOM_TELEPORT, destX, destY);
		case TELEPORT_TYPE_RANDOM_INVISIBLE:
		    return GameObjects.createTeleportObject(MazeObjects.RANDOM_INVISIBLE_TELEPORT, destX, destY);
		case TELEPORT_TYPE_RANDOM_ONESHOT:
		    return GameObjects.createTeleportObject(MazeObjects.RANDOM_ONE_SHOT_TELEPORT, destX, destY);
		case TELEPORT_TYPE_RANDOM_INVISIBLE_ONESHOT:
		    return GameObjects.createTeleportObject(MazeObjects.RANDOM_INVISIBLE_ONE_SHOT_TELEPORT, destX,
			    destY);
		default:
		    break;
		}
	    }
	} else {
	    switch (type) {
	    case TELEPORT_TYPE_GENERIC:
	    case TELEPORT_TYPE_INVISIBLE_GENERIC:
	    case TELEPORT_TYPE_ONESHOT:
	    case TELEPORT_TYPE_INVISIBLE_ONESHOT:
	    case TELEPORT_TYPE_TWOWAY:
	    case TELEPORT_TYPE_MOVING_FINISH:
	    case TELEPORT_TYPE_FIRST_MOVING_FINISH:
	    case TELEPORT_TYPE_CHAIN:
	    case TELEPORT_TYPE_INVISIBLE_CHAIN:
	    case TELEPORT_TYPE_ONESHOT_CHAIN:
	    case TELEPORT_TYPE_INVISIBLE_ONESHOT_CHAIN:
		this.horzScroll.removeAdjustmentListener(this.mhandler);
		this.vertScroll.removeAdjustmentListener(this.mhandler);
		this.secondaryPane.removeMouseListener(this.mhandler);
		this.horzScroll.addAdjustmentListener(this.thandler);
		this.vertScroll.addAdjustmentListener(this.thandler);
		this.secondaryPane.addMouseListener(this.thandler);
		this.elMgr.setCameFromZ(this.elMgr.getEditorLocationZ());
		this.disableDownOneLevel();
		this.disableUpOneLevel();
		break;
	    default:
		break;
	    }
	}
	return null;
    }

    public MazeObject editMetalButtonTarget() {
	Mazer5D.getBagOStuff().showMessage("Click to set metal button target");
	this.horzScroll.removeAdjustmentListener(this.mhandler);
	this.vertScroll.removeAdjustmentListener(this.mhandler);
	this.secondaryPane.removeMouseListener(this.mhandler);
	this.horzScroll.addAdjustmentListener(this.mbhandler);
	this.vertScroll.addAdjustmentListener(this.mbhandler);
	this.secondaryPane.addMouseListener(this.mbhandler);
	this.elMgr.setCameFromZ(this.elMgr.getEditorLocationZ());
	this.disableDownOneLevel();
	this.disableUpOneLevel();
	return null;
    }

    public MazeObject editTreasureChestContents() {
	Mazer5D.getBagOStuff().showMessage("Pick treasure chest contents");
	this.setDefaultContents();
	this.disableOutput();
	Modes.setInTreasure();
	this.outputFrame.attachAndSave(this.treasurePane);
	this.outputFrame.setTitle("Pick Treasure Chest Contents");
	return null;
    }

    private void setDefaultContents() {
	GenericContainer tc = null;
	MazeObject contents = null;
	int contentsIndex = 0;
	final BagOStuff app = Mazer5D.getBagOStuff();
	try {
	    tc = (GenericContainer) app.getMazeManager().getMazeObject(this.elMgr.getEditorLocationX(),
		    this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(), Layers.OBJECT);
	    contents = tc.getSavedObject();
	    for (int x = 0; x < this.containableObjects.length; x++) {
		if (contents.getName().equals(this.containableObjects[x].getName())) {
		    contentsIndex = x;
		    break;
		}
	    }
	} catch (final ClassCastException cce) {
	    // Do nothing
	} catch (final NullPointerException npe) {
	    // Do nothing
	}
	this.treasurePicker.selectLastPickedChoice(contentsIndex);
    }

    public void editFinishToDestination(final GenericTeleport ft) {
	String input1 = null;
	int destW = 0;
	input1 = CommonDialogs.showTextInputDialog("Destination Level:", "Editor");
	if (input1 != null) {
	    try {
		destW = Integer.parseInt(input1) - 1;
		ft.setDestinationLevel(destW);
	    } catch (final NumberFormatException nf) {
		CommonDialogs.showDialog("Destination level must be an integer greater than 0.");
	    }
	}
    }

    public void setTeleportDestination(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int destX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int destY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	final int destZ = this.elMgr.getEditorLocationZ();
	try {
	    app.getMazeManager().getMaze().getCell(destX, destY, destZ, Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    this.horzScroll.removeAdjustmentListener(this.thandler);
	    this.vertScroll.removeAdjustmentListener(this.thandler);
	    this.secondaryPane.removeMouseListener(this.thandler);
	    this.horzScroll.addAdjustmentListener(this.mhandler);
	    this.vertScroll.addAdjustmentListener(this.mhandler);
	    this.secondaryPane.addMouseListener(this.mhandler);
	    return;
	}
	switch (this.TELEPORT_TYPE) {
	case TELEPORT_TYPE_GENERIC:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_INVISIBLE_GENERIC:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.INVISIBLE_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_ONESHOT:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.ONE_SHOT_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_INVISIBLE_ONESHOT:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.INVISIBLE_ONE_SHOT_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_TWOWAY:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.TWO_WAY_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    this.pairTwoWayTeleport(destX, destY, destZ);
	    break;
	case TELEPORT_TYPE_MOVING_FINISH:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.MOVING_FINISH, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_FIRST_MOVING_FINISH:
	    final Maze m = app.getMazeManager().getMaze();
	    m.setFirstMovingFinishX(destX);
	    m.setFirstMovingFinishY(destY);
	    m.setFirstMovingFinishZ(destZ);
	    break;
	case TELEPORT_TYPE_CHAIN:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.CHAIN_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_INVISIBLE_CHAIN:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.INVISIBLE_CHAIN_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_ONESHOT_CHAIN:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.ONE_SHOT_CHAIN_TELEPORT, destX, destY, destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	case TELEPORT_TYPE_INVISIBLE_ONESHOT_CHAIN:
	    app.getMazeManager().getMaze().setCell(
		    GameObjects.createTeleportObject(MazeObjects.INVISIBLE_ONE_SHOT_CHAIN_TELEPORT, destX, destY,
			    destZ),
		    this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		    Layers.OBJECT);
	    break;
	default:
	    break;
	}
	this.horzScroll.removeAdjustmentListener(this.thandler);
	this.vertScroll.removeAdjustmentListener(this.thandler);
	this.secondaryPane.removeMouseListener(this.thandler);
	this.horzScroll.addAdjustmentListener(this.mhandler);
	this.vertScroll.addAdjustmentListener(this.mhandler);
	this.secondaryPane.addMouseListener(this.mhandler);
	this.checkFlags();
	if (this.TELEPORT_TYPE == MazeEditor.TELEPORT_TYPE_MOVING_FINISH) {
	    Mazer5D.getBagOStuff().showMessage("Next moving finish set.");
	} else if (this.TELEPORT_TYPE == MazeEditor.TELEPORT_TYPE_FIRST_MOVING_FINISH) {
	    Mazer5D.getBagOStuff().showMessage("First moving finish set.");
	} else {
	    Mazer5D.getBagOStuff().showMessage("Destination set.");
	}
	app.getMazeManager().setDirty(true);
	this.redrawEditor();
    }

    void setConditionalTeleportDestination(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int destX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int destY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	final int destZ = this.elMgr.getEditorLocationZ();
	if (this.instanceBeingEdited != null) {
	    if (this.conditionalEditFlag == MazeEditor.CEF_DEST1) {
		this.instanceBeingEdited.setDestinationRow(destX);
		this.instanceBeingEdited.setDestinationColumn(destY);
		this.instanceBeingEdited.setDestinationFloor(destZ);
	    } else if (this.conditionalEditFlag == MazeEditor.CEF_DEST2) {
		this.instanceBeingEdited.setDestinationRow2(destX);
		this.instanceBeingEdited.setDestinationColumn2(destY);
		this.instanceBeingEdited.setDestinationFloor2(destZ);
	    }
	    this.instanceBeingEdited = null;
	}
	this.horzScroll.removeAdjustmentListener(this.cthandler);
	this.vertScroll.removeAdjustmentListener(this.cthandler);
	this.secondaryPane.removeMouseListener(this.cthandler);
	this.horzScroll.addAdjustmentListener(this.mhandler);
	this.vertScroll.addAdjustmentListener(this.mhandler);
	this.secondaryPane.addMouseListener(this.mhandler);
	this.checkFlags();
	Mazer5D.getBagOStuff().showMessage("Destination set.");
	app.getMazeManager().setDirty(true);
	this.redrawEditor();
    }

    public void setMetalButtonTarget(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int destX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int destY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	final int destZ = this.elMgr.getEditorLocationZ();
	try {
	    app.getMazeManager().getMaze().getCell(destX, destY, destZ, Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    this.horzScroll.removeAdjustmentListener(this.mbhandler);
	    this.vertScroll.removeAdjustmentListener(this.mbhandler);
	    this.secondaryPane.removeMouseListener(this.mbhandler);
	    this.horzScroll.addAdjustmentListener(this.mhandler);
	    this.vertScroll.addAdjustmentListener(this.mhandler);
	    this.secondaryPane.addMouseListener(this.mhandler);
	    return;
	}
	app.getMazeManager().getMaze().setCell(
		GameObjects.createTeleportObject(MazeObjects.METAL_BUTTON, destX, destY, destZ),
		this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		Layers.OBJECT);
	this.horzScroll.removeAdjustmentListener(this.mbhandler);
	this.vertScroll.removeAdjustmentListener(this.mbhandler);
	this.secondaryPane.removeMouseListener(this.mbhandler);
	this.horzScroll.addAdjustmentListener(this.mhandler);
	this.vertScroll.addAdjustmentListener(this.mhandler);
	this.secondaryPane.addMouseListener(this.mhandler);
	this.checkFlags();
	Mazer5D.getBagOStuff().showMessage("Target set.");
	app.getMazeManager().setDirty(true);
	this.redrawEditor();
    }

    public void setTreasureChestContents() {
	this.outputFrame.restoreSaved();
	Modes.restore();
	this.enableOutput();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObject contents = this.containableObjects[this.treasurePicker.getPicked()];
	app.getMazeManager().getMaze().setCell(
		GameObjects.createContainerObject(MazeObjects.METAL_BUTTON, contents.getUniqueID()),
		this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getCameFromZ(),
		Layers.OBJECT);
	this.checkFlags();
	Mazer5D.getBagOStuff().showMessage("Contents set.");
	app.getMazeManager().setDirty(true);
	this.redrawEditor();
    }

    public void editPlayerLocation() {
	// Swap event handlers
	this.horzScroll.removeAdjustmentListener(this.mhandler);
	this.vertScroll.removeAdjustmentListener(this.mhandler);
	this.secondaryPane.removeMouseListener(this.mhandler);
	this.horzScroll.addAdjustmentListener(this.shandler);
	this.vertScroll.addAdjustmentListener(this.shandler);
	this.secondaryPane.addMouseListener(this.shandler);
	Mazer5D.getBagOStuff().showMessage("Click to set start point");
    }

    public void setPlayerLocation(final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int oldX = app.getMazeManager().getMaze().getStartColumn();
	final int oldY = app.getMazeManager().getMaze().getStartRow();
	final int oldZ = app.getMazeManager().getMaze().getStartFloor();
	// Erase old player
	try {
	    app.getMazeManager().getMaze().setCell(MazeEditor.EMPTY, oldX, oldY, oldZ, Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    // Ignore
	}
	// Set new player
	app.getMazeManager().getMaze().setStartRow(y);
	app.getMazeManager().getMaze().setStartColumn(x);
	app.getMazeManager().getMaze().setStartFloor(z);
	app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.PLAYER), x, y, z, Layers.OBJECT);
    }

    public void setPlayerLocation() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int oldX = app.getMazeManager().getMaze().getStartColumn();
	final int oldY = app.getMazeManager().getMaze().getStartRow();
	final int oldZ = app.getMazeManager().getMaze().getStartFloor();
	// Erase old player
	try {
	    app.getMazeManager().getMaze().setCell(MazeEditor.EMPTY, oldX, oldY, oldZ, Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    // Ignore
	}
	// Set new player
	app.getMazeManager().getMaze().setStartRow(this.elMgr.getEditorLocationY());
	app.getMazeManager().getMaze().setStartColumn(this.elMgr.getEditorLocationX());
	app.getMazeManager().getMaze().setStartFloor(this.elMgr.getEditorLocationZ());
	app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.PLAYER),
		this.elMgr.getEditorLocationX(), this.elMgr.getEditorLocationY(), this.elMgr.getEditorLocationZ(),
		Layers.OBJECT);
    }

    void setPlayerLocation(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	final int destX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int destY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	final int oldX = app.getMazeManager().getMaze().getStartColumn();
	final int oldY = app.getMazeManager().getMaze().getStartRow();
	final int oldZ = app.getMazeManager().getMaze().getStartFloor();
	// Erase old player
	try {
	    app.getMazeManager().getMaze().setCell(MazeEditor.EMPTY, oldX, oldY, oldZ, Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    // Ignore
	}
	// Set new player
	try {
	    app.getMazeManager().getMaze().saveStart();
	    app.getMazeManager().getMaze().setStartRow(destY);
	    app.getMazeManager().getMaze().setStartColumn(destX);
	    app.getMazeManager().getMaze().setStartFloor(this.elMgr.getEditorLocationZ());
	    app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.PLAYER), destX, destY,
		    this.elMgr.getEditorLocationZ(), Layers.OBJECT);
	    Mazer5D.getBagOStuff().showMessage("Start point set.");
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    app.getMazeManager().getMaze().restoreStart();
	    try {
		app.getMazeManager().getMaze().setCell(GameObjects.createObject(MazeObjects.PLAYER), oldX, oldY, oldZ,
			Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException aioob2) {
		// Ignore
	    }
	    Mazer5D.getBagOStuff().showMessage("Aim within the maze");
	}
	// Swap event handlers
	this.horzScroll.removeAdjustmentListener(this.shandler);
	this.vertScroll.removeAdjustmentListener(this.shandler);
	this.secondaryPane.removeMouseListener(this.shandler);
	this.horzScroll.addAdjustmentListener(this.mhandler);
	this.vertScroll.addAdjustmentListener(this.mhandler);
	this.secondaryPane.addMouseListener(this.mhandler);
	// Set dirty flag
	app.getMazeManager().setDirty(true);
	this.redrawEditor();
    }

    public void editMaze() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (app.getMazeManager().getLoaded()) {
	    app.getGUIManager().hideGUI();
	    Modes.setInEditor();
	    // Reset game state
	    app.getGameManager().resetGameState();
	    // Create the managers
	    if (this.mazeChanged) {
		this.elMgr = new EditorLocationManager();
		this.evMgr = new EditorViewingWindowManager();
		this.elMgr.setLimitsFromMaze(app.getMazeManager().getMaze());
		this.evMgr.halfOffsetMaximumViewingWindowLocationsFromMaze(app.getMazeManager().getMaze());
		this.mazeChanged = false;
	    }
	    this.setUpGUI();
	    this.clearHistory();
	    // Make sure message area is attached to border pane
	    this.borderPane.removeAll();
	    this.borderPane.add(this.messageLabel, BorderLayout.NORTH);
	    this.borderPane.add(this.outputPane, BorderLayout.CENTER);
	    this.borderPane.add(this.picker.getPicker(), BorderLayout.EAST);
	    this.redrawEditor();
	    this.checkFlags();
	} else {
	    CommonDialogs.showDialog("No Maze Opened");
	}
    }

    public void newMaze() {
	final BagOStuff bag = Mazer5D.getBagOStuff();
	boolean success = true;
	boolean saved = true;
	int status = 0;
	if (bag.getMazeManager().getDirty()) {
	    status = bag.getMazeManager().showSaveDialog();
	    if (status == CommonDialogs.YES_OPTION) {
		bag.getMazeManager().saveMaze();
		saved = !bag.getMazeManager().getDirty();
	    } else if (status == CommonDialogs.CANCEL_OPTION) {
		saved = false;
	    } else {
		bag.getMazeManager().setDirty(false);
	    }
	}
	if (saved) {
	    bag.getGameManager().getPlayerManager().resetPlayerLocation();
	    bag.getMazeManager().setMaze(new Maze());
	    success = this.addLevelInternal(true);
	    if (success) {
		bag.getMazeManager().clearLastUsedFilenames();
		this.clearHistory();
	    }
	} else {
	    success = false;
	}
	bag.getMazeManager().setLoaded(success);
	if (success) {
	    this.mazeChanged = true;
	    bag.getGameManager().stateChanged();
	    bag.getMazeManager().clearLockedFlag();
	}
    }

    public void fixLimits() {
	// Fix limits
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (app.getMazeManager().getMaze() != null && this.elMgr != null && this.evMgr != null) {
	    this.elMgr.setLimitsFromMaze(app.getMazeManager().getMaze());
	    this.evMgr.halfOffsetMaximumViewingWindowLocationsFromMaze(app.getMazeManager().getMaze());
	}
    }

    private boolean confirmNonUndoable(final String task) {
	final int confirm = CommonDialogs.showConfirmDialog("Are you sure you want to " + task + "?"
		+ " This action is NOT undoable and will clear the undo/redo history!", "Editor");
	if (confirm == CommonDialogs.YES_OPTION) {
	    this.clearHistory();
	    return true;
	}
	return false;
    }

    public void cutLevel() {
	final int level = this.getLocationManager().getEditorLocationW();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().cutLevel();
	this.fixLimits();
	this.updateEditorLevelAbsolute(level);
	this.checkFlags();
    }

    public void copyLevel() {
	final int level = this.getLocationManager().getEditorLocationW();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().copyLevel();
	this.fixLimits();
	this.updateEditorLevelAbsolute(level);
	this.checkFlags();
    }

    public void pasteLevel() {
	final int level = this.getLocationManager().getEditorLocationW();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().pasteLevel();
	this.fixLimits();
	this.updateEditorLevelAbsolute(level);
	this.checkFlags();
    }

    public void insertLevelFromClipboard() {
	final int level = this.getLocationManager().getEditorLocationW();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().insertLevelFromClipboard();
	this.fixLimits();
	this.updateEditorLevelAbsolute(level);
	this.checkFlags();
    }

    public void fillLevel() {
	if (this.confirmNonUndoable("overwrite the active level with default data")) {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().fillLevelDefault();
	    Mazer5D.getBagOStuff().showMessage("Level filled.");
	    Mazer5D.getBagOStuff().getMazeManager().setDirty(true);
	    this.redrawEditor();
	}
    }

    public void fillFloor() {
	if (this.confirmNonUndoable("overwrite the active floor within the active level with default data")) {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().fillFloorDefault(this.elMgr.getEditorLocationZ());
	    Mazer5D.getBagOStuff().showMessage("Floor filled.");
	    Mazer5D.getBagOStuff().getMazeManager().setDirty(true);
	    this.redrawEditor();
	}
    }

    public void fillLevelRandomly() {
	if (this.confirmNonUndoable("overwrite the active level with random data")) {
	    if (this.useFillRuleSets()) {
		Mazer5D.getBagOStuff().getMazeManager().getMaze().fillLevelRandomlyCustom();
	    } else {
		Mazer5D.getBagOStuff().getMazeManager().getMaze().fillLevelRandomly();
	    }
	    Mazer5D.getBagOStuff().showMessage("Level randomly filled.");
	    Mazer5D.getBagOStuff().getMazeManager().setDirty(true);
	    this.redrawEditor();
	}
    }

    public void fillFloorRandomly() {
	if (this.confirmNonUndoable("overwrite the active floor within the active level with random data")) {
	    if (this.useFillRuleSets()) {
		Mazer5D.getBagOStuff().getMazeManager().getMaze()
			.fillFloorRandomlyCustom(this.elMgr.getEditorLocationZ());
	    } else {
		Mazer5D.getBagOStuff().getMazeManager().getMaze().fillFloorRandomly(this.elMgr.getEditorLocationZ());
	    }
	    Mazer5D.getBagOStuff().showMessage("Floor randomly filled.");
	    Mazer5D.getBagOStuff().getMazeManager().setDirty(true);
	    this.redrawEditor();
	}
    }

    public boolean addLevel() {
	return this.addLevelInternal(false);
    }

    private boolean addLevelInternal(final boolean flag) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int levelSizeX, levelSizeY, levelSizeZ;
	final int maxR = Maze.getMaxRows();
	final int minR = Maze.getMinRows();
	final int maxC = Maze.getMaxColumns();
	final int minC = Maze.getMinColumns();
	final int maxF = Maze.getMaxFloors();
	final int minF = Maze.getMinFloors();
	String msg = null;
	if (flag) {
	    msg = "New Maze";
	} else {
	    msg = "New Level";
	}
	boolean success = true;
	String input1, input2, input3;
	input1 = CommonDialogs.showTextInputDialog("Number of rows (" + minR + "-" + maxR + ")?", msg);
	if (input1 != null) {
	    input2 = CommonDialogs.showTextInputDialog("Number of columns (" + minC + "-" + maxC + ")?", msg);
	    if (input2 != null) {
		input3 = CommonDialogs.showTextInputDialog("Number of floors (" + minF + "-" + maxF + ")?", msg);
		if (input3 != null) {
		    try {
			levelSizeX = Integer.parseInt(input1);
			levelSizeY = Integer.parseInt(input2);
			levelSizeZ = Integer.parseInt(input3);
			if (levelSizeX < minR) {
			    throw new NumberFormatException("Rows must be at least " + minR + ".");
			}
			if (levelSizeX > maxR) {
			    throw new NumberFormatException("Rows must be less than or equal to " + maxR + ".");
			}
			if (levelSizeY < minC) {
			    throw new NumberFormatException("Columns must be at least " + minC + ".");
			}
			if (levelSizeY > maxC) {
			    throw new NumberFormatException("Columns must be less than or equal to " + maxC + ".");
			}
			if (levelSizeZ < minF) {
			    throw new NumberFormatException("Floors must be at least " + minF + ".");
			}
			if (levelSizeZ > maxF) {
			    throw new NumberFormatException("Floors must be less than or equal to " + maxF + ".");
			}
			final int saveLevel = app.getMazeManager().getMaze().getActiveLevelNumber();
			success = app.getMazeManager().getMaze().addLevel(levelSizeX, levelSizeY, levelSizeZ);
			if (success) {
			    this.fixLimits();
			    if (!flag) {
				this.evMgr.setViewingWindowLocationX(0 - (this.evMgr.getViewingWindowSizeX() - 1) / 2);
				this.evMgr.setViewingWindowLocationY(0 - (this.evMgr.getViewingWindowSizeY() - 1) / 2);
			    }
			    app.getMazeManager().getMaze().fillLevel(
				    GameObjects.createObject(Prefs.getEditorDefaultFill()), MazeEditor.EMPTY);
			    // Save the entire level
			    app.getMazeManager().getMaze().save();
			    app.getMazeManager().getMaze().switchLevel(saveLevel);
			    this.checkFlags();
			}
		    } catch (final NumberFormatException nf) {
			CommonDialogs.showDialog(nf.getMessage());
			success = false;
		    }
		} else {
		    // User canceled
		    success = false;
		}
	    } else {
		// User canceled
		success = false;
	    }
	} else {
	    // User canceled
	    success = false;
	}
	return success;
    }

    public boolean resizeLevel() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int levelSizeX, levelSizeY, levelSizeZ;
	final int maxR = Maze.getMaxRows();
	final int minR = Maze.getMinRows();
	final int maxC = Maze.getMaxColumns();
	final int minC = Maze.getMinColumns();
	final int maxF = Maze.getMaxFloors();
	final int minF = Maze.getMinFloors();
	final String msg = "Resize Level";
	boolean success = true;
	String input1, input2, input3;
	input1 = CommonDialogs.showTextInputDialogWithDefault("Number of rows (" + minR + "-" + maxR + ")?", msg,
		Integer.toString(app.getMazeManager().getMaze().getRows()));
	if (input1 != null) {
	    input2 = CommonDialogs.showTextInputDialogWithDefault("Number of columns (" + minC + "-" + maxC + ")?", msg,
		    Integer.toString(app.getMazeManager().getMaze().getColumns()));
	    if (input2 != null) {
		input3 = CommonDialogs.showTextInputDialogWithDefault("Number of floors (" + minF + "-" + maxF + ")?",
			msg, Integer.toString(app.getMazeManager().getMaze().getFloors()));
		if (input3 != null) {
		    try {
			levelSizeX = Integer.parseInt(input1);
			levelSizeY = Integer.parseInt(input2);
			levelSizeZ = Integer.parseInt(input3);
			if (levelSizeX < minR) {
			    throw new NumberFormatException("Rows must be at least " + minR + ".");
			}
			if (levelSizeX > maxR) {
			    throw new NumberFormatException("Rows must be less than or equal to " + maxR + ".");
			}
			if (levelSizeY < minC) {
			    throw new NumberFormatException("Columns must be at least " + minC + ".");
			}
			if (levelSizeY > maxC) {
			    throw new NumberFormatException("Columns must be less than or equal to " + maxC + ".");
			}
			if (levelSizeZ < minF) {
			    throw new NumberFormatException("Floors must be at least " + minF + ".");
			}
			if (levelSizeZ > maxF) {
			    throw new NumberFormatException("Floors must be less than or equal to " + maxF + ".");
			}
			app.getMazeManager().getMaze().resize(levelSizeX, levelSizeY, levelSizeZ);
			this.fixLimits();
			this.evMgr.setViewingWindowLocationX(0 - (this.evMgr.getViewingWindowSizeX() - 1) / 2);
			this.evMgr.setViewingWindowLocationY(0 - (this.evMgr.getViewingWindowSizeY() - 1) / 2);
			// Save the entire level
			app.getMazeManager().getMaze().save();
			this.checkFlags();
			// Redraw
			this.redrawEditor();
		    } catch (final NumberFormatException nf) {
			CommonDialogs.showDialog(nf.getMessage());
			success = false;
		    }
		} else {
		    // User canceled
		    success = false;
		}
	    } else {
		// User canceled
		success = false;
	    }
	} else {
	    // User canceled
	    success = false;
	}
	return success;
    }

    public boolean removeLevel() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int level;
	boolean success = true;
	String input;
	input = CommonDialogs.showTextInputDialog(
		"Level Number (1-" + app.getMazeManager().getMaze().getLevels() + ")?", "Remove Level");
	if (input != null) {
	    try {
		level = Integer.parseInt(input);
		if (level < 1 || level > app.getMazeManager().getMaze().getLevels()) {
		    throw new NumberFormatException("Level number must be in the range 1 to "
			    + app.getMazeManager().getMaze().getLevels() + ".");
		}
		success = app.getMazeManager().getMaze().removeLevel();
		if (success) {
		    this.fixLimits();
		    if (level == this.elMgr.getEditorLocationW() + 1) {
			// Deleted current level - go to level 1
			this.updateEditorLevelAbsolute(0);
		    }
		    this.checkFlags();
		}
	    } catch (final NumberFormatException nf) {
		CommonDialogs.showDialog(nf.getMessage());
		success = false;
	    }
	} else {
	    // User canceled
	    success = false;
	}
	return success;
    }

    public void goToLocationHandler() {
	int locX, locY, locZ, locW;
	final String msg = "Go To Location...";
	String input1, input2, input3, input4;
	input1 = CommonDialogs.showTextInputDialog("Row?", msg);
	if (input1 != null) {
	    input2 = CommonDialogs.showTextInputDialog("Column?", msg);
	    if (input2 != null) {
		input3 = CommonDialogs.showTextInputDialog("Floor?", msg);
		if (input3 != null) {
		    input4 = CommonDialogs.showTextInputDialog("Level?", msg);
		    if (input4 != null) {
			try {
			    locX = Integer.parseInt(input1) - 1;
			    locY = Integer.parseInt(input2) - 1;
			    locZ = Integer.parseInt(input3) - 1;
			    locW = Integer.parseInt(input4) - 1;
			    this.updateEditorPositionAbsolute(locX, locY, locZ, locW);
			} catch (final NumberFormatException nf) {
			    CommonDialogs.showDialog(nf.getMessage());
			}
		    }
		}
	    }
	}
    }

    public void goToDestinationHandler() {
	if (!this.goToDestMode) {
	    this.goToDestMode = true;
	    Mazer5D.getBagOStuff().showMessage("Click a teleport to go to its destination");
	}
    }

    void goToDestination(final int x, final int y) {
	if (this.goToDestMode) {
	    this.goToDestMode = false;
	    final int xOffset = this.vertScroll.getValue() - this.vertScroll.getMinimum();
	    final int yOffset = this.horzScroll.getValue() - this.horzScroll.getMinimum();
	    final int locX = x / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationX() - xOffset + yOffset;
	    final int locY = y / ImageConstants.SIZE + this.evMgr.getViewingWindowLocationY() + xOffset - yOffset;
	    final int locZ = this.elMgr.getEditorLocationZ();
	    final MazeObject there = Mazer5D.getBagOStuff().getMazeManager().getMazeObject(locX, locY, locZ,
		    Layers.OBJECT);
	    if (there instanceof GenericTeleport) {
		final GenericTeleport gt = (GenericTeleport) there;
		final int destX = gt.getDestinationRow();
		final int destY = gt.getDestinationColumn();
		final int destZ = gt.getDestinationFloor();
		final int destW = this.elMgr.getEditorLocationW();
		this.updateEditorPositionAbsolute(destX, destY, destZ, destW);
		Mazer5D.getBagOStuff().showMessage(StaticStrings.EMPTY);
		this.redrawVirtual(destX, destY, MazeEditor.DEST);
	    } else {
		Mazer5D.getBagOStuff().showMessage("This object does not have a destination.");
	    }
	}
    }

    public void showOutput() {
	this.checkFlags();
	this.outputFrame.attachAndSave(this.borderPane);
	this.outputFrame.setTitle("Editor");
    }

    public void hideOutput() {
	this.outputFrame.restoreSaved();
    }

    void disableOutput() {
	this.outputPane.setEnabled(false);
    }

    void enableOutput() {
	this.outputPane.setEnabled(true);
	this.checkFlags();
    }

    public void exitEditor() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	// Hide the editor
	this.hideOutput();
	final MazeManager mm = app.getMazeManager();
	final GameManager gm = app.getGameManager();
	// Save the entire level
	mm.getMaze().save();
	// Reset the viewing window
	gm.resetViewingWindowAndPlayerLocation();
	gm.stateChanged();
	Mazer5D.getBagOStuff().getGUIManager().showGUI();
    }

    private void setUpGUI() {
	// Create content containers
	this.outputFrame = MainWindow.getMainWindow();
	this.borderPane = this.outputFrame.createContent();
	this.outputPane = new JPanel();
	this.secondaryPane = new JPanel();
	this.commandPane = new JPanel();
	this.treasurePane = this.outputFrame.createContent();
	// Configure content containers
	this.borderPane.setLayout(new BorderLayout());
	this.gridbag = new GridBagLayout();
	this.c = new GridBagConstraints();
	this.outputPane.setLayout(this.gridbag);
	this.secondaryPane
		.setLayout(new GridLayout(this.evMgr.getViewingWindowSizeX(), this.evMgr.getViewingWindowSizeY()));
	this.commandPane.setLayout(new BoxLayout(this.commandPane, BoxLayout.PAGE_AXIS));
	this.treasurePane.setLayout(new BorderLayout());
	// Create components
	this.messageLabel = new JLabel(" ");
	this.horzScroll = new JScrollBar(Adjustable.HORIZONTAL, this.evMgr.getMinimumViewingWindowLocationY(),
		this.evMgr.getViewingWindowSizeY(), this.evMgr.getMinimumViewingWindowLocationY(),
		this.evMgr.getMaximumViewingWindowLocationY());
	this.vertScroll = new JScrollBar(Adjustable.VERTICAL, this.evMgr.getMinimumViewingWindowLocationX(),
		this.evMgr.getViewingWindowSizeX(), this.evMgr.getMinimumViewingWindowLocationX(),
		this.evMgr.getMaximumViewingWindowLocationX());
	// Attach component event handlers
	this.horzScroll.addAdjustmentListener(this.mhandler);
	this.vertScroll.addAdjustmentListener(this.mhandler);
	this.secondaryPane.addMouseListener(this.mhandler);
	// Create command buttons
	this.editorUndo = new JButton("Undo");
	this.editorRedo = new JButton("Redo");
	this.editorCutLevel = new JButton("Cut Level");
	this.editorCopyLevel = new JButton("Copy Level");
	this.editorPasteLevel = new JButton("Paste Level");
	this.editorInsertLevelFromClipboard = new JButton("Insert Level From Clipboard");
	this.editorClearHistory = new JButton("Clear History");
	this.editorGoToLocation = new JButton("Go To Location...");
	this.editorGoToDestination = new JButton("Go To Destination...");
	this.editorUpOneFloor = new JButton("Up One Floor");
	this.editorDownOneFloor = new JButton("Down One Floor");
	this.editorUpOneLevel = new JButton("Up One Level");
	this.editorDownOneLevel = new JButton("Down One Level");
	this.editorAddLevel = new JButton("Add a Level...");
	this.editorRemoveLevel = new JButton("Remove a Level...");
	this.editorResizeLevel = new JButton("Resize Current Level...");
	this.editorFillFloor = new JButton("Fill Current Floor");
	this.editorFillLevel = new JButton("Fill Current Level");
	this.editorFillFloorRandomly = new JButton("Fill Current Floor Randomly");
	this.editorFillLevelRandomly = new JButton("Fill Current Level Randomly");
	this.editorFillRuleSets = new JButton("Fill Rule Sets...");
	this.editorFillUseRuleSets = new JToggleButton("Use Fill Rule Sets");
	this.editorToggleLayer = new JButton("Toggle Layer");
	this.editorLevelPreferences = new JButton("Level Preferences...");
	this.editorMazePreferences = new JButton("Maze Preferences...");
	this.editorSetStartPoint = new JButton("Set Start Point...");
	this.editorSetFirstMovingFinish = new JButton("Set First Moving Finish...");
	this.editorExit = new JButton("Exit Editor");
	// Attach command event handlers
	this.editorUndo.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.undo();
		}
	    }.start();
	});
	this.editorRedo.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.redo();
		}
	    }.start();
	});
	this.editorCutLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.cutLevel();
		}
	    }.start();
	});
	this.editorCopyLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.copyLevel();
		}
	    }.start();
	});
	this.editorPasteLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.pasteLevel();
		}
	    }.start();
	});
	this.editorInsertLevelFromClipboard.addActionListener(h -> this.insertLevelFromClipboard());
	this.editorClearHistory.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.clearHistory();
		}
	    }.start();
	});
	this.editorGoToLocation.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.goToLocationHandler();
		}
	    }.start();
	});
	this.editorGoToDestination.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.goToDestinationHandler();
		}
	    }.start();
	});
	this.editorUpOneFloor.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.updateEditorPosition(0, 0, 1, 0);
		}
	    }.start();
	});
	this.editorDownOneFloor.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.updateEditorPosition(0, 0, -1, 0);
		}
	    }.start();
	});
	this.editorUpOneLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.updateEditorPosition(0, 0, 0, 1);
		}
	    }.start();
	});
	this.editorDownOneLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.updateEditorPosition(0, 0, 0, -1);
		}
	    }.start();
	});
	this.editorAddLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.addLevel();
		}
	    }.start();
	});
	this.editorRemoveLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.removeLevel();
		}
	    }.start();
	});
	this.editorResizeLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.resizeLevel();
		}
	    }.start();
	});
	this.editorFillFloor.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.fillFloor();
		}
	    }.start();
	});
	this.editorFillLevel.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.fillLevel();
		}
	    }.start();
	});
	this.editorFillFloorRandomly.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.fillFloorRandomly();
		}
	    }.start();
	});
	this.editorFillLevelRandomly.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.fillLevelRandomly();
		}
	    }.start();
	});
	this.editorFillRuleSets.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    Mazer5D.getBagOStuff().getRuleSetPicker().editRuleSets();
		}
	    }.start();
	});
	this.editorToggleLayer.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.toggleLayer();
		}
	    }.start();
	});
	this.editorLevelPreferences.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.setLevelPrefs();
		}
	    }.start();
	});
	this.editorMazePreferences.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.setMazePrefs();
		}
	    }.start();
	});
	this.editorSetStartPoint.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.editPlayerLocation();
		}
	    }.start();
	});
	this.editorSetFirstMovingFinish.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.editTeleportDestination(MazeEditor.TELEPORT_TYPE_FIRST_MOVING_FINISH);
		}
	    }.start();
	});
	this.editorExit.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    MazeEditor.this.doneEditing();
		}
	    }.start();
	});
	// Set initial command state
	this.editorUndo.setEnabled(false);
	this.editorRedo.setEnabled(false);
	this.editorCutLevel.setEnabled(true);
	this.editorCopyLevel.setEnabled(true);
	this.editorPasteLevel.setEnabled(true);
	this.editorInsertLevelFromClipboard.setEnabled(true);
	this.editorClearHistory.setEnabled(false);
	this.editorGoToLocation.setEnabled(true);
	this.editorGoToDestination.setEnabled(true);
	this.editorUpOneFloor.setEnabled(false);
	this.editorDownOneFloor.setEnabled(false);
	this.editorUpOneLevel.setEnabled(false);
	this.editorDownOneLevel.setEnabled(false);
	this.editorAddLevel.setEnabled(false);
	this.editorRemoveLevel.setEnabled(false);
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
	this.editorExit.setEnabled(true);
	// Assemble everything together
	this.commandPane.add(this.editorUndo);
	this.commandPane.add(this.editorRedo);
	this.commandPane.add(this.editorCutLevel);
	this.commandPane.add(this.editorCopyLevel);
	this.commandPane.add(this.editorPasteLevel);
	this.commandPane.add(this.editorInsertLevelFromClipboard);
	this.commandPane.add(this.editorClearHistory);
	this.commandPane.add(this.editorGoToLocation);
	this.commandPane.add(this.editorGoToDestination);
	this.commandPane.add(this.editorUpOneFloor);
	this.commandPane.add(this.editorDownOneFloor);
	this.commandPane.add(this.editorUpOneLevel);
	this.commandPane.add(this.editorDownOneLevel);
	this.commandPane.add(this.editorAddLevel);
	this.commandPane.add(this.editorRemoveLevel);
	this.commandPane.add(this.editorResizeLevel);
	this.commandPane.add(this.editorFillFloor);
	this.commandPane.add(this.editorFillLevel);
	this.commandPane.add(this.editorFillFloorRandomly);
	this.commandPane.add(this.editorFillLevelRandomly);
	this.commandPane.add(this.editorFillRuleSets);
	this.commandPane.add(this.editorFillUseRuleSets);
	this.commandPane.add(this.editorToggleLayer);
	this.commandPane.add(this.editorLevelPreferences);
	this.commandPane.add(this.editorMazePreferences);
	this.commandPane.add(this.editorSetStartPoint);
	this.commandPane.add(this.editorSetFirstMovingFinish);
	this.commandPane.add(this.editorExit);
	this.drawGrid = new JLabel[this.evMgr.getViewingWindowSizeX()][this.evMgr.getViewingWindowSizeY()];
	for (int x = 0; x < this.evMgr.getViewingWindowSizeX(); x++) {
	    for (int y = 0; y < this.evMgr.getViewingWindowSizeY(); y++) {
		this.drawGrid[x][y] = new JLabel();
		// Mac OS X-specific fix to make draw grid line up properly
		if (System.getProperty("os.name").startsWith("Mac OS X")) {
		    this.drawGrid[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		this.secondaryPane.add(this.drawGrid[x][y]);
	    }
	}
	this.c.fill = GridBagConstraints.BOTH;
	this.c.gridx = 0;
	this.c.gridy = 0;
	this.gridbag.setConstraints(this.secondaryPane, this.c);
	this.outputPane.add(this.secondaryPane);
	this.c.gridx = 1;
	this.c.gridy = 0;
	this.c.gridwidth = GridBagConstraints.REMAINDER;
	this.gridbag.setConstraints(this.vertScroll, this.c);
	this.outputPane.add(this.vertScroll);
	this.c.gridx = 0;
	this.c.gridy = 1;
	this.c.gridwidth = 1;
	this.c.gridheight = GridBagConstraints.REMAINDER;
	this.gridbag.setConstraints(this.horzScroll, this.c);
	this.outputPane.add(this.horzScroll);
	this.updatePicker();
	this.borderPane.add(this.outputPane, BorderLayout.CENTER);
	this.borderPane.add(this.messageLabel, BorderLayout.NORTH);
	this.borderPane.add(this.picker.getPicker(), BorderLayout.WEST);
	this.borderPane.add(this.commandPane, BorderLayout.EAST);
	this.treasurePane.add(this.treasurePicker.getPicker(), BorderLayout.CENTER);
	this.treasurePane.add(this.pick, BorderLayout.SOUTH);
    }

    public void undo() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	this.engine.undo();
	final MazeObject obj = this.engine.getObject();
	final int x = this.engine.getX();
	final int y = this.engine.getY();
	final int z = this.engine.getZ();
	final int w = this.engine.getW();
	final int e = this.engine.getE();
	this.elMgr.setEditorLocationX(x);
	this.elMgr.setEditorLocationY(y);
	this.elMgr.setCameFromZ(z);
	if (x != -1 && y != -1 && z != -1 && w != -1) {
	    final MazeObject oldObj = app.getMazeManager().getMazeObject(x, y, z, e);
	    if (!obj.getName().equals(GameObjects.createObject(MazeObjects.STAIRS_UP).getName())
		    && !obj.getName().equals(GameObjects.createObject(MazeObjects.STAIRS_DOWN).getName())) {
		if (obj.getName().equals(GameObjects.createObject(MazeObjects.TWO_WAY_TELEPORT).getName())) {
		    app.getMazeManager().getMaze().setCell(obj, x, y, z, e);
		    this.reverseCheckTwoWayTeleportPair(z);
		    this.checkStairPair(z);
		} else {
		    this.checkTwoWayTeleportPair(z);
		    app.getMazeManager().getMaze().setCell(obj, x, y, z, e);
		    this.checkStairPair(z);
		}
	    } else {
		app.getMazeManager().getMaze().setCell(obj, x, y, z, e);
		this.reverseCheckStairPair(z);
	    }
	    this.updateRedoHistory(oldObj, x, y, z, w, e);
	    this.checkFlags();
	    this.redrawEditor();
	} else {
	    Mazer5D.getBagOStuff().showMessage("Nothing to undo");
	}
    }

    public void redo() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	this.engine.redo();
	final MazeObject obj = this.engine.getObject();
	final int x = this.engine.getX();
	final int y = this.engine.getY();
	final int z = this.engine.getZ();
	final int w = this.engine.getW();
	final int e = this.engine.getE();
	this.elMgr.setEditorLocationX(x);
	this.elMgr.setEditorLocationY(y);
	this.elMgr.setCameFromZ(z);
	if (x != -1 && y != -1 && z != -1 && w != -1) {
	    final MazeObject oldObj = app.getMazeManager().getMazeObject(x, y, z, e);
	    if (!obj.getName().equals(GameObjects.createObject(MazeObjects.STAIRS_UP).getName())
		    && !obj.getName().equals(GameObjects.createObject(MazeObjects.STAIRS_DOWN).getName())) {
		if (obj.getName().equals(GameObjects.createObject(MazeObjects.TWO_WAY_TELEPORT).getName())) {
		    app.getMazeManager().getMaze().setCell(obj, x, y, z, e);
		    this.reverseCheckTwoWayTeleportPair(z);
		    this.checkStairPair(z);
		} else {
		    this.checkTwoWayTeleportPair(z);
		    app.getMazeManager().getMaze().setCell(obj, x, y, z, e);
		    this.checkStairPair(z);
		}
	    } else {
		app.getMazeManager().getMaze().setCell(obj, x, y, z, e);
		this.reverseCheckStairPair(z);
	    }
	    this.updateUndoHistory(oldObj, x, y, z, w, e);
	    this.checkFlags();
	    this.redrawEditor();
	} else {
	    Mazer5D.getBagOStuff().showMessage("Nothing to redo");
	}
    }

    public void clearHistory() {
	final int res = CommonDialogs.showConfirmDialog("Are you sure you want to clear the history?", "Editor");
	if (res == CommonDialogs.YES_OPTION) {
	    this.engine = new UndoRedoEngine();
	    this.checkFlags();
	}
    }

    private void updateUndoHistory(final MazeObject obj, final int x, final int y, final int z, final int w,
	    final int e) {
	this.engine.updateUndoHistory(obj, x, y, z, w, e);
    }

    private void updateRedoHistory(final MazeObject obj, final int x, final int y, final int z, final int w,
	    final int e) {
	this.engine.updateRedoHistory(obj, x, y, z, w, e);
    }

    public void updatePicker() {
	if (this.elMgr != null) {
	    BufferedImageIcon[] newImages = null;
	    String[] newNames = null;
	    if (this.elMgr.getEditorLocationE() == Layers.GROUND) {
		newImages = this.groundEditorAppearances;
		newNames = this.groundNames;
	    } else {
		newImages = this.objectEditorAppearances;
		newNames = this.objectNames;
	    }
	    if (this.picker != null) {
		this.picker.updatePicker(newImages, newNames);
	    } else {
		this.picker = new PicturePicker(newImages, newNames);
		this.picker.changePickerColor(new Color(223, 223, 223));
	    }
	    this.picker.updatePickerLayout(this.outputPane.getHeight());
	}
    }

    public void doneEditing() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	boolean success = false;
	int status = CommonDialogs.DEFAULT_OPTION;
	if (app.getMazeManager().getDirty()) {
	    status = app.getMazeManager().showSaveDialog();
	    if (status == CommonDialogs.YES_OPTION) {
		app.getMazeManager().saveMaze();
		success = !app.getMazeManager().getDirty();
		if (success) {
		    this.exitEditor();
		}
	    } else if (status == CommonDialogs.NO_OPTION) {
		app.getMazeManager().setDirty(false);
		this.exitEditor();
	    }
	} else {
	    this.exitEditor();
	}
    }

    private class EventHandler implements AdjustmentListener, MouseListener {
	public EventHandler() {
	    // Do nothing
	}

	// handle scroll bars
	@Override
	public void adjustmentValueChanged(final AdjustmentEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final MazeEditor me = MazeEditor.this;
		    final Adjustable src = e.getAdjustable();
		    final int dir = src.getOrientation();
		    final int value = src.getValue();
		    int relValue = 0;
		    switch (dir) {
		    case Adjustable.HORIZONTAL:
			relValue = value - me.evMgr.getViewingWindowLocationY();
			me.updateEditorPosition(0, relValue, 0, 0);
			break;
		    case Adjustable.VERTICAL:
			relValue = value - me.evMgr.getViewingWindowLocationX();
			me.updateEditorPosition(relValue, 0, 0, 0);
			break;
		    default:
			break;
		    }
		}
	    }.start();
	}

	// handle mouse
	@Override
	public void mousePressed(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final MazeEditor me = MazeEditor.this;
		    final int x = e.getX();
		    final int y = e.getY();
		    if (e.isAltDown()) {
			if (!me.goToDestMode) {
			    me.editObjectProperties(x, y);
			}
		    } else if (e.isShiftDown()) {
			me.probeObjectProperties(x, y);
		    } else {
			if (me.goToDestMode) {
			    me.goToDestination(x, y);
			} else {
			    me.editObject(x, y);
			}
		    }
		}
	    }.start();
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	    // Do nothing
	}
    }

    private class TreasureEventHandler implements ActionListener {
	public TreasureEventHandler() {
	    // Do nothing
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
	    new Thread() {
		@Override
		public void run() {
		    new Thread() {
			@Override
			public void run() {
			    MazeEditor.this.setTreasureChestContents();
			}
		    }.start();
		}
	    }.start();
	}
    }

    private class StartEventHandler implements AdjustmentListener, MouseListener {
	public StartEventHandler() {
	    // Do nothing
	}

	// handle scroll bars
	@Override
	public void adjustmentValueChanged(final AdjustmentEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final MazeEditor me = MazeEditor.this;
		    final Adjustable src = e.getAdjustable();
		    final int dir = src.getOrientation();
		    final int value = src.getValue();
		    int relValue = 0;
		    switch (dir) {
		    case Adjustable.HORIZONTAL:
			relValue = value - me.evMgr.getViewingWindowLocationY();
			me.updateEditorPosition(0, relValue, 0, 0);
			break;
		    case Adjustable.VERTICAL:
			relValue = value - me.evMgr.getViewingWindowLocationX();
			me.updateEditorPosition(relValue, 0, 0, 0);
			break;
		    default:
			break;
		    }
		}
	    }.start();
	}

	// handle mouse
	@Override
	public void mousePressed(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final int x = e.getX();
		    final int y = e.getY();
		    MazeEditor.this.setPlayerLocation(x, y);
		}
	    }.start();
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	    // Do nothing
	}
    }

    private class TeleportEventHandler implements AdjustmentListener, MouseListener {
	public TeleportEventHandler() {
	    // Do nothing
	}

	// handle scroll bars
	@Override
	public void adjustmentValueChanged(final AdjustmentEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final MazeEditor me = MazeEditor.this;
		    final Adjustable src = e.getAdjustable();
		    final int dir = src.getOrientation();
		    final int value = src.getValue();
		    int relValue = 0;
		    switch (dir) {
		    case Adjustable.HORIZONTAL:
			relValue = value - me.evMgr.getViewingWindowLocationY();
			me.updateEditorPosition(0, relValue, 0, 0);
			break;
		    case Adjustable.VERTICAL:
			relValue = value - me.evMgr.getViewingWindowLocationX();
			me.updateEditorPosition(relValue, 0, 0, 0);
			break;
		    default:
			break;
		    }
		}
	    }.start();
	}

	// handle mouse
	@Override
	public void mousePressed(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final int x = e.getX();
		    final int y = e.getY();
		    MazeEditor.this.setTeleportDestination(x, y);
		}
	    }.start();
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	    // Do nothing
	}
    }

    private class ConditionalTeleportEventHandler implements AdjustmentListener, MouseListener {
	public ConditionalTeleportEventHandler() {
	    // Do nothing
	}

	// handle scroll bars
	@Override
	public void adjustmentValueChanged(final AdjustmentEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final MazeEditor me = MazeEditor.this;
		    final Adjustable src = e.getAdjustable();
		    final int dir = src.getOrientation();
		    final int value = src.getValue();
		    int relValue = 0;
		    switch (dir) {
		    case Adjustable.HORIZONTAL:
			relValue = value - me.evMgr.getViewingWindowLocationY();
			me.updateEditorPosition(0, relValue, 0, 0);
			break;
		    case Adjustable.VERTICAL:
			relValue = value - me.evMgr.getViewingWindowLocationX();
			me.updateEditorPosition(relValue, 0, 0, 0);
			break;
		    default:
			break;
		    }
		}
	    }.start();
	}

	// handle mouse
	@Override
	public void mousePressed(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final int x = e.getX();
		    final int y = e.getY();
		    MazeEditor.this.setConditionalTeleportDestination(x, y);
		}
	    }.start();
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	    // Do nothing
	}
    }

    private class MetalButtonEventHandler implements AdjustmentListener, MouseListener {
	public MetalButtonEventHandler() {
	    // Do nothing
	}

	// handle scroll bars
	@Override
	public void adjustmentValueChanged(final AdjustmentEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final MazeEditor me = MazeEditor.this;
		    final Adjustable src = e.getAdjustable();
		    final int dir = src.getOrientation();
		    final int value = src.getValue();
		    int relValue = 0;
		    switch (dir) {
		    case Adjustable.HORIZONTAL:
			relValue = value - me.evMgr.getViewingWindowLocationY();
			me.updateEditorPosition(0, relValue, 0, 0);
			break;
		    case Adjustable.VERTICAL:
			relValue = value - me.evMgr.getViewingWindowLocationX();
			me.updateEditorPosition(relValue, 0, 0, 0);
			break;
		    default:
			break;
		    }
		}
	    }.start();
	}

	// handle mouse
	@Override
	public void mousePressed(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final int x = e.getX();
		    final int y = e.getY();
		    MazeEditor.this.setMetalButtonTarget(x, y);
		}
	    }.start();
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	    // Do nothing
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	    // Do nothing
	}
    }
}
