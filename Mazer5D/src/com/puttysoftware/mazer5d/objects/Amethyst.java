/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Amethyst extends MazeObject {
    // Constructors
    public Amethyst() {
	super(MazeObjects.AMETHYST);
    }

    @Override
    protected String getNameHook() {
	return "Amethyst";
    }

    @Override
    protected String getPluralNameHook() {
	return "Amethysts";
    }

    @Override
    protected String getDescriptionHook() {
	return "Amethysts increase your score when picked up.";
    }
}
