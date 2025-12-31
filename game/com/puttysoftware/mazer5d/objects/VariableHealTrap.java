/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import org.retropipes.diane.random.RandomRange;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;

class VariableHealTrap extends GenericTrap {
    // Fields
    private RandomRange healingGiven;
    private static final int MIN_HEALING = 1;
    private int maxHealing;

    // Constructors
    public VariableHealTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Variable Heal Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Variable Heal Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	this.maxHealing = Mazer5D.getBagOStuff().getMazeManager().getMaze().getMaximumHP() / 10;
	if (this.maxHealing < VariableHealTrap.MIN_HEALING) {
	    this.maxHealing = VariableHealTrap.MIN_HEALING;
	}
	this.healingGiven = new RandomRange(VariableHealTrap.MIN_HEALING, this.maxHealing);
	Mazer5D.getBagOStuff().getMazeManager().getMaze().heal(this.healingGiven.generate());
	SoundPlayer.playSound(SoundIndex.BARRIER, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().decay();
    }

    @Override
    protected String getDescriptionHook() {
	return "Variable Heal Traps heal you when stepped on, then disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.VARIABLE_HEAL_TRAP;
    }
}