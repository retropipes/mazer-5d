package com.puttysoftware.mazer5d.abc;

import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericMovingObject extends MazeObjectModel {
    // Fields
    protected MazeObjectModel savedObject;

    // Constructors
    public GenericMovingObject(final boolean solid) {
        super(solid);
        this.setType(TypeConstants.TYPE_MOVING);
    }

    // Methods
    public MazeObjectModel getSavedObject() {
        return this.savedObject;
    }

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

    @Override
    public int getCustomProperty(final int propID) {
        return MazeObjectModel.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }

    public void setSavedObject(final MazeObjectModel inThere) {
        // Do nothing
    }
}
