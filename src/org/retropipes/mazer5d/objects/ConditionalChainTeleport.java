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
import org.retropipes.mazer5d.objects.abc.GenericConditionalTeleport;

class ConditionalChainTeleport extends GenericConditionalTeleport {
    // Constructors
    public ConditionalChainTeleport() {
	super();
    }

    @Override
    protected final void customPostMoveAction(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int testVal;
	if (this.getSunMoon() == GenericConditionalTeleport.TRIGGER_SUN) {
	    testVal = inv.getItemCount(MazeObjects.SUN_STONE);
	} else if (this.getSunMoon() == GenericConditionalTeleport.TRIGGER_MOON) {
	    testVal = inv.getItemCount(MazeObjects.MOON_STONE);
	} else {
	    testVal = 0;
	}
	if (testVal >= this.getTriggerValue()) {
	    app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow2(), this.getDestinationColumn2(),
		    this.getDestinationFloor2(), this.getDestinationLevel());
	} else {
	    app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
		    this.getDestinationFloor(), this.getDestinationLevel());
	}
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
	this.postMoveActionHook();
    }

    @Override
    protected String getNameHook() {
	return "Conditional Chain Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "Conditional Chain Teleports";
    }

    @Override
    protected String getDescriptionHook() {
	return "Conditional Chain Teleports send you to one of two predetermined destinations when stepped on, depending on how many Sun or Moon Stones are in your inventory.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CONDITIONAL_CHAIN_TELEPORT;
    }
}