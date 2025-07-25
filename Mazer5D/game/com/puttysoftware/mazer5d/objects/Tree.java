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
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericInfiniteLock;

class Tree extends GenericInfiniteLock {
    // Constructors
    public Tree() {
        super(new Axe());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            Mazer5D.getBagOStuff().showMessage("You need an axe");
        }
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        if (!this.getKey().isInfinite()) {
            inv.removeItem(this.getKey().getUniqueID());
        }
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().decayTo(new CutTree());
        SoundPlayer.playSound(SoundIndex.UNLOCK, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
        return "Tree";
    }

    @Override
    protected String getPluralNameHook() {
        return "Trees";
    }

    @Override
    protected String getDescriptionHook() {
        return "Trees transform into Cut Trees when hit with an Axe.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.TREE;
    }
}