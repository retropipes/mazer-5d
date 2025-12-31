/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericSingleKey;

class YellowKey extends GenericSingleKey {
    // Constructors
    public YellowKey() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Yellow Key";
    }

    @Override
    protected String getPluralNameHook() {
	return "Yellow Keys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Yellow Keys will unlock Yellow Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.YELLOW_KEY;
    }
}