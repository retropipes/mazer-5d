/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericWallTrap;

class WallTrap14 extends GenericWallTrap {
    public WallTrap14() {
	super(14, new TrappedWall14());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall Traps 14 disappear when stepped on, causing all Trapped Walls 14 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_TRAP_14;
    }
}
