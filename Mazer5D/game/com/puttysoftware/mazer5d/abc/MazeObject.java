/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.io.IOException;
import java.util.Objects;

import com.puttysoftware.diane.utilties.Directions;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.editor.ruleset.RuleSet;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.DataFileLoader;
import com.puttysoftware.mazer5d.loader.DataLoader;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.maze.Maze;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utility.ArrowTypes;
import com.puttysoftware.mazer5d.utility.Layers;
import com.puttysoftware.mazer5d.utility.MazeObjectActions;
import com.puttysoftware.mazer5d.utility.MazeObjects;
import com.puttysoftware.mazer5d.utility.RandomGenerationRule;
import com.puttysoftware.mazer5d.utility.TypeConstants;
import com.puttysoftware.randomrange.RandomRange;

public class MazeObject implements RandomGenerationRule {
    // Properties
    private final SolidProperties sp;
    private final MoveProperties mp;
    private final OtherProperties op;
    private final OtherCounters oc;
    private final VisionProperties vp;
    private final CustomCounters cc;
    private final CustomFlags cf;
    private final CustomTexts ct;
    private final TypeProperties tp;
    private RuleSet ruleSet;
    private MazeObject savedObject;
    private MazeObjects uniqueID;
    private static final int NO_CUSTOM_COUNTERS = 0;

