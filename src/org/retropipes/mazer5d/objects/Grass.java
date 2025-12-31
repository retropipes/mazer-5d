/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericGround;

class Grass extends GenericGround {
    // Constructors
    public Grass() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Grass";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Grass";
    }

    @Override
    protected String getDescriptionHook() {
	return "Grass is one of the many types of ground.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GRASS;
    }
}