/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects.abc;

import org.retropipes.diane.random.RandomRange;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.maze.Maze;

public abstract class GenericPotion extends MazeObject {
    // Fields
    private int effectValue;
    private RandomRange effect;
    private boolean effectValueIsPercentage;
    private static final long SCORE_SMASH = 20L;
    private static final long SCORE_CONSUME = 50L;

    // Constructors
    protected GenericPotion(final boolean usePercent) {
	super(false);
	this.effectValueIsPercentage = usePercent;
	this.setType(TypeConstants.TYPE_POTION);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    protected GenericPotion(final boolean usePercent, final int min, final int max) {
	super(false);
	this.effectValueIsPercentage = usePercent;
	this.effect = new RandomRange(min, max);
	this.setType(TypeConstants.TYPE_POTION);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }

    @Override
    protected final void customPostMoveAction(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	if (this.effect != null) {
	    this.effectValue = this.effect.generate();
	} else {
	    this.effectValue = this.getEffectValue();
	}
	if (this.effectValueIsPercentage) {
	    if (this.effectValue >= 0) {
		m.healPercentage(this.effectValue);
	    } else {
		m.doDamagePercentage(-this.effectValue);
	    }
	} else {
	    if (this.effectValue >= 0) {
		m.heal(this.effectValue);
	    } else {
		m.doDamage(-this.effectValue);
	    }
	}
	Mazer5D.getBagOStuff().getGameManager().decay();
	if (this.effectValue >= 0) {
	    SoundPlayer.playSound(SoundIndex.HEAL, SoundGroup.GAME);
	} else {
	    SoundPlayer.playSound(SoundIndex.HURT, SoundGroup.GAME);
	}
	Mazer5D.getBagOStuff().getGameManager().addToScore(GenericPotion.SCORE_CONSUME);
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
	SoundPlayer.playSound(SoundIndex.SHATTER, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().addToScore(GenericPotion.SCORE_SMASH);
	return false;
    }

    public int getEffectValue() {
	if (this.effect != null) {
	    return this.effect.generate();
	} else {
	    return 0;
	}
    }
}