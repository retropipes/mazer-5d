/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericTrappedWall;

class TrappedWall8 extends GenericTrappedWall {
    public TrappedWall8() {
        super(8);
    }

    @Override
    protected String getDescriptionHook() {
        return "Trapped Walls 8 disappear when any Wall Trap 8 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.TRAPPED_WALL_8;
    }
}
