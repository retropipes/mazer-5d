/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects.abc;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.loader.SoundPlayer;

public abstract class GenericWallTrap extends MazeObject {
    // Fields
    private int number;
    private GenericTrappedWall trigger;
    private final GenericTrappedWall masterTrigger = (GenericTrappedWall) GameObjects
	    .createObject(MazeObjects.MASTER_TRAPPED_WALL);
    protected static final int NUMBER_MASTER = -1;

    // Constructors
    protected GenericWallTrap(final int newNumber, final GenericTrappedWall newTrigger) {
	super(false);
	this.number = newNumber;
	this.trigger = newTrigger;
	this.setType(TypeConstants.TYPE_WALL_TRAP);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	Mazer5D.getBagOStuff().getGameManager().decay();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().findAllMatchingObjectsAndDecay(this.masterTrigger);
	if (this.number == GenericWallTrap.NUMBER_MASTER) {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().masterTrapTrigger();
	} else {
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().findAllMatchingObjectsAndDecay(this);
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().findAllMatchingObjectsAndDecay(this.trigger);
	}
	Mazer5D.getBagOStuff().getGameManager().redrawMaze();
	SoundPlayer.playSound(SoundIndex.WALL_TRAP, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	if (this.number != GenericWallTrap.NUMBER_MASTER) {
	    return "Wall Trap " + this.number;
	} else {
	    return "Master Wall Trap";
	}
    }

    @Override
    public String getGameName() {
	return "Wall Trap";
    }

    @Override
    protected String getPluralNameHook() {
	if (this.number != GenericWallTrap.NUMBER_MASTER) {
	    return "Wall Traps " + this.number;
	} else {
	    return "Master Wall Traps";
	}
    }

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}