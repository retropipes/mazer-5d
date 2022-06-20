/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericTeleport extends MazeObject {
    // Constants
    // Constructors
    protected GenericTeleport() {
	super(false);
	this.setType(TypeConstants.TYPE_TELEPORT);
	this.addCustomCounters(this.getCustomFormat());
    }

    protected GenericTeleport(final int destinationRow, final int destinationColumn, final int destinationFloor) {
	super(false);
	this.addCustomCounters(this.getCustomFormat());
	this.setDestinationRow(destinationRow);
	this.setDestinationColumn(destinationColumn);
	this.setDestinationFloor(destinationFloor);
	this.setType(TypeConstants.TYPE_TELEPORT);
    }

    protected GenericTeleport(final boolean doesAcceptPushInto) {
	super(false, false, doesAcceptPushInto, false, false, false, false, true, false, 0);
	this.setType(TypeConstants.TYPE_TELEPORT);
    }

    // Accessor methods
    @Override
    public int getDestinationLevel() {
	return Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationW();
    }

    // Scriptability
    public void activate() {
	// Do nothing
    }

    public void deactivate() {
	// Do nothing
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePositionAbsolute(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor());
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
	return Layers.OBJECT;
    }

    @Override
    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName() + ": Destination (" + (this.getDestinationColumn() + 1) + ","
		+ (this.getDestinationRow() + 1) + "," + (this.getDestinationFloor() + 1) + ")");
    }

    @Override
    public abstract MazeObject editorPropertiesHook();

    @Override
    public boolean defersSetProperties() {
	return true;
    }

    @Override
    public int getCustomFormat() {
	return 3;
    }

    @Override
    public void setDestinationLevel(final int inDestW) {
	// Do nothing
    }
}
