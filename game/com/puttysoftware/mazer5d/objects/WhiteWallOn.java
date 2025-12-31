/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericToggleWall;

class WhiteWallOn extends GenericToggleWall {
    // Constructors
    public WhiteWallOn() {
	super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "White Wall On";
    }

    @Override
    protected String getPluralNameHook() {
	return "White Walls On";
    }

    @Override
    protected String getDescriptionHook() {
	return "White Walls On can NOT be walked through, and will change to White Walls Off when a White Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WHITE_WALL_ON;
    }
}