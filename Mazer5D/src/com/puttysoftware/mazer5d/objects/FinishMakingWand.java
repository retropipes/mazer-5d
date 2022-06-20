/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWand;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class FinishMakingWand extends GenericWand {
    public FinishMakingWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Finish-Making Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Finish-Making Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(new Finish(), x, y, z);
	SoundPlayer.playSound(SoundIndex.CREATE, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Finish-Making Wands will create a finish when used.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FINISH_MAKING_WAND;
    }
}
