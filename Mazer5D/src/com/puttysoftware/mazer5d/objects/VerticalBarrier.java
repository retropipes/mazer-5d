/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBarrier;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class VerticalBarrier extends GenericBarrier {
    // Constructors
    public VerticalBarrier() {
	super();
    }

    @Override
    public String getName() {
	return "Vertical Barrier";
    }

    @Override
    public String getPluralName() {
	return "Vertical Barriers";
    }

    @Override
    public String getDescription() {
	return "Vertical Barriers are impassable - you'll need to go around them.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.VERTICAL_BARRIER;
    }
}