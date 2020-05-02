/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericMultipleKey extends GenericKey {
    // Constructors
    protected GenericMultipleKey() {
        super(true);
        this.setType(TypeConstants.TYPE_MULTIPLE_USE);
        this.setType(TypeConstants.TYPE_KEY);
        this.setType(TypeConstants.TYPE_INVENTORYABLE);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }
}