/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

class OneWayNorthWall extends GenericWall {
    public OneWayNorthWall() {
	super(false, true, true, true, false, true, true, true);
	this.setType(TypeConstants.TYPE_PLAIN_WALL);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected String getNameHook() {
	return "One-Way North Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "One-Way North Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "One-Way North Walls allow movement through them only North.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ONE_WAY_NORTH_WALL;
    }
}
