/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.maze;

import java.io.File;
import java.io.IOException;

import com.puttysoftware.fileutils.FileUtilities;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.file.FileExtensions;
import com.puttysoftware.mazer5d.file.TempDirCleanup;
import com.puttysoftware.mazer5d.file.format.PrefixIO;
import com.puttysoftware.mazer5d.file.format.SuffixIO;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.objects.abc.GenericCharacter;
import com.puttysoftware.mazer5d.objects.abc.GenericMovingObject;
import com.puttysoftware.mazer5d.prefs.Prefs;
import com.puttysoftware.randomrange.RandomLongRange;

public class Maze {
	// Properties
	private MazeData mazeData;
	private MazeData clipboard;
	private int startW;
	private int levelCount;
	private int activeLevel;
	private int currHP;
	private int maxHP;
	private String mazeTitle;
	private String mazeStartMessage;
	private String mazeEndMessage;
	private String basePath;
	private PrefixIO prefixHandler;
	private SuffixIO suffixHandler;
	private final int[] savedStart;
	private static final int MIN_LEVELS = 1;
	private static final int MAX_LEVELS = Integer.MAX_VALUE;

	// Constructors
	public Maze() {
		this.mazeData = null;
		this.clipboard = null;
		this.levelCount = 0;
		this.startW = 0;
		this.activeLevel = 0;
		this.currHP = 1000;
		this.maxHP = 1000;
		this.prefixHandler = null;
		this.suffixHandler = null;
		this.mazeTitle = "Untitled Maze";
		this.mazeStartMessage = "Let's Solve The Maze!";
		this.mazeEndMessage = "Maze Solved!";
		this.savedStart = new int[4];
		final long random = new RandomLongRange(0, Long.MAX_VALUE).generate();
		final String randomID = Long.toHexString(random);
		this.basePath = System.getProperty("java.io.tmpdir") + File.separator + Translations.load(Strings.PROGRAM_NAME)
				+ File.separator + randomID + FileExtensions.getMazeTempExtensionWithPeriod();
		final File base = new File(this.basePath);
		base.mkdirs();
	}

	// Cleanup hook
	static {
		Runtime.getRuntime().addShutdownHook(new TempDirCleanup());
	}

	// Static methods
	public static String getMazeTempFolder() {
		return System.getProperty("java.io.tmpdir") + File.separator + Translations.load(Strings.PROGRAM_NAME);
	}

	public static int getMinLevels() {
		return Maze.MIN_LEVELS;
	}

	public static int getMaxLevels() {
		return Maze.MAX_LEVELS;
	}

	public static int getMinFloors() {
		return MazeData.getMinFloors();
	}

	public static int getMaxFloors() {
		return MazeData.getMaxFloors();
	}

	public static int getMinColumns() {
		return MazeData.getMinColumns();
	}

	public static int getMaxColumns() {
		return MazeData.getMaxColumns();
	}

	public static int getMinRows() {
		return MazeData.getMinRows();
	}

	public static int getMaxRows() {
		return MazeData.getMaxRows();
	}

	// Methods
	public int getCurrentHP() {
		return this.currHP;
	}

	public int getMaximumHP() {
		return this.maxHP;
	}

	public String getHPString() {
		return "Health: " + this.currHP + "/" + this.maxHP;
	}

	public void setMaximumHP(final int max) {
		this.maxHP = max;
		if (this.currHP > this.maxHP) {
			this.currHP = this.maxHP;
		}
	}

	public boolean isAlive() {
		return this.currHP > 0;
	}

	public void doPoisonDamage() {
		this.doDamage(1);
	}

	public void doDamage(final int amount) {
		this.currHP -= amount;
		if (this.currHP < 0) {
			this.currHP = 0;
		}
		if (this.currHP > this.maxHP) {
			this.currHP = this.maxHP;
		}
	}

	public void doDamagePercentage(final int percent) {
		int fP = percent;
		if (fP > GenericCharacter.FULL_HEAL_PERCENTAGE) {
			fP = GenericCharacter.FULL_HEAL_PERCENTAGE;
		}
		if (fP < 0) {
			fP = 0;
		}
		final double fPMultiplier = fP / (double) GenericCharacter.FULL_HEAL_PERCENTAGE;
		int modValue = (int) (this.maxHP * fPMultiplier);
		if (modValue <= 0) {
			modValue = 1;
		}
		this.currHP -= modValue;
		if (this.currHP < 0) {
			this.currHP = 0;
		}
		if (this.currHP > this.maxHP) {
			this.currHP = this.maxHP;
		}
	}

