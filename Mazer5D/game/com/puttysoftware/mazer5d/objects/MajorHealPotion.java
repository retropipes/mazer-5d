/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPotion;

class MajorHealPotion extends GenericPotion {
    // Fields
    private static final int MIN_HEAL = 6;
    private static final int MAX_HEAL = 50;

    // Constructors
    public MajorHealPotion() {
        super(true, MajorHealPotion.MIN_HEAL, MajorHealPotion.MAX_HEAL);
    }

    @Override
    protected String getNameHook() {
        return "Major Heal Potion";
    }

    @Override
    protected String getPluralNameHook() {
        return "Major Heal Potions";
    }

    @Override
    protected String getDescriptionHook() {
        return "Major Heal Potions heal you significantly when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.MAJOR_HEAL_POTION;
    }
}