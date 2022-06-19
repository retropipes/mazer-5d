/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GarnetWall extends GenericMultipleLock {
    // Constructors
    public GarnetWall() {
	super(new GarnetSquare());
    }

    @Override
    public String getName() {
	return "Garnet Wall";
    }

    @Override
    public String getPluralName() {
	return "Garnet Walls";
    }

    @Override
    public String getDescription() {
	return "Garnet Walls are impassable without enough Garnet Squares.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.GARNET_WALL;
    }
}