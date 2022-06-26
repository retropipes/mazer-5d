/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.objects.abc.GenericGenerator;
import com.puttysoftware.mazer5d.utilities.ArrowTypes;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class EnragedBarrierGenerator extends GenericGenerator {
    // Fields
    private int RAGE_CYCLES;
    private static final int RAGE_LIMIT = 3;

    // Constructors
    public EnragedBarrierGenerator() {
	super();
	this.TIMER_DELAY = 6;
	this.RAGE_CYCLES = 0;
    }

    @Override
    protected String getNameHook() {
	return "Enraged Barrier Generator";
    }

    @Override
    protected String getPluralNameHook() {
	return "Enraged Barrier Generators";
    }

    @Override
    protected String getDescriptionHook() {
	return "Enraged Barrier Generators create Barriers. When hit or shot, they stop generating for a while, then resume generating faster than normal.";
    }

    @Override
    protected boolean preMoveActionHook(final int dirX, final int dirY, final int dirZ, final int dirW) {
	this.RAGE_CYCLES++;
	if (this.RAGE_CYCLES == EnragedBarrierGenerator.RAGE_LIMIT) {
	    final BagOStuff app = Mazer5D.getBagOStuff();
	    final BarrierGenerator bg = new BarrierGenerator();
	    app.getGameManager().morph(bg, dirX, dirY, dirZ);
	    bg.timerExpiredAction(dirX, dirY);
	}
	return true;
    }

    @Override
    protected void arrowHitActionHook(final int locX, final int locY, final int locZ, final int arrowType,
	    final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (arrowType == ArrowTypes.ICE) {
	    app.getGameManager().morph(new IcedBarrierGenerator(), locX, locY, locZ);
	} else if (arrowType == ArrowTypes.POISON) {
	    app.getGameManager().morph(new PoisonedBarrierGenerator(), locX, locY, locZ);
	} else if (arrowType == ArrowTypes.SHOCK) {
	    app.getGameManager().morph(new ShockedBarrierGenerator(), locX, locY, locZ);
	} else {
	    this.preMoveAction(false, locX, locY, inv);
	}
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ENRAGED_BARRIER_GENERATOR;
    }
}
