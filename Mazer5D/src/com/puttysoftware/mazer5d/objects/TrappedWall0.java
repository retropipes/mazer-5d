/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericTrappedWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class TrappedWall0 extends GenericTrappedWall {
    public TrappedWall0() {
	super(0);
    }

    @Override
    protected String getDescriptionHook() {
	return "Trapped Walls 0 disappear when any Wall Trap 0 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TRAPPED_WALL_0;
    }
}
