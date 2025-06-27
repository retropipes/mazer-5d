/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class RedWallOff extends GenericToggleWall {
    // Constructors
    public RedWallOff() {
        super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
        return "Red Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
        return "Red Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
        return "Red Walls Off can be walked through, and will change to Red Walls On when a Red Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.RED_WALL_OFF;
    }
}