	public void heal(final int amount) {
		this.currHP += amount;
		if (this.currHP < 0) {
			this.currHP = 0;
		}
		if (this.currHP > this.maxHP) {
			this.currHP = this.maxHP;
		}
	}

	public void healFully() {
		this.currHP = this.maxHP;
	}

	public void healPercentage(final int percent) {
		int fP = percent;
		if (fP > GenericCharacter.FULL_HEAL_PERCENTAGE) {
			fP = GenericCharacter.FULL_HEAL_PERCENTAGE;
		}
		if (fP < 0) {
			fP = 0;
		}
		final double fPMultiplier = fP / (double) GenericCharacter.FULL_HEAL_PERCENTAGE;
		final int difference = this.maxHP - this.getCurrentHP();
		int modValue = (int) (difference * fPMultiplier);
		if (modValue <= 0) {
			modValue = 1;
		}
		this.currHP += modValue;
		if (this.currHP < 0) {
			this.currHP = 0;
		}
		if (this.currHP > this.maxHP) {
			this.currHP = this.maxHP;
		}
	}

	public String getBasePath() {
		return this.basePath;
	}

	public void setPrefixHandler(final PrefixIO ph) {
		this.prefixHandler = ph;
	}

	public void setSuffixHandler(final SuffixIO sh) {
		this.suffixHandler = sh;
	}

	public String getMazeTitle() {
		return this.mazeTitle;
	}

	public void setMazeTitle(final String title) {
		if (title == null) {
			this.mazeTitle = StaticStrings.EMPTY;
		} else {
			this.mazeTitle = title;
		}
	}

	public String getMazeStartMessage() {
		return this.mazeStartMessage;
	}

	public void setMazeStartMessage(final String msg) {
		if (msg == null) {
			this.mazeStartMessage = StaticStrings.EMPTY;
		} else {
			this.mazeStartMessage = msg;
		}
	}

	public String getMazeEndMessage() {
		return this.mazeEndMessage;
	}

	public void setMazeEndMessage(final String msg) {
		if (msg == null) {
			this.mazeEndMessage = StaticStrings.EMPTY;
		} else {
			this.mazeEndMessage = msg;
		}
	}

	public String getLevelTitle() {
		return this.mazeData.getLevelTitle();
	}

	public void setLevelTitle(final String title) {
		this.mazeData.setLevelTitle(title);
	}

	public String getLevelStartMessage() {
		return this.mazeData.getLevelStartMessage();
	}

	public void setLevelStartMessage(final String msg) {
		this.mazeData.setLevelStartMessage(msg);
	}

	public String getLevelEndMessage() {
		return this.mazeData.getLevelEndMessage();
	}

	public void setLevelEndMessage(final String msg) {
		this.mazeData.setLevelEndMessage(msg);
	}

	public int getExploreRadius() {
		return this.mazeData.getExploreRadius();
	}

	public void setExploreRadius(final int newER) {
		this.mazeData.setExploreRadius(newER);
	}

	public void addVisionMode(final int newMode) {
		this.mazeData.addVisionMode(newMode);
	}

	public void removeVisionMode(final int newMode) {
		this.mazeData.removeVisionMode(newMode);
	}

	public void updateVisibleSquares(final int xp, final int yp, final int zp) {
		this.mazeData.updateVisibleSquares(xp, yp, zp);
	}

	public int getFinishMoveSpeed() {
		return this.mazeData.getFinishMoveSpeed();
	}

	public void setFinishMoveSpeed(final int value) {
		this.mazeData.setFinishMoveSpeed(value);
	}

	public void setFirstMovingFinishX(final int value) {
		this.mazeData.setFirstMovingFinishX(value);
	}

	public void setFirstMovingFinishY(final int value) {
		this.mazeData.setFirstMovingFinishY(value);
	}

	public void setFirstMovingFinishZ(final int value) {
		this.mazeData.setFirstMovingFinishZ(value);
	}

	public int getPoisonPower() {
		return this.mazeData.getPoisonPower();
	}

	public void setPoisonPower(final int pp) {
		this.mazeData.setPoisonPower(pp);
	}

