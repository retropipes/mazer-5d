/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBow;
import com.puttysoftware.mazer5d.utility.ArrowTypes;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class PoisonBow extends GenericBow {
    // Constants
    private static final int BOW_USES = 30;

    // Constructors
    public PoisonBow() {
	super(PoisonBow.BOW_USES, ArrowTypes.POISON);
    }

    @Override
    protected String getNameHook() {
	return "Poison Bow";
    }

    @Override
    protected String getPluralNameHook() {
	return "Poison Bows";
    }

    @Override
    protected String getDescriptionHook() {
	return "Poison Bows allow shooting of Poison Arrows, which weaken Barrier Generators upon contact, and do everything normal arrows do.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.POISON_BOW;
    }
}
