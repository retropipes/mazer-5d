/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class RedWallOn extends GenericToggleWall {
    // Constructors
    public RedWallOn() {
        super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
        return "Red Wall On";
    }

    @Override
    protected String getPluralNameHook() {
        return "Red Walls On";
    }

    @Override
    protected String getDescriptionHook() {
        return "Red Walls On can NOT be walked through, and will change to Red Walls Off when a Red Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.RED_WALL_ON;
    }
}