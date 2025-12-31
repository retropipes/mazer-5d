/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.ArrowTypes;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBow;

class FireBow extends GenericBow {
    // Constants
    private static final int BOW_USES = 30;

    // Constructors
    public FireBow() {
	super(FireBow.BOW_USES, ArrowTypes.FIRE);
    }

    @Override
    protected String getNameHook() {
	return "Fire Bow";
    }

    @Override
    protected String getPluralNameHook() {
	return "Fire Bows";
    }

    @Override
    protected String getDescriptionHook() {
	return "Fire Bows allow shooting of Fire Arrows, which burn Barrier Generators upon contact, and do eformatVersionything normal arrows do.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FIRE_BOW;
    }
}
