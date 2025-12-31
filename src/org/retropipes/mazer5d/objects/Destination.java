/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericTeleport;

class Destination extends GenericTeleport {
    // Constructors
    public Destination() {
	super(0, 0, 0);
    }

    @Override
    protected String getNameHook() {
	return "Destination";
    }

    @Override
    protected String getPluralNameHook() {
	return "Destinations";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	return null;
    }

    @Override
    protected String getDescriptionHook() {
	return "Destinations are where Teleports take you to.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DESTINATION;
    }
}