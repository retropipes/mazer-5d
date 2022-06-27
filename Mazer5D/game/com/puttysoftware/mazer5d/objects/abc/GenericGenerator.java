/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.diane.utilties.Directions;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.utility.ArrowTypes;
import com.puttysoftware.mazer5d.utility.Layers;
import com.puttysoftware.mazer5d.utility.MazeObjects;
import com.puttysoftware.mazer5d.utility.TypeConstants;

public abstract class GenericGenerator extends GenericWall {
    // Fields
    protected int SCAN_LIMIT = 6;
    protected int TIMER_DELAY = 12;

    // Constructors
    protected GenericGenerator() {
	super();
	this.activateTimer(this.TIMER_DELAY);
	this.setType(TypeConstants.TYPE_REACTS_TO_ICE);
	this.setType(TypeConstants.TYPE_REACTS_TO_FIRE);
	this.setType(TypeConstants.TYPE_REACTS_TO_POISON);
	this.setType(TypeConstants.TYPE_REACTS_TO_SHOCK);
	this.setType(TypeConstants.TYPE_GENERATOR);
    }

    protected abstract boolean preMoveActionHook(int dirX, int dirY, int dirZ, int dirW);

    @Override
    public boolean preMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Remove barriers if present
	boolean scanResult = false;
	boolean flag = false;
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int pz = app.getGameManager().getPlayerManager().getPlayerLocationZ();
	final int pw = app.getGameManager().getPlayerManager().getPlayerLocationW();
	MazeObjects mo2UID, mo4UID, mo6UID, mo8UID, invalidUID, horzUID, vertUID;
	invalidUID = MazeObjects.BOUNDS;
	horzUID = MazeObjects.HORIZONTAL_BARRIER;
	vertUID = MazeObjects.VERTICAL_BARRIER;
	final MazeObject mo2 = app.getMazeManager().getMazeObject(dirX - 1, dirY, pz, Layers.OBJECT);
	try {
	    mo2UID = mo2.getUniqueID();
	} catch (final NullPointerException np) {
	    mo2UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo2UID = invalidUID;
	}
	final MazeObject mo4 = app.getMazeManager().getMazeObject(dirX, dirY - 1, pz, Layers.OBJECT);
	try {
	    mo4UID = mo4.getUniqueID();
	} catch (final NullPointerException np) {
	    mo4UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo4UID = invalidUID;
	}
	final MazeObject mo6 = app.getMazeManager().getMazeObject(dirX, dirY + 1, pz, Layers.OBJECT);
	try {
	    mo6UID = mo6.getUniqueID();
	} catch (final NullPointerException np) {
	    mo6UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo6UID = invalidUID;
	}
	final MazeObject mo8 = app.getMazeManager().getMazeObject(dirX + 1, dirY, pz, Layers.OBJECT);
	try {
	    mo8UID = mo8.getUniqueID();
	} catch (final NullPointerException np) {
	    mo8UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo8UID = invalidUID;
	}
	if (mo2UID.equals(horzUID)) {
	    scanResult = this.scan(Directions.WEST, dirX, dirY, pz, this.SCAN_LIMIT, false);
	    if (scanResult) {
		this.generate(Directions.WEST, dirX, dirY, pz, this.SCAN_LIMIT, false);
		flag = true;
	    }
	}
	if (mo4UID.equals(vertUID)) {
	    scanResult = this.scan(Directions.NORTH, dirX, dirY, pz, this.SCAN_LIMIT, false);
	    if (scanResult) {
		this.generate(Directions.NORTH, dirX, dirY, pz, this.SCAN_LIMIT, false);
		flag = true;
	    }
	}
	if (mo6UID.equals(vertUID)) {
	    scanResult = this.scan(Directions.SOUTH, dirX, dirY, pz, this.SCAN_LIMIT, false);
	    if (scanResult) {
		this.generate(Directions.SOUTH, dirX, dirY, pz, this.SCAN_LIMIT, false);
		flag = true;
	    }
	}
	if (mo8UID.equals(horzUID)) {
	    scanResult = this.scan(Directions.EAST, dirX, dirY, pz, this.SCAN_LIMIT, false);
	    if (scanResult) {
		this.generate(Directions.EAST, dirX, dirY, pz, this.SCAN_LIMIT, false);
		flag = true;
	    }
	}
	if (flag) {
	    SoundPlayer.playSound(SoundIndex.GENERATE, SoundGroup.GAME);
	    this.activateTimer(this.TIMER_DELAY);
	    app.getGameManager().redrawMazeNoRebuild();
	}
	return this.preMoveActionHook(dirX, dirY, pz, pw);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    public void timerExpiredAction(final int dirX, final int dirY) {
	// Generate barriers again
	boolean scanResult = false;
	boolean flag = false;
	final BagOStuff app = Mazer5D.getBagOStuff();
	final int pz = app.getGameManager().getPlayerManager().getPlayerLocationZ();
	MazeObjects mo2UID, mo4UID, mo6UID, mo8UID, invalidUID, horzUID, vertUID;
	invalidUID = MazeObjects.BOUNDS;
	horzUID = MazeObjects.HORIZONTAL_BARRIER;
	vertUID = MazeObjects.VERTICAL_BARRIER;
	final MazeObject mo2 = app.getMazeManager().getMazeObject(dirX - 1, dirY, pz, Layers.OBJECT);
	try {
	    mo2UID = mo2.getUniqueID();
	} catch (final NullPointerException np) {
	    mo2UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo2UID = invalidUID;
	}
	final MazeObject mo4 = app.getMazeManager().getMazeObject(dirX, dirY - 1, pz, Layers.OBJECT);
	try {
	    mo4UID = mo4.getUniqueID();
	} catch (final NullPointerException np) {
	    mo4UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo4UID = invalidUID;
	}
	final MazeObject mo6 = app.getMazeManager().getMazeObject(dirX, dirY + 1, pz, Layers.OBJECT);
	try {
	    mo6UID = mo6.getUniqueID();
	} catch (final NullPointerException np) {
	    mo6UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo6UID = invalidUID;
	}
	final MazeObject mo8 = app.getMazeManager().getMazeObject(dirX + 1, dirY, pz, Layers.OBJECT);
	try {
	    mo8UID = mo8.getUniqueID();
	} catch (final NullPointerException np) {
	    mo8UID = invalidUID;
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    mo8UID = invalidUID;
	}
	if (!mo2UID.equals(horzUID)) {
	    scanResult = this.scan(Directions.WEST, dirX, dirY, pz, this.SCAN_LIMIT, true);
	    if (scanResult) {
		this.generate(Directions.WEST, dirX, dirY, pz, this.SCAN_LIMIT, true);
		flag = true;
	    }
	}
	if (!mo4UID.equals(vertUID)) {
	    scanResult = this.scan(Directions.NORTH, dirX, dirY, pz, this.SCAN_LIMIT, true);
	    if (scanResult) {
		this.generate(Directions.NORTH, dirX, dirY, pz, this.SCAN_LIMIT, true);
		flag = true;
	    }
	}
	if (!mo6UID.equals(vertUID)) {
	    scanResult = this.scan(Directions.SOUTH, dirX, dirY, pz, this.SCAN_LIMIT, true);
	    if (scanResult) {
		this.generate(Directions.SOUTH, dirX, dirY, pz, this.SCAN_LIMIT, true);
		flag = true;
	    }
	}
	if (!mo8UID.equals(horzUID)) {
	    scanResult = this.scan(Directions.EAST, dirX, dirY, pz, this.SCAN_LIMIT, true);
	    if (scanResult) {
		this.generate(Directions.EAST, dirX, dirY, pz, this.SCAN_LIMIT, true);
		flag = true;
	    }
	}
	if (flag) {
	    SoundPlayer.playSound(SoundIndex.GENERATE, SoundGroup.GAME);
	    app.getGameManager().redrawMazeNoRebuild();
	}
	// Activate the timer again
	this.activateTimer(this.TIMER_DELAY);
    }

