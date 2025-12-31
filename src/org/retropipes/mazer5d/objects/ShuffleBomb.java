/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericUsableObject;

class ShuffleBomb extends GenericUsableObject {
    // Constants
    private static final int EFFECT_RADIUS = 3;

    // Constructors
    public ShuffleBomb() {
	super(1);
    }

    @Override
    protected String getNameHook() {
	return "Shuffle Bomb";
    }

    @Override
    protected String getPluralNameHook() {
	return "Shuffle Bombs";
    }

    @Override
    protected String getDescriptionHook() {
	return "Shuffle Bombs randomly rearrange anything in an area of radius 3 centered on the target point.";
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	// Destroy bomb
	Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
	// Act as if bomb was used
	this.useAction(null, locX, locY, locZ);
	// Stop arrow
	return false;
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	// Shuffle objects
	SoundPlayer.playSound(SoundIndex.EXPLODE, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getMazeManager().getMaze().radialScanShuffleObjects(x, y, z, ShuffleBomb.EFFECT_RADIUS);
	// Player might have moved
	Mazer5D.getBagOStuff().getGameManager().findPlayerAndAdjust();
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(null, x, y, z);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SHUFFLE_BOMB;
    }
}