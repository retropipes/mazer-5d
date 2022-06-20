/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.maze.effects.MazeEffectConstants;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericProgrammableLock extends GenericSingleLock {
    private static final GenericSingleKey SIGNAL = GameObjects.createSignalCrystal();

    protected GenericProgrammableLock() {
	super(GenericProgrammableLock.SIGNAL);
	this.setType(TypeConstants.TYPE_UNLOCKED_LOSE_CHOSEN_KEY);
	this.setType(TypeConstants.TYPE_UNLOCKED_LOSE_KEY);
	this.setType(TypeConstants.TYPE_LOCK);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (!app.getGameManager().isEffectActive(MazeEffectConstants.EFFECT_GHOSTLY)
		&& !inv.isItemThere(MazeObjects.PASSWALL_BOOTS)) {
	    if (this.getKey() != GenericProgrammableLock.SIGNAL) {
		if (!this.getKey().isInfinite()) {
		    inv.removeItem(this.getKey().getUniqueID());
		}
	    }
	    app.getGameManager().decay();
	    // Play unlock sound, if it's enabled
	    SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
	    Mazer5D.getBagOStuff().getGameManager().addToScore(GenericLock.SCORE_UNLOCK);
	} else {
	    SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
	}
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
	    if (this.getKey() == GenericProgrammableLock.SIGNAL) {
		Mazer5D.getBagOStuff().showMessage("You need a Crystal");
	    } else {
		Mazer5D.getBagOStuff().showMessage("You need a " + this.getKey().getName());
	    }
	}
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    public boolean isConditionallySolid(final ObjectInventory inv) {
	if (this.getKey() != GenericProgrammableLock.SIGNAL) {
	    return !inv.isItemThere(this.getKey().getUniqueID());
	} else {
	    return !inv.isItemCategoryThere(TypeConstants.TYPE_PROGRAMMABLE_USE);
	}
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	// Handle passwall boots and ghost amulet
	if (inv.isItemThere(MazeObjects.PASSWALL_BOOTS) || inv.isItemThere(MazeObjects.GHOST_AMULET)) {
	    return false;
	} else {
	    if (this.getKey() != GenericProgrammableLock.SIGNAL) {
		return !inv.isItemThere(this.getKey().getUniqueID());
	    } else {
		return !inv.isItemCategoryThere(TypeConstants.TYPE_PROGRAMMABLE_USE);
	    }
	}
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final String[] tempKeyNames = GameObjects.getAllProgrammableKeyNames();
	final MazeObject[] tempKeys = GameObjects.getAllProgrammableKeys();
	final String[] keyNames = new String[tempKeyNames.length + 1];
	final MazeObject[] keys = new MazeObject[tempKeys.length + 1];
	System.arraycopy(tempKeyNames, 0, keyNames, 0, tempKeyNames.length);
	System.arraycopy(tempKeys, 0, keys, 0, tempKeys.length);
	keyNames[tempKeyNames.length] = "Any Crystal";
	keys[tempKeys.length] = GenericProgrammableLock.SIGNAL;
	int oldIndex = -1;
	for (oldIndex = 0; oldIndex < keyNames.length; oldIndex++) {
	    if (this.getKey().getName().equals(keyNames[oldIndex])) {
		break;
	    }
	}
	oldIndex--;
	if (oldIndex == -1) {
	    oldIndex = 0;
	}
	final String res = CommonDialogs.showInputDialog("Set Key for " + this.getName(), "Editor", keyNames,
		keyNames[oldIndex]);
	if (res != null) {
	    int index = -1;
	    for (index = 0; index < keyNames.length; index++) {
		if (res.equals(keyNames[index])) {
		    break;
		}
	    }
	    if (index != -1) {
		this.setKey((GenericKey) keys[index]);
	    }
	}
	return this;
    }

    @Override
    protected MazeObject readMazeObjectHookXML(final MazeDataReader reader, final int formatVersion) throws IOException {
	final MazeObject o = GameObjects.readObject(reader, formatVersion);
	if (o == null) {
	    this.setKey(GenericProgrammableLock.SIGNAL);
	} else {
	    this.setKey((GenericKey) o);
	}
	return this;
    }

    @Override
    protected void writeMazeObjectHookXML(final MazeDataWriter writer) throws IOException {
	this.getKey().writeMazeObjectXML(writer);
    }
}
