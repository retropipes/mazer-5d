/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBow;
import com.puttysoftware.mazer5d.utility.ArrowTypes;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class Bow extends GenericBow {
    // Constants
    private static final int BOW_USES = -1;

    // Constructors
    public Bow() {
	super(Bow.BOW_USES, ArrowTypes.PLAIN);
    }

    @Override
    protected String getNameHook() {
	return "Bow";
    }

    @Override
    protected String getPluralNameHook() {
	return "Bows";
    }

    @Override
    protected String getDescriptionHook() {
	return "Bows shoot an unlimited supply of normal arrows.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BOW;
    }
}
