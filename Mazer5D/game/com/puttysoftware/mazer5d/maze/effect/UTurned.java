/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.maze.effect;

import com.puttysoftware.diane.utilties.Directions;

public class UTurned extends MazeEffect {
    // Constructor
    public UTurned(final int newRounds) {
	super("U-Turned", newRounds);
    }

    @Override
    public int modifyMove1(final int arg) {
	switch (arg) {
	case Directions.NORTH:
	    return Directions.SOUTH;
	case Directions.SOUTH:
	    return Directions.NORTH;
	case Directions.WEST:
	    return Directions.EAST;
	case Directions.EAST:
	    return Directions.WEST;
	case Directions.NORTHWEST:
	    return Directions.SOUTHEAST;
	case Directions.NORTHEAST:
	    return Directions.SOUTHWEST;
	case Directions.SOUTHWEST:
	    return Directions.NORTHEAST;
	case Directions.SOUTHEAST:
	    return Directions.NORTHWEST;
	default:
	    break;
	}
	return 0;
    }
}