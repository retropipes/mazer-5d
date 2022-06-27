/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericMovableObject;
import com.puttysoftware.mazer5d.utility.MazeObjects;

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