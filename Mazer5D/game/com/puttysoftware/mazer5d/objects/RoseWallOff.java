/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class RoseWallOff extends GenericToggleWall {
    // Constructors
    public RoseWallOff() {
        super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
        return "Rose Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
        return "Rose Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
        return "Rose Walls Off can be walked through, and will change to Rose Walls On when a Rose Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.ROSE_WALL_OFF;
    }
}