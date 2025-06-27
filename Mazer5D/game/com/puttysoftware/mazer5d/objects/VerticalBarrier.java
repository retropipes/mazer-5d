/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericBarrier;

class VerticalBarrier extends GenericBarrier {
    // Constructors
    public VerticalBarrier() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Vertical Barrier";
    }

    @Override
    protected String getPluralNameHook() {
        return "Vertical Barriers";
    }

    @Override
    protected String getDescriptionHook() {
        return "Vertical Barriers are impassable - you'll need to go around them.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.VERTICAL_BARRIER;
    }
}