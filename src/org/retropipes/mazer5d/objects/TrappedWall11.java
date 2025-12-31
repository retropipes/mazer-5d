/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericTrappedWall;

class TrappedWall11 extends GenericTrappedWall {
    public TrappedWall11() {
	super(11);
    }

    @Override
    protected String getDescriptionHook() {
	return "Trapped Walls 11 disappear when any Wall Trap 11 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TRAPPED_WALL_11;
    }
}
