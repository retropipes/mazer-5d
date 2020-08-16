/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericBoots extends GenericPass {
    // Constructors
    protected GenericBoots() {
        super();
        this.setType(TypeConstants.TYPE_BOOTS);
        this.setType(TypeConstants.TYPE_PASS);
        this.setType(TypeConstants.TYPE_INFINITE_USE);
        this.setType(TypeConstants.TYPE_KEY);
        this.setType(TypeConstants.TYPE_INVENTORYABLE);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public abstract String getName();
}
