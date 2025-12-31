/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericTrappedWall;

class MasterTrappedWall extends GenericTrappedWall {
    public MasterTrappedWall() {
	super(GenericTrappedWall.NUMBER_MASTER);
    }

    @Override
    protected String getDescriptionHook() {
	return "Master Trapped Walls disappear when any Wall Trap is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MASTER_TRAPPED_WALL;
    }
}
