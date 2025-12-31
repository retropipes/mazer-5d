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

class IceBomb extends GenericUsableObject {
    // Constants
    private static final int EFFECT_RADIUS = 2;

    // Constructors
    public IceBomb() {
	super(1);
    }

    @Override
    protected String getNameHook() {
	return "Ice Bomb";
    }

    @Override
    protected String getPluralNameHook() {
	return "Ice Bombs";
    }

    @Override
    protected String getDescriptionHook() {
	return "Ice Bombs freeze anything in an area of radius 2 centered on the target point.";
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	// Act as if bomb was used
	this.useAction(null, locX, locY, locZ);
	// Destroy bomb
	Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
	// Stop arrow
	return false;
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	SoundPlayer.playSound(SoundIndex.EXPLODE, SoundGroup.GAME);
	// Freeze objects that react to ice
	Mazer5D.getBagOStuff().getMazeManager().getMaze().radialScanFreezeObjects(x, y, z, IceBomb.EFFECT_RADIUS);
	// Freeze ground, too
	Mazer5D.getBagOStuff().getMazeManager().getMaze().radialScanFreezeGround(x, y, z, IceBomb.EFFECT_RADIUS);
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(null, x, y, z);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ICE_BOMB;
    }
}
