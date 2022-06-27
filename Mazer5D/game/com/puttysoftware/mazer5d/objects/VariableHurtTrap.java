/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;
import com.puttysoftware.mazer5d.utility.MazeObjects;
import com.puttysoftware.randomrange.RandomRange;

class VariableHurtTrap extends GenericTrap {
    // Fields
    private RandomRange damageDealt;
    private static final int MIN_DAMAGE = 1;
    private int maxDamage;

    // Constructors
    public VariableHurtTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Variable Hurt Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Variable Hurt Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	this.maxDamage = Mazer5D.getBagOStuff().getMazeManager().getMaze().getMaximumHP() / 10;
	if (this.maxDamage < VariableHurtTrap.MIN_DAMAGE) {
	    this.maxDamage = VariableHurtTrap.MIN_DAMAGE;
	}
	this.damageDealt = new RandomRange(VariableHurtTrap.MIN_DAMAGE, this.maxDamage);
	Mazer5D.getBagOStuff().getMazeManager().getMaze().doDamage(this.damageDealt.generate());
	SoundPlayer.playSound(SoundIndex.BARRIER, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().decay();
    }

    @Override
    protected String getDescriptionHook() {
	return "Variable Hurt Traps hurt you when stepped on, then disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.VARIABLE_HURT_TRAP;
    }
}