/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects.abc;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;

public abstract class GenericBarrier extends GenericWall {
    // Constants
    private static final int BARRIER_DAMAGE_PERCENT = 2;

    // Constructors
    protected GenericBarrier() {
	super();
	this.setType(TypeConstants.TYPE_BARRIER);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Display impassable barrier message
	final BagOStuff app = Mazer5D.getBagOStuff();
	Mazer5D.getBagOStuff().showMessage("The barrier is impassable!");
	SoundPlayer.playSound(SoundIndex.BARRIER, SoundGroup.GAME);
	// Hurt the player for trying to cross the barrier
	app.getMazeManager().getMaze().doDamagePercentage(GenericBarrier.BARRIER_DAMAGE_PERCENT);
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}