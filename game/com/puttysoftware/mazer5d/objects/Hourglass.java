/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTimeModifier;

class Hourglass extends GenericTimeModifier {
    // Fields
    private static final long SCORE_GRAB = 10L;

    // Constructors
    public Hourglass() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Hourglass";
    }

    @Override
    protected String getPluralNameHook() {
	return "Hourglasses";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().decay();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().extendTimerByInitialValue();
	SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().addToScore(Hourglass.SCORE_GRAB);
    }

    @Override
    protected String getDescriptionHook() {
	return "Hourglasses extend the time to solve the current level by the initial value.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.HOURGLASS;
    }
}
