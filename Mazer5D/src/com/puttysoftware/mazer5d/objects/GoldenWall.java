/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GoldenWall extends GenericMultipleLock {
    // Constructors
    public GoldenWall() {
	super(new GoldenSquare());
    }

    @Override
    protected String getNameHook() {
	return "Golden Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Golden Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Golden Walls are impassable without enough Golden Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GOLDEN_WALL;
    }
}