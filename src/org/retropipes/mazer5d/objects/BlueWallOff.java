/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class BlueWallOff extends GenericToggleWall {
    // Constructors
    public BlueWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Blue Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
	return "Blue Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
	return "Blue Walls Off can be walked through, and will change to Blue Walls On when a Blue Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BLUE_WALL_OFF;
    }
}