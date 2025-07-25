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

class SeaweedHouse extends FinishTo {
    // Constructors
    public SeaweedHouse() {
        super();
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        SoundPlayer.playSound(SoundIndex.UP, SoundGroup.GAME);
        app.getGameManager().goToLevel(this.getDestinationLevel());
    }

    @Override
    protected String getNameHook() {
        return "Seaweed House";
    }

    @Override
    protected String getPluralNameHook() {
        return "Seaweed Houses";
    }

    @Override
    protected String getDescriptionHook() {
        return "Seaweed Houses send you inside when walked on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.SEAWEED_HOUSE;
    }
}