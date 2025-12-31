/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericToggleWall;

class SkyWallOn extends GenericToggleWall {
    // Constructors
    public SkyWallOn() {
	super(true);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Sky Wall On";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sky Walls On";
    }

    @Override
    protected String getDescriptionHook() {
	return "Sky Walls On can NOT be walked through, and will change to Sky Walls Off when a Sky Button is pressed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SKY_WALL_ON;
    }
}