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

class OrangeLock extends GenericSingleLock {
    // Constructors
    public OrangeLock() {
        super(new OrangeKey());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            Mazer5D.getBagOStuff().showMessage("You need an orange key");
        }
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
        return "Orange Lock";
    }

    @Override
    protected String getPluralNameHook() {
        return "Orange Locks";
    }

    @Override
    protected String getDescriptionHook() {
        return "Orange Locks require Orange Keys to open.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.ORANGE_LOCK;
    }
}