/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPotion;

class MinorHurtPotion extends GenericPotion {
    // Fields
    private static final int MIN_HURT = -1;
    private static final int MAX_HURT = -5;

    // Constructors
    public MinorHurtPotion() {
	super(true, MinorHurtPotion.MAX_HURT, MinorHurtPotion.MIN_HURT);
    }

    @Override
    protected String getNameHook() {
	return "Minor Hurt Potion";
    }

    @Override
    protected String getPluralNameHook() {
	return "Minor Hurt Potions";
    }

    @Override
    protected String getDescriptionHook() {
	return "Minor Hurt Potions hurt you slightly when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MINOR_HURT_POTION;
    }
}