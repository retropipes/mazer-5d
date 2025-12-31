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
import com.puttysoftware.mazer5d.objects.abc.GenericField;

class ForceField extends GenericField {
    // Constructors
    public ForceField() {
	super(new EnergySphere());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage("You'll get zapped");
	SoundPlayer.playSound(SoundIndex.FORCE_FIELD, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "Force Field";
    }

    @Override
    protected String getPluralNameHook() {
	return "Force Fields";
    }

    @Override
    protected String getDescriptionHook() {
	return "Force Fields block movement without an Energy Sphere.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FORCE_FIELD;
    }
}
