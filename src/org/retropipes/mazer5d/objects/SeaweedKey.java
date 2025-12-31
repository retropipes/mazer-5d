/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericSingleKey;

class SeaweedKey extends GenericSingleKey {
    // Constructors
    public SeaweedKey() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Seaweed Key";
    }

    @Override
    protected String getPluralNameHook() {
	return "Seaweed Keys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Seaweed Keys will unlock Seaweed Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SEAWEED_KEY;
    }
}