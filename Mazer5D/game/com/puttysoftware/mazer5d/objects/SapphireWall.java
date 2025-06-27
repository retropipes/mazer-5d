/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleLock;

class SapphireWall extends GenericMultipleLock {
    // Constructors
    public SapphireWall() {
        super(new SapphireSquare());
    }

    @Override
    protected String getNameHook() {
        return "Sapphire Wall";
    }

    @Override
    protected String getPluralNameHook() {
        return "Sapphire Walls";
    }

    @Override
    protected String getDescriptionHook() {
        return "Sapphire Walls are impassable without enough Sapphire Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.SAPPHIRE_WALL;
    }
}