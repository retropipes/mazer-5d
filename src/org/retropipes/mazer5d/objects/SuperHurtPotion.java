/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericPotion;

class SuperHurtPotion extends GenericPotion {
    // Constructors
    public SuperHurtPotion() {
	super(false);
    }

    @Override
    protected String getNameHook() {
	return "Super Hurt Potion";
    }

    @Override
    protected String getPluralNameHook() {
	return "Super Hurt Potions";
    }

    @Override
    public int getEffectValue() {
	return -(Mazer5D.getBagOStuff().getMazeManager().getMaze().getCurrentHP() - 1);
    }

    @Override
    protected String getDescriptionHook() {
	return "Super Hurt Potions bring you to the brink of death when picked up.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SUPER_HURT_POTION;
    }
}