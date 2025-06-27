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
import com.puttysoftware.mazer5d.objects.abc.GenericRandomTeleport;

class RandomTeleport extends GenericRandomTeleport {
    // Constructors
    public RandomTeleport() {
        super(0, 0);
    }

    public RandomTeleport(final int newRandomRangeY, final int newRandomRangeX) {
        super(newRandomRangeY, newRandomRangeX);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        int dr, dc;
        do {
            dr = this.getDestinationRow();
            dc = this.getDestinationColumn();
        } while (!app.getGameManager().tryUpdatePositionRelative(dr, dc));
        app.getGameManager().updatePositionRelative(dr, dc);
        SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
        return "Random Teleport";
    }

    @Override
    protected String getPluralNameHook() {
        return "Random Teleports";
    }

    @Override
    protected String getDescriptionHook() {
        return "Random Teleports, unlike regular Teleports, send you to a randomly chosen destination.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.RANDOM_TELEPORT;
    }
}