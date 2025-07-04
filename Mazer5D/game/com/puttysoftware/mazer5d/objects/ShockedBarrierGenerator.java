/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.ArrowTypes;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.objects.abc.GenericGenerator;

class ShockedBarrierGenerator extends GenericGenerator {
	// Fields
	private int SHOCK_CYCLES;
	private static final int SHOCK_LIMIT = 2;

	// Constructors
	public ShockedBarrierGenerator() {
		super();
		this.TIMER_DELAY = 2;
		this.SHOCK_CYCLES = 0;
	}

	@Override
	protected String getNameHook() {
		return "Shocked Barrier Generator";
	}

	@Override
	protected String getPluralNameHook() {
		return "Shocked Barrier Generators";
	}

	@Override
	protected String getDescriptionHook() {
		return "Shocked Barrier Generators create Barriers. When hit or shot, they stop generating for a while, then resume generating MUCH faster than normal.";
	}

	@Override
	protected boolean preMoveActionHook(final int dirX, final int dirY, final int dirZ, final int dirW) {
		this.SHOCK_CYCLES++;
		if (this.SHOCK_CYCLES == ShockedBarrierGenerator.SHOCK_LIMIT) {
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
		} else if (arrowType == ArrowTypes.FIRE) {
			app.getGameManager().morph(new EnragedBarrierGenerator(), locX, locY, locZ);
		} else {
			this.preMoveAction(false, locX, locY, inv);
		}
	}

	@Override
	protected MazeObjects getUniqueIDHook() {
		return MazeObjects.SHOCKED_BARRIER_GENERATOR;
	}
}
