/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericGround;

class Snow extends GenericGround {
    // Constructors
    public Snow() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Snow";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Snow";
    }

    @Override
    protected String getDescriptionHook() {
	return "Snow is one of the many types of ground.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SNOW;
    }
}