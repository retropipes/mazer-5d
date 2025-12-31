/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericPassThroughObject;

class Empty extends GenericPassThroughObject {
    // Constructors
    public Empty() {
	super(true, true, true, true);
	this.setType(TypeConstants.TYPE_PASS_THROUGH);
	this.setType(TypeConstants.TYPE_EMPTY_SPACE);
    }

    @Override
    protected String getNameHook() {
	return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Emptiness";
    }

    @Override
    protected String getDescriptionHook() {
	return "Squares of Emptiness are what fills areas that aren't occupied by other objects.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.EMPTY;
    }
}