/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class SilformatVersionWall extends GenericMultipleLock {
    // Constructors
    public SilformatVersionWall() {
	super(new SilformatVersionSquare());
    }

    @Override
    protected String getNameHook() {
	return "SilformatVersion Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "SilformatVersion Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "SilformatVersion Walls are impassable without enough SilformatVersion Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SILVER_WALL;
    }
}