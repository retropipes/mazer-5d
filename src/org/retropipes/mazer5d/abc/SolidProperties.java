/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.abc;

import java.util.Objects;

import org.retropipes.diane.direction.legacy.Directions;
import org.retropipes.diane.direction.legacy.DirectionsResolver;
import org.retropipes.diane.storage.FlagStorage;

class SolidProperties {
    // Private enumeration
    private enum SolidDataTypes {
	EXTERNAL(0),
	INTERNAL(1);

	private int index;

	SolidDataTypes(final int value) {
	    this.index = value;
	}
    }

    // Properties
    private final FlagStorage solidData;
    private static final int SOLID_DATA_TYPES = 2;

    // Constructors
    public SolidProperties() {
	this.solidData = new FlagStorage(SolidProperties.SOLID_DATA_TYPES, Directions.COUNT);
    }

    public SolidProperties(final SolidProperties source) {
	this.solidData = new FlagStorage(source.solidData);
    }

    // Methods
    @Override
    @Deprecated
    public SolidProperties clone() {
	return new SolidProperties(this);
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (this.getClass() != obj.getClass()) {
	    return false;
	}
	final SolidProperties other = (SolidProperties) obj;
	if (!Objects.equals(this.solidData, other.solidData)) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 89 * hash + Objects.hashCode(this.solidData);
	return hash;
    }

    public boolean isSolid() {
	boolean result = false;
	for (int dir = 0; dir < Directions.COUNT; dir++) {
	    result = result || this.solidData.getCell(SolidDataTypes.EXTERNAL.index, dir);
	    result = result || this.solidData.getCell(SolidDataTypes.INTERNAL.index, dir);
	}
	return result;
    }

    public boolean isDirectionallySolid(final boolean ie, final int dirX, final int dirY) {
	final int dir = DirectionsResolver.resolve(dirX, dirY);
	if (ie) {
	    return this.solidData.getCell(SolidDataTypes.EXTERNAL.index, dir);
	} else {
	    return this.solidData.getCell(SolidDataTypes.INTERNAL.index, dir);
	}
    }

    public void setSolid(final boolean value) {
	for (int dir = 0; dir < Directions.COUNT; dir++) {
	    this.solidData.setCell(value, SolidDataTypes.EXTERNAL.index, dir);
	    this.solidData.setCell(value, SolidDataTypes.INTERNAL.index, dir);
	}
    }

    public void setDirectionallySolid(final boolean ie, final int dir, final boolean value) {
	if (ie) {
	    this.solidData.setCell(value, SolidDataTypes.EXTERNAL.index, dir);
	} else {
	    this.solidData.setCell(value, SolidDataTypes.INTERNAL.index, dir);
	}
    }
}
