/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.maze.effect.MazeEffectConstants;

public abstract class GenericLock extends MazeObject {
	// Fields
	private GenericKey key;
	protected static final long SCORE_UNLOCK = 30L;

	// Constructors
	protected GenericLock(final GenericKey mgk) {
		super(true);
		this.key = mgk;
		this.setType(TypeConstants.TYPE_LOCK);
	}

	protected GenericLock(final GenericKey mgk, final boolean doesAcceptPushInto) {
		super(true, false, doesAcceptPushInto, false, false, false, false, true, false, 0);
		this.key = mgk;
		this.setType(TypeConstants.TYPE_LOCK);
	}

	protected GenericLock(final boolean isSolid, final GenericKey mgk) {
		super(isSolid);
		this.key = mgk;
		this.setType(TypeConstants.TYPE_LOCK);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final GenericLock other = (GenericLock) obj;
		if (this.key != other.key && (this.key == null || !this.key.equals(other.key))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (this.key != null ? this.key.hashCode() : 0);
		return hash;
	}

	// Accessor methods
	public GenericKey getKey() {
		return this.key;
	}

	protected void setKey(final GenericKey newKey) {
		this.key = newKey;
	}

	// Scriptability
	@Override
	public abstract void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv);

	@Override
	protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
		final BagOStuff app = Mazer5D.getBagOStuff();
		if (!app.getGameManager().isEffectActive(MazeEffectConstants.EFFECT_GHOSTLY)
				&& !inv.isItemThere(MazeObjects.PASSWALL_BOOTS)) {
			if (!this.key.isInfinite()) {
				inv.removeItem(this.key.getUniqueID());
			}
			app.getGameManager().decay();
			SoundPlayer.playSound(SoundIndex.UNLOCK, SoundGroup.GAME);
			Mazer5D.getBagOStuff().getGameManager().addToScore(GenericLock.SCORE_UNLOCK);
		} else {
			SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
		}
	}

	@Override
	public boolean isConditionallySolid(final ObjectInventory inv) {
		return !inv.isItemThere(this.key.getUniqueID());
	}

	@Override
	public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
			final ObjectInventory inv) {
		// Handle passwall boots and ghost amulet
		if (inv.isItemThere(MazeObjects.PASSWALL_BOOTS) || inv.isItemThere(MazeObjects.GHOST_AMULET)) {
			return false;
		} else {
			return !inv.isItemThere(this.key.getUniqueID());
		}
	}

	@Override
	protected abstract String getNameHook();

	@Override
	protected int getLayerHook() {
		return Layers.OBJECT;
	}
}