/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.objects.abc.GenericWall;

class FadingWall extends GenericWall {
    // Fields
    private static final int SCAN_LIMIT = 3;

    // Constructors
    public FadingWall() {
	super();
	this.activateTimer(1);
    }

    @Override
    public void timerExpiredAction(final int dirX, final int dirY) {
	// Disappear if the player is close to us
	boolean scanResult = false;
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int pz = app.getGameManager().getPlayerManager().getPlayerLocationZ();
	final int pl = Layers.OBJECT;
	final String targetName = new Player().getName();
	scanResult = app.getMazeManager().getMaze().radialScan(dirX, dirY, pz, pl, FadingWall.SCAN_LIMIT, targetName);
	if (scanResult) {
	    app.getGameManager().morph(GameObjects.getEmptySpace(), dirX, dirY, pz);
	}
	this.activateTimer(1);
    }

    @Override
    protected String getNameHook() {
	return "Fading Wall";
    }

    @Override
    public String getGameName() {
	return "Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Fading Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Fading Walls disappear when you get close to them.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FADING_WALL;
    }
}
