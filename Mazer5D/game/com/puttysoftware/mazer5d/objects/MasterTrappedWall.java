/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericTrappedWall;

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
