/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericScoreIncreaser extends MazeObjectModel {
    // Constructors
    protected GenericScoreIncreaser() {
        super(false);
        this.setType(TypeConstants.TYPE_SCORE_INCREASER);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        Mazer5D.getBagOStuff().getGameManager().decay();
        SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
        this.postMoveActionHook();
        Mazer5D.getBagOStuff().getGameManager().redrawMaze();
    }

    public abstract void postMoveActionHook();

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }

    @Override
    public boolean arrowHitAction(final int locX, final int locY,
            final int locZ, final int dirX, final int dirY, final int arrowType,
            final ObjectInventory inv) {
        Mazer5D.getBagOStuff().getGameManager().morph(GameObjects
                .getEmptySpace(), locX, locY, locZ);
        SoundPlayer.playSound(SoundIndex.SHATTER, SoundGroup.GAME);
        return false;
    }

    @Override
    public int getCustomProperty(final int propID) {
        return MazeObjectModel.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }
}
