/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPotion;

class MajorUnknownPotion extends GenericPotion {
    // Fields
    private static final int MIN_EFFECT = -25;
    private static final int MAX_EFFECT = 25;

    // Constructors
    public MajorUnknownPotion() {
        super(true, MajorUnknownPotion.MIN_EFFECT, MajorUnknownPotion.MAX_EFFECT);
    }

    @Override
    protected String getNameHook() {
        return "Major Unknown Potion";
    }

    @Override
    protected String getPluralNameHook() {
        return "Major Unknown Potions";
    }

    @Override
    protected String getDescriptionHook() {
        return "Major Unknown Potions might heal you or hurt you significantly when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.MAJOR_UNKNOWN_POTION;
    }
}