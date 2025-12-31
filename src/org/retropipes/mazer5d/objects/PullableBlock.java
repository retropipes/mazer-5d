/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericMovableObject;

class PullableBlock extends GenericMovableObject {
    // Constructors
    public PullableBlock() {
	super(false, true);
    }

    @Override
    protected String getNameHook() {
	return "Pullable Block";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pullable Blocks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Pullable Blocks can only be pulled, not pushed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PULLABLE_BLOCK;
    }
}