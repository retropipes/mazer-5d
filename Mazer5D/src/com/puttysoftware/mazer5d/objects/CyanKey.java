/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericSingleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class CyanKey extends GenericSingleKey {
    // Constructors
    public CyanKey() {
	super();
    }

    // Scriptability
    @Override
    public String getName() {
	return "Cyan Key";
    }

    @Override
    public String getPluralName() {
	return "Cyan Keys";
    }

    @Override
    public String getDescription() {
	return "Cyan Keys will unlock Cyan Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CYAN_KEY;
    }
}