	public void doPoisonousAmulet() {
		this.mazeData.doPoisonousAmulet();
	}

	public void doCounterpoisonAmulet() {
		this.mazeData.doCounterpoisonAmulet();
	}

	public void undoPoisonAmulets() {
		this.mazeData.undoPoisonAmulets();
	}

	public String getPoisonString() {
		return this.mazeData.getPoisonString();
	}

	public static int getMaxPoisonPower() {
		return MazeData.getMaxPoisonPower();
	}

	public boolean getAutoFinishThresholdEnabled() {
		return this.mazeData.getAutoFinishThresholdEnabled();
	}

	public void setAutoFinishThresholdEnabled(final boolean afte) {
		this.mazeData.setAutoFinishThresholdEnabled(afte);
	}

	public int getAutoFinishThreshold() {
		return this.mazeData.getAutoFinishThreshold();
	}

	public void setAutoFinishThreshold(final int aft) {
		this.mazeData.setAutoFinishThreshold(aft);
	}

	public int getAlternateAutoFinishThreshold() {
		return this.mazeData.getAlternateAutoFinishThreshold();
	}

	public void setAlternateAutoFinishThreshold(final int aaft) {
		this.mazeData.setAlternateAutoFinishThreshold(aaft);
	}

	public int getNextLevel() {
		return this.mazeData.getNextLevel();
	}

	public boolean useOffset() {
		return this.mazeData.useOffset();
	}

	public void setUseOffset(final boolean uo) {
		this.mazeData.setUseOffset(uo);
	}

	public void setNextLevel(final int nl) {
		this.mazeData.setNextLevel(nl);
	}

	public void setNextLevelOffset(final int nlo) {
		this.mazeData.setNextLevelOffset(nlo);
	}

	public int getAlternateNextLevel() {
		return this.mazeData.getAlternateNextLevel();
	}

	public boolean useAlternateOffset() {
		return this.mazeData.useAlternateOffset();
	}

	public void setUseAlternateOffset(final boolean uao) {
		this.mazeData.setUseAlternateOffset(uao);
	}

	public void setAlternateNextLevel(final int anl) {
		this.mazeData.setAlternateNextLevel(anl);
	}

	public void setAlternateNextLevelOffset(final int anlo) {
		this.mazeData.setAlternateNextLevelOffset(anlo);
	}

	public int getActiveLevelNumber() {
		return this.activeLevel;
	}

	public void switchLevel(final int level) {
		this.switchLevelInternal(level);
	}

	public void switchLevelOffset(final int level) {
		this.switchLevelInternal(this.activeLevel + level);
	}

	private void switchLevelInternal(final int level) {
		if (this.activeLevel != level) {
			if (this.mazeData != null) {
				try (MazeDataWriter writer = this.getLevelWriter()) {
					// Save old level
					this.writeMazeLevel(writer);
					writer.close();
				} catch (final IOException io) {
					// Ignore
				}
			}
			this.activeLevel = level;
			try (MazeDataReader reader = this.getLevelReader()) {
				// Load new level
				this.readMazeLevel(reader);
				reader.close();
			} catch (final IOException io) {
				// Ignore
			}
		}
	}

	public boolean doesLevelExist(final int level) {
		return level < this.levelCount && level >= 0;
	}

	public boolean doesLevelExistOffset(final int level) {
		return this.activeLevel + level < this.levelCount && this.activeLevel + level >= 0;
	}

	public void cutLevel() {
		if (this.levelCount > 1) {
			this.clipboard = this.mazeData;
			this.removeLevel();
		}
	}

	public void copyLevel() {
		this.clipboard = this.mazeData;
	}

	public void pasteLevel() {
		if (this.clipboard != null) {
			this.mazeData = this.clipboard;
			Mazer5D.getBagOStuff().getMazeManager().setDirty(true);
		}
	}

	public boolean isPasteBlocked() {
		return this.clipboard == null;
	}

	public boolean isCutBlocked() {
		return this.levelCount <= 1;
	}

	public boolean insertLevelFromClipboard() {
		if (this.levelCount < Maze.MAX_LEVELS) {
			this.mazeData = this.clipboard;
			this.levelCount++;
			return true;
		} else {
			return false;
		}
	}

