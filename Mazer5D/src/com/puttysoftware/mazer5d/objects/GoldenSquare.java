/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GoldenSquare extends GenericMultipleKey {
    // Constructors
    public GoldenSquare() {
	super();
    }

    @Override
    public String getName() {
	return "Golden Square";
    }

    @Override
    public String getPluralName() {
	return "Golden Squares";
    }

    @Override
    public String getDescription() {
	return "Golden Squares are the keys to Golden Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GOLDEN_SQUARE;
    }
}