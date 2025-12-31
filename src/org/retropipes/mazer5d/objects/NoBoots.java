/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericInventoryModifier;

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
