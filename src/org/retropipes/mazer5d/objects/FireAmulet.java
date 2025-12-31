/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.maze.effect.MazeEffectConstants;
import org.retropipes.mazer5d.objects.abc.GenericAmulet;

class FireAmulet extends GenericAmulet {
    // Constants
    private static final int EFFECT_DURATION = 30;

    // Constructors
    public FireAmulet() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Fire Amulet";
    }

    @Override
    protected String getPluralNameHook() {
	return "Fire Amulets";
    }

    @Override
    protected String getDescriptionHook() {
	return "Fire Amulets grant the power to transform ground into Hot Rock for 30 steps. Note that you can only wear one amulet at once.";
    }

    @Override
    public void stepAction() {
	final int x = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationX();
	final int y = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationY();
	final int z = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationZ();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().hotGround(x, y, z);
    }

    @Override
    public void postMoveActionHook() {
	Mazer5D.getBagOStuff().getGameManager().activateEffect(MazeEffectConstants.EFFECT_FIERY,
		FireAmulet.EFFECT_DURATION);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FIRE_AMULET;
    }
}