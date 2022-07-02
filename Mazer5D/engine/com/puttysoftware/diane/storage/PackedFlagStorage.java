/*  Diane Game Engine
Copyleft (C) 2019 Eric Ahnell

Any questions should be directed to the author via email at: support@puttysoftware.com
 */
package com.puttysoftware.diane.storage;

/**
 * Data storage for boolean values (true/false).
 */
public class PackedFlagStorage {
    // Fields
    /**
     * The underlying long where data is stored. Exposed for serialization purposes
     * for use with the protected copy constructor.
     */
    protected long dataStore;

    // Constructor
    /**
     * Main constructor.
     *
     */
    public PackedFlagStorage() {
	this.dataStore = 0L;
    }

    // Copy constructor
    /**
     * Main copy constructor.
     *
     * @param source the @self to make a copy of
     */
    public PackedFlagStorage(final PackedFlagStorage source) {
	this.dataStore = source.dataStore;
    }

    // Protected copy constructor
    /**
     * Serialization-related protected copy constructor.
     *
     * @param source the underlying long where stored data came from
     */
    public PackedFlagStorage(final long source) {
	this.dataStore = source;
    }

    // Methods
    /**
     * Check for equality.
     *
     * @param obj the other object to check
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof PackedFlagStorage)) {
	    return false;
	}
	final PackedFlagStorage other = (PackedFlagStorage) obj;
	if (this.dataStore != other.dataStore) {
	    return false;
	}
	return true;
    }

    /**
     * Get data at a given location in storage.
     *
     * @param loc the location to get data from
     * @return the data at that location
     */
    public final boolean getFlag(final int loc) {
	final long check = this.dataStore | (long) Math.pow(2, loc);
	return this.dataStore == check;
    }

    /**
     * Get the underlying long to serialize.
     *
     * @return the underlying long
     */
    public final long serialize() {
	return this.dataStore;
    }

    /**
     * Hashing support.
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	final int result = 1;
	return prime * result + Long.hashCode(this.dataStore);
    }

    /**
     * Change stored data at a given location.
     *
     * @param val the new data value
     * @param loc the location to modify
     */
    public final void setFlag(final boolean val, final int loc) {
	final long check = this.dataStore | (long) Math.pow(2, loc);
	final boolean test = this.dataStore == check;
	if (test != val) {
	    if (val) {
		this.dataStore = check;
	    } else {
		this.dataStore = this.dataStore & ~(long) Math.pow(2, loc);
	    }
	}
    }
}
