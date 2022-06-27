/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericGround;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class Ice extends GenericGround {
    public Ice() {
	super(true, true, false, false, false);
    }

    @Override
    protected String getNameHook() {
	return "Ice";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Ice";
    }

    @Override
    public boolean overridesDefaultPostMove() {
	return true;
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK_ON_ICE, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Ice is one of the many types of ground - it is frictionless. Anything that crosses it will slide.";
    }

    @Override
    public boolean hasFrictionConditionally(final ObjectInventory inv, final boolean moving) {
	if (inv.isItemThere(MazeObjects.GLUE_BOOTS)) {
	    if (moving) {
		return false;
	    } else {
		return true;
	    }
	} else {
	    return false;
	}
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ICE;
    }
}
