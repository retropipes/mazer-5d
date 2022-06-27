/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericTrappedWall;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class TrappedWall6 extends GenericTrappedWall {
    public TrappedWall6() {
	super(6);
    }

    @Override
    protected String getDescriptionHook() {
	return "Trapped Walls 6 disappear when any Wall Trap 6 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TRAPPED_WALL_6;
    }
}
