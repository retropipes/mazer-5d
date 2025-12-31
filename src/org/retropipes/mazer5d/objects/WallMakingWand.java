/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericWand;

class WallMakingWand extends GenericWand {
    public WallMakingWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Wall-Making Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Wall-Making Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(new Wall(), x, y, z);
	SoundPlayer.playSound(SoundIndex.CREATE, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Wall-Making Wands will create an ordinary wall in the target square when used.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WALL_MAKING_WAND;
    }
}
