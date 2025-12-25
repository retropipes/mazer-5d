/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;

class GoldenSquare extends GenericMultipleKey {
    // Constructors
    public GoldenSquare() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Golden Square";
    }

    @Override
    protected String getPluralNameHook() {
	return "Golden Squares";
    }

    @Override
    protected String getDescriptionHook() {
	return "Golden Squares are the keys to Golden Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GOLDEN_SQUARE;
    }
}