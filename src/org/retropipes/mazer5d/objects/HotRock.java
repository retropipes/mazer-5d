/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericGround;

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