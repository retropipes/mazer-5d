/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;

class MasterWallTrap extends GenericWallTrap {
    public MasterWallTrap() {
	super(GenericWallTrap.NUMBER_MASTER, null);
    }

    @Override
    protected String getDescriptionHook() {
	return "Master Wall Traps disappear when stepped on, causing all types of Trapped Walls to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MASTER_WALL_TRAP;
    }
}
