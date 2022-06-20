/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;

public abstract class GenericTransientObject extends MazeObject {
    // Fields
    private String name;
    private final String baseName;

    // Constructors
    protected GenericTransientObject(final String newBaseName) {
	super(true);
	this.baseName = newBaseName;
	this.name = newBaseName;
    }

    // Methods
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    public final String getName() {
	return this.name;
    }

    @Override
    public String getPluralName() {
	return this.name + "s";
    }

    @Override
    public String getDescription() {
	return null;
    }

    public final void setNameSuffix(final String suffix) {
	this.name = this.baseName + " " + suffix;
    }

    @Override
    public int getLayer() {
	return Layers.OBJECT;
    }
}
