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
import com.puttysoftware.mazer5d.maze.effects.MazeEffectConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class CounterclockwiseRotationTrap extends GenericTrap {
    // Fields
    private static final int EFFECT_DURATION = 10;

    // Constructors
    public CounterclockwiseRotationTrap() {
	super();
    }

    @Override
    public String getName() {
	return "Counterclockwise Rotation Trap";
    }

    @Override
    public String getPluralName() {
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
    public String getDescription() {
	return "Counterclockwise Rotation Traps rotate your controls counterclockwise for 10 steps when stepped on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.COUNTERCLOCKWISE_ROTATION_TRAP;
    }
}