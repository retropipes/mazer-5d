/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.ArrowTypes;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;

public abstract class GenericCharacter extends MazeObject {
    // Fields
    public static final int FULL_HEAL_PERCENTAGE = 100;
    private static final int SHOT_SELF_NORMAL_DAMAGE = 5;
    private static final int SHOT_SELF_SPECIAL_DAMAGE = 10;

    // Constructors
    protected GenericCharacter() {
        super(false);
        this.setSavedObject(GameObjects.getEmptySpace());
        this.setType(TypeConstants.TYPE_CHARACTER);
    }

    // Methods
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        // Do nothing
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
        return Layers.OBJECT;
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
            final int dirY, final int arrowType, final ObjectInventory inv) {
        // Shot self
        Mazer5D.getBagOStuff().showMessage("Ouch, you shot yourself!");
        if (arrowType == ArrowTypes.PLAIN) {
            Mazer5D.getBagOStuff().getMazeManager().getMaze().doDamage(GenericCharacter.SHOT_SELF_NORMAL_DAMAGE);
        } else {
            Mazer5D.getBagOStuff().getMazeManager().getMaze().doDamage(GenericCharacter.SHOT_SELF_SPECIAL_DAMAGE);
        }
        SoundPlayer.playSound(SoundIndex.LAVA, SoundGroup.GAME);
        return false;
    }

    @Override
    public int getCustomFormat() {
        return 0;
    }

    @Override
    protected void writeMazeObjectHook(final MazeDataWriter writer) throws IOException {
        this.getSavedObject().writeMazeObject(writer);
    }

    @Override
    protected MazeObject readMazeObjectHook(final MazeDataReader reader, final MazeVersion formatVersion)
            throws IOException {
        this.setSavedObject(GameObjects.readObject(reader, formatVersion));
        return this;
    }
}
