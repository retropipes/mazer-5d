/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericGround;

class Sand extends GenericGround {
    // Constructors
    public Sand() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Sand";
    }

    @Override
    protected String getPluralNameHook() {
        return "Squares of Sand";
    }

    @Override
    protected String getDescriptionHook() {
        return "Sand is one of the many types of ground.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.SAND;
    }
}