/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericLightModifier;
import com.puttysoftware.mazer5d.utility.MazeObjects;

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
