/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericProgrammableLock;

class CrystalWall extends GenericProgrammableLock {
    // Constructors
    public CrystalWall() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Crystal Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Crystal Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Crystal Walls require one Crystal to open. The crystal type required may be different from wall to wall.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CRYSTAL_WALL;
    }
}