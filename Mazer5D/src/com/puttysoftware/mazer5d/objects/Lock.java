/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericSingleLock;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Lock extends GenericSingleLock {
    // Constructors
    public Lock() {
	super(new Key());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
	    Mazer5D.getBagOStuff().showMessage("You need a key");
	}
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Lock";
    }

    @Override
    protected String getPluralNameHook() {
	return "Locks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Locks require Keys to open.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.LOCK;
    }
}