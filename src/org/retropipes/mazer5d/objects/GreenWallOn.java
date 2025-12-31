/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class GreenWallOn extends GenericToggleWall {
    // Constructors
    public GreenWallOn() {
	super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Green Wall On";
    }

    @Override
    protected String getPluralNameHook() {
	return "Green Walls On";
    }

    @Override
    protected String getDescriptionHook() {
	return "Green Walls On can NOT be walked through, and will change to Green Walls Off when a Green Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GREEN_WALL_ON;
    }
}