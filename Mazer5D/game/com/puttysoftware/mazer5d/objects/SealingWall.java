/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class SealingWall extends GenericWall {
    // Constructors
    public SealingWall() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Sealing Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sealing Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Sealing Walls are impassable and impossible to destroy.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SEALING_WALL;
    }
}