/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.GenericToggleWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class RedWallOff extends GenericToggleWall {
    // Constructors
    public RedWallOff() {
        super(false);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Red Wall Off";
    }

    @Override
    public String getPluralName() {
        return "Red Walls Off";
    }

    @Override
    public String getDescription() {
        return "Red Walls Off can be walked through, and will change to Red Walls On when a Red Button is pressed.";
    }

    @Override
    public MazeObjects getUniqueID() {
        return MazeObjects.RED_WALL_OFF;
    }
}