/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBow;
import com.puttysoftware.mazer5d.utilities.ArrowTypes;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Bow extends GenericBow {
    // Constants
    private static final int BOW_USES = -1;

    // Constructors
    public Bow() {
	super(Bow.BOW_USES, ArrowTypes.PLAIN);
    }

    @Override
    public String getName() {
	return "Bow";
    }

    @Override
    public String getPluralName() {
	return "Bows";
    }

    @Override
    public String getDescription() {
	return "Bows shoot an unlimited supply of normal arrows.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.BOW;
    }
}
