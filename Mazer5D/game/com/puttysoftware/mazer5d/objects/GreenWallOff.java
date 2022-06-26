/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GreenWallOff extends GenericToggleWall {
    // Constructors
    public GreenWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Green Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
	return "Green Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
	return "Green Walls Off can be walked through, and will change to Green Walls On when a Green Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GREEN_WALL_OFF;
    }
}