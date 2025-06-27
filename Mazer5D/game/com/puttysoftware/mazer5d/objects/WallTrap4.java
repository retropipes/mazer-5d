/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericWallTrap;

class WallTrap4 extends GenericWallTrap {
    public WallTrap4() {
        super(4, new TrappedWall4());
    }

    @Override
    protected String getDescriptionHook() {
        return "Wall Traps 4 disappear when stepped on, causing all Trapped Walls 4 to also disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.WALL_TRAP_4;
    }
}
