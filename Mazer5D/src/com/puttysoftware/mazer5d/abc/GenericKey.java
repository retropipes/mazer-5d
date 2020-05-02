/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericKey extends GenericInventoryableObject {
    // Constructors
    protected GenericKey(final boolean infiniteUse) {
        super(false, 0);
        this.setType(TypeConstants.TYPE_KEY);
        this.setType(TypeConstants.TYPE_INVENTORYABLE);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public abstract String getName();

}
