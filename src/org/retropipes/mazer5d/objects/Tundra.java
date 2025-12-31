/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericGround;

class Tundra extends GenericGround {
    // Constructors
    public Tundra() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Tundra";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Tundra";
    }

    @Override
    protected String getDescriptionHook() {
	return "Tundra is one of the many types of ground.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TUNDRA;
    }
}