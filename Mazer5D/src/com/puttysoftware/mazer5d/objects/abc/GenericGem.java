/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericGem extends MazeObject {
    // Fields
    private static final long SCORE_SMASH = 10L;
    private static final long SCORE_GRAB = 20L;

    // Constructors
    protected GenericGem() {
	super(false);
	this.setType(TypeConstants.TYPE_GEM);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().decay();
	Mazer5D.getBagOStuff().getGameManager().addToScore(GenericGem.SCORE_GRAB);
	this.postMoveActionHook();
	Mazer5D.getBagOStuff().getGameManager().redrawMaze();
    }

    public abstract void postMoveActionHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
	SoundPlayer.playSound(SoundIndex.SHATTER, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().addToScore(GenericGem.SCORE_SMASH);
	return false;
    }
}
