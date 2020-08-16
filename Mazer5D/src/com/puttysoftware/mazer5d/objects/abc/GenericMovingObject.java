package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.MazeObjectModel;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericMovingObject extends MazeObjectModel {
    // Constructors
    public GenericMovingObject(final boolean solid) {
        super(solid);
        this.setType(TypeConstants.TYPE_MOVING);
    }

    // Methods
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        this.postMoveActionHook();
    }

    public void postMoveActionHook() {
        // Do nothing
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }

}
