/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericLightModifier;

class DarkGem extends GenericLightModifier {
    // Constructors
    public DarkGem() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Dark Gem";
    }

    @Override
    protected String getPluralNameHook() {
        return "Dark Gems";
    }

    @Override
    protected String getDescriptionHook() {
        return "Dark Gems shroud the immediately adjacent area in permanent darkness.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.DARK_GEM;
    }
}
