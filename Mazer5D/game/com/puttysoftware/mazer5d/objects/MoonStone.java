/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericInventoryableObject;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class MoonStone extends GenericInventoryableObject {
    // Constants
    private static final long SCORE_GRAB_STONE = 1L;

    // Constructors
    public MoonStone() {
	super(false, 0);
    }

    @Override
    protected String getNameHook() {
	return "Moon Stone";
    }

    @Override
    protected String getPluralNameHook() {
	return "Moon Stones";
    }

    @Override
    protected String getDescriptionHook() {
	return "Moon Stones act as a trigger for other actions when collected.";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	inv.addItem(this);
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().decay();
	SoundPlayer.playSound(SoundIndex.SUN_STONE, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().addToScore(MoonStone.SCORE_GRAB_STONE);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MOON_STONE;
    }
}