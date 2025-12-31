/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects.abc;

import org.retropipes.mazer5d.abc.TypeConstants;

public abstract class GenericPlug extends GenericInfiniteKey {
    protected GenericPlug() {
	super();
	this.setType(TypeConstants.TYPE_PLUG);
	this.setType(TypeConstants.TYPE_INFINITE_USE);
	this.setType(TypeConstants.TYPE_KEY);
	this.setType(TypeConstants.TYPE_INVENTORYABLE);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    // FIXME: Hack
    @Override
    protected String getNameHook() {
	return this.getName();
    }

    // FIXME: Hack
    @Override
    protected String getPluralNameHook() {
	return this.getPluralName();
    }

    // FIXME: Hack
    @Override
    protected String getDescriptionHook() {
	return this.getDescription();
    }
}