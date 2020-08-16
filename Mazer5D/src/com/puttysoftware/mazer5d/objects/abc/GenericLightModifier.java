/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

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
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        // Do nothing
    }

    @Override
    public int getLayer() {
        return Layers.OBJECT;
    }

}
