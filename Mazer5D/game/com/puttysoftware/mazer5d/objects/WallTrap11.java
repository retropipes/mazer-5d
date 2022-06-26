/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class WallTrap11 extends GenericWallTrap {
    public WallTrap11() {
	super(11, new TrappedWall11());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall Traps 11 disappear when stepped on, causing all Trapped Walls 11 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_11;
    }
}
