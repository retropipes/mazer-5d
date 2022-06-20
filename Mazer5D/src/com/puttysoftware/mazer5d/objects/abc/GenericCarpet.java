/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

public abstract class GenericCarpet extends GenericGround {
    // Fields
    private final String color;

    // Constructors
    protected GenericCarpet(final String newColor) {
	super();
	this.color = newColor;
    }

    @Override
    protected
    final String getNameHook() {
	return this.color + " Carpet";
    }

    @Override
    protected
    final String getPluralNameHook() {
	return "Squares of " + this.color + " Carpet";
    }

    @Override
    protected
    final String getDescriptionHook() {
	return "Squares of " + this.color + " Carpet are one of the many types of ground.";
    }
}
