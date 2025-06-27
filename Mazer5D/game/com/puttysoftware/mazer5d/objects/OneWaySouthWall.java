/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class OneWaySouthWall extends GenericWall {
    public OneWaySouthWall() {
        super(true, false, true, true, true, false, true, true);
        this.setType(TypeConstants.TYPE_PLAIN_WALL);
        this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected String getNameHook() {
        return "One-Way South Wall";
    }

    @Override
    protected String getPluralNameHook() {
        return "One-Way South Walls";
    }

    @Override
    protected String getDescriptionHook() {
        return "One-Way South Walls allow movement through them only South.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.ONE_WAY_SOUTH_WALL;
    }
}
