/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericGem;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class DimnessGem extends GenericGem {
    // Constructors
    public DimnessGem() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Dimness Gem";
    }

    @Override
    protected String getPluralNameHook() {
	return "Dimness Gems";
    }

    @Override
    public void postMoveActionHook() {
	Mazer5D.getBagOStuff().getMazeManager().getMaze().decrementVisionRadius();
	SoundPlayer.playSound(SoundIndex.DARKNESS, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Dimness Gems decrease the visible area by 1.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DIMNESS_GEM;
    }
}
