/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericTrappedWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class TrappedWall14 extends GenericTrappedWall {
    public TrappedWall14() {
	super(14);
    }

    @Override
    protected String getDescriptionHook() {
	return "Trapped Walls 14 disappear when any Wall Trap 14 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TRAPPED_WALL_14;
    }
}
