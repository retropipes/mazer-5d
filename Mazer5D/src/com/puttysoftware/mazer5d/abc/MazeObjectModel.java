/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.editor.rulesets.RuleSet;
import com.puttysoftware.mazer5d.files.io.XDataReader;
import com.puttysoftware.mazer5d.files.io.XDataWriter;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.maze.MazeModel;
import com.puttysoftware.mazer5d.utilities.ArrowTypes;
import com.puttysoftware.mazer5d.utilities.Directions;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.RandomGenerationRule;
import com.puttysoftware.mazer5d.utilities.TypeConstants;
import com.puttysoftware.randomrange.RandomRange;

public abstract class MazeObjectModel implements RandomGenerationRule {
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
    public static final int DEFAULT_CUSTOM_VALUE = 0;
    protected static final int CUSTOM_FORMAT_MANUAL_OVERRIDE = -1;

    // Constructors
    public MazeObjectModel(final boolean isSolid) {
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

    public MazeObjectModel(final boolean isSolidXN, final boolean isSolidXS,
            final boolean isSolidXE, final boolean isSolidXW,
            final boolean isSolidIN, final boolean isSolidIS,
            final boolean isSolidIE, final boolean isSolidIW) {
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
        this.vp.setDirectionallySightBlocking(true, Directions.NORTH,
                isSolidXN);
        this.vp.setDirectionallySightBlocking(true, Directions.SOUTH,
                isSolidXS);
        this.vp.setDirectionallySightBlocking(true, Directions.EAST, isSolidXE);
        this.vp.setDirectionallySightBlocking(true, Directions.WEST, isSolidXW);
        this.vp.setDirectionallySightBlocking(false, Directions.NORTH,
                isSolidIN);
        this.vp.setDirectionallySightBlocking(false, Directions.SOUTH,
                isSolidIS);
        this.vp.setDirectionallySightBlocking(false, Directions.EAST,
                isSolidIE);
        this.vp.setDirectionallySightBlocking(false, Directions.WEST,
                isSolidIW);
        this.vp.setDirectionallySightBlocking(true, Directions.NORTHEAST, true);
        this.vp.setDirectionallySightBlocking(true, Directions.SOUTHEAST, true);
        this.vp.setDirectionallySightBlocking(true, Directions.NORTHWEST, true);
        this.vp.setDirectionallySightBlocking(true, Directions.SOUTHWEST, true);
        this.vp.setDirectionallySightBlocking(false, Directions.NORTHEAST,
                true);
        this.vp.setDirectionallySightBlocking(false, Directions.SOUTHEAST,
                true);
        this.vp.setDirectionallySightBlocking(false, Directions.NORTHWEST,
                true);
        this.vp.setDirectionallySightBlocking(false, Directions.SOUTHWEST,
                true);
        this.cc = new CustomCounters();
        this.cf = new CustomFlags();
        this.ct = new CustomTexts();
        this.tp = new TypeProperties();
    }

    public MazeObjectModel(final boolean isSolid, final boolean isPushable,
            final boolean doesAcceptPushInto, final boolean doesAcceptPushOut,
            final boolean isPullable, final boolean doesAcceptPullInto,
            final boolean doesAcceptPullOut, final boolean hasFriction,
            final boolean isUsable, final int newUses) {
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

    public MazeObjectModel(final boolean isSolid, final boolean isPushable,
            final boolean doesAcceptPushInto, final boolean doesAcceptPushOut,
            final boolean isPullable, final boolean doesAcceptPullInto,
            final boolean doesAcceptPullOut, final boolean hasFriction,
            final boolean isUsable, final int newUses,
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

    public MazeObjectModel(final boolean isSolid, final boolean isUsable,
            final int newUses, final boolean canBeInventoried) {
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

    public MazeObjectModel() {
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
    public MazeObjectModel(final MazeObjectModel source) {
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

    // Obsolete methods
    @Override
    public MazeObjectModel clone() {
        try {
            final MazeObjectModel copy = this.getClass().getConstructor()
                    .newInstance();
            copy.sp = new SolidProperties(this.sp);
            copy.mp = new MoveProperties(this.mp);
            copy.op = new OtherProperties(this.op);
            copy.oc = new OtherCounters(this.oc);
            copy.vp = new VisionProperties(this.vp);
            copy.cc = new CustomCounters(this.cc);
            copy.cf = new CustomFlags(this.cf);
            copy.ct = new CustomTexts(this.ct);
            copy.tp = new TypeProperties(this.tp);
            if (this.ruleSet != null) {
                copy.ruleSet = this.ruleSet.clone();
            }
            return copy;
        } catch (final InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new AssertionError("Should not ever get here!");
        }
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
        if (!(obj instanceof MazeObjectModel)) {
            return false;
        }
        final MazeObjectModel other = (MazeObjectModel) obj;
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
    public boolean hasRuleSet() {
        return this.ruleSet != null;
    }

    public void giveRuleSet() {
        this.ruleSet = new RuleSet();
    }

    public void takeRuleSet() {
        this.ruleSet = null;
    }

    public RuleSet getRuleSet() {
        return this.ruleSet;
    }

    public boolean isSolid() {
        return this.sp.isSolid();
    }

    public boolean isDirectionallySolid(final boolean ie, final int dirX,
            final int dirY) {
        return this.sp.isDirectionallySolid(ie, dirX, dirY);
    }

    public boolean isSightBlocking() {
        return this.vp.isSightBlocking();
    }

    public boolean isDirectionallySightBlocking(final boolean ie,
            final int dirX, final int dirY) {
        return this.vp.isDirectionallySightBlocking(ie, dirX, dirY);
    }

    public boolean isOfType(final int testType) {
        return this.tp.isOfType(testType);
    }

    protected void setType(final int newType) {
        this.tp.setType(newType, true);
    }

    public boolean isPushable() {
        return this.mp.isPushable();
    }

    public boolean isDirectionallyPushable(final int dirX, final int dirY) {
        return this.mp.isDirectionallyPushable(dirX, dirY);
    }

    public boolean isPullable() {
        return this.mp.isPullable();
    }

    public boolean isDirectionallyPullable(final int dirX, final int dirY) {
        return this.mp.isDirectionallyPullable(dirX, dirY);
    }

    public boolean isPullableInto() {
        return this.mp.isPullableInto();
    }

    public boolean isDirectionallyPullableInto(final int dirX, final int dirY) {
        return this.mp.isDirectionallyPullableInto(dirX, dirY);
    }

    public boolean isPushableInto() {
        return this.mp.isPushableInto();
    }

    public boolean isDirectionallyPushableInto(final int dirX, final int dirY) {
        return this.mp.isDirectionallyPushableInto(dirX, dirY);
    }

    public boolean isPullableOut() {
        return this.mp.isPullableOut();
    }

    public boolean isDirectionallyPullableOut(final int dirX, final int dirY) {
        return this.mp.isDirectionallyPullableOut(dirX, dirY);
    }

    public boolean isPushableOut() {
        return this.mp.isPushableOut();
    }

    public boolean isDirectionallyPushableOut(final int dirX, final int dirY) {
        return this.mp.isDirectionallyPushableOut(dirX, dirY);
    }

    public boolean hasFriction() {
        return this.op.hasFriction();
    }

    public boolean isUsable() {
        return this.op.isUsable();
    }

    public int getUses() {
        return this.oc.getUses();
    }

    public boolean isDestroyable() {
        return this.op.isDestroyable();
    }

    public boolean doesChainReact() {
        return this.op.isChainReacting();
    }

    public boolean doesChainReactHorizontally() {
        return this.op.isChainReactingHorizontally();
    }

    public boolean doesChainReactVertically() {
        return this.op.isChainReactingVertically();
    }

    public boolean isInventoryable() {
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

    // Scripting
    public boolean isConditionallySolid(final ObjectInventory inv) {
        // Handle ghost amulet and passwall boots
        if (inv.isItemThere(MazeObjects.GHOST_AMULET) || inv.isItemThere(
                MazeObjects.PASSWALL_BOOTS)) {
            return false;
        } else {
            return this.isSolid();
        }
    }

    public boolean isConditionallyDirectionallySolid(final boolean ie,
            final int dirX, final int dirY, final ObjectInventory inv) {
        // Handle ghost amulet and passwall boots
        if (inv.isItemThere(MazeObjects.GHOST_AMULET) || inv.isItemThere(
                MazeObjects.PASSWALL_BOOTS)) {
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
    public boolean preMoveAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        return true;
    }

    public abstract void postMoveAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv);

    /**
     *
     * @param ie
     * @param dirX
     * @param dirY
     * @param inv
     */
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
        Mazer5D.getBagOStuff().showMessage("Can't go that way");
    }

    /**
     *
     * @param inv
     * @param moving
     * @return
     */
    public boolean hasFrictionConditionally(final ObjectInventory inv,
            final boolean moving) {
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

    public MazeObjectModel editorPropertiesHook() {
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
    public void pushAction(final ObjectInventory inv, final MazeObjectModel mo,
            final int x, final int y, final int pushX, final int pushY) {
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
    public void pushIntoAction(final ObjectInventory inv,
            final MazeObjectModel pushed, final int x, final int y,
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
    public void pushOutAction(final ObjectInventory inv,
            final MazeObjectModel pushed, final int x, final int y,
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
    public void pushFailedAction(final ObjectInventory inv, final int x,
            final int y, final int pushX, final int pushY) {
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
    public void pullAction(final ObjectInventory inv, final MazeObjectModel mo,
            final int x, final int y, final int pullX, final int pullY) {
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
    public void pullIntoAction(final ObjectInventory inv,
            final MazeObjectModel pulled, final int x, final int y,
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
    public void pullOutAction(final ObjectInventory inv,
            final MazeObjectModel pulled, final int x, final int y,
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
    public void pullFailedAction(final ObjectInventory inv, final int x,
            final int y, final int pullX, final int pullY) {
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
    public void useAction(final MazeObjectModel mo, final int x, final int y,
            final int z) {
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
    public boolean arrowHitAction(final int locX, final int locY,
            final int locZ, final int dirX, final int dirY, final int arrowType,
            final ObjectInventory inv) {
        // Stop non-ghost arrows passing through solid objects
        if (arrowType == ArrowTypes.GHOST) {
            return true;
        } else {
            if (this.isConditionallySolid(inv)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public MazeObjectModel gameRenderHook() {
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
    public void determineCurrentAppearance(final int x, final int y,
            final int z) {
        // Do nothing
    }

    public void stepAction() {
        // Do nothing
    }

    abstract public String getName();

    public boolean canMove() {
        return false;
    }

    public boolean isMoving() {
        return false;
    }

    abstract public String getPluralName();

    abstract public String getDescription();

    abstract public int getLayer();

    abstract public int getCustomProperty(int propID);

    abstract public void setCustomProperty(int propID, int value);

    public int getCustomFormat() {
        return 0;
    }

    @Override
    public boolean shouldGenerateObject(final MazeModel maze, final int row,
            final int col, final int floor, final int level, final int layer) {
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
    public int getMinimumRequiredQuantity(final MazeModel maze) {
        return RandomGenerationRule.NO_LIMIT;
    }

    @Override
    public int getMaximumRequiredQuantity(final MazeModel maze) {
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

    public final void writeMazeObjectXML(final XDataWriter writer)
            throws IOException {
        writer.writeString(this.getXMLIdentifier());
        final int ccf = this.getCustomFormat();
        if (ccf == MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE) {
            this.writeMazeObjectHookXML(writer);
        } else {
            for (int x = 0; x < ccf; x++) {
                final int cx = this.getCustomProperty(x + 1);
                writer.writeInt(cx);
            }
        }
    }

    public final MazeObjectModel readMazeObjectXML(final XDataReader reader,
            final String ident, final int ver) throws IOException {
        if (ident.equals(this.getXMLIdentifier())) {
            final int ccf = this.getCustomFormat();
            if (ccf == MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE) {
                return this.readMazeObjectHookXML(reader, ver);
            } else {
                for (int x = 0; x < ccf; x++) {
                    final int cx = reader.readInt();
                    this.setCustomProperty(x + 1, cx);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    public final MazeObjectModel readMazeObjectXML2(final XDataReader reader,
            final String ident, final int ver) throws IOException {
        if (ident.equals(this.getXMLIdentifier())) {
            final int ccf = this.getCustomFormat();
            if (ccf == MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE) {
                return this.readMazeObjectHookXML(reader, ver);
            } else {
                for (int x = 0; x < ccf; x++) {
                    final int cx = reader.readInt();
                    this.setCustomProperty(x + 1, cx);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    public final MazeObjectModel readMazeObjectXML3(final XDataReader reader,
            final String ident, final int ver) throws IOException {
        if (ident.equals(this.getXMLIdentifier())) {
            final int ccf = this.getCustomFormat();
            if (ccf == MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE) {
                return this.readMazeObjectHookXML(reader, ver);
            } else {
                for (int x = 0; x < ccf; x++) {
                    final int cx = reader.readInt();
                    this.setCustomProperty(x + 1, cx);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    public final MazeObjectModel readMazeObjectXML4(final XDataReader reader,
            final String ident, final int ver) throws IOException {
        if (ident.equals(this.getXMLIdentifier())) {
            final int ccf = this.getCustomFormat();
            if (ccf == MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE) {
                return this.readMazeObjectHookXML(reader, ver);
            } else {
                for (int x = 0; x < ccf; x++) {
                    final int cx = reader.readInt();
                    this.setCustomProperty(x + 1, cx);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    public final MazeObjectModel readMazeObjectXML5(final XDataReader reader,
            final String ident, final int ver) throws IOException {
        if (ident.equals(this.getXMLIdentifier())) {
            final int ccf = this.getCustomFormat();
            if (ccf == MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE) {
                return this.readMazeObjectHookXML(reader, ver);
            } else {
                for (int x = 0; x < ccf; x++) {
                    final int cx = reader.readInt();
                    this.setCustomProperty(x + 1, cx);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    /**
     *
     * @param writer
     * @throws IOException
     */
    protected void writeMazeObjectHookXML(final XDataWriter writer)
            throws IOException {
        // Do nothing - but let subclasses override
    }

    /**
     *
     * @param reader
     * @param formatVersion
     * @return
     * @throws IOException
     */
    protected MazeObjectModel readMazeObjectHookXML(final XDataReader reader,
            final int formatVersion) throws IOException {
        // Dummy implementation, subclasses can override
        return this;
    }
}
