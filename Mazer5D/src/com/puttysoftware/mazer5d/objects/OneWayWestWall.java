/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.GenericWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

class OneWayWestWall extends GenericWall {
    public OneWayWestWall() {
        super(true, true, true, false, true, true, true, false);
        this.setType(TypeConstants.TYPE_PLAIN_WALL);
        this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    public String getName() {
        return "One-Way West Wall";
    }

    @Override
    public String getPluralName() {
        return "One-Way West Walls";
    }

    @Override
    public String getDescription() {
        return "One-Way West Walls allow movement through them only West.";
    }

    @Override
    public MazeObjects getUniqueID() {
        return MazeObjects.ONE_WAY_WEST_WALL;
    }
}
