/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.InfiniteRecursionException;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericMovableObject;
import com.puttysoftware.mazer5d.utility.Layers;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class Springboard extends StairsUp {
    // Constructors
    public Springboard() {
	super(true);
    }

    @Override
    protected String getNameHook() {
	return "Springboard";
    }

    @Override
    protected String getPluralNameHook() {
	return "Springboards";
    }

    @Override
    public boolean preMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	return this.searchNestedSprings(dirX, dirY,
		Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationZ() + 1, inv);
    }

    private boolean searchNestedSprings(final int dirX, final int dirY, final int floor, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	// Stop infinite recursion
	final int ucl = app.getMazeManager().getMaze().getFloors() * 2;
	if (floor >= ucl) {
	    throw new InfiniteRecursionException();
	}
	if (app.getGameManager().doesFloorExist(floor)) {
	    final MazeObject obj = app.getMazeManager().getMaze().getCell(dirX, dirY, floor, Layers.OBJECT);
	    if (obj.isConditionallySolid(inv)) {
		return false;
	    } else {
		if (obj.getName().equals("Springboard") || obj.getName().equals("Invisible Springboard")) {
		    return this.searchNestedSprings(dirX, dirY, floor + 1, inv);
		} else if (obj.getName().equals("Pit") || obj.getName().equals("Invisible Pit")) {
		    return false;
		} else {
		    return true;
		}
	    }
	} else {
	    return false;
	}
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePositionAbsolute(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor());
	SoundPlayer.playSound(SoundIndex.SPRINGBOARD, SoundGroup.GAME);
    }

    @Override
    public void pushIntoAction(final ObjectInventory inv, final MazeObject pushed, final int x, final int y,
	    final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	try {
	    this.searchNestedSprings(x, y, z + 1, inv);
	    if (pushed.isPushable()) {
		final GenericMovableObject pushedInto = (GenericMovableObject) pushed;
		app.getGameManager().updatePushedIntoPositionAbsolute(x, y, z - 1, x, y, z, pushedInto, this);
		SoundPlayer.playSound(SoundIndex.SPRINGBOARD, SoundGroup.GAME);
	    }
	} catch (final InfiniteRecursionException ir) {
	    SoundPlayer.playSound(SoundIndex.SPRINGBOARD, SoundGroup.GAME);
	    Mazer5D.getBagOStuff().getMazeManager().getMaze().setCell(GameObjects.getEmptySpace(), x, y, z,
		    Layers.OBJECT);
	}
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	if (!app.getGameManager().isFloorAbove()) {
	    if (ie) {
		return true;
	    } else {
		return false;
	    }
	} else {
	    return false;
	}
    }

    @Override
    public void editorPlaceHook() {
	// Do nothing
    }

    @Override
    public MazeObject editorPropertiesHook() {
	return null;
    }

    @Override
    protected String getDescriptionHook() {
	return "Springboards bounce anything that wanders into them to the floor above. If one of these is placed on the top-most floor, it is impassable.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SPRINGBOARD;
    }
}
