/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class SkyWallOff extends GenericToggleWall {
    // Constructors
    public SkyWallOff() {
        super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
        return "Sky Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
        return "Sky Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
        return "Sky Walls Off can be walked through, and will change to Sky Walls On when a Sky Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.SKY_WALL_OFF;
    }
}