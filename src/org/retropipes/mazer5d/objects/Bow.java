/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.ArrowTypes;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBow;

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
