/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class OrangeWallOff extends GenericToggleWall {
    // Constructors
    public OrangeWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Orange Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
	return "Orange Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
	return "Orange Walls Off can be walked through, and will change to Orange Walls On when a Orange Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ORANGE_WALL_OFF;
    }
}