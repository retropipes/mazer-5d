/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericTrappedWall;

class TrappedWall16 extends GenericTrappedWall {
    public TrappedWall16() {
	super(16);
    }

    @Override
    protected String getDescriptionHook() {
	return "Trapped Walls 16 disappear when any Wall Trap 16 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TRAPPED_WALL_16;
    }
}
