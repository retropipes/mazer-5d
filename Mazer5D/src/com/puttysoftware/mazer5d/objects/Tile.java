/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericGround;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

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
	return "Tile is one of the many types of ground - unlike other types of ground, objects can be pushed and pulled over Tiles.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TILE;
    }
}