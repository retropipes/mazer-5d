/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericWall;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

class Wall extends GenericWall {
    // Constructors
    public Wall() {
	super();
	this.setType(TypeConstants.TYPE_PLAIN_WALL);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    public String getName() {
	return "Wall";
    }

    @Override
    public String getPluralName() {
	return "Walls";
    }

    @Override
    public String getDescription() {
	return "Walls are impassable - you'll need to go around them.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.WALL;
    }
}