/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericWall;

class DamagedWall extends GenericWall {
    // Constructors
    public DamagedWall() {
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
	// Destroy the wall
	final int pz = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationZ();
	Mazer5D.getBagOStuff().getGameManager().morph(new CrumblingWall(), dirX, dirY, pz);
	SoundPlayer.playSound(SoundIndex.CRACK, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Damaged Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Damaged Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Damaged Walls turn into Crumbling Walls when hit.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DAMAGED_WALL;
    }
}