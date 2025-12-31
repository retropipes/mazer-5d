/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class SeaweedWallOn extends GenericToggleWall {
    // Constructors
    public SeaweedWallOn() {
	super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Seaweed Wall On";
    }

    @Override
    protected String getPluralNameHook() {
	return "Seaweed Walls On";
    }

    @Override
    protected String getDescriptionHook() {
	return "Seaweed Walls On can NOT be walked through, and will change to Seaweed Walls Off when a Seaweed Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SEAWEED_WALL_ON;
    }
}