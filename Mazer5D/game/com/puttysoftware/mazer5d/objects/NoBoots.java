/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericInventoryModifier;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class NoBoots extends GenericInventoryModifier {
    // Constructors
    public NoBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "No Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of No Boots";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().decay();
	inv.removeAllBoots();
	SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "No Boots remove any boots worn when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.NO_BOOTS;
    }
}
