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
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericInvisibleTeleport extends GenericTeleport {
    // Constructors
    protected GenericInvisibleTeleport(final int destinationRow,
            final int destinationColumn, final int destinationFloor) {
        super(destinationRow, destinationColumn, destinationFloor);
        this.setType(TypeConstants.TYPE_INVISIBLE_TELEPORT);
        this.setType(TypeConstants.TYPE_TELEPORT);
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().updatePositionAbsolute(this.getDestinationRow(),
                this.getDestinationColumn(), this.getDestinationFloor());
        Mazer5D.getBagOStuff().showMessage("Invisible Teleport!");
        SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    public abstract String getName();

    @Override
    public abstract MazeObjectModel editorPropertiesHook();
}