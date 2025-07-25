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

class HalfHourglass extends GenericTimeModifier {
    // Fields
    private static final long SCORE_GRAB = 5L;

    // Constructors
    public HalfHourglass() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Half Hourglass";
    }

    @Override
    protected String getPluralNameHook() {
        return "Half Hourglasses";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        Mazer5D.getBagOStuff().getGameManager().decay();
        Mazer5D.getBagOStuff().getMazeManager().getMaze().extendTimerByInitialValueHalved();
        SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
        Mazer5D.getBagOStuff().getGameManager().addToScore(HalfHourglass.SCORE_GRAB);
    }

    @Override
    protected String getDescriptionHook() {
        return "Half Hourglasses extend the time to solve the current level by half the initial value.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.HALF_HOURGLASS;
    }
}
