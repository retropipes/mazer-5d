/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTeleport;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class OneShotControllableTeleport extends GenericTeleport {
    // Constructors
    public OneShotControllableTeleport() {
	super(0, 0, 0);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
	app.getGameManager().controllableTeleport();
	app.getGameManager().decay();
    }

    @Override
    protected String getNameHook() {
	return "One-Shot Controllable Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "One-Shot Controllable Teleports";
    }

    @Override
    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName());
    }

    @Override
    public MazeObject editorPropertiesHook() {
	return null;
    }

    @Override
    protected String getDescriptionHook() {
	return "One-Shot Controllable Teleports let you choose the place you teleport to, then disappear.";
    }

    @Override
    public int getCustomFormat() {
	return 0;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ONE_SHOT_CONTROLLABLE_TELEPORT;
    }
}