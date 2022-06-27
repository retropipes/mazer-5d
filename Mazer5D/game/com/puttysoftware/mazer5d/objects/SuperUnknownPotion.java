/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericPotion;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class SuperUnknownPotion extends GenericPotion {
    // Fields
    private static final int MIN_EFFECT = -99;
    private static final int MAX_EFFECT = 99;

    // Constructors
    public SuperUnknownPotion() {
	super(true, SuperUnknownPotion.MIN_EFFECT, SuperUnknownPotion.MAX_EFFECT);
    }

    @Override
    protected String getNameHook() {
	return "Super Unknown Potion";
    }

    @Override
    protected String getPluralNameHook() {
	return "Super Unknown Potions";
    }

    @Override
    protected String getDescriptionHook() {
	return "Super Unknown Potions might heal you almost fully or hurt you to the brink of death when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SUPER_UNKNOWN_POTION;
    }
}