/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;

class InvisibleSpringboard extends Springboard {
    // Constructors
    public InvisibleSpringboard() {
	super();
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().showMessage("Some unseen force prevents movement that way...");
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePositionAbsolute(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor());
	SoundPlayer.playSound(SoundIndex.SPRINGBOARD, SoundGroup.GAME);
	Mazer5D.getBagOStuff().showMessage("Invisible Springboard!");
    }

    @Override
    protected String getNameHook() {
	return "Invisible Springboard";
    }

    @Override
    public String getGameName() {
	return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
	return "Invislble Springboards";
    }

    @Override
    protected String getDescriptionHook() {
	return "Invisible Springboards bounce anything that wanders into them to the floor above. If one of these is placed on the top-most floor, it is impassable.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.INVISIBLE_SPRINGBOARD;
    }
}
