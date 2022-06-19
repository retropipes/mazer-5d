/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericGround;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class SunkenBlock extends GenericGround {
    // Constructors
    public SunkenBlock() {
	super(true, true, true, true);
    }

    @Override
    public String getName() {
	return "Sunken Block";
    }

    @Override
    public String getPluralName() {
	return "Sunken Blocks";
    }

    @Override
    public String getDescription() {
	return "Sunken Blocks are created when Pushable Blocks are pushed into Water, and behave just like Tiles.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.SUNKEN_BLOCK;
    }
}