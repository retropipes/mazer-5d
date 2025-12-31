/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericRandomInvisibleTeleport;

class RandomInvisibleTeleport extends GenericRandomInvisibleTeleport {
    // Constructors
    public RandomInvisibleTeleport() {
	super(0, 0);
    }

    public RandomInvisibleTeleport(final int newRandomRangeY, final int newRandomRangeX) {
	super(newRandomRangeY, newRandomRangeX);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Random Invisible Teleport";
    }

    @Override
    public String getGameName() {
	return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
	return "Random Invisible Teleports";
    }

    @Override
    protected String getDescriptionHook() {
	return "Random Invisible Teleports are both random and invisible.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.RANDOM_INVISIBLE_TELEPORT;
    }
}