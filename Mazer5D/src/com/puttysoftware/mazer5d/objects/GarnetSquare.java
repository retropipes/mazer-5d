/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GarnetSquare extends GenericMultipleKey {
    // Constructors
    public GarnetSquare() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Garnet Square";
    }

    @Override
    protected String getPluralNameHook() {
	return "Garnet Squares";
    }

    @Override
    protected String getDescriptionHook() {
	return "Garnet Squares are the keys to Garnet Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GARNET_SQUARE;
    }
}