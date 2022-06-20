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

class DisarmTrapWand extends GenericWand {
    // Constructors
    public DisarmTrapWand() {
	super();
    }

    @Override
    public String getName() {
	return "Disarm Trap Wand";
    }

    @Override
    public String getPluralName() {
	return "Disarm Trap Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(GameObjects.getEmptySpace(), x, y, z);
	SoundPlayer.playSound(SoundIndex.DESTROY, SoundGroup.GAME);
    }

    @Override
    public String getDescription() {
	return "Disarm Trap Wands will make one trap disappear when used, if aimed at a trap.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.DISARM_TRAP_WAND;
    }
}
