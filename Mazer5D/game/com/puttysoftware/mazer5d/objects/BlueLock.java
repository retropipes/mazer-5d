/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericSingleLock;

class BlueLock extends GenericSingleLock {
    // Constructors
    public BlueLock() {
        super(new BlueKey());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            Mazer5D.getBagOStuff().showMessage("You need a blue key");
        }
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
        return "Blue Lock";
    }

    @Override
    protected String getPluralNameHook() {
        return "Blue Locks";
    }

    @Override
    protected String getDescriptionHook() {
        return "Blue Locks require Blue Keys to open.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.BLUE_LOCK;
    }
}