/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Diamond extends MazeObject {
    // Constructors
    public Diamond() {
	super(MazeObjects.DIAMOND);
    }

    @Override
    protected String getNameHook() {
	return "Diamond";
    }

    @Override
    protected String getPluralNameHook() {
	return "Diamonds";
    }

    @Override
    protected String getDescriptionHook() {
	return "Diamonds increase your score when picked up.";
    }
}
