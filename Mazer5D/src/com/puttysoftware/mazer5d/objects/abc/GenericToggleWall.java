/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjectModel;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericToggleWall extends MazeObjectModel {
    // Constructors
    protected GenericToggleWall(final boolean solidState) {
        super(solidState);
        this.setType(TypeConstants.TYPE_TOGGLE_WALL);
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        Mazer5D.getBagOStuff().showMessage("Can't go that way");
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }

}
