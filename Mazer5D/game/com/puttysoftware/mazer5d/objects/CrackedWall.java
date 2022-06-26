/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

class CrackedWall extends GenericWall {
    // Constructors
    public CrackedWall() {
	super();
	this.setType(TypeConstants.TYPE_BREAKABLE_WALL);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	this.moveFailedAction(true, locX, locY, inv);
	return false;
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Crack the wall
	final int pz = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationZ();
	Mazer5D.getBagOStuff().getGameManager().morph(new DamagedWall(), dirX, dirY, pz);
	SoundPlayer.playSound(SoundIndex.CRACK, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Cracked Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Cracked Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Cracked Walls turn into Damaged Walls when hit.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CRACKED_WALL;
    }
}