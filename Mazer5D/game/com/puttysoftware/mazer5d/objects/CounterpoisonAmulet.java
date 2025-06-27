/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.maze.effect.MazeEffectConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericAmulet;

class CounterpoisonAmulet extends GenericAmulet {
    // Constants
    private static final int EFFECT_DURATION = 30;

    // Constructors
    public CounterpoisonAmulet() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Counterpoison Amulet";
    }

    @Override
    protected String getPluralNameHook() {
        return "Counterpoison Amulets";
    }

    @Override
    protected String getDescriptionHook() {
        return "Counterpoison Amulets grant the power to make the air less poisonous for 30 steps. Note that you can only wear one amulet at once.";
    }

    @Override
    public void postMoveActionHook() {
        Mazer5D.getBagOStuff().getGameManager().activateEffect(MazeEffectConstants.EFFECT_COUNTER_POISONED,
                CounterpoisonAmulet.EFFECT_DURATION);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.COUNTERPOISON_AMULET;
    }
}