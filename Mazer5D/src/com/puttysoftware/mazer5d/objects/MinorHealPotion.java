/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericPotion;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class MinorHealPotion extends GenericPotion {
    // Fields
    private static final int MIN_HEAL = 1;
    private static final int MAX_HEAL = 5;

    // Constructors
    public MinorHealPotion() {
	super(true, MinorHealPotion.MIN_HEAL, MinorHealPotion.MAX_HEAL);
    }

    @Override
    protected String getNameHook() {
	return "Minor Heal Potion";
    }

    @Override
    protected String getPluralNameHook() {
	return "Minor Heal Potions";
    }

    @Override
    protected String getDescriptionHook() {
	return "Minor Heal Potions heal you slightly when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MINOR_HEAL_POTION;
    }
}