    protected boolean scan(final int dir, final int x, final int y, final int z, final int limit, final boolean o) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObjects invalidUID = MazeObjects.BOUNDS;
	if (dir == Directions.EAST) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x + l, y, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    try {
			if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
			    return true;
			}
			if (mo.isSolid()) {
			    return false;
			}
		    } catch (final NullPointerException np) {
			// Do nothing
		    }
		} else {
		    if (!moUID.equals(MazeObjects.HORIZONTAL_BARRIER)) {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				return true;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
		    }
		}
	    }
	} else if (dir == Directions.NORTH) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x, y - l, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    try {
			if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
			    return true;
			}
			if (mo.isSolid()) {
			    return false;
			}
		    } catch (final NullPointerException np) {
			// Do nothing
		    }
		} else {
		    if (!moUID.equals(MazeObjects.VERTICAL_BARRIER)) {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				return true;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
		    }
		}
	    }
	} else if (dir == Directions.SOUTH) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x, y + l, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    try {
			if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
			    return true;
			}
			if (mo.isSolid()) {
			    return false;
			}
		    } catch (final NullPointerException np) {
			// Do nothing
		    }
		} else {
		    if (!moUID.equals(MazeObjects.VERTICAL_BARRIER)) {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				return true;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
		    }
		}
	    }
	} else if (dir == Directions.WEST) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x - l, y, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    try {
			if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
			    return true;
			}
			if (mo.isSolid()) {
			    return false;
			}
		    } catch (final NullPointerException np) {
			// Do nothing
		    }
		} else {
		    if (!moUID.equals(MazeObjects.HORIZONTAL_BARRIER)) {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				return true;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
		    }
		}
	    }
	}
	return false;
    }

    protected void generate(final int dir, final int x, final int y, final int z, final int limit, final boolean o) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final MazeObjects invalidUID = MazeObjects.BOUNDS;
	if (dir == Directions.EAST) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x + l, y, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    if (moUID.equals(MazeObjects.HORIZONTAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(
				    GameObjects.createObject(MazeObjects.HORIZONTAL_BARRIER), x + l, y, z,
				    Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		} else {
		    if (!moUID.equals(MazeObjects.HORIZONTAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(GameObjects.getEmptySpace(), x + l, y, z,
				    Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		}
	    }
	} else if (dir == Directions.NORTH) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x, y - l, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    if (moUID.equals(MazeObjects.VERTICAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(
				    GameObjects.createObject(MazeObjects.VERTICAL_BARRIER), x, y - l, z, Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		} else {
		    if (!moUID.equals(MazeObjects.VERTICAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(GameObjects.getEmptySpace(), x, y - l, z,
				    Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		}
	    }
	} else if (dir == Directions.SOUTH) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x, y + l, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    if (moUID.equals(MazeObjects.VERTICAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(
				    GameObjects.createObject(MazeObjects.VERTICAL_BARRIER), x, y + l, z, Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		} else {
		    if (!moUID.equals(MazeObjects.VERTICAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(GameObjects.getEmptySpace(), x, y + l, z,
				    Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		}
	    }
	} else if (dir == Directions.WEST) {
	    for (int l = 1; l < limit; l++) {
		final MazeObject mo = app.getMazeManager().getMazeObject(x - l, y, z, Layers.OBJECT);
		MazeObjects moUID;
		try {
		    moUID = mo.getUniqueID();
		} catch (final NullPointerException np) {
		    moUID = invalidUID;
		}
		if (o) {
		    if (moUID.equals(MazeObjects.HORIZONTAL_BARRIER)) {
			break;
		    } else {
			if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
			    break;
			}
			try {
			    app.getMazeManager().getMaze().setCell(
				    GameObjects.createObject(MazeObjects.HORIZONTAL_BARRIER), x - l, y, z,
				    Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		} else {
		    if (!moUID.equals(MazeObjects.HORIZONTAL_BARRIER)) {
			break;
		    } else {
			try {
			    if (mo.isOfType(TypeConstants.TYPE_GENERATOR)) {
				break;
			    }
			} catch (final NullPointerException np) {
			    // Do nothing
			}
			try {
			    app.getMazeManager().getMaze().setCell(GameObjects.getEmptySpace(), x - l, y, z,
				    Layers.OBJECT);
			} catch (final ArrayIndexOutOfBoundsException aioob) {
			    // Do nothing
			}
		    }
		}
	    }
	}
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
	    final int dirY, final int arrowType, final ObjectInventory inv) {
	// Behave as if the generator was walked into, unless the arrow was an
	// ice arrow
	if (arrowType == ArrowTypes.PLAIN) {
	    this.preMoveAction(false, locX, locY, inv);
	} else {
	    this.arrowHitActionHook(locX, locY, locZ, arrowType, inv);
	}
	return false;
    }

    protected abstract void arrowHitActionHook(int locX, int locY, int locZ, int arrowType, ObjectInventory inv);
}
