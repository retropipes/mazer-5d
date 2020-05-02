/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericProgrammableKey extends GenericSingleKey {
    // Fields
    private String color;

    // Constructors
    protected GenericProgrammableKey(final String newColor) {
        super();
        this.color = newColor;
        this.setType(TypeConstants.TYPE_PROGRAMMABLE_USE);
        this.setType(TypeConstants.TYPE_SINGLE_USE);
        this.setType(TypeConstants.TYPE_KEY);
        this.setType(TypeConstants.TYPE_INVENTORYABLE);
        this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public GenericProgrammableKey clone() {
        final GenericProgrammableKey copy = (GenericProgrammableKey) super.clone();
        copy.color = this.color;
        return copy;
    }

    @Override
    public String getName() {
        return this.color + " Crystal";
    }

    @Override
    public String getPluralName() {
        return this.color + " Crystals";
    }

    @Override
    public String getDescription() {
        return this.color
                + " Crystals may open Crystal Walls, and can be used only once.";
    }
}