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
import org.retropipes.mazer5d.objects.abc.GenericInvisibleTeleport;

class InvisibleOneShotChainTeleport extends GenericInvisibleTeleport {
    // Constructors
    public InvisibleOneShotChainTeleport() {
	super(0, 0, 0);
    }

    public InvisibleOneShotChainTeleport(final int destinationRow, final int destinationColumn,
	    final int destinationFloor) {
	super(destinationRow, destinationColumn, destinationFloor);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().decay();
	app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor(), this.getDestinationLevel());
	Mazer5D.getBagOStuff().showMessage("Invisible Teleport!");
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Invisible One-Shot Chain Teleport";
    }

    @Override
    public String getGameName() {
	return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
	return "Invisible One-Shot Chain Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_INVISIBLE_ONESHOT);
	return mo;
    }

    @Override
    protected String getDescriptionHook() {
	return "Invisible One-Shot Chain Teleports are a combination of invisible, one-shot, and chain teleport behaviors.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.INVISIBLE_ONE_SHOT_CHAIN_TELEPORT;
    }
}