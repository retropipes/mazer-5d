/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Ruby extends MazeObject {
    // Constructors
    public Ruby() {
	super(MazeObjects.RUBY);
    }

    @Override
    protected String getNameHook() {
	return "Ruby";
    }

    @Override
    protected String getPluralNameHook() {
	return "Rubys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Rubys increase your score when picked up.";
    }
}
