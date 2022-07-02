/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class BreakableWallVertical extends GenericWall {
    // Constructors
    public BreakableWallVertical() {
	super(true, true);
    }

    @Override
    public void chainReactionAction(final int x, final int y, final int z) {
	SoundPlayer.playSound(SoundIndex.CRACK, SoundGroup.GAME);
	this.doChainReact(x, y, z);
    }

    public void doChainReact(final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	BreakableWallVertical curr = null;
	try {
	    curr = (BreakableWallVertical) app.getMazeManager().getMazeObject(x, y, z, Layers.OBJECT);
	} catch (final ClassCastException cce) {
	    // We're not a breakable wall vertical, so abort
	    return;
	}
	String mo2Name, mo8Name, invalidName, currName;
	invalidName = new Bounds().getName();
	currName = curr.getName();
	final MazeObject mo2 = app.getMazeManager().getMazeObject(x, y - 1, z, Layers.OBJECT);
	try {
	    mo2Name = mo2.getName();
	} catch (final NullPointerException np) {
	    mo2Name = invalidName;
	}
	final MazeObject mo8 = app.getMazeManager().getMazeObject(x, y + 1, z, Layers.OBJECT);
	try {
	    mo8Name = mo8.getName();
	} catch (final NullPointerException np) {
	    mo8Name = invalidName;
	}
	app.getGameManager().morph(GameObjects.getEmptySpace(), x, y, z);
	if (mo2Name.equals(currName)) {
	    curr.doChainReact(x, y - 1, z);
	}
	if (mo8Name.equals(currName)) {
	    curr.doChainReact(x, y + 1, z);
	}
    }

    @Override
    protected String getNameHook() {
	return "Breakable Wall Vertical";
    }

    @Override
    public String getGameName() {
	return "Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Breakable Walls Vertical";
    }

    @Override
    protected String getDescriptionHook() {
	return "Breakable Walls Vertical disintegrate when touched, causing other Breakable Walls Vertical nearby to also disintegrate.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BREAKABLE_WALL_VERTICAL;
    }
}
