/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBarrier;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class HorizontalBarrier extends GenericBarrier {
    // Constructors
    public HorizontalBarrier() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Horizontal Barrier";
    }

    @Override
    protected String getPluralNameHook() {
	return "Horizontal Barriers";
    }

    @Override
    protected String getDescriptionHook() {
	return "Horizontal Barriers are impassable - you'll need to go around them.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.HORIZONTAL_BARRIER;
    }
}