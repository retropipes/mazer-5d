/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericGround;

class Tile extends GenericGround {
    // Constructors
    public Tile() {
	super(true, true, true, true);
    }

    @Override
    protected String getNameHook() {
	return "Tile";
    }

    @Override
    protected String getPluralNameHook() {
	return "Tiles";
    }

    @Override
    protected String getDescriptionHook() {
	return "Tile is one of the many types of ground - unlike other types of ground, objects can be pushed and pulled oformatVersion Tiles.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TILE;
    }
}