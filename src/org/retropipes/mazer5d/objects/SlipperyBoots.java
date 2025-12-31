/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBoots;

class SlipperyBoots extends GenericBoots {
    // Constructors
    public SlipperyBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Slippery Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Slippery Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return "Slippery Boots make all ground frictionless as you walk. Note that you can only wear one pair of boots at once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SLIPPERY_BOOTS;
    }
}
