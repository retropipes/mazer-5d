/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.util.Objects;

import com.puttysoftware.diane.storage.FlagStorage;

class TypeProperties {
    // Properties
    private final FlagStorage typeData;
    private static final int TYPE_PROPERTY_INDICES = 53;

    // Constructors
    public TypeProperties() {
	this.typeData = new FlagStorage(TypeProperties.TYPE_PROPERTY_INDICES);
    }

    public TypeProperties(final TypeProperties source) {
	this.typeData = new FlagStorage(source.typeData);
    }

    // Methods
    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (this.getClass() != obj.getClass()) {
	    return false;
	}
	final TypeProperties other = (TypeProperties) obj;
	if (!Objects.equals(this.typeData, other.typeData)) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 89 * hash + Objects.hashCode(this.typeData);
	return hash;
    }

    public boolean isOfType(final int testType) {
	return this.typeData.getCell(testType);
    }

    public void setType(final int newType, final boolean value) {
	this.typeData.setCell(value, newType);
    }
}
