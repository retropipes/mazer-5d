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
import org.retropipes.mazer5d.objects.abc.GenericTrap;

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
