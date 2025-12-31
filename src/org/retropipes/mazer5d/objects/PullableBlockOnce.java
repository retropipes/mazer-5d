/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericMovableObject;

class PullableBlockOnce extends GenericMovableObject {
    // Constructors
    public PullableBlockOnce() {
	super(false, true);
    }

    @Override
    protected String getNameHook() {
	return "Pullable Block Once";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pullable Blocks Once";
    }

    @Override
    public void pullAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pushX,
	    final int pushY) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePulledPosition(x, y, pushX, pushY, this);
	SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
	app.getGameManager().morphOther(new Wall(), pushX, pushY, Layers.OBJECT);
    }

    @Override
    protected String getDescriptionHook() {
	return "Pullable Blocks Once can only be pulled once, before turning into a wall.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PULLABLE_BLOCK_ONCE;
    }
}