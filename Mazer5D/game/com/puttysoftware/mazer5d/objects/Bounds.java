/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class Bounds extends GenericWall {
	// Properties
	private String currAppearance;

	// Constructors
	public Bounds() {
		super(false, false);
		this.currAppearance = "Void";
	}

	@Override
	public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
			final ObjectInventory inv) {
		// Disallow passing through Void under ANY circumstances
		return true;
	}

	@Override
	public void determineCurrentAppearance(final int x, final int y, final int z) {
		final BagOStuff app = Mazer5D.getBagOStuff();
		String mo1Name, mo2Name, mo3Name, mo4Name, mo6Name, mo7Name, mo8Name, mo9Name, thisName;
		thisName = this.getName();
		final MazeObject mo1 = app.getMazeManager().getMazeObject(x - 1, y - 1, z, Layers.OBJECT);
		try {
			mo1Name = mo1.getName();
		} catch (final NullPointerException np) {
			mo1Name = thisName;
		}
		final MazeObject mo2 = app.getMazeManager().getMazeObject(x - 1, y, z, Layers.OBJECT);
		try {
			mo2Name = mo2.getName();
		} catch (final NullPointerException np) {
			mo2Name = thisName;
		}
		final MazeObject mo3 = app.getMazeManager().getMazeObject(x - 1, y + 1, z, Layers.OBJECT);
		try {
			mo3Name = mo3.getName();
		} catch (final NullPointerException np) {
			mo3Name = thisName;
		}
		final MazeObject mo4 = app.getMazeManager().getMazeObject(x, y - 1, z, Layers.OBJECT);
		try {
			mo4Name = mo4.getName();
		} catch (final NullPointerException np) {
			mo4Name = thisName;
		}
		final MazeObject mo6 = app.getMazeManager().getMazeObject(x, y + 1, z, Layers.OBJECT);
		try {
			mo6Name = mo6.getName();
		} catch (final NullPointerException np) {
			mo6Name = thisName;
		}
		final MazeObject mo7 = app.getMazeManager().getMazeObject(x + 1, y - 1, z, Layers.OBJECT);
		try {
			mo7Name = mo7.getName();
		} catch (final NullPointerException np) {
			mo7Name = thisName;
		}
		final MazeObject mo8 = app.getMazeManager().getMazeObject(x + 1, y, z, Layers.OBJECT);
		try {
			mo8Name = mo8.getName();
		} catch (final NullPointerException np) {
			mo8Name = thisName;
		}
		final MazeObject mo9 = app.getMazeManager().getMazeObject(x + 1, y + 1, z, Layers.OBJECT);
		try {
			mo9Name = mo9.getName();
		} catch (final NullPointerException np) {
			mo9Name = thisName;
		}
		if (!thisName.equals(mo1Name) || !thisName.equals(mo2Name) || !thisName.equals(mo3Name)
				|| !thisName.equals(mo4Name) || !thisName.equals(mo6Name) || !thisName.equals(mo7Name)
				|| !thisName.equals(mo8Name) || !thisName.equals(mo9Name)) {
			this.currAppearance = "Sealing Wall";
		} else {
			this.currAppearance = "Void";
		}
	}

	@Override
	protected String getNameHook() {
		return "Void";
	}

	@Override
	public String getGameName() {
		return this.currAppearance;
	}

	@Override
	protected String getPluralNameHook() {
		return "Voids";
	}

	@Override
	protected String getDescriptionHook() {
		return "The Void surrounds the maze, and cannot be altered in any way.";
	}

	@Override
	protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
			final int dirY, final int arrowType, final ObjectInventory inv) {
		// Stop arrow
		return false;
	}

	@Override
	protected MazeObjects getUniqueIDHook() {
		return MazeObjects.BOUNDS;
	}
}
