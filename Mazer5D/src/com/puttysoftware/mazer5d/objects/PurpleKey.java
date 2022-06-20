/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericSingleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class PurpleKey extends GenericSingleKey {
    // Constructors
    public PurpleKey() {
	super();
    }

    // Scriptability
    @Override
    public String getName() {
	return "Purple Key";
    }

    @Override
    public String getPluralName() {
	return "Purple Keys";
    }

    @Override
    public String getDescription() {
	return "Purple Keys will unlock Purple Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PURPLE_KEY;
    }
}