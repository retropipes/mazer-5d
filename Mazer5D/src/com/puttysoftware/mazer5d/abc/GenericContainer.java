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
import com.puttysoftware.mazer5d.maze.effects.MazeEffectConstants;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericContainer extends GenericLock {
    // Constructors
    protected GenericContainer(final GenericSingleKey mgk) {
        super(mgk);
        this.setSavedObject(GameObjects.getEmptySpace());
        this.setType(TypeConstants.TYPE_CONTAINER);
        this.setType(TypeConstants.TYPE_UNLOCKED_LOSE_KEY);
        this.setType(TypeConstants.TYPE_LOCK);
    }

    protected GenericContainer(final GenericSingleKey mgk,
            final MazeObjectModel insideObject) {
        super(mgk);
        this.setSavedObject(insideObject);
        this.setType(TypeConstants.TYPE_CONTAINER);
        this.setType(TypeConstants.TYPE_UNLOCKED_LOSE_KEY);
        this.setType(TypeConstants.TYPE_LOCK);
    }

    @Override
    public boolean defersSetProperties() {
        return true;
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        if (!app.getGameManager().isEffectActive(
                MazeEffectConstants.EFFECT_GHOSTLY) && !inv.isItemThere(
                        MazeObjects.PASSWALL_BOOTS)) {
            if (!this.getKey().isInfinite()) {
                inv.removeItem(this.getKey().getUniqueID());
            }
            final int pz = app.getGameManager().getPlayerManager()
                    .getPlayerLocationZ();
            if (this.getSavedObject() != null) {
                app.getGameManager().morph(this.getSavedObject(), dirX, dirY,
                        pz);
            } else {
                app.getGameManager().decay();
            }
            SoundPlayer.playSound(SoundIndex.UNLOCK, SoundGroup.GAME);
            app.getGameManager().backUpPlayer();
            Mazer5D.getBagOStuff().getGameManager().addToScore(
                    GenericLock.SCORE_UNLOCK);
        } else {
            SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
        }
    }

    @Override
    public void editorProbeHook() {
        if (!this.getSavedObject().getName().equals("Empty")) {
            Mazer5D.getBagOStuff().showMessage(this.getName() + ": Contains "
                    + this.getSavedObject().getName());
        } else {
            Mazer5D.getBagOStuff().showMessage(this.getName()
                    + ": Contains Nothing");
        }
    }

    @Override
    public abstract MazeObjectModel editorPropertiesHook();

    @Override
    protected MazeObjectModel readMazeObjectHookXML(final XDataReader reader,
            final int formatVersion) throws IOException {
        this.setSavedObject(GameObjects.readObject(reader, formatVersion));
        return this;
    }

    @Override
    protected void writeMazeObjectHookXML(final XDataWriter writer)
            throws IOException {
        if (this.getSavedObject() == null) {
            GameObjects.getEmptySpace().writeMazeObjectXML(writer);
        } else {
            this.getSavedObject().writeMazeObjectXML(writer);
        }
    }
}