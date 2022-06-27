/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWall;
import com.puttysoftware.mazer5d.utility.MazeObjects;
import com.puttysoftware.mazer5d.utility.TypeConstants;

class OneWayEastWall extends GenericWall {
    public OneWayEastWall() {
	super(true, true, false, true, true, true, false, true);
	this.setType(TypeConstants.TYPE_PLAIN_WALL);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected String getNameHook() {
	return "One-Way East Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "One-Way East Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "One-Way East Walls allow movement through them only East.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ONE_WAY_EAST_WALL;
    }
}
