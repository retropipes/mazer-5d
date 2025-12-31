/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;

public abstract class GenericInventoryableObject extends MazeObject {
    // Fields
    protected static final long SCORE_GRAB = 10L;

    // Constructors
    protected GenericInventoryableObject(final boolean isUsable, final int newUses) {
	super(false, isUsable, newUses, true);
	this.setType(TypeConstants.TYPE_INVENTORYABLE);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	inv.addItem(this);
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().decay();
	SoundPlayer.playSound(SoundIndex.GRAB, SoundGroup.GAME);
	Mazer5D.getBagOStuff().getGameManager().addToScore(GenericInventoryableObject.SCORE_GRAB);
    }

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}
