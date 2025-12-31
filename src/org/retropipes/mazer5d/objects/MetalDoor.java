/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericSingleLock;

class MetalDoor extends GenericSingleLock {
    // Constructors
    public MetalDoor() {
	super(new MetalKey());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
	    Mazer5D.getBagOStuff().showMessage("You need a metal key");
	}
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Metal Door";
    }

    @Override
    protected String getPluralNameHook() {
	return "Metal Doors";
    }

    @Override
    protected String getDescriptionHook() {
	return "Metal Doors require Metal Keys to open, or Metal Boots and a Metal Button.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.METAL_DOOR;
    }
}