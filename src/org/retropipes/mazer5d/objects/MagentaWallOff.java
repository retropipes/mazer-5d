/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class MagentaWallOff extends GenericToggleWall {
    // Constructors
    public MagentaWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Magenta Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
	return "Magenta Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
	return "Magenta Walls Off can be walked through, and will change to Magenta Walls On when a Magenta Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MAGENTA_WALL_OFF;
    }
}