/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.compatibility.maze.effects;

import com.puttysoftware.mazer5d.utilities.Directions;

public class RotatedCCW extends MazeEffect {
    // Constructor
    public RotatedCCW(final int newRounds) {
        super("Rotated CCW", newRounds);
    }

    @Override
    public int modifyMove1(final int arg) {
        switch (arg) {
        case Directions.NORTH:
            return Directions.WEST;
        case Directions.SOUTH:
            return Directions.EAST;
        case Directions.WEST:
            return Directions.SOUTH;
        case Directions.EAST:
            return Directions.NORTH;
        case Directions.NORTHWEST:
            return Directions.SOUTHWEST;
        case Directions.NORTHEAST:
            return Directions.NORTHWEST;
        case Directions.SOUTHWEST:
            return Directions.SOUTHEAST;
        case Directions.SOUTHEAST:
            return Directions.NORTHEAST;
        default:
            break;
        }
        return 0;
    }
}