/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class Wall extends GenericWall {
    // Constructors
    public Wall() {
        super();
        this.setType(TypeConstants.TYPE_PLAIN_WALL);
        this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected String getNameHook() {
        return "Wall";
    }

    @Override
    protected String getPluralNameHook() {
        return "Walls";
    }

    @Override
    protected String getDescriptionHook() {
        return "Walls are impassable - you'll need to go around them.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.WALL;
    }
}