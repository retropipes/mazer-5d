/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.locale.GameResource;
import com.puttysoftware.mazer5d.locale.GameResources;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericWall extends MazeObject {
    // Constructors
    protected GenericWall() {
	super(true);
	this.setType(TypeConstants.TYPE_WALL);
    }

    protected GenericWall(final boolean isSolidXN, final boolean isSolidXS, final boolean isSolidXE,
	    final boolean isSolidXW, final boolean isSolidIN, final boolean isSolidIS, final boolean isSolidIE,
	    final boolean isSolidIW) {
	super(isSolidXN, isSolidXS, isSolidXE, isSolidXW, isSolidIN, isSolidIS, isSolidIE, isSolidIW);
	this.setType(TypeConstants.TYPE_WALL);
    }

    protected GenericWall(final boolean isDestroyable, final boolean doesChainReact) {
	super(true, false, false, false, false, false, false, true, false, 0, isDestroyable, doesChainReact);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage(GameResources.translate(GameResource.MOVE_FAIL_DEFAULT));
	// Play move failed sound, if it's enabled
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}