/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPotion;

class MajorHurtPotion extends GenericPotion {
    // Fields
    private static final int MIN_HURT = -6;
    private static final int MAX_HURT = -50;

    // Constructors
    public MajorHurtPotion() {
        super(true, MajorHurtPotion.MAX_HURT, MajorHurtPotion.MIN_HURT);
    }

    @Override
    protected String getNameHook() {
        return "Major Hurt Potion";
    }

    @Override
    protected String getPluralNameHook() {
        return "Major Hurt Potions";
    }

    @Override
    protected String getDescriptionHook() {
        return "Major Hurt Potions hurt you significantly when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.MAJOR_HURT_POTION;
    }
}