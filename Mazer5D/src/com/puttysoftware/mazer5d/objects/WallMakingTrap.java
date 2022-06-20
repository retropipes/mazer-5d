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
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class WallMakingTrap extends GenericTrap {
    public WallMakingTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Wall-Making Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Wall-Making Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().delayedDecayTo(new Wall());
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall-Making Traps create a Wall when you step OFF them.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_MAKING_TRAP;
    }
}
