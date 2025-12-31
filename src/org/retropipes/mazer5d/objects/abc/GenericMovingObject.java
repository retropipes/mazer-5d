package org.retropipes.mazer5d.objects.abc;

import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.game.ObjectInventory;

public abstract class GenericMovingObject extends MazeObject {
    // Constructors
    public GenericMovingObject(final boolean solid) {
	super(solid);
	this.setType(TypeConstants.TYPE_MOVING);
    }

    // Methods
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	this.postMoveActionHook();
    }

    public void postMoveActionHook() {
	// Do nothing
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}
