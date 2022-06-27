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

class PushableBlockThrice extends GenericMovableObject {
    // Constructors
    public PushableBlockThrice() {
	super(true, false);
    }

    @Override
    protected String getNameHook() {
	return "Pushable Block Thrice";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pushable Blocks Thrice";
    }

    @Override
    public void pushAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pushX,
	    final int pushY) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePushedPosition(x, y, pushX, pushY, this);
	SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
	app.getGameManager().morphOther(new PushableBlockTwice(), pushX, pushY, Layers.OBJECT);
    }

    @Override
    protected String getDescriptionHook() {
	return "Pushable Blocks Thrice can only be pushed three times, before turning into a wall.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PUSHABLE_BLOCK_THRICE;
    }
}