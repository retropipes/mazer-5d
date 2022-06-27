/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.diane.gui.dialog.MainWindow;
import com.puttysoftware.diane.gui.dialog.MainWindowContent;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.asset.MusicGroup;
import com.puttysoftware.mazer5d.asset.MusicIndex;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.file.MazeManager;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.ImageConstants;
import com.puttysoftware.mazer5d.loader.MusicPlayer;
import com.puttysoftware.mazer5d.loader.ObjectImageLoader;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.maze.Maze;
import com.puttysoftware.mazer5d.maze.effect.MazeEffectConstants;
import com.puttysoftware.mazer5d.maze.effect.MazeEffectManager;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericBow;
import com.puttysoftware.mazer5d.objects.abc.GenericCharacter;
import com.puttysoftware.mazer5d.objects.abc.GenericMovableObject;
import com.puttysoftware.mazer5d.prefs.Prefs;
import com.puttysoftware.mazer5d.utility.ArrowTypes;
import com.puttysoftware.mazer5d.utility.Layers;
import com.puttysoftware.mazer5d.utility.MazeObjects;
import com.puttysoftware.mazer5d.utility.TypeConstants;

public class GameManager implements MazeEffectConstants {
    // Fields
    private MainWindow outputFrame;
    private MainWindowContent borderPane;
    private JPanel outputPane, progressPane, summaryPane, commandPane;
    private JLabel messageLabel;
    private JProgressBar autoFinishProgress, alternateAutoFinishProgress;
    private MazeObject savedMazeObject, objectBeingUsed;
    private GenericBow activeBow;
    private EventHandler handler;
    private ObjectInventory objectInv, savedObjectInv;
    private JButton showObjectInventory, useObject, switchBow, reset, showScore, showTable, endGame;
    private boolean pullInProgress;
    private boolean using;
    private int lastUsedObjectIndex;
    private int lastUsedBowIndex;
    private boolean knm;
    private boolean savedGameFlag;
    private int activeArrowType;
    private int poisonCounter;
    private final PlayerLocationManager plMgr;
    private final GameViewingWindowManager vwMgr;
    private final ScoreTracker st;
    private final StatGUI sg;
    private final MazeEffectManager em;
    private JLabel[][] drawGrid;
    private boolean delayedDecayActive;
    private MazeObject delayedDecayObject;
    private boolean actingRemotely;
    boolean arrowActive;
    boolean teleporting;
    private int[] remoteCoords;
    private boolean autoFinishEnabled;
    private int autoFinishThreshold;
    private int alternateAutoFinishThreshold;
    private boolean stateChanged;
    private boolean trueSightFlag;
    private static final MazeObject EMPTY = GameObjects.getEmptySpace();

    // Constructors
    public GameManager() {
	this.plMgr = new PlayerLocationManager();
	this.vwMgr = new GameViewingWindowManager();
	this.st = new ScoreTracker();
	this.em = new MazeEffectManager();
	this.sg = new StatGUI();
	this.setUpGUI();
	this.setPullInProgress(false);
	this.setUsingAnItem(false);
	this.savedMazeObject = GameManager.EMPTY;
	this.lastUsedObjectIndex = 0;
	this.lastUsedBowIndex = GameObjects.getAllBowNames().length - 1;
	this.knm = false;
	this.savedGameFlag = false;
	this.delayedDecayActive = false;
	this.delayedDecayObject = null;
	this.actingRemotely = false;
	this.remoteCoords = new int[3];
	this.arrowActive = false;
	this.teleporting = false;
	this.activeArrowType = ArrowTypes.PLAIN;
	this.stateChanged = true;
	this.poisonCounter = 0;
	this.activeBow = (GenericBow) GameObjects.createObject(MazeObjects.BOW);
	this.autoFinishEnabled = false;
	this.autoFinishThreshold = 0;
	this.alternateAutoFinishThreshold = 0;
	this.trueSightFlag = false;
    }

    // Methods
    public boolean newGame() {
	return true;
    }

    private StatGUI getStatGUI() {
	return this.sg;
    }

    public void stateChanged() {
	this.stateChanged = true;
    }

    public void enableTrueSight() {
	this.trueSightFlag = true;
	this.redrawMaze();
    }

    public void disableTrueSight() {
	this.trueSightFlag = false;
	this.redrawMaze();
    }

    public PlayerLocationManager getPlayerManager() {
	return this.plMgr;
    }

    void arrowDone() {
	this.arrowActive = false;
	this.objectInv.useBow(this.activeBow);
	this.getStatGUI().updateStats();
	this.checkGameOver();
    }

    public MazeObject getSavedMazeObject() {
	return this.savedMazeObject;
    }

    private void saveSavedMazeObject() {
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final int pz = this.plMgr.getPlayerLocationZ();
	final GenericCharacter player = (GenericCharacter) Mazer5D.getBagOStuff().getMazeManager().getMaze().getCell(px,
		py, pz, Layers.OBJECT);
	player.setSavedObject(this.savedMazeObject);
    }

    private void restoreSavedMazeObject() {
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final int pz = this.plMgr.getPlayerLocationZ();
	final GenericCharacter player = (GenericCharacter) Mazer5D.getBagOStuff().getMazeManager().getMaze().getCell(px,
		py, pz, Layers.OBJECT);
	this.savedMazeObject = player.getSavedObject();
    }

