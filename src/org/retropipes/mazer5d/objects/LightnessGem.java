/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericGem;

class LightnessGem extends GenericGem {
    // Constructors
    public LightnessGem() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Lightness Gem";
    }

    @Override
    protected String getPluralNameHook() {
	return "Lightness Gems";
    }

    @Override
    public void postMoveActionHook() {
	Mazer5D.getBagOStuff().getMazeManager().getMaze().incrementVisionRadius();
	SoundPlayer.playSound(SoundIndex.LIGHT, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Lightness Gems increase the visible area by 1.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.LIGHTNESS_GEM;
    }
}
