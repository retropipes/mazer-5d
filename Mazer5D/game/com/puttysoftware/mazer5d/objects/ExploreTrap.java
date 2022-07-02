/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.abc.VisionModes;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;

class ExploreTrap extends GenericTrap {
    // Constructors
    public ExploreTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Explore Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Explore Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.CHANGE, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getMazeManager().getMaze().addVisionMode(VisionModes.EXPLORE);
	Mazer5D.getBagOStuff().getGameManager().decay();
    }

    @Override
    protected String getDescriptionHook() {
	return "Explore Traps turn exploring mode on, then disappear.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.EXPLORE_TRAP;
    }
}