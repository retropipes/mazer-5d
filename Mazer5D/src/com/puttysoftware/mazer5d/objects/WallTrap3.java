/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class WallTrap3 extends GenericWallTrap {
    public WallTrap3() {
	super(3, new TrappedWall3());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall Traps 3 disappear when stepped on, causing all Trapped Walls 3 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_3;
    }
}
