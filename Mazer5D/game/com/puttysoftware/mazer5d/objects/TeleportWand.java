/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWand;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class TeleportWand extends GenericWand {
    public TeleportWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Teleport Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Teleport Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(null, x, y, z);
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePositionAbsolute(x, y, z);
    }

    @Override
    protected String getDescriptionHook() {
	return "Teleport Wands will teleport you to the target square when used. You cannot teleport to areas you cannot see.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TELEPORT_WAND;
    }
}
