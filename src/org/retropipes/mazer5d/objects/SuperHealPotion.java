/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericPotion;

class SuperHealPotion extends GenericPotion {
    // Constructors
    public SuperHealPotion() {
	super(false);
    }

    @Override
    protected String getNameHook() {
	return "Super Heal Potion";
    }

    @Override
    protected String getPluralNameHook() {
	return "Super Heal Potions";
    }

    @Override
    public int getEffectValue() {
	return Mazer5D.getBagOStuff().getMazeManager().getMaze().getMaximumHP();
    }

    @Override
    protected String getDescriptionHook() {
	return "Super Heal Potions heal you completely when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SUPER_HEAL_POTION;
    }
}