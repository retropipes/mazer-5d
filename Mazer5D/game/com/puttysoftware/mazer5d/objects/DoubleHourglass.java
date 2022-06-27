/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTimeModifier;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class DoubleHourglass extends GenericTimeModifier {
    // Fields
    private static final long SCORE_GRAB = 20L;

    // Constructors
    public DoubleHourglass() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Double Hourglass";
    }

    @Override
    protected String getPluralNameHook() {
	return "Double Hourglasses";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().decay();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().extendTimerByInitialValueDoubled();
	SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().addToScore(DoubleHourglass.SCORE_GRAB);
    }

    @Override
    protected String getDescriptionHook() {
	return "Double Hourglasses extend the time to solve the current level by double the initial value.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DOUBLE_HOURGLASS;
    }
}
