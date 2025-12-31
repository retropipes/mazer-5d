/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.editor.MazeEditor;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericTeleport;

class ChainTeleport extends GenericTeleport {
    // Constructors
    public ChainTeleport() {
	super(0, 0, 0);
    }

    public ChainTeleport(final int destinationRow, final int destinationColumn, final int destinationFloor) {
	super(destinationRow, destinationColumn, destinationFloor);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor(), this.getDestinationLevel());
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Chain Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "Chain Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_GENERIC);
	return mo;
    }

    @Override
    protected String getDescriptionHook() {
	return "Chain Teleports send you to a predetermined destination when stepped on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CHAIN_TELEPORT;
    }
}