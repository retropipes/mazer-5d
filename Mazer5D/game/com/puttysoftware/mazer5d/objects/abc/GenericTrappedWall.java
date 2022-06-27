/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.utility.TypeConstants;

public abstract class GenericTrappedWall extends GenericWall {
    // Fields
    private int number;
    protected static final int NUMBER_MASTER = -1;

    // Constructors
    protected GenericTrappedWall(final int newNumber) {
	super();
	this.number = newNumber;
	this.setType(TypeConstants.TYPE_TRAPPED_WALL);
	this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected String getNameHook() {
	if (this.number == GenericTrappedWall.NUMBER_MASTER) {
	    return "Master Trapped Wall";
	} else {
	    return "Trapped Wall " + this.number;
	}
    }

    @Override
    public String getGameName() {
	return "Wall";
    }

    @Override
    protected String getPluralNameHook() {
	if (this.number == GenericTrappedWall.NUMBER_MASTER) {
	    return "Master Trapped Walls";
	} else {
	    return "Trapped Walls " + this.number;
	}
    }
}