/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericBow extends GenericUsableObject {
    // Fields
    private final int AT;

    // Constructors
    protected GenericBow(final int uses, final int arrowType) {
        super(uses);
        this.AT = arrowType;
        this.setType(TypeConstants.TYPE_BOW);
        this.setType(TypeConstants.TYPE_USABLE);
        this.setType(TypeConstants.TYPE_INVENTORYABLE);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        // Do nothing
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y,
            final int z) {
        // Do nothing
    }

    @Override
    public abstract String getName();

    public int getArrowType() {
        return this.AT;
    }

}
