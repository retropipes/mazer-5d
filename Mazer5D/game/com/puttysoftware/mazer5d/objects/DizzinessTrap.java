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
import com.puttysoftware.mazer5d.maze.effect.MazeEffectConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;

class DizzinessTrap extends GenericTrap {
    // Fields
    private static final int EFFECT_DURATION = 10;

    // Constructors
    public DizzinessTrap() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Dizziness Trap";
    }

    @Override
    protected String getPluralNameHook() {
        return "Dizziness Traps";
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        Mazer5D.getBagOStuff().showMessage("You feel dizzy!");
        Mazer5D.getBagOStuff().getGameManager().activateEffect(MazeEffectConstants.EFFECT_DIZZY,
                DizzinessTrap.EFFECT_DURATION);
        SoundPlayer.playSound(SoundIndex.DIZZY, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
        return "Dizziness Traps randomly alter your controls each step for 10 steps when stepped on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.DIZZINESS_TRAP;
    }
}