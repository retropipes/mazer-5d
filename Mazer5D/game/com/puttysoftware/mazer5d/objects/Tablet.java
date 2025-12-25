/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericInfiniteKey;

class Tablet extends GenericInfiniteKey {
    // Constructors
    public Tablet() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Tablet";
    }

    @Override
    protected String getPluralNameHook() {
	return "Tablets";
    }

    @Override
    protected String getDescriptionHook() {
	return "Tablets are used to fill Tablet Slots, and make them disappear. Tablets can be used infinitely many times.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TABLET;
    }
}