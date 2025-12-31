/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.ArrowTypes;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBow;

class IceBow extends GenericBow {
    // Constants
    private static final int BOW_USES = 30;

    // Constructors
    public IceBow() {
	super(IceBow.BOW_USES, ArrowTypes.ICE);
    }

    @Override
    protected String getNameHook() {
	return "Ice Bow";
    }

    @Override
    protected String getPluralNameHook() {
	return "Ice Bows";
    }

    @Override
    protected String getDescriptionHook() {
	return "Ice Bows allow shooting of Ice Arrows, which freeze Barrier Generators upon contact, and do eformatVersionything normal arrows do.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ICE_BOW;
    }
}
