/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class MagentaWallOff extends GenericToggleWall {
    // Constructors
    public MagentaWallOff() {
	super(false);
    }

    // Scriptability
    @Override
    public String getName() {
	return "Magenta Wall Off";
    }

    @Override
    public String getPluralName() {
	return "Magenta Walls Off";
    }

    @Override
    public String getDescription() {
	return "Magenta Walls Off can be walked through, and will change to Magenta Walls On when a Magenta Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MAGENTA_WALL_OFF;
    }
}