/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericSingleKey;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class BlueKey extends GenericSingleKey {
    // Constructors
    public BlueKey() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Blue Key";
    }

    @Override
    protected String getPluralNameHook() {
	return "Blue Keys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Blue Keys will unlock Blue Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BLUE_KEY;
    }
}