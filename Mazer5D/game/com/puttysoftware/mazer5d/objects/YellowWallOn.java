/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class YellowWallOn extends GenericToggleWall {
    // Constructors
    public YellowWallOn() {
        super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
        return "Yellow Wall On";
    }

    @Override
    protected String getPluralNameHook() {
        return "Yellow Walls On";
    }

    @Override
    protected String getDescriptionHook() {
        return "Yellow Walls On can NOT be walked through, and will change to Yellow Walls Off when a Yellow Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.YELLOW_WALL_ON;
    }
}