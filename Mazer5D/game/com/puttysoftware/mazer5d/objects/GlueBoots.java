/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericBoots;

class GlueBoots extends GenericBoots {
    // Constructors
    public GlueBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Glue Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Glue Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return "Glue Boots allow walking on Ice without slipping. Note that you can only wear one pair of boots at once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GLUE_BOOTS;
    }
}
