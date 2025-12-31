/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericWallTrap;

class WallTrap17 extends GenericWallTrap {
    public WallTrap17() {
	super(17, new TrappedWall17());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall Traps 17 disappear when stepped on, causing all Trapped Walls 17 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_17;
    }
}
