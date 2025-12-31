/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.diane.random.RandomRange;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericTrap;

class WarpTrap extends GenericTrap {
    // Fields
    private RandomRange rr, rc, rf;

    // Constructors
    public WarpTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Warp Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Warp Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int maxRow, maxCol, maxFloor, rRow, rCol, rFloor;
	maxRow = app.getMazeManager().getMaze().getRows() - 1;
	this.rr = new RandomRange(0, maxRow);
	maxCol = app.getMazeManager().getMaze().getColumns() - 1;
	this.rc = new RandomRange(0, maxCol);
	maxFloor = app.getMazeManager().getMaze().getFloors() - 1;
	this.rf = new RandomRange(0, maxFloor);
	do {
	    rRow = this.rr.generate();
	    rCol = this.rc.generate();
	    rFloor = this.rf.generate();
	} while (!app.getGameManager().tryUpdatePositionAbsolute(rRow, rCol, rFloor));
	app.getGameManager().updatePositionAbsolute(rRow, rCol, rFloor);
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Warp Traps send anything that steps on one to a random location.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WARP_TRAP;
    }
}
