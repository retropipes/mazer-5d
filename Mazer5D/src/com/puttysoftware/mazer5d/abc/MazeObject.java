/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.editor.rulesets.RuleSet;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.DataLoader;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.maze.Maze;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utilities.ArrowTypes;
import com.puttysoftware.mazer5d.utilities.Directions;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.MazeObjectActions;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.RandomGenerationRule;
import com.puttysoftware.mazer5d.utilities.TypeConstants;
import com.puttysoftware.randomrange.RandomRange;

public abstract class MazeObject implements RandomGenerationRule {
    // Properties
    private SolidProperties sp;
    private MoveProperties mp;
    private OtherProperties op;
    private OtherCounters oc;
    private VisionProperties vp;
    private CustomCounters cc;
    private CustomFlags cf;
    private CustomTexts ct;
    private TypeProperties tp;
    private RuleSet ruleSet;
    private MazeObject savedObject;
    private static final int NO_CUSTOM_COUNTERS = 0;

    // Constructors
    public MazeObject(final boolean isSolid) {
	this.sp = new SolidProperties();
	this.sp.setSolid(isSolid);
	this.mp = new MoveProperties();
	this.op = new OtherProperties();
	this.oc = new OtherCounters();
	this.vp = new VisionProperties();
	this.cc = new CustomCounters();
	this.cf = new CustomFlags();
	this.ct = new CustomTexts();
	this.tp = new TypeProperties();
    }

    public MazeObject(final boolean isSolidXN, final boolean isSolidXS, final boolean isSolidXE,
	    final boolean isSolidXW, final boolean isSolidIN, final boolean isSolidIS, final boolean isSolidIE,
	    final boolean isSolidIW) {
	this.sp = new SolidProperties();
	this.sp.setDirectionallySolid(true, Directions.NORTH, isSolidXN);
	this.sp.setDirectionallySolid(true, Directions.SOUTH, isSolidXS);
	this.sp.setDirectionallySolid(true, Directions.EAST, isSolidXE);
	this.sp.setDirectionallySolid(true, Directions.WEST, isSolidXW);
	this.sp.setDirectionallySolid(false, Directions.NORTH, isSolidIN);
	this.sp.setDirectionallySolid(false, Directions.SOUTH, isSolidIS);
	this.sp.setDirectionallySolid(false, Directions.EAST, isSolidIE);
	this.sp.setDirectionallySolid(false, Directions.WEST, isSolidIW);
	this.sp.setDirectionallySolid(true, Directions.NORTHEAST, true);
	this.sp.setDirectionallySolid(true, Directions.SOUTHEAST, true);
	this.sp.setDirectionallySolid(true, Directions.NORTHWEST, true);
	this.sp.setDirectionallySolid(true, Directions.SOUTHWEST, true);
	this.sp.setDirectionallySolid(false, Directions.NORTHEAST, true);
	this.sp.setDirectionallySolid(false, Directions.SOUTHEAST, true);
	this.sp.setDirectionallySolid(false, Directions.NORTHWEST, true);
	this.sp.setDirectionallySolid(false, Directions.SOUTHWEST, true);
	this.mp = new MoveProperties();
	this.op = new OtherProperties();
	this.oc = new OtherCounters();
	this.vp = new VisionProperties();
	this.vp.setDirectionallySightBlocking(true, Directions.NORTH, isSolidXN);
	this.vp.setDirectionallySightBlocking(true, Directions.SOUTH, isSolidXS);
	this.vp.setDirectionallySightBlocking(true, Directions.EAST, isSolidXE);
	this.vp.setDirectionallySightBlocking(true, Directions.WEST, isSolidXW);
	this.vp.setDirectionallySightBlocking(false, Directions.NORTH, isSolidIN);
	this.vp.setDirectionallySightBlocking(false, Directions.SOUTH, isSolidIS);
	this.vp.setDirectionallySightBlocking(false, Directions.EAST, isSolidIE);
	this.vp.setDirectionallySightBlocking(false, Directions.WEST, isSolidIW);
	this.vp.setDirectionallySightBlocking(true, Directions.NORTHEAST, true);
	this.vp.setDirectionallySightBlocking(true, Directions.SOUTHEAST, true);
	this.vp.setDirectionallySightBlocking(true, Directions.NORTHWEST, true);
	this.vp.setDirectionallySightBlocking(true, Directions.SOUTHWEST, true);
	this.vp.setDirectionallySightBlocking(false, Directions.NORTHEAST, true);
	this.vp.setDirectionallySightBlocking(false, Directions.SOUTHEAST, true);
	this.vp.setDirectionallySightBlocking(false, Directions.NORTHWEST, true);
	this.vp.setDirectionallySightBlocking(false, Directions.SOUTHWEST, true);
	this.cc = new CustomCounters();
	this.cf = new CustomFlags();
	this.ct = new CustomTexts();
	this.tp = new TypeProperties();
    }

