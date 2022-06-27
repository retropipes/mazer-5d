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

class ArrowTrap extends GenericTrap {
    // Constructors
    public ArrowTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Arrow Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Arrow Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage("The arrow is stopped!");
	return false;
    }

    @Override
    protected String getDescriptionHook() {
	return "Arrow Traps stop arrows.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ARROW_TRAP;
    }
}