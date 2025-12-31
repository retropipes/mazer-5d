/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects.abc;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.locale.Strings;
import org.retropipes.mazer5d.locale.Translations;

public abstract class GenericToggleWall extends MazeObject {
    // Constructors
    protected GenericToggleWall(final boolean solidState) {
	super(solidState);
	this.setType(TypeConstants.TYPE_TOGGLE_WALL);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage(Translations.load(Strings.MOVE_FAIL_DEFAULT));
	SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}
