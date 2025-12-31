/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericLightModifier;

class LightGem extends GenericLightModifier {
    // Constructors
    public LightGem() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Light Gem";
    }

    @Override
    protected String getPluralNameHook() {
	return "Light Gems";
    }

    @Override
    protected String getDescriptionHook() {
	return "Light Gems bathe the immediately adjacent area in permanent light.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.LIGHT_GEM;
    }
}
