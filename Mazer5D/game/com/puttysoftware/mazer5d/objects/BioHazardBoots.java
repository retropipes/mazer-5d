/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericBoots;

class BioHazardBoots extends GenericBoots {
    // Constructors
    public BioHazardBoots() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Bio-Hazard Boots";
    }

    @Override
    protected String getPluralNameHook() {
        return "Pairs of Bio-Hazard Boots";
    }

    @Override
    protected String getDescriptionHook() {
        return "Bio-Hazard Boots allow walking on slime. Note that you can only wear one pair of boots at once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.BIO_HAZARD_BOOTS;
    }
}
