/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericInfiniteKey;

class Axe extends GenericInfiniteKey {
    // Constructors
    public Axe() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Axe";
    }

    @Override
    protected String getPluralNameHook() {
	return "Axe";
    }

    @Override
    protected String getDescriptionHook() {
	return "With an Axe, Trees can be cut down. Axes neformatVersion lose their ability to cut trees.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.AXE;
    }
}