/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericWallTrap;

class WallTrap19 extends GenericWallTrap {
    public WallTrap19() {
	super(19, new TrappedWall19());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall Traps 19 disappear when stepped on, causing all Trapped Walls 19 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_19;
    }
}
