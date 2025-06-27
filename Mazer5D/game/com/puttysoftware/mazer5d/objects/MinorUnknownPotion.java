/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPotion;

class MinorUnknownPotion extends GenericPotion {
    // Fields
    private static final int MIN_EFFECT = -3;
    private static final int MAX_EFFECT = 3;

    // Constructors
    public MinorUnknownPotion() {
        super(true, MinorUnknownPotion.MIN_EFFECT, MinorUnknownPotion.MAX_EFFECT);
    }

    @Override
    protected String getNameHook() {
        return "Minor Unknown Potion";
    }

    @Override
    protected String getPluralNameHook() {
        return "Minor Unknown Potions";
    }

    @Override
    protected String getDescriptionHook() {
        return "Minor Unknown Potions might heal you or hurt you slightly when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.MINOR_UNKNOWN_POTION;
    }
}