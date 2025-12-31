/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;

class SilformatVersionSquare extends GenericMultipleKey {
    // Constructors
    public SilformatVersionSquare() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "SilformatVersion Square";
    }

    @Override
    protected String getPluralNameHook() {
	return "SilformatVersion Squares";
    }

    @Override
    protected String getDescriptionHook() {
	return "SilformatVersion Squares are the keys to SilformatVersion Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SILVER_SQUARE;
    }
}