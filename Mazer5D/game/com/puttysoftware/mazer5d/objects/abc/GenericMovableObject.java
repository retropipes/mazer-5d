/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
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
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;

public abstract class GenericMovableObject extends MazeObject {
    // Constructors
    protected GenericMovableObject(final boolean pushable, final boolean pullable) {
	super(true, pushable, false, false, pullable, false, false, true, false, 0);
	this.setSavedObject(GameObjects.getEmptySpace());
	this.setType(TypeConstants.TYPE_MOVABLE);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    public void pushAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pushX,
	    final int pushY) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePushedPosition(x, y, pushX, pushY, this);
	this.setSavedObject(mo);
	SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
    }

    @Override
    public void pullAction(final ObjectInventory inv, final MazeObject mo, final int x, final int y, final int pullX,
	    final int pullY) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePulledPosition(x, y, pullX, pullY, this);
	this.setSavedObject(mo);
	SoundPlayer.playSound(SoundIndex.PUSH_PULL, SoundGroup.GAME);
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }

    @Override
    protected MazeObject readMazeObjectHook(final MazeDataReader reader, final MazeVersion formatVersion)
	    throws IOException {
	this.setSavedObject(GameObjects.readObject(reader, formatVersion));
	return this;
    }

    @Override
    protected void writeMazeObjectHook(final MazeDataWriter writer) throws IOException {
	this.getSavedObject().writeMazeObject(writer);
    }

    @Override
    public int getCustomFormat() {
	return 0;
    }
}