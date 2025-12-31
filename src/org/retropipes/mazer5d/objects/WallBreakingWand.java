/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericWand;

class WallBreakingWand extends GenericWand {
    // Constructors
    public WallBreakingWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Wall-Breaking Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Wall-Breaking Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(GameObjects.getEmptySpace(), x, y, z);
	SoundPlayer.playSound(SoundIndex.DESTROY, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall-Breaking Wands will destroy one wall when used, if aimed at a wall.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_BREAKING_WAND;
    }
}
