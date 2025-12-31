/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericPassThroughObject;

class FakeWall extends GenericPassThroughObject {
    // Constructors
    public FakeWall() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Fake Wall";
    }

    @Override
    public String getGameName() {
	return "Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Fake Walls";
    }

    @Override
    protected String getDescriptionHook() {
	return "Fake Walls look like walls, but can be walked through.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FAKE_WALL;
    }
}