/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBoots;

class MetalBoots extends GenericBoots {
    // Constructors
    public MetalBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Metal Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Metal Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return "Metal Boots allow Metal Buttons to be triggered. Note that you can only wear one pair of boots at once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.METAL_BOOTS;
    }
}
