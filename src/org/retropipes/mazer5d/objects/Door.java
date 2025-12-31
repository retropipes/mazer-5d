/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.objects.abc.GenericPassThroughObject;

class Door extends GenericPassThroughObject {
    // Constructors
    public Door() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Door";
    }

    @Override
    protected String getPluralNameHook() {
	return "Doors";
    }

    @Override
    protected String getDescriptionHook() {
	return "Doors are purely decorative, but they do stop arrows from passing through.";
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	return false;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DOOR;
    }
}