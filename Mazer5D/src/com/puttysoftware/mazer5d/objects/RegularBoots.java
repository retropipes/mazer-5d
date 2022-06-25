/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.objects.abc.GenericBoots;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class RegularBoots extends GenericBoots {
    // Constructors
    public RegularBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Regular Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Regular Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return StaticStrings.EMPTY;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.REGULAR_BOOTS;
    }
}
