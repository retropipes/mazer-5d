/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericMultipleKey;

class RubySquare extends GenericMultipleKey {
    // Constructors
    public RubySquare() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Ruby Square";
    }

    @Override
    protected String getPluralNameHook() {
        return "Ruby Squares";
    }

    @Override
    protected String getDescriptionHook() {
        return "Ruby Squares are the keys to Ruby Walls.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.RUBY_SQUARE;
    }
}