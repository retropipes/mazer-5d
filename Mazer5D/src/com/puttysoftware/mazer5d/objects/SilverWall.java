/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.GenericMultipleLock;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class SilverWall extends GenericMultipleLock {
    // Constructors
    public SilverWall() {
        super(new SilverSquare());
    }

    @Override
    public String getName() {
        return "Silver Wall";
    }

    @Override
    public String getPluralName() {
        return "Silver Walls";
    }

    @Override
    public String getDescription() {
        return "Silver Walls are impassable without enough Silver Squares.";
    }

    @Override
    public MazeObjects getUniqueID() {
        return MazeObjects.SILVER_WALL;
    }
}