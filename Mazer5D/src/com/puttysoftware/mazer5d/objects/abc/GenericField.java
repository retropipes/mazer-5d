/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericField extends GenericInfiniteLock {
    // Constructors
    protected GenericField(final GenericPass mgp) {
        super(mgp);
        this.setType(TypeConstants.TYPE_FIELD);
        this.setType(TypeConstants.TYPE_UNLOCKED_KEEP_KEY);
        this.setType(TypeConstants.TYPE_LOCK);
    }

    protected GenericField(final GenericPass mgp,
            final boolean doesAcceptPushInto) {
        super(mgp, doesAcceptPushInto);
        this.setType(TypeConstants.TYPE_FIELD);
        this.setType(TypeConstants.TYPE_UNLOCKED_KEEP_KEY);
        this.setType(TypeConstants.TYPE_LOCK);
    }

    protected GenericField(final boolean isSolid, final GenericPass mgp) {
        super(isSolid, mgp);
        this.setType(TypeConstants.TYPE_FIELD);
        this.setType(TypeConstants.TYPE_UNLOCKED_KEEP_KEY);
        this.setType(TypeConstants.TYPE_LOCK);
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        // Do nothing
    }

    @Override
    public boolean isConditionallySolid(final ObjectInventory inv) {
        return !inv.isItemThere(this.getKey().getUniqueID());
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie,
            final int dirX, final int dirY, final ObjectInventory inv) {
        return !inv.isItemThere(this.getKey().getUniqueID());
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return Layers.GROUND;
    }
}
