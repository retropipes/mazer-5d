/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects.abc;

import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.game.ObjectInventory;

public abstract class GenericLightModifier extends MazeObject {
    // Fields
    private final int EFFECT_RADIUS = 1;

    // Constructors
    protected GenericLightModifier() {
	super(true);
	this.setType(TypeConstants.TYPE_LIGHT_MODIFIER);
    }

    public int getEffectRadius() {
	return this.EFFECT_RADIUS;
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }
}
