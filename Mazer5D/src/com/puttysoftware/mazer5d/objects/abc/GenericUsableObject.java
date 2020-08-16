/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.MazeObjectModel;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericUsableObject extends GenericInventoryableObject {
    // Constructors
    protected GenericUsableObject(final int newUses) {
        super(true, newUses);
        this.setType(TypeConstants.TYPE_USABLE);
        this.setType(TypeConstants.TYPE_INVENTORYABLE);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public abstract void useAction(MazeObjectModel mo, int x, int y, int z);

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }

    @Override
    public abstract void useHelper(int x, int y, int z);
}
