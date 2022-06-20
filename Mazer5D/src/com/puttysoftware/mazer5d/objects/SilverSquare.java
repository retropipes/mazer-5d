/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class SilverSquare extends GenericMultipleKey {
    // Constructors
    public SilverSquare() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Silver Square";
    }

    @Override
    protected String getPluralNameHook() {
	return "Silver Squares";
    }

    @Override
    protected String getDescriptionHook() {
	return "Silver Squares are the keys to Silver Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SILVER_SQUARE;
    }
}