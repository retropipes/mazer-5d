/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

class OneWaySouthWall extends GenericWall {
    public OneWaySouthWall() {
        super(true, false, true, true, true, false, true, true);
        this.setType(TypeConstants.TYPE_PLAIN_WALL);
        this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    public String getName() {
        return "One-Way South Wall";
    }

    @Override
    public String getPluralName() {
        return "One-Way South Walls";
    }

    @Override
    public String getDescription() {
        return "One-Way South Walls allow movement through them only South.";
    }

    @Override
    public MazeObjects getUniqueID() {
        return MazeObjects.ONE_WAY_SOUTH_WALL;
    }
}