    public MazeObject(final boolean isSolid, final boolean isPushable, final boolean doesAcceptPushInto,
	    final boolean doesAcceptPushOut, final boolean isPullable, final boolean doesAcceptPullInto,
	    final boolean doesAcceptPullOut, final boolean hasFriction, final boolean isUsable, final int newUses) {
	this.sp = new SolidProperties();
	this.sp.setSolid(isSolid);
	this.mp = new MoveProperties();
	this.mp.setPushable(isPushable);
	this.mp.setPushableInto(doesAcceptPushInto);
	this.mp.setPushableOut(doesAcceptPushOut);
	this.mp.setPullable(isPullable);
	this.mp.setPullableInto(doesAcceptPullInto);
	this.mp.setPullableOut(doesAcceptPullOut);
	this.op = new OtherProperties();
	this.op.setFriction(hasFriction);
	this.op.setUsable(isUsable);
	this.oc = new OtherCounters();
	this.oc.setUses(newUses);
	this.vp = new VisionProperties();
	this.vp.setSightBlocking(isSolid);
	this.cc = new CustomCounters();
	this.cf = new CustomFlags();
	this.ct = new CustomTexts();
	this.tp = new TypeProperties();
    }

    public MazeObject(final boolean isSolid, final boolean isPushable, final boolean doesAcceptPushInto,
	    final boolean doesAcceptPushOut, final boolean isPullable, final boolean doesAcceptPullInto,
	    final boolean doesAcceptPullOut, final boolean hasFriction, final boolean isUsable, final int newUses,
	    final boolean isDestroyable, final boolean doesChainReact) {
	this.sp = new SolidProperties();
	this.sp.setSolid(isSolid);
	this.mp = new MoveProperties();
	this.mp.setPushable(isPushable);
	this.mp.setPushableInto(doesAcceptPushInto);
	this.mp.setPushableOut(doesAcceptPushOut);
	this.mp.setPullable(isPullable);
	this.mp.setPullableInto(doesAcceptPullInto);
	this.mp.setPullableOut(doesAcceptPullOut);
	this.op = new OtherProperties();
	this.op.setFriction(hasFriction);
	this.op.setUsable(isUsable);
	this.op.setDestroyable(isDestroyable);
	this.op.setChainReacting(doesChainReact);
	this.oc = new OtherCounters();
	this.oc.setUses(newUses);
	this.vp = new VisionProperties();
	this.cc = new CustomCounters();
	this.cf = new CustomFlags();
	this.ct = new CustomTexts();
	this.tp = new TypeProperties();
    }

    public MazeObject(final boolean isSolid, final boolean isUsable, final int newUses,
	    final boolean canBeInventoried) {
	this.sp = new SolidProperties();
	this.sp.setSolid(isSolid);
	this.mp = new MoveProperties();
	this.op = new OtherProperties();
	this.op.setUsable(isUsable);
	this.op.setCarryable(canBeInventoried);
	this.oc = new OtherCounters();
	this.oc.setUses(newUses);
	this.vp = new VisionProperties();
	this.cc = new CustomCounters();
	this.cf = new CustomFlags();
	this.ct = new CustomTexts();
	this.tp = new TypeProperties();
    }

