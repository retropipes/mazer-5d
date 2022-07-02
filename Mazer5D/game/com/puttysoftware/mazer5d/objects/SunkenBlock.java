/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericGround;

class SunkenBlock extends GenericGround {
    // Constructors
    public SunkenBlock() {
	super(true, true, true, true);
    }

    @Override
    protected String getNameHook() {
	return "Sunken Block";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sunken Blocks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Sunken Blocks are created when Pushable Blocks are pushed into Water, and behave just like Tiles.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SUNKEN_BLOCK;
    }
}