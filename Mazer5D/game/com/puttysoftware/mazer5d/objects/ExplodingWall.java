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
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class ExplodingWall extends GenericWall {
	// Constructors
	public ExplodingWall() {
		super(true, true);
	}

	@Override
	public boolean preMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
		Mazer5D.getBagOStuff().showMessage("BOOM!");
		return true;
	}

	@Override
	public void chainReactionAction(final int x, final int y, final int z) {
		// Explode this wall, and any exploding walls next to this wall as well
		final BagOStuff app = Mazer5D.getBagOStuff();
		ExplodingWall curr = null;
		try {
			curr = (ExplodingWall) app.getMazeManager().getMazeObject(x, y, z, Layers.OBJECT);
		} catch (final ClassCastException cce) {
			// We're not an exploding wall, so abort
			return;
		}
		String mo2Name, mo4Name, mo6Name, mo8Name, invalidName, currName;
		invalidName = new Bounds().getName();
		currName = curr.getName();
		final MazeObject mo2 = app.getMazeManager().getMazeObject(x - 1, y, z, Layers.OBJECT);
		try {
			mo2Name = mo2.getName();
		} catch (final NullPointerException np) {
			mo2Name = invalidName;
		}
		final MazeObject mo4 = app.getMazeManager().getMazeObject(x, y - 1, z, Layers.OBJECT);
		try {
			mo4Name = mo4.getName();
		} catch (final NullPointerException np) {
			mo4Name = invalidName;
		}
		final MazeObject mo6 = app.getMazeManager().getMazeObject(x, y + 1, z, Layers.OBJECT);
		try {
			mo6Name = mo6.getName();
		} catch (final NullPointerException np) {
			mo6Name = invalidName;
		}
		final MazeObject mo8 = app.getMazeManager().getMazeObject(x + 1, y, z, Layers.OBJECT);
		try {
			mo8Name = mo8.getName();
		} catch (final NullPointerException np) {
			mo8Name = invalidName;
		}
		app.getGameManager().morph(GameObjects.getEmptySpace(), x, y, z, "BOOM!");
		if (mo2Name.equals(currName)) {
			curr.chainReactionAction(x - 1, y, z);
		}
		if (mo4Name.equals(currName)) {
			curr.chainReactionAction(x, y - 1, z);
		}
		if (mo6Name.equals(currName)) {
			curr.chainReactionAction(x, y + 1, z);
		}
		if (mo8Name.equals(currName)) {
			curr.chainReactionAction(x + 1, y, z);
		}
		SoundPlayer.playSound(SoundIndex.EXPLODE, SoundGroup.GAME);
	}

	@Override
	protected String getNameHook() {
		return "Exploding Wall";
	}

	@Override
	public String getGameName() {
		return "Wall";
	}

	@Override
	protected String getPluralNameHook() {
		return "Exploding Walls";
	}

	@Override
	protected String getDescriptionHook() {
		return "Exploding Walls explode when touched, causing other Exploding Walls nearby to also explode.";
	}

	@Override
	protected MazeObjects getUniqueIDHook() {
		return MazeObjects.EXPLODING_WALL;
	}
}