    public boolean isFloorBelow() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
		    this.plMgr.getPlayerLocationZ() - 1, Layers.OBJECT);
	    return true;
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    return false;
	} catch (final NullPointerException np) {
	    return false;
	}
    }

    public boolean isFloorAbove() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
		    this.plMgr.getPlayerLocationZ() + 1, Layers.OBJECT);
	    return true;
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    return false;
	} catch (final NullPointerException np) {
	    return false;
	}
    }

    public boolean doesFloorExist(final int floor) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(), floor, Layers.OBJECT);
	    return true;
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    return false;
	} catch (final NullPointerException np) {
	    return false;
	}
    }

    public void resetObjectInventory() {
	this.objectInv = new ObjectInventory();
    }

    public void setSavedGameFlag(final boolean value) {
	this.savedGameFlag = value;
    }

    private void saveObjectInventory() {
	this.savedObjectInv = this.objectInv.clone();
    }

    private void restoreObjectInventory() {
	if (this.savedObjectInv != null) {
	    this.objectInv = this.savedObjectInv.clone();
	} else {
	    this.objectInv = new ObjectInventory();
	}
    }

    public boolean usingAnItem() {
	return this.using;
    }

    public boolean isTeleporting() {
	return this.teleporting;
    }

    public void setUsingAnItem(final boolean isUsing) {
	this.using = isUsing;
    }

    public void setPullInProgress(final boolean pulling) {
	this.pullInProgress = pulling;
    }

    public boolean isPullInProgress() {
	return this.pullInProgress;
    }

    public void setStatusMessage(final String msg) {
	this.messageLabel.setText(msg);
    }

    public boolean isEffectActive(final int effectID) {
	return this.em.isEffectActive(effectID);
    }

    private void decayEffects() {
	this.em.decayEffects();
    }

    public void activateEffect(final int effectID, final int duration) {
	this.em.activateEffect(effectID, duration);
    }

    public void deactivateEffect(final int effectID) {
	this.em.deactivateEffect(effectID);
    }

    private void deactivateAllEffects() {
	this.em.deactivateAllEffects();
    }

    int[] doEffects(final int x, final int y) {
	return this.em.doEffects(x, y);
    }

    public void setRemoteAction(final int x, final int y, final int z) {
	this.remoteCoords = new int[] { x, y, z };
	this.actingRemotely = true;
    }

    public void doRemoteAction(final int x, final int y, final int z) {
	this.setRemoteAction(x, y, z);
	final MazeObject acted = Mazer5D.getBagOStuff().getMazeManager().getMazeObject(x, y, z, Layers.OBJECT);
	acted.preMoveAction(false, x, y, this.objectInv);
	acted.postMoveAction(false, x, y, this.objectInv);
	if (acted.doesChainReact()) {
	    acted.chainReactionAction(x, y, z);
	}
    }

    public void doClockwiseRotate(final int r) {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	boolean b = false;
	if (this.actingRemotely) {
	    b = m.rotateRadiusClockwise(this.remoteCoords[0], this.remoteCoords[1], this.remoteCoords[2], r);
	} else {
	    b = m.rotateRadiusClockwise(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
		    this.plMgr.getPlayerLocationZ(), r);
	}
	if (b) {
	    this.findPlayerAndAdjust();
	} else {
	    this.keepNextMessage();
	    Mazer5D.getBagOStuff().showMessage("Rotation failed!");
	}
    }

    public void findPlayerAndAdjust() {
	// Find the player, adjust player location
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	final int w = this.plMgr.getPlayerLocationW();
	m.findPlayer();
	this.plMgr.setPlayerLocation(m.getFindResultColumn(), m.getFindResultRow(), m.getFindResultFloor(), w);
	this.resetViewingWindow();
	this.redrawMaze();
    }

    private void fireStepActions() {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	if (m.getPoisonPower() > 0) {
	    this.poisonCounter++;
	    if (this.poisonCounter >= m.getPoisonPower()) {
		// Poison
		this.poisonCounter = 0;
		m.doPoisonDamage();
	    }
	}
	this.objectInv.fireStepActions();
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final int pz = this.plMgr.getPlayerLocationZ();
	m.updateVisibleSquares(px, py, pz);
    }

    public void doCounterclockwiseRotate(final int r) {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	boolean b = false;
	if (this.actingRemotely) {
	    b = m.rotateRadiusCounterclockwise(this.remoteCoords[0], this.remoteCoords[1], this.remoteCoords[2], r);
	} else {
	    b = m.rotateRadiusCounterclockwise(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
		    this.plMgr.getPlayerLocationZ(), r);
	}
	if (b) {
	    this.findPlayerAndAdjust();
	} else {
	    this.keepNextMessage();
	    Mazer5D.getBagOStuff().showMessage("Rotation failed!");
	}
    }

    public void fireArrow(final int x, final int y) {
	if (this.objectInv.getUses(this.activeBow) == 0) {
	    Mazer5D.getBagOStuff().showMessage("You're out of arrows!");
	} else {
	    final ArrowTask at = new ArrowTask(x, y, this.activeArrowType);
	    this.arrowActive = true;
	    at.start();
	}
    }

    public void updatePositionRelative(final int dirX, final int dirY) {
	this.actingRemotely = false;
	boolean redrawsSuspended = false;
	int px = this.plMgr.getPlayerLocationX();
	int py = this.plMgr.getPlayerLocationY();
	final int pz = this.plMgr.getPlayerLocationZ();
	final int[] mod = this.doEffects(dirX, dirY);
	final int fX = mod[0];
	final int fY = mod[1];
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	m.tickTimers(pz);
	boolean proceed = false;
	MazeObject o = GameManager.EMPTY;
	MazeObject acted = GameManager.EMPTY;
	MazeObject groundInto = GameManager.EMPTY;
	MazeObject below = null;
	MazeObject previousBelow = null;
	MazeObject nextBelow = null;
	MazeObject nextAbove = null;
	MazeObject nextNextBelow = null;
	MazeObject nextNextAbove = null;
	final boolean isXNonZero = fX != 0;
	final boolean isYNonZero = fY != 0;
	int pullX = 0, pullY = 0, pushX = 0, pushY = 0;
	if (isXNonZero) {
	    final int signX = (int) Math.signum(fX);
	    pushX = (Math.abs(fX) + 1) * signX;
	    pullX = (Math.abs(fX) - 1) * signX;
	}
	if (isYNonZero) {
	    final int signY = (int) Math.signum(fY);
	    pushY = (Math.abs(fY) + 1) * signY;
	    pullY = (Math.abs(fY) - 1) * signY;
	}
	do {
	    try {
		try {
		    o = m.getCell(px + fX, py + fY, pz, Layers.OBJECT);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    o = GameManager.EMPTY;
		}
		try {
		    below = m.getCell(px, py, pz, Layers.GROUND);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    below = GameManager.EMPTY;
		}
		try {
		    nextBelow = m.getCell(px + fX, py + fY, pz, Layers.GROUND);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    nextBelow = GameManager.EMPTY;
		}
		try {
		    nextAbove = m.getCell(px + fX, py + fY, pz, Layers.OBJECT);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    nextAbove = GameObjects.createObject(MazeObjects.WALL);
		}
		try {
		    previousBelow = m.getCell(px - fX, py - fY, pz, Layers.GROUND);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    previousBelow = GameManager.EMPTY;
		}
		try {
		    nextNextBelow = m.getCell(px + 2 * fX, py + 2 * fY, pz, Layers.GROUND);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    nextNextBelow = GameManager.EMPTY;
		}
		try {
		    nextNextAbove = m.getCell(px + 2 * fX, py + 2 * fY, pz, Layers.OBJECT);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    nextNextAbove = GameObjects.createObject(MazeObjects.WALL);
		}
		try {
		    proceed = o.preMoveAction(true, px + fX, py + fY, this.objectInv);
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    proceed = true;
		} catch (final InfiniteRecursionException ir) {
		    proceed = false;
		}
	    } catch (final NullPointerException np) {
		proceed = false;
		o = GameManager.EMPTY;
	    }
	    if (proceed) {
		this.plMgr.savePlayerLocation();
		this.vwMgr.saveViewingWindow();
		try {
		    if (this.checkSolid(fX + px, fY + py, this.savedMazeObject, below, nextBelow, nextAbove)) {
			if (this.delayedDecayActive) {
			    this.doDelayedDecay();
			}
			m.setCell(this.savedMazeObject, px, py, pz, Layers.OBJECT);
			acted = GameManager.EMPTY;
			try {
			    acted = m.getCell(px - fX, py - fY, pz, Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException ae) {
			    // Do nothing
			}
			if (acted.isPullable() && this.isPullInProgress()) {
			    if (!this.checkPull(fX, fY, pullX, pullY, acted, previousBelow, below,
				    this.savedMazeObject)) {
				// Pull failed - object can't move that way
				acted.pullFailedAction(this.objectInv, fX, fY, pullX, pullY);
				this.decayEffects();
				proceed = false;
			    }
			} else if (!acted.isPullable() && this.isPullInProgress()) {
			    // Pull failed - object not pullable
			    acted.pullFailedAction(this.objectInv, fX, fY, pullX, pullY);
			    this.decayEffects();
			    proceed = false;
			}
			this.plMgr.offsetPlayerLocationX(fX);
			this.plMgr.offsetPlayerLocationY(fY);
			px += fX;
			py += fY;
			this.vwMgr.offsetViewingWindowLocationX(fY);
			this.vwMgr.offsetViewingWindowLocationY(fX);
			this.savedMazeObject = m.getCell(px, py, pz, Layers.OBJECT);
			m.setCell(GameObjects.createObject(MazeObjects.PLAYER), px, py, pz, Layers.OBJECT);
			this.decayEffects();
			app.getMazeManager().setDirty(true);
			this.fireStepActions();
			if (this.objectInv.isItemThere(MazeObjects.ICE_AMULET)
				|| this.objectInv.isItemThere(MazeObjects.PASSWALL_BOOTS)) {
			    redrawsSuspended = true;
			} else {
			    this.redrawMaze();
			}
			groundInto = m.getCell(px, py, pz, Layers.GROUND);
			m.setCell(groundInto, px, py, pz, Layers.GROUND);
			if (groundInto.overridesDefaultPostMove()) {
			    groundInto.postMoveAction(false, px, py, this.objectInv);
			    if (!this.savedMazeObject.isOfType(TypeConstants.TYPE_PASS_THROUGH)) {
				this.savedMazeObject.postMoveAction(false, px, py, this.objectInv);
			    }
			} else {
			    this.savedMazeObject.postMoveAction(false, px, py, this.objectInv);
			}
		    } else {
			acted = m.getCell(px + fX, py + fY, pz, Layers.OBJECT);
			if (acted.isPushable()) {
			    if (this.checkPush(fX, fY, pushX, pushY, acted, nextBelow, nextNextBelow, nextNextAbove)) {
				if (this.delayedDecayActive) {
				    this.doDelayedDecay();
				}
				m.setCell(this.savedMazeObject, px, py, pz, Layers.OBJECT);
				this.plMgr.offsetPlayerLocationX(fX);
				this.plMgr.offsetPlayerLocationY(fY);
				px += fX;
				py += fY;
				this.vwMgr.offsetViewingWindowLocationX(fY);
				this.vwMgr.offsetViewingWindowLocationY(fX);
				this.savedMazeObject = m.getCell(px, py, pz, Layers.OBJECT);
				m.setCell(GameObjects.createObject(MazeObjects.PLAYER), px, py, pz, Layers.OBJECT);
				this.decayEffects();
				app.getMazeManager().setDirty(true);
				this.fireStepActions();
				if (this.objectInv.isItemThere(MazeObjects.ICE_AMULET)
					|| this.objectInv.isItemThere(MazeObjects.PASSWALL_BOOTS)) {
				    redrawsSuspended = true;
				} else {
				    this.redrawMaze();
				}
				groundInto = m.getCell(px, py, pz, Layers.GROUND);
				m.setCell(groundInto, px, py, pz, Layers.GROUND);
				if (groundInto.overridesDefaultPostMove()) {
				    groundInto.postMoveAction(false, px, py, this.objectInv);
				    if (!this.savedMazeObject.isOfType(TypeConstants.TYPE_PASS_THROUGH)) {
					this.savedMazeObject.postMoveAction(false, px, py, this.objectInv);
				    }
				} else {
				    this.savedMazeObject.postMoveAction(false, px, py, this.objectInv);
				}
			    } else {
				// Push failed - object can't move that way
				acted.pushFailedAction(this.objectInv, fX, fY, pushX, pushY);
				this.decayEffects();
				this.fireStepActions();
				proceed = false;
			    }
			} else if (acted.doesChainReact()) {
			    acted.chainReactionAction(px + fX, py + fY, pz);
			} else {
			    // Move failed - object is solid in that direction
			    this.fireMoveFailedActions(px + fX, py + fY, this.savedMazeObject, below, nextBelow,
				    nextAbove);
			    this.decayEffects();
			    this.fireStepActions();
			    proceed = false;
			}
		    }
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    this.vwMgr.restoreViewingWindow();
		    this.plMgr.restorePlayerLocation();
		    m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
			    this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		    // Move failed - attempted to go outside the maze
		    o.moveFailedAction(false, this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
			    this.objectInv);
		    this.decayEffects();
		    this.fireStepActions();
		    Mazer5D.getBagOStuff().showMessage(Translations.load(Strings.MOVE_FAIL_DEFAULT));
		    o = GameManager.EMPTY;
		    proceed = false;
		}
	    } else {
		// Move failed - pre-move check failed
		o.moveFailedAction(false, px + fX, py + fY, this.objectInv);
		this.decayEffects();
		this.fireStepActions();
		proceed = false;
	    }
	    if (redrawsSuspended
		    && !this.checkLoopCondition(proceed, fX, fY, groundInto, below, nextBelow, nextAbove)) {
		// Redraw post-suspend
		this.redrawMaze();
		redrawsSuspended = false;
	    }
	} while (this.checkLoopCondition(proceed, fX, fY, groundInto, below, nextBelow, nextAbove));
	this.getStatGUI().updateStats();
	this.checkGameOver();
	// Check for auto-finish
	if (this.autoFinishEnabled) {
	    // Normal auto-finish
	    final int ssCount = this.objectInv.getItemCount(MazeObjects.SUN_STONE);
	    this.autoFinishProgress.setValue(ssCount);
	    this.autoFinishProgress.setString((int) ((double) this.autoFinishProgress.getValue()
		    / (double) this.autoFinishProgress.getMaximum() * 100.0) + "%");
	    if (ssCount >= this.autoFinishThreshold) {
		// Auto-Finish
		SoundPlayer.playSound(SoundIndex.FINISH, SoundGroup.GAME);
		this.solvedLevel();
	    }
	    // Alternate auto-finish
	    final int msCount = this.objectInv.getItemCount(MazeObjects.MOON_STONE);
	    this.alternateAutoFinishProgress.setValue(msCount);
	    this.alternateAutoFinishProgress.setString((int) ((double) this.alternateAutoFinishProgress.getValue()
		    / (double) this.alternateAutoFinishProgress.getMaximum() * 100.0) + "%");
	    if (msCount >= this.alternateAutoFinishThreshold) {
		// Auto-Finish
		SoundPlayer.playSound(SoundIndex.FINISH, SoundGroup.GAME);
		this.solvedLevelAlternate();
	    }
	}
    }

    private boolean checkLoopCondition(final boolean proceed, final int x, final int y, final MazeObject groundInto,
	    final MazeObject below, final MazeObject nextBelow, final MazeObject nextAbove) {
	// Handle slippery boots and ice amulet
	if (this.objectInv.isItemThere(MazeObjects.SLIPPERY_BOOTS)
		|| this.objectInv.isItemThere(MazeObjects.ICE_AMULET)) {
	    return proceed && this.checkSolid(x, y, this.savedMazeObject, below, nextBelow, nextAbove);
	} else {
	    return proceed && !groundInto.hasFrictionConditionally(this.objectInv, false)
		    && this.checkSolid(x, y, this.savedMazeObject, below, nextBelow, nextAbove);
	}
    }

    public void backUpPlayer() {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	final int pz = this.plMgr.getPlayerLocationZ();
	this.plMgr.restorePlayerLocation();
	this.vwMgr.restoreViewingWindow();
	final int opx = this.plMgr.getPlayerLocationX();
	final int opy = this.plMgr.getPlayerLocationY();
	this.savedMazeObject = m.getCell(opx, opy, pz, Layers.OBJECT);
	m.setCell(GameObjects.createObject(MazeObjects.PLAYER), opx, opy, pz, Layers.OBJECT);
	this.redrawMaze();
    }

    private boolean checkSolid(final int x, final int y, final MazeObject inside, final MazeObject below,
	    final MazeObject nextBelow, final MazeObject nextAbove) {
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final boolean insideSolid = inside.isConditionallyDirectionallySolid(false, x - px, y - py, this.objectInv);
	final boolean belowSolid = below.isConditionallyDirectionallySolid(false, x - px, y - py, this.objectInv);
	boolean nextBelowSolid = nextBelow.isConditionallyDirectionallySolid(true, x - px, y - py, this.objectInv);
	// Handle hot boots, slippery boots, fire amulet, and ice amulet
	if (this.objectInv.isItemThere(MazeObjects.HOT_BOOTS) || this.objectInv.isItemThere(MazeObjects.SLIPPERY_BOOTS)
		|| this.objectInv.isItemThere(MazeObjects.FIRE_AMULET)
		|| this.objectInv.isItemThere(MazeObjects.ICE_AMULET)) {
	    nextBelowSolid = false;
	}
	final boolean nextAboveSolid = nextAbove.isConditionallyDirectionallySolid(true, x - px, y - py,
		this.objectInv);
	if (insideSolid || belowSolid || nextBelowSolid || nextAboveSolid) {
	    return false;
	} else {
	    return true;
	}
    }

    private boolean checkSolidAbsolute(final MazeObject inside, final MazeObject below, final MazeObject nextBelow,
	    final MazeObject nextAbove) {
	final boolean insideSolid = inside.isConditionallySolid(this.objectInv);
	final boolean belowSolid = below.isConditionallySolid(this.objectInv);
	final boolean nextBelowSolid = nextBelow.isConditionallySolid(this.objectInv);
	final boolean nextAboveSolid = nextAbove.isConditionallySolid(this.objectInv);
	if (insideSolid || belowSolid || nextBelowSolid || nextAboveSolid) {
	    return false;
	} else {
	    return true;
	}
    }

    private void fireMoveFailedActions(final int x, final int y, final MazeObject inside, final MazeObject below,
	    final MazeObject nextBelow, final MazeObject nextAbove) {
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final boolean insideSolid = inside.isConditionallyDirectionallySolid(false, x - px, y - py, this.objectInv);
	final boolean belowSolid = below.isConditionallyDirectionallySolid(false, x - px, y - py, this.objectInv);
	final boolean nextBelowSolid = nextBelow.isConditionallyDirectionallySolid(true, x - px, y - py,
		this.objectInv);
	final boolean nextAboveSolid = nextAbove.isConditionallyDirectionallySolid(true, x - px, y - py,
		this.objectInv);
	if (insideSolid) {
	    inside.moveFailedAction(false, x, y, this.objectInv);
	}
	if (belowSolid) {
	    below.moveFailedAction(false, x, y, this.objectInv);
	}
	if (nextBelowSolid) {
	    nextBelow.moveFailedAction(false, x, y, this.objectInv);
	}
	if (nextAboveSolid) {
	    nextAbove.moveFailedAction(false, x, y, this.objectInv);
	}
    }

    private boolean checkPush(final int x, final int y, final int pushX, final int pushY, final MazeObject acted,
	    final MazeObject nextBelow, final MazeObject nextNextBelow, final MazeObject nextNextAbove) {
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final int pz = this.plMgr.getPlayerLocationZ();
	final boolean nextBelowAccept = nextBelow.isPushableOut();
	final boolean nextNextBelowAccept = nextNextBelow.isPushableInto();
	final boolean nextNextAboveAccept = nextNextAbove.isPushableInto();
	if (nextBelowAccept && nextNextBelowAccept && nextNextAboveAccept) {
	    nextBelow.pushOutAction(this.objectInv, acted, px + pushX, py + pushY, pz);
	    acted.pushAction(this.objectInv, nextNextAbove, x, y, pushX, pushY);
	    nextNextAbove.pushIntoAction(this.objectInv, acted, px + pushX, py + pushY, pz);
	    nextNextBelow.pushIntoAction(this.objectInv, acted, px + pushX, py + pushY, pz);
	    return true;
	} else {
	    return false;
	}
    }

    private boolean checkPull(final int x, final int y, final int pullX, final int pullY, final MazeObject acted,
	    final MazeObject previousBelow, final MazeObject below, final MazeObject above) {
	final int px = this.plMgr.getPlayerLocationX();
	final int py = this.plMgr.getPlayerLocationY();
	final int pz = this.plMgr.getPlayerLocationZ();
	final boolean previousBelowAccept = previousBelow.isPullableOut();
	final boolean belowAccept = below.isPullableInto();
	final boolean aboveAccept = above.isPullableInto();
	if (previousBelowAccept && belowAccept && aboveAccept) {
	    previousBelow.pullOutAction(this.objectInv, acted, px - pullX, py - pullY, pz);
	    acted.pullAction(this.objectInv, above, x, y, pullX, pullY);
	    above.pullIntoAction(this.objectInv, acted, px - pullX, py - pullY, pz);
	    below.pullIntoAction(this.objectInv, acted, px - pullX, py - pullY, pz);
	    return true;
	} else {
	    return false;
	}
    }

    public void updatePushedPosition(final int x, final int y, final int pushX, final int pushY,
	    final GenericMovableObject o) {
	final int xInc = (int) Math.signum(x), yInc = (int) Math.signum(y);
	int cumPushX = pushX, cumPushY = pushY, cumX = x, cumY = y;
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeManager mm = app.getMazeManager();
	MazeObject there = mm.getMazeObject(this.plMgr.getPlayerLocationX() + cumX,
		this.plMgr.getPlayerLocationY() + cumY, this.plMgr.getPlayerLocationZ(), Layers.GROUND);
	if (there != null) {
	    do {
		this.movePushedObjectPosition(cumX, cumY, cumPushX, cumPushY, o, there);
		cumX += xInc;
		cumY += yInc;
		cumPushX += xInc;
		cumPushY += yInc;
		there = mm.getMazeObject(this.plMgr.getPlayerLocationX() + cumX, this.plMgr.getPlayerLocationY() + cumY,
			this.plMgr.getPlayerLocationZ(), Layers.GROUND);
		if (there == null) {
		    break;
		}
	    } while (!there.hasFrictionConditionally(this.objectInv, true));
	}
    }

    private void movePushedObjectPosition(final int x, final int y, final int pushX, final int pushY,
	    final GenericMovableObject o, final MazeObject g) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.setCell(o.getSavedObject(), this.plMgr.getPlayerLocationX() + x, this.plMgr.getPlayerLocationY() + y,
		    this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    m.setCell(o, this.plMgr.getPlayerLocationX() + pushX, this.plMgr.getPlayerLocationY() + pushY,
		    this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    if (g.overridesDefaultPostMove()) {
		g.postMoveAction(false, this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
			this.objectInv);
	    }
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	}
    }

    public void updatePulledPosition(final int x, final int y, final int pullX, final int pullY,
	    final GenericMovableObject o) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.setCell(o.getSavedObject(), this.plMgr.getPlayerLocationX() - x, this.plMgr.getPlayerLocationY() - y,
		    this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    m.setCell(o, this.plMgr.getPlayerLocationX() - pullX, this.plMgr.getPlayerLocationY() - pullY,
		    this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	}
    }

    public void updatePushedIntoPositionAbsolute(final int x, final int y, final int z, final int x2, final int y2,
	    final int z2, final GenericMovableObject pushedInto, final MazeObject source) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    if (!m.getCell(x, y, z, Layers.OBJECT).isConditionallySolid(this.objectInv)) {
		final MazeObject saved = m.getCell(x, y, z, Layers.OBJECT);
		m.setCell(pushedInto, x, y, z, Layers.OBJECT);
		pushedInto.setSavedObject(saved);
		m.setCell(source, x2, y2, z2, Layers.OBJECT);
		saved.pushIntoAction(this.objectInv, pushedInto, x2, y2, z2 - 1);
		this.redrawMaze();
		app.getMazeManager().setDirty(true);
	    }
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    m.setCell(GameManager.EMPTY, x2, y2, z2, Layers.OBJECT);
	}
    }

    public boolean tryUpdatePositionRelative(final int x, final int y) {
	try {
	    final BagOStuff app = Mazer5D.getBagOStuff();
	    final Maze m = app.getMazeManager().getMaze();
	    final MazeObject below = m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
		    this.plMgr.getPlayerLocationZ(), Layers.GROUND);
	    final MazeObject nextBelow = m.getCell(this.plMgr.getPlayerLocationX() + x,
		    this.plMgr.getPlayerLocationY() + y, this.plMgr.getPlayerLocationZ(), Layers.GROUND);
	    final MazeObject nextAbove = m.getCell(this.plMgr.getPlayerLocationX() + x,
		    this.plMgr.getPlayerLocationY() + y, this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    return this.checkSolid(x, y, this.savedMazeObject, below, nextBelow, nextAbove);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    return false;
	}
    }

    public boolean tryUpdatePositionAbsolute(final int x, final int y, final int z) {
	try {
	    final BagOStuff app = Mazer5D.getBagOStuff();
	    final Maze m = app.getMazeManager().getMaze();
	    final MazeObject below = m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
		    this.plMgr.getPlayerLocationZ(), Layers.GROUND);
	    final MazeObject nextBelow = m.getCell(x, y, z, Layers.GROUND);
	    final MazeObject nextAbove = m.getCell(x, y, z, Layers.OBJECT);
	    return this.checkSolidAbsolute(this.savedMazeObject, below, nextBelow, nextAbove);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    return false;
	}
    }

    public void updatePositionAbsolute(final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.getCell(x, y, z, Layers.OBJECT).preMoveAction(true, x, y, this.objectInv);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
	this.plMgr.savePlayerLocation();
	this.vwMgr.saveViewingWindow();
	try {
	    if (!m.getCell(x, y, z, Layers.OBJECT).isConditionallySolid(this.objectInv)) {
		m.setCell(this.savedMazeObject, this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
			this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		this.plMgr.setPlayerLocation(x, y, z, 0);
		this.vwMgr.setViewingWindowLocationX(this.plMgr.getPlayerLocationY() - this.vwMgr.getOffsetFactorX());
		this.vwMgr.setViewingWindowLocationY(this.plMgr.getPlayerLocationX() - this.vwMgr.getOffsetFactorY());
		this.savedMazeObject = m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
			this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
			this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		this.redrawMaze();
		app.getMazeManager().setDirty(true);
		this.savedMazeObject.postMoveAction(false, x, y, this.objectInv);
	    }
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    this.plMgr.restorePlayerLocation();
	    this.vwMgr.restoreViewingWindow();
	    m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
		    this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    Mazer5D.getBagOStuff().showMessage("Can't go outside the maze");
	} catch (final NullPointerException np) {
	    this.plMgr.restorePlayerLocation();
	    this.vwMgr.restoreViewingWindow();
	    m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
		    this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    Mazer5D.getBagOStuff().showMessage("Can't go outside the maze");
	}
    }

    public void updatePositionAbsoluteNoEvents(final int x, final int y, final int z, final int w) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	this.plMgr.savePlayerLocation();
	this.vwMgr.saveViewingWindow();
	try {
	    if (!m.getCell(x, y, z, Layers.OBJECT).isConditionallySolid(this.objectInv)) {
		m.setCell(this.savedMazeObject, this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
			this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		this.plMgr.setPlayerLocation(x, y, z, w);
		this.vwMgr.setViewingWindowLocationX(this.plMgr.getPlayerLocationY() - this.vwMgr.getOffsetFactorX());
		this.vwMgr.setViewingWindowLocationY(this.plMgr.getPlayerLocationX() - this.vwMgr.getOffsetFactorY());
		this.savedMazeObject = m.getCell(this.plMgr.getPlayerLocationX(), this.plMgr.getPlayerLocationY(),
			this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
			this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		this.redrawMaze();
		app.getMazeManager().setDirty(true);
	    }
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    this.plMgr.restorePlayerLocation();
	    this.vwMgr.restoreViewingWindow();
	    m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
		    this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    Mazer5D.getBagOStuff().showMessage("Can't go outside the maze");
	} catch (final NullPointerException np) {
	    this.plMgr.restorePlayerLocation();
	    this.vwMgr.restoreViewingWindow();
	    m.setCell(GameObjects.createObject(MazeObjects.PLAYER), this.plMgr.getPlayerLocationX(),
		    this.plMgr.getPlayerLocationY(), this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
	    Mazer5D.getBagOStuff().showMessage("Can't go outside the maze");
	}
    }

    public void showCurrentScore() {
	this.st.showCurrentScore();
    }

    public void showScoreTable() {
	this.st.showScoreTable();
    }

    public void addToScore(final long value) {
	this.st.addToScore(value);
    }

    public void redrawMaze() {
	// Draw the maze
	final EmptyBorder eb = new EmptyBorder(0, 0, 0, 0);
	this.outputPane.removeAll();
	for (int x = 0; x < this.vwMgr.getViewingWindowSizeX(); x++) {
	    for (int y = 0; y < this.vwMgr.getViewingWindowSizeY(); y++) {
		this.drawGrid[x][y] = new JLabel();
		// Fix to make draw grid line up properly
		this.drawGrid[x][y].setBorder(eb);
		this.outputPane.add(this.drawGrid[x][y]);
	    }
	}
	this.redrawMazeNoRebuild();
    }

    public void redrawMazeNoRebuild() {
	// Draw the maze
	final BagOStuff app = Mazer5D.getBagOStuff();
	int x, y, u, v;
	int xFix, yFix;
	boolean visible;
	u = this.plMgr.getPlayerLocationX();
	v = this.plMgr.getPlayerLocationY();
	for (x = this.vwMgr.getViewingWindowLocationX(); x <= this.vwMgr.getLowerRightViewingWindowLocationX(); x++) {
	    for (y = this.vwMgr.getViewingWindowLocationY(); y <= this.vwMgr
		    .getLowerRightViewingWindowLocationY(); y++) {
		xFix = x - this.vwMgr.getViewingWindowLocationX();
		yFix = y - this.vwMgr.getViewingWindowLocationY();
		visible = app.getMazeManager().getMaze().isSquareVisible(u, v, y, x);
		try {
		    if (visible) {
			MazeObject name1, name2;
			name1 = app.getMazeManager().getMaze().getCell(y, x, this.plMgr.getPlayerLocationZ(),
				Layers.GROUND);
			name2 = app.getMazeManager().getMaze().getCell(y, x, this.plMgr.getPlayerLocationZ(),
				Layers.OBJECT);
			if (this.trueSightFlag) {
			    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.getCompositeImage(name1, name2, false));
			} else {
			    this.drawGrid[xFix][yFix].setIcon(ObjectImageLoader.getCompositeImage(name1, name2, true));
			}
		    } else {
			this.drawGrid[xFix][yFix]
				.setIcon(ObjectImageLoader.load(GameObjects.createObject(MazeObjects.DARKNESS), true));
		    }
		} catch (final ArrayIndexOutOfBoundsException ae) {
		    if (this.trueSightFlag) {
			this.drawGrid[xFix][yFix]
				.setIcon(ObjectImageLoader.load(GameObjects.createObject(MazeObjects.BOUNDS), true));
		    } else {
			this.drawGrid[xFix][yFix]
				.setIcon(ObjectImageLoader.load(GameObjects.createObject(MazeObjects.BOUNDS), true));
		    }
		} catch (final NullPointerException np) {
		    if (this.trueSightFlag) {
			this.drawGrid[xFix][yFix]
				.setIcon(ObjectImageLoader.load(GameObjects.createObject(MazeObjects.BOUNDS), true));
		    } else {
			this.drawGrid[xFix][yFix]
				.setIcon(ObjectImageLoader.load(GameObjects.createObject(MazeObjects.BOUNDS), true));
		    }
		}
	    }
	}
	if (this.knm) {
	    this.knm = false;
	} else {
	    this.setStatusMessage(" ");
	}
    }

    void redrawOneSquare(final int x, final int y, final boolean useDelay, final MazeObject obj3) {
	// Draw the square
	final BagOStuff app = Mazer5D.getBagOStuff();
	int xFix, yFix;
	boolean visible;
	xFix = y - this.vwMgr.getViewingWindowLocationX();
	yFix = x - this.vwMgr.getViewingWindowLocationY();
	visible = app.getMazeManager().getMaze().isSquareVisible(this.plMgr.getPlayerLocationX(),
		this.plMgr.getPlayerLocationY(), x, y);
	try {
	    if (visible) {
		MazeObject name1, name2;
		name1 = app.getMazeManager().getMaze().getCell(x, y, this.plMgr.getPlayerLocationZ(), Layers.GROUND);
		name2 = app.getMazeManager().getMaze().getCell(x, y, this.plMgr.getPlayerLocationZ(), Layers.OBJECT);
		if (this.trueSightFlag) {
		    this.drawGrid[xFix][yFix]
			    .setIcon(ObjectImageLoader.getVirtualCompositeImage(name1, name2, obj3, false));
		} else {
		    this.drawGrid[xFix][yFix]
			    .setIcon(ObjectImageLoader.getVirtualCompositeImage(name1, name2, obj3, true));
		}
	    } else {
		this.drawGrid[xFix][yFix]
			.setIcon(ObjectImageLoader.load(GameObjects.createObject(MazeObjects.DARKNESS), true));
	    }
	    this.drawGrid[xFix][yFix].repaint();
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
	if (useDelay) {
	    // Delay, for animation purposes
	    try {
		Thread.sleep(60);
	    } catch (final InterruptedException ie) {
		// Ignore
	    }
	}
    }

    public void resetViewingWindowAndPlayerLocation() {
	this.resetPlayerLocation();
	this.resetViewingWindow();
    }

    public void resetViewingWindow() {
	this.vwMgr.setViewingWindowLocationX(this.plMgr.getPlayerLocationY() - this.vwMgr.getOffsetFactorX());
	this.vwMgr.setViewingWindowLocationY(this.plMgr.getPlayerLocationX() - this.vwMgr.getOffsetFactorY());
    }

    public void resetPlayerLocation() {
	this.resetPlayerLocation(0);
    }

    public void resetPlayerLocation(final int level) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	m.switchLevel(level);
	this.plMgr.setPlayerLocation(m.getStartColumn(), m.getStartRow(), m.getStartFloor(), level);
    }

    public void resetCurrentLevel() {
	if (!this.usingAnItem()) {
	    final int result = CommonDialogs.showConfirmDialog("Are you sure you want to reset the current level?",
		    Translations.load(Strings.PROGRAM_NAME));
	    if (result == CommonDialogs.YES_OPTION) {
		this.resetLevel(this.plMgr.getPlayerLocationW());
	    }
	}
    }

    public void resetGameState() {
	this.deactivateAllEffects();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	app.getMazeManager().setDirty(false);
	m.restore();
	m.resetTimer();
	this.setSavedGameFlag(false);
	this.st.resetScore();
	this.decay();
	this.objectInv = new ObjectInventory();
	this.savedObjectInv = new ObjectInventory();
	final int startW = m.getStartLevel();
	final boolean playerExists = m.doesPlayerExist();
	if (playerExists) {
	    m.switchLevel(startW);
	    this.plMgr.setPlayerLocation(m.getStartColumn(), m.getStartRow(), m.getStartFloor(), startW);
	    m.save();
	}
    }

    public void resetLevel(final int level) {
	this.deactivateAllEffects();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	app.getMazeManager().setDirty(true);
	m.restore();
	m.resetTimer();
	final boolean playerExists = m.doesPlayerExist();
	if (playerExists) {
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    this.st.resetScore(app.getMazeManager().getScoresFileName());
	    this.resetPlayerLocation(level);
	    this.resetViewingWindow();
	    m.resetVisionRadius();
	    this.decay();
	    this.restoreObjectInventory();
	    this.redrawMaze();
	}
    }

    public void goToLevel(final int level) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	final boolean levelExists = m.doesLevelExist(level);
	if (levelExists) {
	    this.saveSavedMazeObject();
	    m.switchLevel(level);
	    this.plMgr.setPlayerLocationW(level);
	    this.findPlayerAndAdjust();
	    this.restoreSavedMazeObject();
	} else {
	    this.solvedMaze();
	}
    }

    public void solvedLevel() {
	this.deactivateAllEffects();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	CommonDialogs.showTitledDialog(m.getLevelEndMessage(), m.getLevelTitle());
	final boolean levelExists;
	if (m.useOffset()) {
	    levelExists = m.doesLevelExistOffset(m.getNextLevel());
	} else {
	    levelExists = m.doesLevelExist(m.getNextLevel());
	}
	if (levelExists) {
	    m.restore();
	    m.resetTimer();
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    if (m.useOffset()) {
		m.switchLevelOffset(m.getNextLevel());
	    } else {
		m.switchLevel(m.getNextLevel());
	    }
	    this.resetPlayerLocation(this.plMgr.getPlayerLocationW() + 1);
	    this.resetViewingWindow();
	    m.resetVisionRadius();
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    this.decay();
	    this.saveObjectInventory();
	    CommonDialogs.showTitledDialog(m.getLevelStartMessage(), m.getLevelTitle());
	    this.redrawMaze();
	} else {
	    this.solvedMaze();
	}
    }

    public void solvedLevelAlternate() {
	this.deactivateAllEffects();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	CommonDialogs.showTitledDialog(m.getLevelEndMessage(), m.getLevelTitle());
	final boolean levelExists;
	if (m.useAlternateOffset()) {
	    levelExists = m.doesLevelExistOffset(m.getAlternateNextLevel());
	} else {
	    levelExists = m.doesLevelExist(m.getAlternateNextLevel());
	}
	if (levelExists) {
	    m.restore();
	    m.resetTimer();
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    if (m.useOffset()) {
		m.switchLevelOffset(m.getAlternateNextLevel());
	    } else {
		m.switchLevel(m.getAlternateNextLevel());
	    }
	    this.resetPlayerLocation(this.plMgr.getPlayerLocationW() + 1);
	    this.resetViewingWindow();
	    m.resetVisionRadius();
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    this.decay();
	    this.saveObjectInventory();
	    CommonDialogs.showTitledDialog(m.getLevelStartMessage(), m.getLevelTitle());
	    this.redrawMaze();
	} else {
	    this.solvedMaze();
	}
    }

    public void solvedLevelWarp(final int level) {
	this.deactivateAllEffects();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	CommonDialogs.showTitledDialog(m.getLevelEndMessage(), m.getLevelTitle());
	final boolean levelExists = m.doesLevelExist(level);
	if (levelExists) {
	    m.restore();
	    m.resetTimer();
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    m.switchLevel(level);
	    this.resetPlayerLocation(level);
	    this.resetViewingWindow();
	    m.resetVisionRadius();
	    // Activate first moving finish, if one exists
	    m.deactivateAllMovingFinishes();
	    m.activateFirstMovingFinish();
	    this.decay();
	    this.saveObjectInventory();
	    CommonDialogs.showTitledDialog(m.getLevelStartMessage(), m.getLevelTitle());
	    this.redrawMaze();
	} else {
	    this.solvedMaze();
	}
    }

    public void solvedMaze() {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	m.resetTimer();
	SoundPlayer.playSound(SoundIndex.WIN_GAME, SoundGroup.GAME);
	CommonDialogs.showTitledDialog(m.getMazeEndMessage(), m.getMazeTitle());
	if (this.st.checkScore()) {
	    Mazer5D.getBagOStuff().playHighScoreSound();
	}
	this.st.commitScore();
	this.exitGame();
    }

    public void endGame() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	boolean success = false;
	int status = 0;
	if (app.getMazeManager().getDirty()) {
	    status = app.getMazeManager().showSaveDialog();
	    if (status == CommonDialogs.YES_OPTION) {
		app.getMazeManager().saveMaze();
		success = !app.getMazeManager().getDirty();
		if (success) {
		    this.exitGame();
		}
	    } else if (status == CommonDialogs.NO_OPTION) {
		this.exitGame();
	    }
	} else {
	    this.exitGame();
	}
    }

    private void exitGame() {
	this.stateChanged = true;
	this.deactivateAllEffects();
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	// Restore the maze
	m.restore();
	final boolean playerExists = m.doesPlayerExist();
	if (playerExists) {
	    m.healFully();
	    this.resetViewingWindowAndPlayerLocation();
	} else {
	    app.getMazeManager().setLoaded(false);
	}
	// Wipe the inventory
	this.objectInv = new ObjectInventory();
	// Reset saved game flag
	this.savedGameFlag = false;
	app.getMazeManager().setDirty(false);
	// Exit game
	this.hideOutput();
	app.getGUIManager().showGUI();
    }

    public void checkGameOver() {
	if (!Mazer5D.getBagOStuff().getMazeManager().getMaze().isAlive()) {
	    this.gameOver();
	}
    }

    private void gameOver() {
	SoundPlayer.playSound(SoundIndex.GAME_OVER, SoundGroup.USER_INTERFACE);
	CommonDialogs.showDialog("You have died - Game Over!");
	this.exitGame();
    }

    public void decay() {
	if (this.actingRemotely) {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().setCell(GameManager.EMPTY, this.remoteCoords[0],
		    this.remoteCoords[1], this.remoteCoords[2], Layers.OBJECT);
	} else {
	    this.savedMazeObject = GameManager.EMPTY;
	}
    }

    public void decayTo(final MazeObject decay) {
	if (this.actingRemotely) {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().setCell(decay, this.remoteCoords[0], this.remoteCoords[1],
		    this.remoteCoords[2], Layers.OBJECT);
	} else {
	    this.savedMazeObject = decay;
	}
    }

    private void doDelayedDecay() {
	if (this.actingRemotely) {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().setCell(this.delayedDecayObject, this.remoteCoords[0],
		    this.remoteCoords[1], this.remoteCoords[2], Layers.OBJECT);
	} else {
	    this.savedMazeObject = this.delayedDecayObject;
	}
	this.delayedDecayActive = false;
    }

    public void delayedDecayTo(final MazeObject obj) {
	this.delayedDecayActive = true;
	this.delayedDecayObject = obj;
    }

    public void morph(final MazeObject morphInto, final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.setCell(morphInto, x, y, z, morphInto.getLayer());
	    this.redrawMazeNoRebuild();
	    app.getMazeManager().setDirty(true);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
    }

    public void morph(final MazeObject morphInto, final int x, final int y, final int z, final String msg) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.setCell(morphInto, x, y, z, morphInto.getLayer());
	    Mazer5D.getBagOStuff().showMessage(msg);
	    this.keepNextMessage();
	    this.redrawMazeNoRebuild();
	    app.getMazeManager().setDirty(true);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
    }

    public void morph(final MazeObject morphInto, final int x, final int y, final int z, final int e) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.setCell(morphInto, x, y, z, e);
	    this.redrawMazeNoRebuild();
	    app.getMazeManager().setDirty(true);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
    }

    public void morphOther(final MazeObject morphInto, final int x, final int y, final int e) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	try {
	    m.setCell(morphInto, this.plMgr.getPlayerLocationX() + x, this.plMgr.getPlayerLocationY() + y,
		    this.plMgr.getPlayerLocationZ(), e);
	    this.redrawMazeNoRebuild();
	    app.getMazeManager().setDirty(true);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    // Do nothing
	} catch (final NullPointerException np) {
	    // Do nothing
	}
    }

    public void keepNextMessage() {
	this.knm = true;
    }

    public void showInventoryDialog() {
	if (!this.usingAnItem()) {
	    final String[] invString = this.objectInv.generateInventoryStringArray();
	    CommonDialogs.showInputDialog("Inventory", "Inventory", invString, invString[0]);
	}
    }

    public void showUseDialog() {
	if (!this.usingAnItem()) {
	    this.setUsingAnItem(true);
	    int x;
	    final MazeObject[] choices = GameObjects.getAllUsableObjects();
	    final String[] userChoices = this.objectInv.generateUseStringArray();
	    final String result = CommonDialogs.showInputDialog("Use which item?",
		    Translations.load(Strings.PROGRAM_NAME), userChoices, userChoices[this.lastUsedObjectIndex]);
	    try {
		for (x = 0; x < choices.length; x++) {
		    if (result.equals(userChoices[x])) {
			this.lastUsedObjectIndex = x;
			this.objectBeingUsed = choices[x];
			if (this.objectInv.getUses(this.objectBeingUsed) == 0) {
			    Mazer5D.getBagOStuff().showMessage("That item has no more uses left.");
			    this.setUsingAnItem(false);
			} else {
			    Mazer5D.getBagOStuff().showMessage("Click to set target");
			}
			return;
		    }
		}
	    } catch (final NullPointerException np) {
		this.setUsingAnItem(false);
	    }
	}
    }

    public void showSwitchBowDialog() {
	if (!this.usingAnItem()) {
	    int x;
	    final MazeObject[] choices = GameObjects.getAllBows();
	    final String[] userChoices = this.objectInv.generateBowStringArray();
	    final String result = CommonDialogs.showInputDialog("Switch to which bow?",
		    Translations.load(Strings.PROGRAM_NAME), userChoices, userChoices[this.lastUsedBowIndex]);
	    try {
		for (x = 0; x < choices.length; x++) {
		    if (result.equals(userChoices[x])) {
			this.lastUsedBowIndex = x;
			this.activeBow = (GenericBow) choices[x];
			this.activeArrowType = this.activeBow.getArrowType();
			if (this.objectInv.getUses(this.activeBow) == 0) {
			    Mazer5D.getBagOStuff().showMessage("That bow is out of arrows!");
			} else {
			    Mazer5D.getBagOStuff().showMessage(this.activeBow.getName() + " activated.");
			}
			return;
		    }
		}
	    } catch (final NullPointerException np) {
		// Do nothing
	    }
	}
    }

    public ObjectInventory getObjectInventory() {
	return this.objectInv;
    }

    public void useItemHandler(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	final int xOffset = this.vwMgr.getViewingWindowLocationX() - this.vwMgr.getOffsetFactorX();
	final int yOffset = this.vwMgr.getViewingWindowLocationY() - this.vwMgr.getOffsetFactorY();
	final int destX = x / ImageConstants.SIZE + this.vwMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int destY = y / ImageConstants.SIZE + this.vwMgr.getViewingWindowLocationY() + xOffset - yOffset;
	final int destZ = this.plMgr.getPlayerLocationZ();
	if (this.usingAnItem() && Modes.inGame()) {
	    final boolean visible = app.getMazeManager().getMaze().isSquareVisible(this.plMgr.getPlayerLocationX(),
		    this.plMgr.getPlayerLocationY(), destX, destY);
	    try {
		final MazeObject target = m.getCell(destX, destY, destZ, Layers.OBJECT);
		final MazeObjects uid = this.objectBeingUsed.getUniqueID();
		if ((target.isSolid() || !visible) && uid.equals(MazeObjects.TELEPORT_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Can't teleport there");
		}
		if (target.getUniqueID().equals(MazeObjects.PLAYER)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Don't aim at yourself!");
		}
		if (!target.isDestroyable() && uid.equals(MazeObjects.ANNIHILATION_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Can't destroy that");
		}
		if (!target.isDestroyable() && uid.equals(MazeObjects.WALL_MAKING_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Can't create a wall there");
		}
		if (!target.isDestroyable() && uid.equals(MazeObjects.FINISH_MAKING_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Can't create a finish there");
		}
		if ((!target.isDestroyable() || !target.isOfType(TypeConstants.TYPE_WALL))
			&& uid.equals(MazeObjects.WALL_BREAKING_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Aim at a wall");
		}
		if ((!target.isDestroyable() || !target.isOfType(TypeConstants.TYPE_TRAP))
			&& uid.equals(MazeObjects.DISARM_TRAP_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Aim at a trap");
		}
		if (!target.getUniqueID().equals(GameManager.EMPTY.getUniqueID())
			&& !target.getUniqueID().equals(MazeObjects.DARK_GEM) && uid.equals(MazeObjects.LIGHT_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Aim at either an empty space or a Dark Gem");
		}
		if (!target.getUniqueID().equals(GameManager.EMPTY.getUniqueID())
			&& !target.getUniqueID().equals(MazeObjects.LIGHT_GEM) && uid.equals(MazeObjects.DARK_WAND)) {
		    this.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage("Aim at either an empty space or a Light Gem");
		}
	    } catch (final ArrayIndexOutOfBoundsException ae) {
		this.setUsingAnItem(false);
		Mazer5D.getBagOStuff().showMessage("Aim within the maze");
	    } catch (final NullPointerException np) {
		this.setUsingAnItem(false);
	    }
	}
	if (this.usingAnItem()) {
	    this.objectInv.use(this.objectBeingUsed, destX, destY, destZ);
	    this.redrawMaze();
	}
    }

    public void controllableTeleport() {
	this.teleporting = true;
	Mazer5D.getBagOStuff().showMessage("Click to set destination");
    }

    void controllableTeleportHandler(final int x, final int y) {
	if (this.teleporting) {
	    final int xOffset = this.vwMgr.getViewingWindowLocationX() - this.vwMgr.getOffsetFactorX();
	    final int yOffset = this.vwMgr.getViewingWindowLocationY() - this.vwMgr.getOffsetFactorY();
	    final int destX = x / ImageConstants.SIZE + this.vwMgr.getViewingWindowLocationX() - xOffset + yOffset;
	    final int destY = y / ImageConstants.SIZE + this.vwMgr.getViewingWindowLocationY() + xOffset - yOffset;
	    final int destZ = this.plMgr.getPlayerLocationZ();
	    this.updatePositionAbsolute(destX, destY, destZ);
	    SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
	    this.teleporting = false;
	}
    }

    public void identifyObject(final int x, final int y) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	final int xOffset = this.vwMgr.getViewingWindowLocationX() - this.vwMgr.getOffsetFactorX();
	final int yOffset = this.vwMgr.getViewingWindowLocationY() - this.vwMgr.getOffsetFactorY();
	final int destX = x / ImageConstants.SIZE + this.vwMgr.getViewingWindowLocationX() - xOffset + yOffset;
	final int destY = y / ImageConstants.SIZE + this.vwMgr.getViewingWindowLocationY() + xOffset - yOffset;
	final int destZ = this.plMgr.getPlayerLocationZ();
	try {
	    final MazeObject target1 = m.getCell(destX, destY, destZ, Layers.GROUND);
	    final MazeObject target2 = m.getCell(destX, destY, destZ, Layers.OBJECT);
	    target1.determineCurrentAppearance(destX, destY, destZ);
	    target2.determineCurrentAppearance(destX, destY, destZ);
	    final String gameName1 = target1.gameRenderHook().getName();
	    final String gameName2 = target2.gameRenderHook().getName();
	    Mazer5D.getBagOStuff().showMessage(gameName2 + " on " + gameName1);
	    SoundPlayer.playSound(SoundIndex.IDENTIFY, SoundGroup.GAME);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    final MazeObject ev = GameObjects.createObject(MazeObjects.BOUNDS);
	    ev.determineCurrentAppearance(destX, destY, destZ);
	    Mazer5D.getBagOStuff().showMessage(ev.gameRenderHook().getName());
	    SoundPlayer.playSound(SoundIndex.IDENTIFY, SoundGroup.GAME);
	}
    }

    public void loadGameHook(final MazeDataReader mazeFile, final int formatVersion) throws IOException {
	final BagOStuff app = Mazer5D.getBagOStuff();
	this.objectInv = ObjectInventory.readInventory(mazeFile, formatVersion);
	this.savedObjectInv = ObjectInventory.readInventory(mazeFile, formatVersion);
	app.getMazeManager().setScoresFileName(mazeFile.readString());
	this.st.setScore(mazeFile.readLong());
    }

    public void saveGameHook(final MazeDataWriter mazeFile) throws IOException {
	final BagOStuff app = Mazer5D.getBagOStuff();
	this.objectInv.writeInventory(mazeFile);
	this.savedObjectInv.writeInventory(mazeFile);
	mazeFile.writeString(app.getMazeManager().getScoresFileName());
	mazeFile.writeLong(this.st.getScore());
    }

    public void playMaze() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final Maze m = app.getMazeManager().getMaze();
	if (app.getMazeManager().getLoaded()) {
	    app.getGUIManager().hideGUI();
	    Modes.setInGame();
	    if (this.stateChanged) {
		// Initialize only if the maze state has changed
		this.poisonCounter = 0;
		app.getMazeManager().getMaze().switchLevel(app.getMazeManager().getMaze().getStartLevel());
		this.savedMazeObject = GameManager.EMPTY;
		this.st.setScoreFile(app.getMazeManager().getScoresFileName());
		this.autoFinishEnabled = app.getMazeManager().getMaze().getAutoFinishThresholdEnabled();
		this.autoFinishThreshold = app.getMazeManager().getMaze().getAutoFinishThreshold();
		this.autoFinishProgress.setValue(0);
		this.autoFinishProgress.setString("0%");
		this.alternateAutoFinishThreshold = app.getMazeManager().getMaze().getAlternateAutoFinishThreshold();
		this.alternateAutoFinishProgress.setValue(0);
		this.alternateAutoFinishProgress.setString("0%");
		if (this.autoFinishEnabled) {
		    // Update progress bar
		    this.autoFinishProgress.setEnabled(true);
		    this.autoFinishProgress.setMaximum(this.autoFinishThreshold);
		    // Update alternate progress bar
		    this.alternateAutoFinishProgress.setEnabled(true);
		    this.alternateAutoFinishProgress.setMaximum(this.alternateAutoFinishThreshold);
		} else {
		    this.autoFinishProgress.setEnabled(false);
		    this.alternateAutoFinishProgress.setEnabled(false);
		}
		// Activate first moving finish, if one exists
		m.deactivateAllMovingFinishes();
		m.activateFirstMovingFinish();
		if (!this.savedGameFlag) {
		    this.saveObjectInventory();
		    this.st.resetScore(app.getMazeManager().getScoresFileName());
		}
		this.stateChanged = false;
	    }
	    // Make sure message area is attached to the border pane
	    this.borderPane.removeAll();
	    this.borderPane.add(this.outputPane, BorderLayout.CENTER);
	    this.borderPane.add(this.messageLabel, BorderLayout.NORTH);
	    this.borderPane.add(this.getStatGUI().getStatsPane(), BorderLayout.EAST);
	    this.borderPane.add(this.progressPane, BorderLayout.WEST);
	    this.borderPane.add(this.em.getEffectMessagePanel(), BorderLayout.SOUTH);
	    CommonDialogs.showTitledDialog(m.getMazeStartMessage(), m.getMazeTitle());
	    CommonDialogs.showTitledDialog(m.getLevelStartMessage(), m.getLevelTitle());
	    this.showOutput();
	    this.getStatGUI().updateImages();
	    this.getStatGUI().updateStats();
	    this.checkGameOver();
	    this.redrawMaze();
	} else {
	    CommonDialogs.showDialog("No Maze Opened");
	}
    }

    public void showOutput() {
	MusicPlayer.playMusic(MusicIndex.EXPLORING, MusicGroup.GAME);
	this.outputFrame.attachAndSave(this.borderPane);
	this.outputFrame.setTitle(Translations.load(Strings.PROGRAM_NAME));
	this.outputFrame.addKeyListener(this.handler);
	this.outputPane.addMouseListener(this.handler);
    }

    public void hideOutput() {
	this.outputFrame.removeKeyListener(this.handler);
	this.outputPane.removeMouseListener(this.handler);
	MusicPlayer.playMusic(MusicIndex.TITLE, MusicGroup.USER_INTERFACE);
	this.outputFrame.restoreSaved();
    }

    private void setUpGUI() {
	// Create objects
	this.objectInv = new ObjectInventory();
	this.handler = new EventHandler();
	// Create content containers
	this.outputFrame = MainWindow.getMainWindow();
	this.borderPane = this.outputFrame.createContent();
	this.borderPane.setLayout(new BorderLayout());
	this.summaryPane = new JPanel();
	this.summaryPane.setLayout(new BorderLayout());
	this.progressPane = new JPanel();
	this.progressPane.setLayout(new BoxLayout(this.progressPane, BoxLayout.Y_AXIS));
	this.outputPane = new JPanel();
	this.outputPane
		.setLayout(new GridLayout(this.vwMgr.getViewingWindowSizeX(), this.vwMgr.getViewingWindowSizeY()));
	this.commandPane = new JPanel();
	this.commandPane.setLayout(new BoxLayout(this.commandPane, BoxLayout.PAGE_AXIS));
	this.drawGrid = new JLabel[this.vwMgr.getViewingWindowSizeX()][this.vwMgr.getViewingWindowSizeY()];
	// Create components
	this.autoFinishProgress = new JProgressBar(SwingConstants.VERTICAL);
	this.autoFinishProgress.setStringPainted(true);
	this.alternateAutoFinishProgress = new JProgressBar(SwingConstants.VERTICAL);
	this.alternateAutoFinishProgress.setStringPainted(true);
	this.messageLabel = new JLabel(" ");
	this.messageLabel.setOpaque(true);
	// Create command buttons
	this.showObjectInventory = new JButton("Show Inventory...");
	this.useObject = new JButton("Use an Item...");
	this.switchBow = new JButton("Switch Bow...");
	this.reset = new JButton("Reset Current Level");
	this.showScore = new JButton("Show Current Score");
	this.showTable = new JButton("Show Score Table");
	this.endGame = new JButton("End Game");
	// Attach event handlers
	this.showObjectInventory.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.showInventoryDialog();
		}
	    }.start();
	});
	this.useObject.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.showUseDialog();
		}
	    }.start();
	});
	this.switchBow.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.showSwitchBowDialog();
		}
	    }.start();
	});
	this.reset.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.resetCurrentLevel();
		}
	    }.start();
	});
	this.showScore.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.showCurrentScore();
		}
	    }.start();
	});
	this.showTable.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.showScoreTable();
		}
	    }.start();
	});
	this.endGame.addActionListener(h -> {
	    new Thread() {
		@Override
		public void run() {
		    GameManager.this.endGame();
		}
	    }.start();
	});
	// Set initial command state
	this.showObjectInventory.setEnabled(true);
	this.useObject.setEnabled(true);
	this.switchBow.setEnabled(true);
	this.reset.setEnabled(true);
	this.showScore.setEnabled(true);
	this.showTable.setEnabled(true);
	this.endGame.setEnabled(true);
	// Assemble everything together
	for (int x = 0; x < this.vwMgr.getViewingWindowSizeX(); x++) {
	    for (int y = 0; y < this.vwMgr.getViewingWindowSizeY(); y++) {
		this.drawGrid[x][y] = new JLabel();
		// OS-specific fix to make draw grid line up properly
		if (System.getProperty("os.name").startsWith("Mac OS X")) {
		    this.drawGrid[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		this.outputPane.add(this.drawGrid[x][y]);
	    }
	}
	this.progressPane.add(this.autoFinishProgress);
	this.progressPane.add(this.alternateAutoFinishProgress);
	this.commandPane.add(this.showObjectInventory);
	this.commandPane.add(this.useObject);
	this.commandPane.add(this.switchBow);
	this.commandPane.add(this.reset);
	this.commandPane.add(this.showScore);
	this.commandPane.add(this.showTable);
	this.commandPane.add(this.endGame);
	this.summaryPane.add(this.getStatGUI().getStatsPane(), BorderLayout.CENTER);
	this.summaryPane.add(this.commandPane, BorderLayout.SOUTH);
	this.borderPane.add(this.outputPane, BorderLayout.CENTER);
	this.borderPane.add(this.messageLabel, BorderLayout.NORTH);
	this.borderPane.add(this.progressPane, BorderLayout.WEST);
	this.borderPane.add(this.summaryPane, BorderLayout.EAST);
	this.borderPane.add(this.em.getEffectMessagePanel(), BorderLayout.SOUTH);
    }

    private class EventHandler implements KeyListener, MouseListener {
	public EventHandler() {
	    // Do nothing
	}

	@Override
	public void keyPressed(final KeyEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    if (!GameManager.this.arrowActive) {
			if (!Prefs.oneMove()) {
			    if (e.isAltDown()) {
				EventHandler.this.handleArrows(e);
			    } else {
				EventHandler.this.handleMovement(e);
			    }
			}
		    }
		}
	    }.start();
	}

	@Override
	public void keyReleased(final KeyEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    if (!GameManager.this.arrowActive) {
			if (Prefs.oneMove()) {
			    if (e.isAltDown()) {
				EventHandler.this.handleArrows(e);
			    } else {
				EventHandler.this.handleMovement(e);
			    }
			}
		    }
		}
	    }.start();
	}

	@Override
	public void keyTyped(final KeyEvent e) {
	    // Do nothing
	}

	private void handleMovement(final KeyEvent e) {
	    final GameManager gm = GameManager.this;
	    final int keyCode = e.getKeyCode();
	    if (e.isShiftDown()) {
		gm.setPullInProgress(true);
	    }
	    switch (keyCode) {
	    case KeyEvent.VK_NUMPAD4:
	    case KeyEvent.VK_LEFT:
	    case KeyEvent.VK_A:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(-1, 0);
		}
		break;
	    case KeyEvent.VK_NUMPAD2:
	    case KeyEvent.VK_DOWN:
	    case KeyEvent.VK_X:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(0, 1);
		}
		break;
	    case KeyEvent.VK_NUMPAD6:
	    case KeyEvent.VK_RIGHT:
	    case KeyEvent.VK_D:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(1, 0);
		}
		break;
	    case KeyEvent.VK_NUMPAD8:
	    case KeyEvent.VK_UP:
	    case KeyEvent.VK_W:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(0, -1);
		}
		break;
	    case KeyEvent.VK_NUMPAD7:
	    case KeyEvent.VK_Q:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(-1, -1);
		}
		break;
	    case KeyEvent.VK_NUMPAD9:
	    case KeyEvent.VK_E:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(1, -1);
		}
		break;
	    case KeyEvent.VK_NUMPAD3:
	    case KeyEvent.VK_C:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(1, 1);
		}
		break;
	    case KeyEvent.VK_NUMPAD1:
	    case KeyEvent.VK_Z:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(-1, 1);
		}
		break;
	    case KeyEvent.VK_NUMPAD5:
	    case KeyEvent.VK_S:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.updatePositionRelative(0, 0);
		}
		break;
	    case KeyEvent.VK_ESCAPE:
		if (gm.usingAnItem()) {
		    gm.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage(" ");
		} else if (gm.isTeleporting()) {
		    gm.teleporting = false;
		    Mazer5D.getBagOStuff().showMessage(" ");
		}
		break;
	    default:
		break;
	    }
	    if (gm.isPullInProgress()) {
		gm.setPullInProgress(false);
	    }
	}

	private void handleArrows(final KeyEvent e) {
	    final GameManager gm = GameManager.this;
	    final int keyCode = e.getKeyCode();
	    if (e.isShiftDown()) {
		gm.setPullInProgress(true);
	    }
	    switch (keyCode) {
	    case KeyEvent.VK_NUMPAD4:
	    case KeyEvent.VK_LEFT:
	    case KeyEvent.VK_A:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(-1, 0);
		}
		break;
	    case KeyEvent.VK_NUMPAD2:
	    case KeyEvent.VK_DOWN:
	    case KeyEvent.VK_X:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(0, 1);
		}
		break;
	    case KeyEvent.VK_NUMPAD6:
	    case KeyEvent.VK_RIGHT:
	    case KeyEvent.VK_D:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(1, 0);
		}
		break;
	    case KeyEvent.VK_NUMPAD8:
	    case KeyEvent.VK_UP:
	    case KeyEvent.VK_W:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(0, -1);
		}
		break;
	    case KeyEvent.VK_NUMPAD7:
	    case KeyEvent.VK_Q:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(-1, -1);
		}
		break;
	    case KeyEvent.VK_NUMPAD9:
	    case KeyEvent.VK_E:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(1, -1);
		}
		break;
	    case KeyEvent.VK_NUMPAD3:
	    case KeyEvent.VK_C:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(1, 1);
		}
		break;
	    case KeyEvent.VK_NUMPAD1:
	    case KeyEvent.VK_Z:
		if (!gm.usingAnItem() && !gm.isTeleporting()) {
		    gm.fireArrow(-1, 1);
		}
		break;
	    case KeyEvent.VK_ESCAPE:
		if (gm.usingAnItem()) {
		    gm.setUsingAnItem(false);
		    Mazer5D.getBagOStuff().showMessage(" ");
		} else if (gm.isTeleporting()) {
		    gm.teleporting = false;
		    Mazer5D.getBagOStuff().showMessage(" ");
		}
		break;
	    default:
		break;
	    }
	    if (gm.isPullInProgress()) {
		gm.setPullInProgress(false);
	    }
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
		    final GameManager gm = GameManager.this;
		    if (gm.usingAnItem()) {
			final int x = e.getX();
			final int y = e.getY();
			gm.useItemHandler(x, y);
			gm.setUsingAnItem(false);
		    } else if (e.isShiftDown()) {
			final int x = e.getX();
			final int y = e.getY();
			gm.identifyObject(x, y);
		    } else if (gm.isTeleporting()) {
			final int x = e.getX();
			final int y = e.getY();
			gm.controllableTeleportHandler(x, y);
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
}