    // Constructors
    public MazeObject(final MazeObjects uid) {
	this.uniqueID = uid;
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

    protected MazeObject(final boolean isSolid) {
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

    protected MazeObject(final boolean isSolidXN, final boolean isSolidXS, final boolean isSolidXE,
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

    protected MazeObject(final boolean isSolid, final boolean isPushable, final boolean doesAcceptPushInto,
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

    protected MazeObject(final boolean isSolid, final boolean isPushable, final boolean doesAcceptPushInto,
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

    protected MazeObject(final boolean isSolid, final boolean isUsable, final int newUses,
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

    // Copy constructor
    public MazeObject(final MazeObject source) {
	this.uniqueID = source.uniqueID;
	if (source.savedObject != null) {
	    this.savedObject = new MazeObject(source.savedObject);
	}
	if (source.ruleSet != null) {
	    this.ruleSet = new RuleSet(source.ruleSet);
	}
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
	return Objects.hash(this.cc, this.cf, this.ct, this.mp, this.oc, this.op, this.ruleSet, this.savedObject,
		this.sp, this.tp, this.uniqueID, this.vp);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof MazeObject)) {
	    return false;
	}
	MazeObject other = (MazeObject) obj;
	return Objects.equals(this.cc, other.cc) && Objects.equals(this.cf, other.cf)
		&& Objects.equals(this.ct, other.ct) && Objects.equals(this.mp, other.mp)
		&& Objects.equals(this.oc, other.oc) && Objects.equals(this.op, other.op)
		&& Objects.equals(this.ruleSet, other.ruleSet) && Objects.equals(this.savedObject, other.savedObject)
		&& Objects.equals(this.sp, other.sp) && Objects.equals(this.tp, other.tp)
		&& this.uniqueID == other.uniqueID && Objects.equals(this.vp, other.vp);
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
	int uid = this.getUniqueIDHook().ordinal();
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
	int uid = this.getUniqueIDHook().ordinal();
	MazeObjectActions actions = DataLoader.loadObjectActionData(uid);
	if (actions.has(MazeObjectActions.DISAPPEAR_MOVE)) {
	    Mazer5D.getBagOStuff().getGameManager().decay();
	    Mazer5D.getBagOStuff().getGameManager().redrawMaze();
	}
	if (actions.has(MazeObjectActions.SOUND_MOVE)) {
	    SoundIndex moveSound = SoundIndex.values()[DataLoader.loadObjectActionAddonData(uid,
		    MazeObjectActions.SOUND_MOVE)];
	    SoundPlayer.playSound(moveSound, SoundGroup.GAME);
	}
	if (actions.has(MazeObjectActions.ALTER_SCORE)) {
	    int alterScoreAmount = DataLoader.loadObjectActionAddonData(uid, MazeObjectActions.ALTER_SCORE);
	    Mazer5D.getBagOStuff().getGameManager().addToScore(alterScoreAmount);
	}
	if (!actions.any()) {
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
	Mazer5D.getBagOStuff().showMessage(Translations.load(Strings.MOVE_FAIL_DEFAULT));
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
	Mazer5D.getBagOStuff().showMessage(this.getNameHook());
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
	Mazer5D.getBagOStuff().showMessage(this.getNameHook());
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
	Mazer5D.getBagOStuff().showMessage(Translations.load(Strings.PUSH_FAIL_DEFAULT));
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
	Mazer5D.getBagOStuff().showMessage(Translations.load(Strings.PULL_FAIL_DEFAULT));
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
	int uid = this.getUniqueIDHook().ordinal();
	MazeObjectActions actions = DataLoader.loadObjectActionData(uid);
	if (actions.has(MazeObjectActions.DISAPPEAR_ARROW)) {
	    Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
	}
	if (actions.has(MazeObjectActions.SOUND_ARROW)) {
	    SoundIndex arrowSound = SoundIndex.values()[DataLoader.loadObjectActionAddonData(uid,
		    MazeObjectActions.SOUND_ARROW)];
	    SoundPlayer.playSound(arrowSound, SoundGroup.GAME);
	}
	if (arrowType == ArrowTypes.GHOST) {
	    // Ghost arrows pass through solid objects
	    return true;
	} else {
	    if (actions.any()) {
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

    public boolean oformatVersionridesDefaultPostMove() {
	return false;
    }

    // FIXME: Hack
    public String getGameName() {
	return StaticStrings.EMPTY;
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

    public final String getImageName() {
	return DataLoader.loadObjectImageData(this.getUniqueID().ordinal());
    }

    public final String getName() {
	return DataFileLoader.loadObjectName(this.getUniqueID());
    }

    // FIXME: Hack
    protected String getNameHook() {
	return StaticStrings.EMPTY;
    }

    public final String getPluralName() {
	return DataFileLoader.loadObjectPluralName(this.getUniqueID());
    }

    // FIXME: Hack
    protected String getPluralNameHook() {
	return StaticStrings.EMPTY;
    }

    public final String getDescription() {
	return DataFileLoader.loadObjectDescription(this.getUniqueID());
    }

    // FIXME: Hack
    protected String getDescriptionHook() {
	return StaticStrings.EMPTY;
    }

    public final int getLayer() {
	int uid = this.getUniqueIDHook().ordinal();
	int loadLayer = DataLoader.loadObjectLayerData(uid);
	if (loadLayer != Layers._UNDEFINED) {
	    return loadLayer;
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
    public final String getIdentifier() {
	return this.getNameHook();
    }

    public final MazeObjects getUniqueID() {
	if (this.uniqueID != null) {
	    return this.uniqueID;
	} else {
	    return this.getUniqueIDHook();
	}
    }

    protected MazeObjects getUniqueIDHook() {
	return MazeObjects._NONE;
    }

    public final void writeMazeObject(final MazeDataWriter writer) throws IOException {
	writer.writeString(this.getIdentifier());
	final int ccf = this.customCounterCount();
	if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
	    for (int x = 0; x < ccf; x++) {
		writer.writeInt(this.getCustomCounter(x + 1));
	    }
	}
	this.writeMazeObjectHook(writer);
    }

    public final MazeObject readMazeObject(final MazeDataReader reader, final String ident,
	    final MazeVersion formatVersion) throws IOException {
	if (ident.equals(this.getIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHook(reader, formatVersion);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObject2(final MazeDataReader reader, final String ident,
	    final MazeVersion formatVersion) throws IOException {
	if (ident.equals(this.getIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHook(reader, formatVersion);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObject3(final MazeDataReader reader, final String ident,
	    final MazeVersion formatVersion) throws IOException {
	if (ident.equals(this.getIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHook(reader, formatVersion);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObject4(final MazeDataReader reader, final String ident,
	    final MazeVersion formatVersion) throws IOException {
	if (ident.equals(this.getIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHook(reader, formatVersion);
	} else {
	    return null;
	}
    }

    public final MazeObject readMazeObject5(final MazeDataReader reader, final String ident,
	    final MazeVersion formatVersion) throws IOException {
	if (ident.equals(this.getIdentifier())) {
	    final int ccf = this.customCounterCount();
	    if (ccf != MazeObject.NO_CUSTOM_COUNTERS) {
		for (int x = 0; x < ccf; x++) {
		    final int cx = reader.readInt();
		    this.setCustomCounter(x + 1, cx);
		}
	    }
	    return this.readMazeObjectHook(reader, formatVersion);
	} else {
	    return null;
	}
    }

    /**
     *
     * @param writer
     * @throws IOException
     */
    protected void writeMazeObjectHook(final MazeDataWriter writer) throws IOException {
	// Do nothing - but let subclasses oformatVersionride
    }

    /**
     *
     * @param reader
     * @param formatVersion
     * @return
     * @throws IOException
     */
    protected MazeObject readMazeObjectHook(final MazeDataReader reader, final MazeVersion formatVersion)
	    throws IOException {
	// Dummy implementation, subclasses can oformatVersionride
	return this;
    }
}
