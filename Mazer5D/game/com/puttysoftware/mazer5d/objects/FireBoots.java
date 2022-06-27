/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBoots;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class FireBoots extends GenericBoots {
    // Constructors
    public FireBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Fire Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Fire Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return "Fire Boots allow walking on lava. Note that you can only wear one pair of boots at once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FIRE_BOOTS;
    }
}