	public boolean addLevel(final int rows, final int cols, final int floors) {
		if (this.levelCount < Maze.MAX_LEVELS) {
			if (this.mazeData != null) {
				try (MazeDataWriter writer = this.getLevelWriter()) {
					// Save old level
					this.writeMazeLevel(writer);
					writer.close();
				} catch (final IOException io) {
					// Ignore
				}
			}
			this.mazeData = new MazeData(rows, cols, floors);
			this.levelCount++;
			this.activeLevel = this.levelCount - 1;
			return true;
		} else {
			return false;
		}
	}

	public boolean removeLevel() {
		if (this.levelCount > 1) {
			if (this.activeLevel >= 1 && this.activeLevel <= this.levelCount) {
				this.mazeData = null;
				// Delete file corresponding to current level
				this.getLevelFile(this.activeLevel).delete();
				// Shift all higher-numbered levels down
				for (int x = this.activeLevel; x < this.levelCount - 1; x++) {
					final File sourceLocation = this.getLevelFile(x + 1);
					final File targetLocation = this.getLevelFile(x);
					try {
						FileUtilities.moveFile(sourceLocation, targetLocation);
					} catch (final IOException io) {
						// Ignore
					}
				}
				// Load level 1
				this.switchLevel(0);
				this.levelCount--;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public MazeObject getCell(final int row, final int col, final int floor, final int extra) {
		return this.mazeData.getCell(row, col, floor, extra);
	}

	public int getFindResultRow() {
		return this.mazeData.getFindResultRow();
	}

	public int getFindResultColumn() {
		return this.mazeData.getFindResultColumn();
	}

	public int getFindResultFloor() {
		return this.mazeData.getFindResultFloor();
	}

	public int getStartRow() {
		return this.mazeData.getStartRow();
	}

	public int getStartColumn() {
		return this.mazeData.getStartColumn();
	}

	public int getStartFloor() {
		return this.mazeData.getStartFloor();
	}

	public int getStartLevel() {
		return this.startW;
	}

	public int getRows() {
		return this.mazeData.getRows();
	}

	public int getColumns() {
		return this.mazeData.getColumns();
	}

	public int getFloors() {
		return this.mazeData.getFloors();
	}

	public int getLevels() {
		return this.levelCount;
	}

	public int getVisionRadius() {
		return this.mazeData.getVisionRadius();
	}

	public boolean doesPlayerExist() {
		return this.mazeData != null ? this.mazeData.doesPlayerExist() : false;
	}

	public void findStart() {
		this.mazeData.findStart();
	}

	public boolean findPlayer() {
		if (this.activeLevel > this.levelCount) {
			return false;
		} else {
			return this.mazeData.findPlayer();
		}
	}

	public void findAllObjectPairsAndSwap(final MazeObject o1, final MazeObject o2) {
		this.mazeData.findAllObjectPairsAndSwap(o1, o2);
	}

	public void findAllMatchingObjectsAndDecay(final MazeObject o) {
		this.mazeData.findAllMatchingObjectsAndDecay(o);
	}

	public void masterTrapTrigger() {
		this.mazeData.masterTrapTrigger();
	}

	public void tickTimers(final int floor) {
		this.mazeData.tickTimers(floor);
	}

	public boolean rotateRadiusClockwise(final int x, final int y, final int z, final int r) {
		return this.mazeData.rotateRadiusClockwise(x, y, z, r);
	}

	public boolean rotateRadiusCounterclockwise(final int x, final int y, final int z, final int r) {
		return this.mazeData.rotateRadiusCounterclockwise(x, y, z, r);
	}

	public void resize(final int x, final int y, final int z) {
		this.mazeData.resize(x, y, z);
	}

	public boolean radialScan(final int x, final int y, final int z, final int l, final int r,
			final String targetName) {
		return this.mazeData.radialScan(x, y, z, l, r, targetName);
	}

	public void radialScanFreezeObjects(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanFreezeObjects(x, y, z, r);
	}

	public void radialScanFreezeGround(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanFreezeGround(x, y, z, r);
	}

	public void radialScanEnrageObjects(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanEnrageObjects(x, y, z, r);
	}

	public void radialScanBurnGround(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanBurnGround(x, y, z, r);
	}

	public void radialScanPoisonObjects(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanPoisonObjects(x, y, z, r);
	}

	public void radialScanPoisonGround(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanPoisonGround(x, y, z, r);
	}

	public void radialScanShockObjects(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanShockObjects(x, y, z, r);
	}

	public void radialScanShockGround(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanShockGround(x, y, z, r);
	}

	public void radialScanWarpObjects(final int x, final int y, final int z, final int l, final int r) {
		this.mazeData.radialScanWarpObjects(x, y, z, l, r);
	}

	public void radialScanShuffleObjects(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanShuffleObjects(x, y, z, r);
	}

	public void radialScanQuakeBomb(final int x, final int y, final int z, final int r) {
		this.mazeData.radialScanQuakeBomb(x, y, z, r);
	}

	public boolean isSquareVisible(final int x1, final int y1, final int x2, final int y2) {
		return this.mazeData.isSquareVisible(x1, y1, x2, y2);
	}

	public void setCell(final MazeObject mo, final int row, final int col, final int floor, final int extra) {
		this.mazeData.setCell(mo, row, col, floor, extra);
	}

	public void setStartRow(final int newStartRow) {
		this.mazeData.setStartRow(newStartRow);
	}

	public void setStartColumn(final int newStartColumn) {
		this.mazeData.setStartColumn(newStartColumn);
	}

	public void setStartFloor(final int newStartFloor) {
		this.mazeData.setStartFloor(newStartFloor);
	}

	public void setStartLevel(final int newStartLevel) {
		this.startW = newStartLevel;
	}

	public void resetVisionRadius() {
		this.mazeData.resetVisionRadius();
	}

	public void setVisionRadius(final int newVR) {
		this.mazeData.setVisionRadius(newVR);
	}

	public void setVisionRadiusToMaximum() {
		this.mazeData.setVisionRadiusToMaximum();
	}

	public void setVisionRadiusToMinimum() {
		this.mazeData.setVisionRadiusToMinimum();
	}

	public void incrementVisionRadius() {
		this.mazeData.incrementVisionRadius();
	}

	public void decrementVisionRadius() {
		this.mazeData.decrementVisionRadius();
	}

	public void deactivateAllMovingFinishes() {
		this.mazeData.deactivateAllMovingFinishes();
	}

	public void activateFirstMovingFinish() {
		this.mazeData.activateFirstMovingFinish();
	}

	public void fillLevelDefault() {
		final MazeObject bottom = GameObjects.createObject(Prefs.getEditorDefaultFill());
		final MazeObject top = GameObjects.getEmptySpace();
		this.mazeData.fill(bottom, top);
	}

	public void fillFloorDefault(final int floor) {
		final MazeObject bottom = GameObjects.createObject(Prefs.getEditorDefaultFill());
		final MazeObject top = GameObjects.getEmptySpace();
		this.mazeData.fillFloor(bottom, top, floor);
	}

	public void fillLevel(final MazeObject bottom, final MazeObject top) {
		this.mazeData.fill(bottom, top);
	}

	public void fillLevelRandomly() {
		this.mazeData.fillRandomly(this, this.activeLevel);
	}

	public void fillFloorRandomly(final int z) {
		this.mazeData.fillFloorRandomly(this, z, this.activeLevel);
	}

	public void fillLevelRandomlyCustom() {
		this.mazeData.fillRandomlyCustom(this, this.activeLevel);
	}

	public void fillFloorRandomlyCustom(final int z) {
		this.mazeData.fillFloorRandomlyCustom(this, z, this.activeLevel);
	}

	public void save() {
		this.mazeData.save();
	}

	public void restore() {
		this.mazeData.restore();
	}

	public void saveStart() {
		this.savedStart[0] = this.startW;
		this.savedStart[1] = this.mazeData.getStartRow();
		this.savedStart[2] = this.mazeData.getStartColumn();
		this.savedStart[3] = this.mazeData.getStartFloor();
	}

	public void restoreStart() {
		this.startW = this.savedStart[0];
		this.mazeData.setStartRow(this.savedStart[1]);
		this.mazeData.setStartColumn(this.savedStart[2]);
		this.mazeData.setStartFloor(this.savedStart[3]);
	}

	public void updateMovingBlockPosition(final int move, final int xLoc, final int yLoc,
			final GenericMovingObject block) {
		this.mazeData.updateMovingBlockPosition(move, xLoc, yLoc, block);
	}

	public void warpObject(final MazeObject mo, final int x, final int y, final int z, final int l) {
		this.mazeData.warpObject(mo, x, y, z, l);
	}

	public void hotGround(final int x, final int y, final int z) {
		this.mazeData.hotGround(x, y, z);
	}

	public void enableHorizontalWraparound() {
		this.mazeData.enableHorizontalWraparound();
	}

	public void disableHorizontalWraparound() {
		this.mazeData.disableHorizontalWraparound();
	}

	public void enableVerticalWraparound() {
		this.mazeData.enableVerticalWraparound();
	}

	public void disableVerticalWraparound() {
		this.mazeData.disableVerticalWraparound();
	}

	public void enable3rdDimensionWraparound() {
		this.mazeData.enable3rdDimensionWraparound();
	}

	public void disable3rdDimensionWraparound() {
		this.mazeData.disable3rdDimensionWraparound();
	}

	public boolean isHorizontalWraparoundEnabled() {
		return this.mazeData.isHorizontalWraparoundEnabled();
	}

	public boolean isVerticalWraparoundEnabled() {
		return this.mazeData.isVerticalWraparoundEnabled();
	}

	public boolean is3rdDimensionWraparoundEnabled() {
		return this.mazeData.is3rdDimensionWraparoundEnabled();
	}

	public final String getTimeString() {
		return this.mazeData.getTimeString();
	}

	public final boolean isTimerActive() {
		return this.mazeData.isTimerActive();
	}

	public final void activateTimer(final int ticks) {
		this.mazeData.activateTimer(ticks);
	}

	public final void deactivateTimer() {
		this.mazeData.deactivateTimer();
	}

	public final void resetTimer() {
		this.mazeData.resetTimer();
	}

	public final int getTimerValue() {
		return this.mazeData.getTimerValue();
	}

	public final int getInitialTimerValue() {
		return this.mazeData.getInitialTimerValue();
	}

	public final void extendTimerByInitialValue() {
		this.mazeData.extendTimerByInitialValue();
	}

	public final void extendTimerByInitialValueHalved() {
		this.mazeData.extendTimerByInitialValueHalved();
	}

	public final void extendTimerByInitialValueDoubled() {
		this.mazeData.extendTimerByInitialValueDoubled();
	}

	public Maze readMaze() throws IOException {
		final Maze m = new Maze();
		// Attach handlers
		m.setPrefixHandler(this.prefixHandler);
		m.setSuffixHandler(this.suffixHandler);
		// Make base paths the same
		m.basePath = this.basePath;
		// Create metafile reader
		try (MazeDataReader metaReader = new MazeDataReader(m.basePath + File.separator + "metafile.xml", "maze")) {
			// Read metafile
			final MazeVersion formatVersion = m.readMazeMetafile(metaReader);
			// Set compatibility flags
			if (formatVersion == MazeVersion.V1) {
				Mazer5D.getBagOStuff().getMazeManager().setMaze1Compatible(true);
				Mazer5D.getBagOStuff().getMazeManager().setMaze2Compatible(true);
				Mazer5D.getBagOStuff().getMazeManager().setMaze4Compatible(true);
			} else if (formatVersion == MazeVersion.V2) {
				Mazer5D.getBagOStuff().getMazeManager().setMaze1Compatible(false);
				Mazer5D.getBagOStuff().getMazeManager().setMaze2Compatible(true);
				Mazer5D.getBagOStuff().getMazeManager().setMaze4Compatible(true);
			} else if (formatVersion == MazeVersion.V3 || formatVersion == MazeVersion.V4) {
				Mazer5D.getBagOStuff().getMazeManager().setMaze1Compatible(false);
				Mazer5D.getBagOStuff().getMazeManager().setMaze2Compatible(false);
				Mazer5D.getBagOStuff().getMazeManager().setMaze4Compatible(true);
			} else {
				Mazer5D.getBagOStuff().getMazeManager().setMaze1Compatible(false);
				Mazer5D.getBagOStuff().getMazeManager().setMaze2Compatible(false);
				Mazer5D.getBagOStuff().getMazeManager().setMaze4Compatible(false);
			}
			// Create data reader
			try (MazeDataReader dataReader = m.getLevelReader()) {
				// Read data
				m.readMazeLevel(dataReader, formatVersion);
			}
		}
		return m;
	}

	private MazeDataReader getLevelReader() throws IOException {
		return new MazeDataReader(this.basePath + File.separator + "level" + this.activeLevel + ".xml", "level");
	}

	private MazeVersion readMazeMetafile(final MazeDataReader reader) throws IOException {
		MazeVersion formatVersion = MazeVersion.V1;
		if (this.prefixHandler != null) {
			formatVersion = this.prefixHandler.readPrefix(reader);
		}
		final int levels = reader.readInt();
		this.levelCount = levels;
		this.startW = reader.readInt();
		this.currHP = reader.readInt();
		this.maxHP = reader.readInt();
		this.mazeTitle = reader.readString();
		this.mazeStartMessage = reader.readString();
		this.mazeEndMessage = reader.readString();
		if (this.suffixHandler != null) {
			this.suffixHandler.readSuffix(reader, formatVersion);
		}
		return formatVersion;
	}

	private void readMazeLevel(final MazeDataReader reader) throws IOException {
		this.readMazeLevel(reader, MazeVersion.V5);
	}

	private void readMazeLevel(final MazeDataReader reader, final MazeVersion formatVersion) throws IOException {
		if (formatVersion == MazeVersion.V1) {
			this.mazeData = MazeData.readMazeDataModelV1(reader, formatVersion);
			this.mazeData.readSavedTowerState(reader, formatVersion);
		} else if (formatVersion == MazeVersion.V2) {
			this.mazeData = MazeData.readMazeDataModelV2(reader, formatVersion);
			this.mazeData.readSavedTowerState(reader, formatVersion);
		} else if (formatVersion == MazeVersion.V3) {
			this.mazeData = MazeData.readMazeDataModelV3(reader, formatVersion);
			this.mazeData.readSavedTowerState(reader, formatVersion);
		} else if (formatVersion == MazeVersion.V4) {
			this.mazeData = MazeData.readMazeDataModelV4(reader, formatVersion);
			this.mazeData.readSavedTowerState(reader, formatVersion);
		} else if (formatVersion == MazeVersion.V5) {
			this.mazeData = MazeData.readMazeDataModelV5(reader, formatVersion);
			this.mazeData.readSavedTowerState(reader, formatVersion);
		} else {
			throw new IOException("Unknown maze format formatVersion!");
		}
	}

	private File getLevelFile(final int level) {
		return new File(this.basePath + File.separator + level + FileExtensions.getMazeLevelExtensionWithPeriod());
	}

	public void writeMaze() throws IOException {
		// Clear compatibility flag
		Mazer5D.getBagOStuff().getMazeManager().setMaze1Compatible(false);
		// Clear 2 compatibility flag
		Mazer5D.getBagOStuff().getMazeManager().setMaze2Compatible(false);
		// Clear 4 compatibility flag
		Mazer5D.getBagOStuff().getMazeManager().setMaze4Compatible(false);
		// Create metafile writer
		try (MazeDataWriter metaWriter = new MazeDataWriter(this.basePath + File.separator + "metafile.xml", "maze")) {
			// Write metafile
			this.writeMazeMetafile(metaWriter);
			// Create data writer
			try (MazeDataWriter dataWriter = this.getLevelWriter()) {
				// Write data
				this.writeMazeLevel(dataWriter);
			}
		}
	}

	private MazeDataWriter getLevelWriter() throws IOException {
		return new MazeDataWriter(this.basePath + File.separator + "level" + this.activeLevel + ".xml", "level");
	}

	private void writeMazeMetafile(final MazeDataWriter writer) throws IOException {
		if (this.prefixHandler != null) {
			this.prefixHandler.writePrefix(writer);
		}
		writer.writeInt(this.levelCount);
		writer.writeInt(this.startW);
		writer.writeInt(this.currHP);
		writer.writeInt(this.maxHP);
		writer.writeString(this.mazeTitle);
		writer.writeString(this.mazeStartMessage);
		writer.writeString(this.mazeEndMessage);
		if (this.suffixHandler != null) {
			this.suffixHandler.writeSuffix(writer);
		}
	}

	private void writeMazeLevel(final MazeDataWriter writer) throws IOException {
		// Clear compatibility flag
		Mazer5D.getBagOStuff().getMazeManager().setMaze1Compatible(false);
		// Clear 2 compatibility flag
		Mazer5D.getBagOStuff().getMazeManager().setMaze2Compatible(false);
		// Clear 4 compatibility flag
		Mazer5D.getBagOStuff().getMazeManager().setMaze4Compatible(false);
		// Write the level
		this.mazeData.writeLayeredTower(writer);
		this.mazeData.writeSavedTowerState(writer);
	}
}
