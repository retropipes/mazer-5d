/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class CyanWallOff extends GenericToggleWall {
    // Constructors
    public CyanWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Cyan Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
	return "Cyan Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
	return "Cyan Walls Off can be walked through, and will change to Cyan Walls On when a Cyan Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CYAN_WALL_OFF;
    }
}