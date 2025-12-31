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
import org.retropipes.mazer5d.maze.effect.MazeEffectConstants;
import org.retropipes.mazer5d.objects.abc.GenericTrap;

class CounterclockwiseRotationTrap extends GenericTrap {
    // Fields
    private static final int EFFECT_DURATION = 10;

    // Constructors
    public CounterclockwiseRotationTrap() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Counterclockwise Rotation Trap";
    }

    @Override
    protected String getPluralNameHook() {
	return "Counterclockwise Rotation Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.CHANGE, SoundGroup.GAME);
	Mazer5D.getBagOStuff().showMessage("Your controls are rotated!");
	Mazer5D.getBagOStuff().getGameManager().activateEffect(MazeEffectConstants.EFFECT_ROTATED_COUNTERCLOCKWISE,
		CounterclockwiseRotationTrap.EFFECT_DURATION);
    }

    @Override
    protected String getDescriptionHook() {
	return "Counterclockwise Rotation Traps rotate your controls counterclockwise for 10 steps when stepped on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.COUNTERCLOCKWISE_ROTATION_TRAP;
    }
}