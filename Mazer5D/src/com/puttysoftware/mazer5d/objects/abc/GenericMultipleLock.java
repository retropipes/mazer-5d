/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericMultipleLock extends GenericLock {
    // Constructors
    protected GenericMultipleLock(final GenericMultipleKey mgk) {
	super(mgk);
	this.addOneCustomCounter();
	this.setType(TypeConstants.TYPE_UNLOCKED_LOSE_MULTIPLE_KEYS);
	this.setType(TypeConstants.TYPE_LOCK);
    }

    // Methods
    @Override
    public boolean isConditionallySolid(final ObjectInventory inv) {
	return inv.getItemCount(this.getKey().getUniqueID()) < this.getKeyCount();
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	// Handle passwall boots and ghost amulet
	if (inv.isItemThere(MazeObjects.PASSWALL_BOOTS) || inv.isItemThere(MazeObjects.GHOST_AMULET)) {
	    return false;
	} else {
	    return inv.getItemCount(this.getKey().getUniqueID()) < this.getKeyCount();
	}
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	String fill = StaticStrings.EMPTY;
	if (this.getKeyCount() > 1) {
	    fill = "s";
	} else {
	    fill = StaticStrings.EMPTY;
	}
	Mazer5D.getBagOStuff().showMessage("You need " + this.getKeyCount() + " " + this.getKey().getName() + fill);
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    public MazeObject editorPropertiesHook() {
	try {
	    this.setKeyCount(Integer.parseInt(CommonDialogs.showTextInputDialogWithDefault(
		    "Set Key Count for " + this.getName(), "Editor", Integer.toString(this.getKeyCount()))));
	} catch (final NumberFormatException nf) {
	    // Ignore
	}
	return this;
    }

    @Override
    public int getCustomFormat() {
	return 1;
    }
}
