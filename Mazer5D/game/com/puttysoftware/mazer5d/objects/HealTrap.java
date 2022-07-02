/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;

class HealTrap extends GenericTrap {
    // Fields
    private int healing;

    // Constructors
    public HealTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Heal Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Heal Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	this.healing = Mazer5D.getBagOStuff().getMazeManager().getMaze().getMaximumHP() / 50;
	if (this.healing < 1) {
	    this.healing = 1;
	}
	Mazer5D.getBagOStuff().getMazeManager().getMaze().heal(this.healing);
	SoundPlayer.playSound(SoundIndex.BARRIER, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Heal Traps heal you when stepped on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.HEAL_TRAP;
    }
}