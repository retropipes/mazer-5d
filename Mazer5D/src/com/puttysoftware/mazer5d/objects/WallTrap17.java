/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class WallTrap17 extends GenericWallTrap {
    public WallTrap17() {
	super(17, new TrappedWall17());
    }

    @Override
    public String getDescription() {
	return "Wall Traps 17 disappear when stepped on, causing all Trapped Walls 17 to also disappear.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.WALL_TRAP_17;
    }
}