    public MazeObject() {
	this.sp = new SolidProperties();
	this.mp = new MoveProperties();
	this.op = new OtherProperties();
	this.oc = new OtherCounters();
	this.vp = new VisionProperties();
	this.cc = new CustomCounters();
	this.cf = new CustomFlags();
	this.ct = new CustomTexts();
	this.tp = new TypeProperties();
    }

    // Copy constructor
    public MazeObject(final MazeObject source) {
	this.sp = new SolidProperties(source.sp);
	this.mp = new MoveProperties(source.mp);
	this.op = new OtherProperties(source.op);
	this.oc = new OtherCounters(source.oc);
	this.vp = new VisionProperties(source.vp);
	this.cc = new CustomCounters(source.cc);
	this.cf = new CustomFlags(source.cf);
	this.ct = new CustomTexts(source.ct);
	this.tp = new TypeProperties(source.tp);
    }

    // General methods
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (this.mp == null ? 0 : this.mp.hashCode());
	result = prime * result + (this.sp == null ? 0 : this.sp.hashCode());
	result = prime * result + (this.op == null ? 0 : this.op.hashCode());
	result = prime * result + (this.oc == null ? 0 : this.oc.hashCode());
	result = prime * result + (this.vp == null ? 0 : this.vp.hashCode());
	result = prime * result + (this.cc == null ? 0 : this.cc.hashCode());
	result = prime * result + (this.cf == null ? 0 : this.cf.hashCode());
	result = prime * result + (this.ct == null ? 0 : this.ct.hashCode());
	result = prime * result + (this.tp == null ? 0 : this.tp.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof MazeObject)) {
	    return false;
	}
	final MazeObject other = (MazeObject) obj;
	if (this.mp == null) {
	    if (other.mp != null) {
		return false;
	    }
	} else if (!this.mp.equals(other.mp)) {
	    return false;
	}
	if (this.sp == null) {
	    if (other.sp != null) {
		return false;
	    }
	} else if (!this.sp.equals(other.sp)) {
	    return false;
	}
	if (this.op == null) {
	    if (other.op != null) {
		return false;
	    }
	} else if (!this.op.equals(other.op)) {
	    return false;
	}
	if (this.oc == null) {
	    if (other.oc != null) {
		return false;
	    }
	} else if (!this.oc.equals(other.oc)) {
	    return false;
	}
	if (this.vp == null) {
	    if (other.vp != null) {
		return false;
	    }
	} else if (!this.vp.equals(other.vp)) {
	    return false;
	}
	if (this.cc == null) {
	    if (other.cc != null) {
		return false;
	    }
	} else if (!this.cc.equals(other.cc)) {
	    return false;
	}
	if (this.cf == null) {
	    if (other.cf != null) {
		return false;
	    }
	} else if (!this.cf.equals(other.cf)) {
	    return false;
	}
	if (this.ct == null) {
	    if (other.ct != null) {
		return false;
	    }
	} else if (!this.ct.equals(other.ct)) {
	    return false;
	}
	if (this.tp == null) {
	    if (other.tp != null) {
		return false;
	    }
	} else if (!this.tp.equals(other.tp)) {
	    return false;
	}
	return true;
    }

    // Object state methods
    public final MazeObject getSavedObject() {
	return this.savedObject;
    }

    public final boolean hasSavedObject() {
	return this.savedObject != null;
    }

    public final void setSavedObject(final MazeObject newSaved) {
	this.savedObject = newSaved;
    }

    public final boolean hasRuleSet() {
	return this.ruleSet != null;
    }

    public final void giveRuleSet() {
	this.ruleSet = new RuleSet();
    }

    public final void takeRuleSet() {
	this.ruleSet = null;
    }

    public final RuleSet getRuleSet() {
	return this.ruleSet;
    }

    public final boolean isSolid() {
	return this.sp.isSolid();
    }

    public final boolean isDirectionallySolid(final boolean ie, final int dirX, final int dirY) {
	return this.sp.isDirectionallySolid(ie, dirX, dirY);
    }

    public final boolean isSightBlocking() {
	return this.vp.isSightBlocking();
    }

    public final boolean isDirectionallySightBlocking(final boolean ie, final int dirX, final int dirY) {
	return this.vp.isDirectionallySightBlocking(ie, dirX, dirY);
    }

    public final boolean isOfType(final int testType) {
	int uid = this.getUniqueID().ordinal();
	MazeObjectActions actions = DataLoader.loadObjectActionData(uid);
	if (actions.has(MazeObjectActions.ALTER_SCORE)
		&& (testType == TypeConstants.TYPE_SCORE_INCREASER || testType == TypeConstants.TYPE_CONTAINABLE)) {
	    return true;
	}
	return this.tp.isOfType(testType);
    }

    protected final void setType(final int newType) {
	this.tp.setType(newType, true);
    }

    public final boolean isPushable() {
	return this.mp.isPushable();
    }

    public final boolean isDirectionallyPushable(final int dirX, final int dirY) {
	return this.mp.isDirectionallyPushable(dirX, dirY);
    }

    public final boolean isPullable() {
	return this.mp.isPullable();
    }

    public final boolean isDirectionallyPullable(final int dirX, final int dirY) {
	return this.mp.isDirectionallyPullable(dirX, dirY);
    }

    public final boolean isPullableInto() {
	return this.mp.isPullableInto();
    }

    public final boolean isDirectionallyPullableInto(final int dirX, final int dirY) {
	return this.mp.isDirectionallyPullableInto(dirX, dirY);
    }

    public final boolean isPushableInto() {
	return this.mp.isPushableInto();
    }

    public final boolean isDirectionallyPushableInto(final int dirX, final int dirY) {
	return this.mp.isDirectionallyPushableInto(dirX, dirY);
    }

    public final boolean isPullableOut() {
	return this.mp.isPullableOut();
    }

    public final boolean isDirectionallyPullableOut(final int dirX, final int dirY) {
	return this.mp.isDirectionallyPullableOut(dirX, dirY);
    }

    public final boolean isPushableOut() {
	return this.mp.isPushableOut();
    }

    public final boolean isDirectionallyPushableOut(final int dirX, final int dirY) {
	return this.mp.isDirectionallyPushableOut(dirX, dirY);
    }

    public final boolean hasFriction() {
	return this.op.hasFriction();
    }

    public final boolean isUsable() {
	return this.op.isUsable();
    }

    public final int getUses() {
	return this.oc.getUses();
    }

    public final boolean isDestroyable() {
	return this.op.isDestroyable();
    }

    public final boolean doesChainReact() {
	return this.op.isChainReacting();
    }

    public final boolean doesChainReactHorizontally() {
	return this.op.isChainReactingHorizontally();
    }

    public final boolean doesChainReactVertically() {
	return this.op.isChainReactingVertically();
    }

    public final boolean isInventoryable() {
	return this.op.isCarryable();
    }

    public final void activateTimer(final int ticks) {
	this.op.setTimerTicking(true);
	this.oc.setTimerTicks(ticks);
	this.oc.setTimerReset(ticks);
    }

    public final void deactivateTimer() {
	this.op.setTimerTicking(false);
	this.oc.setTimerTicks(0);
	this.oc.setTimerReset(0);
    }

    public final void extendTimer(final int ticks) {
	if (this.op.isTimerTicking()) {
	    this.oc.extendTimer(ticks);
	}
    }

    public final void extendTimerByInitialValue() {
	if (this.op.isTimerTicking()) {
	    this.oc.extendTimerByReset();
	}
    }

    public final void resetTimer() {
	if (this.op.isTimerTicking()) {
	    this.oc.resetTimer();
	}
    }

    public final void tickTimer(final int dirX, final int dirY) {
	if (this.op.isTimerTicking()) {
	    this.oc.tickTimer();
	    if (this.oc.timeExpired()) {
		this.op.setTimerTicking(false);
		this.oc.setTimerReset(0);
		this.timerExpiredAction(dirX, dirY);
	    }
	}
    }

    public final boolean canMove() {
	return this.isOfType(TypeConstants.TYPE_MOVABLE);
    }

    public final boolean isMoving() {
	return this.isOfType(TypeConstants.TYPE_MOVING);
    }

    public final boolean isInfinite() {
	return this.isOfType(TypeConstants.TYPE_INFINITE_USE);
    }

    // Custom object state methods
    public final int customCounterCount() {
	return this.cc.length();
    }

    protected final boolean addCustomCounters(final int count) {
	return this.cc.add(count);
    }

    protected final void addOneCustomCounter() {
	this.cc.addOne();
    }

    public final int getCustomCounter(final int index) {
	return this.cc.get(index);
    }

    public final boolean decrementCustomCounter(final int index) {
	return this.cc.decrement(index);
    }

    public final boolean incrementCustomCounter(final int index) {
	return this.cc.increment(index);
    }

    public final boolean offsetCustomCounter(final int index, final int value) {
	return this.cc.offset(index, value);
    }

    public final boolean setCustomCounter(final int index, final int value) {
	return this.cc.set(index, value);
    }

    public final int customFlagCount() {
	return this.cf.length();
    }

    protected final boolean addCustomFlags(final int count) {
	return this.cf.add(count);
    }

    protected final void addOneCustomFlag() {
	this.cf.addOne();
    }

    public final boolean getCustomFlag(final int index) {
	return this.cf.get(index);
    }

    public final boolean toggleCustomFlag(final int index) {
	return this.cf.toggle(index);
    }

    public final boolean setCustomFlag(final int index, final boolean value) {
	return this.cf.set(index, value);
    }

    public final int customTextCount() {
	return this.ct.length();
    }

    protected final boolean addCustomTexts(final int count) {
	return this.ct.add(count);
    }

    protected final void addOneCustomText() {
	this.ct.addOne();
    }

    public final String getCustomText(final int index) {
	return this.ct.get(index);
    }

    public final boolean setCustomText(final int index, final String value) {
	return this.ct.set(index, value);
    }

    // Custom object state method aliases
    public int getDestinationRow() {
	return this.getCustomCounter(1);
    }

    public int getDestinationColumn() {
	return this.getCustomCounter(0);
    }

    public int getDestinationFloor() {
	return this.getCustomCounter(2);
    }

    public int getDestinationLevel() {
	return this.getCustomCounter(3);
    }

    public final int getRandomRangeX() {
	return this.getCustomCounter(1);
    }

    public final int getRandomRangeY() {
	return this.getCustomCounter(0);
    }

    public final void setDestinationRow(final int destinationRow) {
	this.setCustomCounter(1, destinationRow);
    }

    public final void setDestinationColumn(final int destinationColumn) {
	this.setCustomCounter(0, destinationColumn);
    }

    public final void setDestinationFloor(final int destinationFloor) {
	this.setCustomCounter(2, destinationFloor);
    }

    public void setDestinationLevel(final int destinationLevel) {
	this.setCustomCounter(3, destinationLevel);
    }

    public final void setRandomRangeX(final int rrx) {
	this.setCustomCounter(1, rrx);
    }

    public final void setRandomRangeY(final int rry) {
	this.setCustomCounter(0, rry);
    }

    public final int getDestinationRow2() {
	return this.getCustomCounter(3);
    }

    public final int getDestinationColumn2() {
	return this.getCustomCounter(4);
    }

    public final int getDestinationFloor2() {
	return this.getCustomCounter(5);
    }

    public final int getTriggerValue() {
	return this.getCustomCounter(6);
    }

    public final int getSunMoon() {
	return this.getCustomCounter(7);
    }

    public final void setDestinationRow2(final int destinationRow2) {
	this.setCustomCounter(3, destinationRow2);
    }

    public final void setDestinationColumn2(final int destinationColumn2) {
	this.setCustomCounter(4, destinationColumn2);
    }

    public final void setDestinationFloor2(final int destinationFloor2) {
	this.setCustomCounter(5, destinationFloor2);
    }

    public final void setTriggerValue(final int t) {
	this.setCustomCounter(6, t);
    }

    public final void setSunMoon(final int sm) {
	this.setCustomCounter(7, sm);
    }

    protected final int getKeyCount() {
	return this.getCustomCounter(0);
    }

    protected final void setKeyCount(final int kc) {
	this.setCustomCounter(0, kc);
    }

    protected final String getSignText() {
	return this.getCustomText(0);
    }

    protected final void setSignText(final String st) {
	this.setCustomText(0, st);
    }

    protected final int getRotationRadius() {
	return this.getCustomCounter(0);
    }

    protected final void setRotationRadius(final int rr) {
	this.setCustomCounter(0, rr);
    }

    protected final boolean getRotationDirection() {
	return this.getCustomFlag(0);
    }

    protected final void setRotationDirection(final boolean rd) {
	this.setCustomFlag(0, rd);
    }

    // Scripting
    public boolean isConditionallySolid(final ObjectInventory inv) {
	// Handle ghost amulet and passwall boots
	if (inv.isItemThere(MazeObjects.GHOST_AMULET) || inv.isItemThere(MazeObjects.PASSWALL_BOOTS)) {
	    return false;
	} else {
	    return this.isSolid();
	}
    }

    public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	// Handle ghost amulet and passwall boots
	if (inv.isItemThere(MazeObjects.GHOST_AMULET) || inv.isItemThere(MazeObjects.PASSWALL_BOOTS)) {
	    return false;
	} else {
	    return this.isDirectionallySolid(ie, dirX, dirY);
	}
    }

    /**
     *
     * @param ie
     * @param dirX
     * @param dirY
     * @param inv
     * @return
     */
    public boolean preMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	return true;
    }

    public final void postMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	int uid = this.getUniqueID().ordinal();
	MazeObjectActions actions = DataLoader.loadObjectActionData(uid);
	if (actions.has(MazeObjectActions.ALTER_SCORE)) {
	    int alterScoreAmount = DataLoader.loadObjectActionAddonData(uid, MazeObjectActions.ALTER_SCORE);
	    Mazer5D.getBagOStuff().getGameManager().decay();
	    SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
	    Mazer5D.getBagOStuff().getGameManager().addToScore(alterScoreAmount);
	    Mazer5D.getBagOStuff().getGameManager().redrawMaze();
	} else {
	    this.customPostMoveAction(ie, dirX, dirY, inv);
	}
    }

    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    /**
     *
     * @param ie
     * @param dirX
     * @param dirY
     * @param inv
     */
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
	Mazer5D.getBagOStuff().showMessage("Can't go that way");
    }

    /**
     *
     * @param inv
     * @param moving
     * @return
     */
    public boolean hasFrictionConditionally(final ObjectInventory inv, final boolean moving) {
	return this.hasFriction();
    }

    public void gameProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName());
    }

    public void editorPlaceHook() {
	// Do nothing
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void editorGenerateHook(final int x, final int y, final int z) {
	// Do nothing
    }

    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName());
    }

    public MazeObject editorPropertiesHook() {
	return null;
    }

    /**
     *
     * @param inv
     * @param mo
     * @param x
     * @param y
     * @param pushX
     * @param pushY
     */
    public void pushAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pushX,
	    final int pushY) {
	// Do nothing
    }

    /**
     *
     * @param inv
     * @param pushed
     * @param x
     * @param y
     * @param z
     */
    public void pushIntoAction(final ObjectInventory inv, final MazeObject pushed, final int x, final int y,
	    final int z) {
	// Do nothing
    }

    /**
     *
     * @param inv
     * @param pushed
     * @param x
     * @param y
     * @param z
     */
    public void pushOutAction(final ObjectInventory inv, final MazeObject pushed, final int x, final int y,
	    final int z) {
	// Do nothing
    }

    /**
     *
     * @param inv
     * @param x
     * @param y
     * @param pushX
     * @param pushY
     */
    public void pushFailedAction(final ObjectInventory inv, final int x, final int y, final int pushX,
	    final int pushY) {
	// Play push failed sound, if it's enabled
	SoundPlayer.playSound(SoundIndex.ACTION_FAILED, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().keepNextMessage();
	Mazer5D.getBagOStuff().showMessage("Can't push that");
    }

    /**
     *
     * @param inv
     * @param mo
     * @param x
     * @param y
     * @param pullX
     * @param pullY
     */
    public void pullAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pullX,
	    final int pullY) {
	// Do nothing
    }

    /**
     *
     * @param inv
     * @param pulled
     * @param x
     * @param y
     * @param z
     */
    public void pullIntoAction(final ObjectInventory inv, final MazeObject pulled, final int x, final int y,
	    final int z) {
	// Do nothing
    }

    /**
     *
     * @param inv
     * @param pulled
     * @param x
     * @param y
     * @param z
     */
    public void pullOutAction(final ObjectInventory inv, final MazeObject pulled, final int x, final int y,
	    final int z) {
	// Do nothing
    }

    /**
     *
     * @param inv
     * @param x
     * @param y
     * @param pullX
     * @param pullY
     */
    public void pullFailedAction(final ObjectInventory inv, final int x, final int y, final int pullX,
	    final int pullY) {
	SoundPlayer.playSound(SoundIndex.ACTION_FAILED, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().keepNextMessage();
	Mazer5D.getBagOStuff().showMessage("Can't pull that");
    }

    /**
     *
     * @param mo
     * @param x
     * @param y
     * @param z
     */
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	// Do nothing
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void useHelper(final int x, final int y, final int z) {
	// Do nothing
    }

    /**
     *
     * @param dirX
     * @param dirY
     */
    public void timerExpiredAction(final int dirX, final int dirY) {
	// Do nothing
    }

    /**
     *
     * @param locX
     * @param locY
     * @param locZ
     * @param dirX
     * @param dirY
     * @param arrowType
     * @param inv
     * @return
     */
    public final boolean arrowHitAction(final int locX, final int locY, final int locZ, final int dirX, final int dirY,
	    final int arrowType, final ObjectInventory inv) {
	// Stop non-ghost arrows passing through solid objects
	if (arrowType == ArrowTypes.GHOST) {
	    return true;
	} else {
	    int uid = this.getUniqueID().ordinal();
	    MazeObjectActions actions = DataLoader.loadObjectActionData(uid);
	    if (actions.has(MazeObjectActions.ALTER_SCORE)) {
		// Score changers shatter when struck by arrows
		Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
		SoundPlayer.playSound(SoundIndex.SHATTER, SoundGroup.GAME);
		return false;
	    }
	    return customArrowHitAction(locX, locY, locZ, dirX, dirY, arrowType, inv);
	}
    }

    /**
     *
     * @param locX
     * @param locY
     * @param locZ
     * @param dirX
     * @param dirY
     * @param arrowType
     * @param inv
     * @return
     */
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	return !this.isConditionallySolid(inv);
    }

    public MazeObject gameRenderHook() {
	return this;
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void chainReactionAction(final int x, final int y, final int z) {
	// Do nothing
    }

    public boolean defersSetProperties() {
	return false;
    }

    public boolean overridesDefaultPostMove() {
	return false;
    }

    public String getGameName() {
	return this.getName();
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void determineCurrentAppearance(final int x, final int y, final int z) {
	// Do nothing
    }

    public void stepAction() {
	// Do nothing
    }

    abstract public String getName();

    abstract public String getPluralName();

    abstract public String getDescription();

    public final int getLayer() {
	int uid = this.getUniqueID().ordinal();
	MazeObjectActions actions = DataLoader.loadObjectActionData(uid);
	if (actions.has(MazeObjectActions.ALTER_SCORE)) {
	    return Layers.OBJECT;
	}
	return getLayerHook();
    }

    protected int getLayerHook() {
	return Layers._UNDEFINED;
    }

    public int getCustomFormat() {
	return 0;
    }

    @Override
    public boolean shouldGenerateObject(final Maze maze, final int row, final int col, final int floor, final int level,
	    final int layer) {
	if (layer == Layers.OBJECT) {
	    // Handle object layer
	    if (!this.isOfType(TypeConstants.TYPE_PASS_THROUGH)) {
		// Limit generation of other objects to 20%, unless required
		if (this.isRequired()) {
		    return true;
		} else {
		    final RandomRange r = new RandomRange(1, 100);
		    if (r.generate() <= 20) {
			return true;
		    } else {
			return false;
		    }
		}
	    } else {
		// Generate pass-through objects at 100%
		return true;
	    }
	} else {
	    // Handle ground layer
	    if (this.isOfType(TypeConstants.TYPE_FIELD)) {
		// Limit generation of fields to 20%
		final RandomRange r = new RandomRange(1, 100);
		if (r.generate() <= 20) {
		    return true;
		} else {
		    return false;
		}
	    } else {
		// Generate other ground at 100%
		return true;
	    }
	}
    }

    @Override
    public int getMinimumRequiredQuantity(final Maze maze) {
	return RandomGenerationRule.NO_LIMIT;
    }

    @Override
    public int getMaximumRequiredQuantity(final Maze maze) {
	return RandomGenerationRule.NO_LIMIT;
    }

    @Override
    public boolean isRequired() {
	return false;
    }

    // File I/O-related methods
    public final String getXMLIdentifier() {
	return this.getName();
    }

    public abstract MazeObjects getUniqueID();

    public final void writeMazeObjectXML(final MazeDataWriter writer) throws IOException {
	writer.writeString(this.getXMLIdentifier());
	final int ccf = this.customCounterCount();
	if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
	    for (int x = 0; x < ccf; x++) {
		writer.writeInt(this.getCustomCounter(x + 1));
	    }
	}
	this.writeMazeObjectHookXML(writer);
    }

    public final MazeObject readMazeObjectXML(final MazeDataReader reader, final String ident, final int ver)
	    throws IOException {
	if (ident.equals(this.getXMLIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHookXML(reader, ver);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObjectXML2(final MazeDataReader reader, final String ident, final int ver)
	    throws IOException {
	if (ident.equals(this.getXMLIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHookXML(reader, ver);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObjectXML3(final MazeDataReader reader, final String ident, final int ver)
	    throws IOException {
	if (ident.equals(this.getXMLIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHookXML(reader, ver);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObjectXML4(final MazeDataReader reader, final String ident, final int ver)
	    throws IOException {
	if (ident.equals(this.getXMLIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHookXML(reader, ver);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObjectXML5(final MazeDataReader reader, final String ident, final int ver)
	    throws IOException {
	if (ident.equals(this.getXMLIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHookXML(reader, ver);
	} else {
	    return null;
	}
    }

    /**
     *
     * @param writer
     * @throws IOException
     */
    protected void writeMazeObjectHookXML(final MazeDataWriter writer) throws IOException {
	// Do nothing - but let subclasses override
    }

    /**
     *
     * @param reader
     * @param formatVersion
     * @return
     * @throws IOException
     */
    protected MazeObject readMazeObjectHookXML(final MazeDataReader reader, final int formatVersion)
	    throws IOException {
	// Dummy implementation, subclasses can override
	return this;
    }
}
