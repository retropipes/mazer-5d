/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericBow;
import com.puttysoftware.mazer5d.utility.ArrowTypes;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class GhostBow extends GenericBow {
    // Constants
    private static final int BOW_USES = 30;

    // Constructors
    public GhostBow() {
	super(GhostBow.BOW_USES, ArrowTypes.GHOST);
    }

    @Override
    protected String getNameHook() {
	return "Ghost Bow";
    }

    @Override
    protected String getPluralNameHook() {
	return "Ghost Bows";
    }

    @Override
    protected String getDescriptionHook() {
	return "Ghost Bows allow shooting of Ghost Arrows, which pass through objects that do not react to arrows, even if they are solid, and do everything normal arrows do.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GHOST_BOW;
    }
}
