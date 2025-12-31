/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class OrangeWallOn extends GenericToggleWall {
    // Constructors
    public OrangeWallOn() {
	super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Orange Wall On";
    }

    @Override
    protected String getPluralNameHook() {
	return "Orange Walls On";
    }

    @Override
    protected String getDescriptionHook() {
	return "Orange Walls On can NOT be walked through, and will change to Orange Walls Off when a Orange Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ORANGE_WALL_ON;
    }
}