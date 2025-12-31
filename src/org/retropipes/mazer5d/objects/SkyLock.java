/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericSingleLock;

class SkyLock extends GenericSingleLock {
    // Constructors
    public SkyLock() {
	super(new SkyKey());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
	    Mazer5D.getBagOStuff().showMessage("You need a sky key");
	}
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Sky Lock";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sky Locks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Sky Locks require Sky Keys to open.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SKY_LOCK;
    }
}