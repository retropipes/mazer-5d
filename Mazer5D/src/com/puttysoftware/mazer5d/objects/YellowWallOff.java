/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class YellowWallOff extends GenericToggleWall {
    // Constructors
    public YellowWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Yellow Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
	return "Yellow Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
	return "Yellow Walls Off can be walked through, and will change to Yellow Walls On when a Yellow Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.YELLOW_WALL_OFF;
    }
}