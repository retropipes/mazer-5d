/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import java.io.IOException;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.files.io.XDataReader;
import com.puttysoftware.mazer5d.files.io.XDataWriter;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTrap;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class RotationTrap extends GenericTrap implements Cloneable {
    // Fields
    private static final boolean CLOCKWISE = true;
    private static final boolean COUNTERCLOCKWISE = false;

    // Constructors
    public RotationTrap() {
        super();
        this.addOneCustomCounter();
        this.addOneCustomFlag();
        this.setRotationRadius(1);
        this.setRotationDirection(RotationTrap.CLOCKWISE);
    }

    public RotationTrap(final int newRadius, final boolean newDirection) {
        super();
        this.addOneCustomCounter();
        this.addOneCustomFlag();
        this.setRotationRadius(newRadius);
        this.setRotationDirection(newDirection);
    }

    @Override
    public void editorProbeHook() {
        String dir;
        if (this.getRotationDirection() == RotationTrap.CLOCKWISE) {
            dir = "Clockwise";
        } else {
            dir = "Counterclockwise";
        }
        Mazer5D.getBagOStuff().showMessage(this.getName() + " (Radius " + this
                .getRotationRadius() + ", Direction " + dir + ")");
    }

    @Override
    public MazeObject editorPropertiesHook() {
        int r = this.getRotationRadius();
        final String[] rChoices = new String[] { "1", "2", "3" };
        final String rres = CommonDialogs.showInputDialog("Rotation Radius:",
                "Editor", rChoices, rChoices[r - 1]);
        try {
            r = Integer.parseInt(rres);
        } catch (final NumberFormatException nf) {
            // Ignore
        }
        boolean d = this.getRotationDirection();
        int di;
        if (d) {
            di = 0;
        } else {
            di = 1;
        }
        final String[] dChoices = new String[] { "Clockwise",
                "Counterclockwise" };
        final String dres = CommonDialogs.showInputDialog("Rotation Direction:",
                "Editor", dChoices, dChoices[di]);
        if (dres.equals(dChoices[0])) {
            d = RotationTrap.CLOCKWISE;
        } else {
            d = RotationTrap.COUNTERCLOCKWISE;
        }
        return new RotationTrap(r, d);
    }

    @Override
    public String getName() {
        return "Rotation Trap";
    }

    @Override
    public String getPluralName() {
        return "Rotation Traps";
    }

    @Override
    protected MazeObject readMazeObjectHookXML(final XDataReader reader,
            final int formatVersion) throws IOException {
        this.setRotationRadius(reader.readInt());
        this.setRotationDirection(reader.readBoolean());
        return this;
    }

    @Override
    protected void writeMazeObjectHookXML(final XDataWriter writer)
            throws IOException {
        writer.writeInt(this.getRotationRadius());
        writer.writeBoolean(this.getRotationDirection());
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        if (this.getRotationDirection()) {
            Mazer5D.getBagOStuff().getGameManager().doClockwiseRotate(this
                    .getRotationRadius());
        } else {
            Mazer5D.getBagOStuff().getGameManager().doCounterclockwiseRotate(
                    this.getRotationRadius());
        }
        SoundPlayer.playSound(SoundIndex.CHANGE, SoundGroup.GAME);
    }

    @Override
    public String getDescription() {
        return "Rotation Traps rotate part of the maze when stepped on.";
    }

    @Override
    public MazeObjects getUniqueID() {
        return MazeObjects.ROTATION_TRAP;
    }
}
