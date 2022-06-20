/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericTrap extends MazeObject {
    // Constructors
    protected GenericTrap() {
	super(false);
	this.setType(TypeConstants.TYPE_TRAP);
    }

    // Scriptability
    @Override
    protected abstract void customPostMoveAction(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv);

    @Override
    protected
    abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}