/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Exit extends FinishTo {
    // Constructors
    public Exit() {
	super();
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	SoundPlayer.playSound(SoundIndex.DOWN, SoundGroup.GAME);
	app.getGameManager().goToLevel(this.getDestinationLevel());
    }

    @Override
    protected String getNameHook() {
	return "Exit";
    }

    @Override
    protected String getPluralNameHook() {
	return "Exits";
    }

    @Override
    protected String getDescriptionHook() {
	return "Exits send you outside when walked on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.EXIT;
    }
}