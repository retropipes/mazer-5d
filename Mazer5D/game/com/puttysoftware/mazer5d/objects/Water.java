/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericField;
import com.puttysoftware.mazer5d.utility.Layers;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class Water extends GenericField {
    // Constructors
    public Water() {
	super(new AquaBoots(), true);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK_ON_WATER, SoundGroup.GAME);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage("You'll drown");
	SoundPlayer.playSound(SoundIndex.WATER, SoundGroup.GAME);
    }

    @Override
    public void pushIntoAction(final ObjectInventory inv, final MazeObject pushed, final int x, final int y,
	    final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (pushed.isPushable()) {
	    app.getGameManager().morph(new SunkenBlock(), x, y, z, Layers.GROUND);
	    app.getGameManager().morph(GameObjects.getEmptySpace(), x, y, z, Layers.OBJECT);
	    SoundPlayer.playSound(SoundIndex.SINK_BLOCK, SoundGroup.GAME);
	}
    }

    @Override
    protected String getNameHook() {
	return "Water";
    }

    @Override
    protected String getPluralNameHook() {
	return "Squares of Water";
    }

    @Override
    public boolean overridesDefaultPostMove() {
	return true;
    }

    @Override
    protected String getDescriptionHook() {
	return "Water is too unstable to walk on without Aqua Boots.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WATER;
    }
}
