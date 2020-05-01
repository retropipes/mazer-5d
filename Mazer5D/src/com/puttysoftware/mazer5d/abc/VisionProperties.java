/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.util.Objects;

import com.puttysoftware.mazer5d.utilities.DirectionResolver;
import com.puttysoftware.mazer5d.utilities.Directions;
import com.puttysoftware.storage.FlagStorage;

class VisionProperties {
    // Private enumeration
    private enum VisionDataTypes {
        EXTERNAL(0),
        INTERNAL(1);

        private int index;

        VisionDataTypes(final int value) {
            this.index = value;
        }
    }

    // Properties
    private final FlagStorage visionData;
    private static final int VISION_DATA_TYPES = 2;

    // Constructors
    public VisionProperties() {
        this.visionData = new FlagStorage(VisionProperties.VISION_DATA_TYPES,
                Directions.COUNT);
    }

    public VisionProperties(final VisionProperties source) {
        this.visionData = new FlagStorage(source.visionData);
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
        final VisionProperties other = (VisionProperties) obj;
        if (!Objects.equals(this.visionData, other.visionData)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.visionData);
        return hash;
    }

    public boolean isSightBlocking() {
        boolean result = false;
        for (int dir = 0; dir < Directions.COUNT; dir++) {
            result = result || this.visionData.getCell(
                    VisionDataTypes.EXTERNAL.index, dir);
            result = result || this.visionData.getCell(
                    VisionDataTypes.INTERNAL.index, dir);
        }
        return result;
    }

    public boolean isDirectionallySightBlocking(final boolean ie,
            final int dirX, final int dirY) {
        final int dir = DirectionResolver.resolve(dirX, dirY);
        if (ie) {
            return this.visionData.getCell(VisionDataTypes.EXTERNAL.index, dir);
        } else {
            return this.visionData.getCell(VisionDataTypes.INTERNAL.index, dir);
        }
    }

    public void setSightBlocking(final boolean value) {
        for (int dir = 0; dir < Directions.COUNT; dir++) {
            this.visionData.setCell(value, VisionDataTypes.EXTERNAL.index, dir);
            this.visionData.setCell(value, VisionDataTypes.INTERNAL.index, dir);
        }
    }

    public void setDirectionallySightBlocking(final boolean ie, final int dir,
            final boolean value) {
        if (ie) {
            this.visionData.setCell(value, VisionDataTypes.EXTERNAL.index, dir);
        } else {
            this.visionData.setCell(value, VisionDataTypes.INTERNAL.index, dir);
        }
    }
}
