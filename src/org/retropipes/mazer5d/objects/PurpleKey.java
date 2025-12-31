/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericSingleKey;

class PurpleKey extends GenericSingleKey {
    // Constructors
    public PurpleKey() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Purple Key";
    }

    @Override
    protected String getPluralNameHook() {
	return "Purple Keys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Purple Keys will unlock Purple Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PURPLE_KEY;
    }
}