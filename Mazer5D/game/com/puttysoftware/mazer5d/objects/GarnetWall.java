/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;

class GarnetWall extends GenericMultipleLock {
    // Constructors
    public GarnetWall() {
	super(new GarnetSquare());
    }

    @Override
    protected String getNameHook() {
	return "Garnet Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Garnet Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Garnet Walls are impassable without enough Garnet Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GARNET_WALL;
    }
}