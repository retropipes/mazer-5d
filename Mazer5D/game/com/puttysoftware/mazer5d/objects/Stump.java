/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Stump extends GenericWall {
    // Constructors
    public Stump() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Stump";
    }

    @Override
    protected String getPluralNameHook() {
	return "Stumps";
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	return true;
    }

    @Override
    protected String getDescriptionHook() {
	return "Stumps stop movement, but not arrows.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.STUMP;
    }
}