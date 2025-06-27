/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericTrappedWall;

class TrappedWall7 extends GenericTrappedWall {
    public TrappedWall7() {
        super(7);
    }

    @Override
    protected String getDescriptionHook() {
        return "Trapped Walls 7 disappear when any Wall Trap 7 is triggered.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.TRAPPED_WALL_7;
    }
}
