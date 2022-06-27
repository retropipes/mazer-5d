/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericPassThroughObject;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class CutTree extends GenericPassThroughObject {
    // Constructors
    public CutTree() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Cut Tree";
    }

    @Override
    protected String getPluralNameHook() {
	return "Cut Trees";
    }

    @Override
    protected String getDescriptionHook() {
	return "Cut Trees are the leftoformatVersion stubs of Trees that have been cut by an Axe.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CUT_TREE;
    }
}