/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.game.ObjectInventory;

public abstract class GenericGround extends MazeObject {
    // Constructors
    protected GenericGround() {
	super(false);
    }

    protected GenericGround(final boolean doesAcceptPushInto, final boolean doesAcceptPushOut,
	    final boolean doesAcceptPullInto, final boolean doesAcceptPullOut) {
	super(false, false, doesAcceptPushInto, doesAcceptPushOut, false, doesAcceptPullInto, doesAcceptPullOut, true,
		false, 0, false, false);
	this.setType(TypeConstants.TYPE_GROUND);
    }

    protected GenericGround(final boolean doesAcceptPushInto, final boolean doesAcceptPushOut,
	    final boolean doesAcceptPullInto, final boolean doesAcceptPullOut, final boolean hasFriction) {
	super(false, false, doesAcceptPushInto, doesAcceptPushOut, false, doesAcceptPullInto, doesAcceptPullOut,
		hasFriction, false, 0, false, false);
	this.setType(TypeConstants.TYPE_GROUND);
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.GROUND;
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }
}
