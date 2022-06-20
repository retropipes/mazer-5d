/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class WallTrap15 extends GenericWallTrap {
    public WallTrap15() {
	super(15, new TrappedWall15());
    }

    @Override
    public String getDescription() {
	return "Wall Traps 15 disappear when stepped on, causing all Trapped Walls 15 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_15;
    }
}
