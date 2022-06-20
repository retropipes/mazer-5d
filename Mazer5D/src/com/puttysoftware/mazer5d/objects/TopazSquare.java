/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class TopazSquare extends GenericMultipleKey {
    // Constructors
    public TopazSquare() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Topaz Square";
    }

    @Override
    protected String getPluralNameHook() {
	return "Topaz Squares";
    }

    @Override
    protected String getDescriptionHook() {
	return "Topaz Squares are the keys to Topaz Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TOPAZ_SQUARE;
    }
}