/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericMultipleLock;

class RubyWall extends GenericMultipleLock {
    // Constructors
    public RubyWall() {
	super(new RubySquare());
    }

    @Override
    protected String getNameHook() {
	return "Ruby Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Ruby Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Ruby Walls are impassable without enough Ruby Squares.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.RUBY_WALL;
    }
}