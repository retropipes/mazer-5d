/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class Crevasse extends GenericWall {
    // Constructors
    public Crevasse() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Crevasse";
    }

    @Override
    protected String getPluralNameHook() {
	return "Crevasses";
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	return true;
    }

    @Override
    protected String getDescriptionHook() {
	return "Crevasses stop movement, but not arrows, which pass oformatVersion them unimpeded.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CREVASSE;
    }
}