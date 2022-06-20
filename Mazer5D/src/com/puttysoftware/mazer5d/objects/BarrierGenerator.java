/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.game.GameManager;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.objects.abc.GenericGenerator;
import com.puttysoftware.mazer5d.utilities.ArrowTypes;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class BarrierGenerator extends GenericGenerator {
    // Constructors
    public BarrierGenerator() {
	super();
    }

    @Override
    public String getName() {
	return "Barrier Generator";
    }

    @Override
    public String getPluralName() {
	return "Barrier Generators";
    }

    @Override
    public String getDescription() {
	return "Barrier Generators create Barriers. When hit or shot, they stop generating for a while, then resume generating.";
    }

    @Override
    protected boolean preMoveActionHook(final int dirX, final int dirY, final int dirZ, final int dirW) {
	return true;
    }

    @Override
    protected void arrowHitActionHook(final int locX, final int locY, final int locZ, final int arrowType,
	    final ObjectInventory inv) {
	final GameManager gm = Mazer5D.getBagOStuff().getGameManager();
	if (arrowType == ArrowTypes.ICE) {
	    gm.morph(new IcedBarrierGenerator(), locX, locY, locZ);
	} else if (arrowType == ArrowTypes.FIRE) {
	    gm.morph(new EnragedBarrierGenerator(), locX, locY, locZ);
	} else if (arrowType == ArrowTypes.POISON) {
	    gm.morph(new PoisonedBarrierGenerator(), locX, locY, locZ);
	} else if (arrowType == ArrowTypes.SHOCK) {
	    gm.morph(new ShockedBarrierGenerator(), locX, locY, locZ);
	} else {
	    this.preMoveAction(false, locX, locY, inv);
	}
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BARRIER_GENERATOR;
    }
}
