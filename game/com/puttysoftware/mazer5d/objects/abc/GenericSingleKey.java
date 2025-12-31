/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.TypeConstants;

public abstract class GenericSingleKey extends GenericKey {
    // Constructors
    protected GenericSingleKey() {
	super(false);
	this.setType(TypeConstants.TYPE_SINGLE_USE);
	this.setType(TypeConstants.TYPE_KEY);
	this.setType(TypeConstants.TYPE_INVENTORYABLE);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }
}