/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWand;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class AnnihilationWand extends GenericWand {
    // Constructors
    public AnnihilationWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Annihilation Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Annihilation Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(GameObjects.getEmptySpace(), x, y, z);
	SoundPlayer.playSound(SoundIndex.DESTROY, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Annihilation Wands will destroy any object (not ground) when used, except the Void or a Sealing Wall.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ANNIHILATION_WAND;
    }
}
