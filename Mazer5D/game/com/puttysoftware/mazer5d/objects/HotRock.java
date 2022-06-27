/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericGround;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class HotRock extends GenericGround {
    // Constructors
    public HotRock() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Hot Rock";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Hot Rock";
    }

    @Override
    protected String getDescriptionHook() {
	return "Hot Rock is one of the many types of ground. It is created by Fire Amulets and Hot Boots, but can also exist on its own.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.HOT_ROCK;
    }
}