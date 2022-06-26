/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class SilverWall extends GenericMultipleLock {
    // Constructors
    public SilverWall() {
	super(new SilverSquare());
    }

    @Override
    protected String getNameHook() {
	return "Silver Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Silver Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Silver Walls are impassable without enough Silver Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SILVER_WALL;
    }
}