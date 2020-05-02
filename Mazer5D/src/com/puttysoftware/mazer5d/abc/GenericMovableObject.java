/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.files.io.XDataReader;
import com.puttysoftware.mazer5d.files.io.XDataWriter;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericMovableObject extends MazeObjectModel {
    // Constructors
    protected GenericMovableObject(final boolean pushable,
            final boolean pullable) {
        super(true, pushable, false, false, pullable, false, false, true, false,
                0);
        this.setSavedObject(GameObjects.getEmptySpace());
        this.setType(TypeConstants.TYPE_MOVABLE);
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        // Do nothing
    }

    @Override
    public void pushAction(final ObjectInventory inv, final MazeObjectModel mo,
            final int x, final int y, final int pushX, final int pushY) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().updatePushedPosition(x, y, pushX, pushY, this);
        this.setSavedObject(mo);
        SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
    }

    @Override
    public void pullAction(final ObjectInventory inv, final MazeObjectModel mo,
            final int x, final int y, final int pullX, final int pullY) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().updatePulledPosition(x, y, pullX, pullY, this);
        this.setSavedObject(mo);
        SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }


    @Override
    protected MazeObjectModel readMazeObjectHookXML(final XDataReader reader,
            final int formatVersion) throws IOException {
        this.setSavedObject(GameObjects.readObject(reader, formatVersion));
        return this;
    }

    @Override
    protected void writeMazeObjectHookXML(final XDataWriter writer)
            throws IOException {
        this.getSavedObject().writeMazeObjectXML(writer);
    }

    @Override
    public int getCustomFormat() {
        if (Mazer5D.getBagOStuff().getMazeManager().isMazeXML1Compatible()) {
            return 0;
        } else {
            return MazeObjectModel.CUSTOM_FORMAT_MANUAL_OVERRIDE;
        }
    }
}