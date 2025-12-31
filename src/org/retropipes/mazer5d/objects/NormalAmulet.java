/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.game.GameManager;
import org.retropipes.mazer5d.maze.effect.MazeEffectConstants;
import org.retropipes.mazer5d.objects.abc.GenericAmulet;

class NormalAmulet extends GenericAmulet {
    // Constructors
    public NormalAmulet() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Normal Amulet";
    }

    @Override
    protected String getPluralNameHook() {
	return "Normal Amulets";
    }

    @Override
    protected String getDescriptionHook() {
	return "Normal Amulets have no special effect. Note that you can only wear one amulet at once.";
    }

    @Override
    public void postMoveActionHook() {
	// Deactivate other amulet effects
	final GameManager gm = Mazer5D.getBagOStuff().getGameManager();
	gm.deactivateEffect(MazeEffectConstants.EFFECT_COUNTER_POISONED);
	gm.deactivateEffect(MazeEffectConstants.EFFECT_FIERY);
	gm.deactivateEffect(MazeEffectConstants.EFFECT_GHOSTLY);
	gm.deactivateEffect(MazeEffectConstants.EFFECT_ICY);
	gm.deactivateEffect(MazeEffectConstants.EFFECT_POISONOUS);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.NORMAL_AMULET;
    }
}