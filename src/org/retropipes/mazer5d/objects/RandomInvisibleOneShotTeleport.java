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

class RandomInvisibleOneShotTeleport extends RandomInvisibleTeleport {
    // Constructors
    public RandomInvisibleOneShotTeleport() {
	super();
    }

    public RandomInvisibleOneShotTeleport(final int newRandomRangeY, final int newRandomRangeX) {
	super(newRandomRangeY, newRandomRangeX);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().decay();
	int dr, dc;
	do {
	    dr = this.getDestinationRow();
	    dc = this.getDestinationColumn();
	} while (!app.getGameManager().tryUpdatePositionRelative(dr, dc));
	app.getGameManager().updatePositionRelative(dr, dc);
	Mazer5D.getBagOStuff().showMessage("Invisible Teleport!");
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Random Invisible One-Shot Teleport";
    }

    @Override
    public String getGameName() {
	return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
	return "Random Invisible One-Shot Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_RANDOM_INVISIBLE_ONESHOT);
	return mo;
    }

    @Override
    protected String getDescriptionHook() {
	return "Random Invisible One-Shot Teleports are random, invisible, and only work once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.RANDOM_INVISIBLE_ONE_SHOT_TELEPORT;
    }
}