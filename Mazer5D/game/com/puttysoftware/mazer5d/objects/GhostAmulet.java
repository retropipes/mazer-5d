/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.maze.effects.MazeEffectConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericAmulet;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GhostAmulet extends GenericAmulet {
    // Constants
    private static final int EFFECT_DURATION = 30;

    // Constructors
    public GhostAmulet() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Ghost Amulet";
    }

    @Override
    protected String getPluralNameHook() {
	return "Ghost Amulets";
    }

    @Override
    protected String getDescriptionHook() {
	return "Ghost Amulets grant the power to walk through walls for 30 steps. Note that you can only wear one amulet at once.";
    }

    @Override
    public void postMoveActionHook() {
	Mazer5D.getBagOStuff().getGameManager().activateEffect(MazeEffectConstants.EFFECT_GHOSTLY,
		GhostAmulet.EFFECT_DURATION);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GHOST_AMULET;
    }
}