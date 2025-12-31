/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericMovableObject;

class PushableBlock extends GenericMovableObject {
    // Constructors
    public PushableBlock() {
	super(true, false);
    }

    @Override
    protected String getNameHook() {
	return "Pushable Block";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pushable Blocks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Pushable Blocks can only be pushed, not pulled.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PUSHABLE_BLOCK;
    }
}