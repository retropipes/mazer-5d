/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;

class WallTrap8 extends GenericWallTrap {
    public WallTrap8() {
	super(8, new TrappedWall8());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall Traps 8 disappear when stepped on, causing all Trapped Walls 8 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_8;
    }
}
