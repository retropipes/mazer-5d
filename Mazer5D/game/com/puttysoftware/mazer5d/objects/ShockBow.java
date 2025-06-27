/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.ArrowTypes;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericBow;

class ShockBow extends GenericBow {
    // Constants
    private static final int BOW_USES = 30;

    // Constructors
    public ShockBow() {
        super(ShockBow.BOW_USES, ArrowTypes.SHOCK);
    }

    @Override
    protected String getNameHook() {
        return "Shock Bow";
    }

    @Override
    protected String getPluralNameHook() {
        return "Shock Bows";
    }

    @Override
    protected String getDescriptionHook() {
        return "Shock Bows allow shooting of Shock Arrows, which energize Barrier Generators upon contact, and do eformatVersionything normal arrows do.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.SHOCK_BOW;
    }
}
