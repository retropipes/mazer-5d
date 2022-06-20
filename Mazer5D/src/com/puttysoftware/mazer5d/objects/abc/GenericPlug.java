/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericPlug extends GenericInfiniteKey {
    // Fields
    private char letter;

    protected GenericPlug(final char newLetter) {
	super();
	this.letter = Character.toUpperCase(newLetter);
	this.setType(TypeConstants.TYPE_PLUG);
	this.setType(TypeConstants.TYPE_INFINITE_USE);
	this.setType(TypeConstants.TYPE_KEY);
	this.setType(TypeConstants.TYPE_INVENTORYABLE);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    protected String getNameHook() {
	return this.letter + " Plug";
    }

    @Override
    protected String getPluralNameHook() {
	return this.letter + " Plugs";
    }

    @Override
    protected String getDescriptionHook() {
	return this.letter + " Plugs open " + this.letter + " Ports, and can be used infinitely many times.";
    }
}