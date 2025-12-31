/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericMovableObject;

class PushablePullableBlock extends GenericMovableObject {
    // Constructors
    public PushablePullableBlock() {
	super(true, true);
    }

    @Override
    protected String getNameHook() {
	return "Pushable/Pullable Block";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pushable/Pullable Blocks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Pushable/Pullable Blocks can be both pushed and pulled.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PUSHABLE_PULLABLE_BLOCK;
    }
}
