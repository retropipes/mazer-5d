/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericPassThroughObject;

class FakeFinish extends GenericPassThroughObject {
    // Constructors
    public FakeFinish() {
	super();
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
	Mazer5D.getBagOStuff().showMessage("Fake exit!");
    }

    @Override
    protected String getNameHook() {
	return "Fake Finish";
    }

    @Override
    public String getGameName() {
	return "Finish";
    }

    @Override
    protected String getPluralNameHook() {
	return "Fake Finishes";
    }

    @Override
    protected String getDescriptionHook() {
	return "Fake Finishes look like regular finishes but don't lead anywhere.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FAKE_FINISH;
    }
}