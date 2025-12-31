/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericWall;

class BreakableWallHorizontal extends GenericWall {
    // Constructors
    public BreakableWallHorizontal() {
	super(true, true);
	this.setType(TypeConstants.TYPE_BREAKABLE_WALL);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    public void chainReactionAction(final int x, final int y, final int z) {
	SoundPlayer.playSound(SoundIndex.CRACK, SoundGroup.GAME);
	BreakableWallHorizontal.doChainReact(x, y, z);
    }

    private static void doChainReact(final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	BreakableWallHorizontal curr = null;
	try {
	    curr = (BreakableWallHorizontal) app.getMazeManager().getMazeObject(x, y, z, Layers.OBJECT);
	} catch (final ClassCastException cce) {
	    // We're not a breakable wall horizontal, so abort
	    return;
	}
	String mo4Name, mo6Name, invalidName, currName;
	invalidName = new Bounds().getName();
	currName = curr.getName();
	final MazeObject mo4 = app.getMazeManager().getMazeObject(x - 1, y, z, Layers.OBJECT);
	try {
	    mo4Name = mo4.getName();
	} catch (final NullPointerException np) {
	    mo4Name = invalidName;
	}
	final MazeObject mo6 = app.getMazeManager().getMazeObject(x + 1, y, z, Layers.OBJECT);
	try {
	    mo6Name = mo6.getName();
	} catch (final NullPointerException np) {
	    mo6Name = invalidName;
	}
	app.getGameManager().morph(GameObjects.getEmptySpace(), x, y, z);
	if (mo4Name.equals(currName)) {
	    BreakableWallHorizontal.doChainReact(x - 1, y, z);
	}
	if (mo6Name.equals(currName)) {
	    BreakableWallHorizontal.doChainReact(x + 1, y, z);
	}
    }

    @Override
    protected String getNameHook() {
	return "Breakable Wall Horizontal";
    }

    @Override
    public String getGameName() {
	return "Wall";
    }

    @Override
    protected String getPluralNameHook() {
	return "Breakable Walls Horizontal";
    }

    @Override
    protected String getDescriptionHook() {
	return "Breakable Walls Horizontal disintegrate when touched, causing other Breakable Walls Horizontal nearby to also disintegrate.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BREAKABLE_WALL_HORIZONTAL;
    }
}
