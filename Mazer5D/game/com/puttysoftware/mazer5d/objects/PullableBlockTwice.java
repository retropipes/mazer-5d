/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericMovableObject;
import com.puttysoftware.mazer5d.utility.Layers;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class PullableBlockTwice extends GenericMovableObject {
    // Constructors
    public PullableBlockTwice() {
	super(false, true);
    }

    @Override
    protected String getNameHook() {
	return "Pullable Block Twice";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pullable Blocks Twice";
    }

    @Override
    public void pullAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pushX,
	    final int pushY) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePulledPosition(x, y, pushX, pushY, this);
	SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
	app.getGameManager().morphOther(new PullableBlockOnce(), pushX, pushY, Layers.OBJECT);
    }

    @Override
    protected String getDescriptionHook() {
	return "Pullable Blocks Twice can only be pulled twice, before turning into a wall.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PULLABLE_BLOCK_TWICE;
    }
}