/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericInventoryModifier extends MazeObjectModel {
    // Constructors
    protected GenericInventoryModifier() {
        super(false);
        this.setType(TypeConstants.TYPE_INVENTORY_MODIFIER);
    }

    @Override
    public abstract void postMoveAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv);

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }

}
