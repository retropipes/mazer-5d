/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class WhiteWallOff extends GenericToggleWall {
    // Constructors
    public WhiteWallOff() {
        super(false);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
        return "White Wall Off";
    }

    @Override
    protected String getPluralNameHook() {
        return "White Walls Off";
    }

    @Override
    protected String getDescriptionHook() {
        return "White Walls Off can be walked through, and will change to White Walls On when a White Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.WHITE_WALL_OFF;
    }
}