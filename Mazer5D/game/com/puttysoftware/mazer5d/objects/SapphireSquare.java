/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;

class SapphireSquare extends GenericMultipleKey {
    // Constructors
    public SapphireSquare() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Sapphire Square";
    }

    @Override
    protected String getPluralNameHook() {
        return "Sapphire Squares";
    }

    @Override
    protected String getDescriptionHook() {
        return "Sapphire Squares are the keys to Sapphire Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.SAPPHIRE_SQUARE;
    }
}