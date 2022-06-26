/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericGround;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Dirt extends GenericGround {
    // Constructors
    public Dirt() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Dirt";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Dirt";
    }

    @Override
    protected String getDescriptionHook() {
	return "Dirt is one of the many types of ground.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DIRT;
    }
}