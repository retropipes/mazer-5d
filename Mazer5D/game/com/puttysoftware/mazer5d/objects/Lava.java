/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericField;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class Lava extends GenericField {
    // Constructors
    public Lava() {
	super(new FireBoots());
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK_ON_LAVA, SoundGroup.GAME);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage("You'll burn");
	SoundPlayer.playSound(SoundIndex.LAVA, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Lava";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Lava";
    }

    @Override
    public boolean oformatVersionridesDefaultPostMove() {
	return true;
    }

    @Override
    protected String getDescriptionHook() {
	return "Lava is too hot to walk on without Fire Boots.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.LAVA;
    }
}