/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class WallTrap16 extends GenericWallTrap {
    public WallTrap16() {
	super(16, new TrappedWall16());
    }

    @Override
    public String getDescription() {
	return "Wall Traps 16 disappear when stepped on, causing all Trapped Walls 16 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_16;
    }
}
