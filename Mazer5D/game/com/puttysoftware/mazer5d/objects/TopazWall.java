/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;

class TopazWall extends GenericMultipleLock {
    // Constructors
    public TopazWall() {
	super(new TopazSquare());
    }

    @Override
    protected String getNameHook() {
	return "Topaz Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Topaz Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Topaz Walls are impassable without enough Topaz Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TOPAZ_WALL;
    }
}