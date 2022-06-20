/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericSingleKey;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Hammer extends GenericSingleKey {
    // Constructors
    public Hammer() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Hammer";
    }

    @Override
    protected String getPluralNameHook() {
	return "Hammers";
    }

    @Override
    protected String getDescriptionHook() {
	return "Hammers are used to destroy Brick Walls, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.HAMMER;
    }
}