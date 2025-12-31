/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericWand;

class WarpWand extends GenericWand {
    public WarpWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Warp Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Warp Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(null, x, y, z);
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getMazeManager().getMaze().warpObject(app.getMazeManager().getMaze().getCell(x, y, z, Layers.OBJECT), x, y,
		z, Layers.OBJECT);
    }

    @Override
    protected String getDescriptionHook() {
	return "Warp Wands will teleport the object at the target square to a random location when used.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WARP_WAND;
    }
}
