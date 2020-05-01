/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.maze.effects;

import com.puttysoftware.mazer5d.utilities.Directions;

public class RotatedCW extends MazeEffect {
    // Constructor
    public RotatedCW(final int newRounds) {
        super("Rotated CW", newRounds);
    }

    @Override
    public int modifyMove1(final int arg) {
        switch (arg) {
        case Directions.NORTH:
            return Directions.EAST;
        case Directions.SOUTH:
            return Directions.WEST;
        case Directions.WEST:
            return Directions.NORTH;
        case Directions.EAST:
            return Directions.SOUTH;
        case Directions.NORTHWEST:
            return Directions.NORTHEAST;
        case Directions.NORTHEAST:
            return Directions.SOUTHEAST;
        case Directions.SOUTHWEST:
            return Directions.NORTHWEST;
        case Directions.SOUTHEAST:
            return Directions.SOUTHWEST;
        default:
            break;
        }
        return 0;
    }
}