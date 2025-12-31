/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.util.Objects;

import org.retropipes.diane.storage.NumberStorage;

class OtherCounters {
    // Private enumeration
    private enum OtherDataTypes {
	USES(0),
	TIMER_TICKS(1),
	TIMER_RESET(2);

	private int index;

	OtherDataTypes(final int value) {
	    this.index = value;
	}
    }

    // Properties
    private final NumberStorage otherData;
    private static final int OTHER_DATA_TYPES = 3;

    // Constructors
    public OtherCounters() {
	this.otherData = new NumberStorage(OtherCounters.OTHER_DATA_TYPES);
    }

    public OtherCounters(final OtherCounters source) {
	this.otherData = new NumberStorage(source.otherData);
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
	final OtherCounters other = (OtherCounters) obj;
	if (!Objects.equals(this.otherData, other.otherData)) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 89 * hash + Objects.hashCode(this.otherData);
	return hash;
    }

    public int getUses() {
	return this.otherData.getCell(OtherDataTypes.USES.index);
    }

    public void setUses(final int value) {
	this.otherData.setCell(value, OtherDataTypes.USES.index);
    }

    public void use() {
	this.otherData.setCell(this.getUses() - 1, OtherDataTypes.USES.index);
    }

    public int getTimerTicks() {
	return this.otherData.getCell(OtherDataTypes.TIMER_TICKS.index);
    }

    public boolean timeExpired() {
	return this.otherData.getCell(OtherDataTypes.TIMER_TICKS.index) == 0;
    }

    public void setTimerTicks(final int value) {
	this.otherData.setCell(value, OtherDataTypes.TIMER_TICKS.index);
    }

    public void extendTimer(final int value) {
	this.otherData.setCell(this.getTimerTicks() + value, OtherDataTypes.TIMER_TICKS.index);
    }

    public void extendTimerByReset() {
	this.otherData.setCell(this.getTimerTicks() + this.getTimerReset(), OtherDataTypes.TIMER_TICKS.index);
    }

    public void tickTimer() {
	this.otherData.setCell(this.getTimerTicks() - 1, OtherDataTypes.USES.index);
    }

    public int getTimerReset() {
	return this.otherData.getCell(OtherDataTypes.TIMER_RESET.index);
    }

    public void setTimerReset(final int value) {
	this.otherData.setCell(value, OtherDataTypes.TIMER_RESET.index);
    }

    public void resetTimer() {
	this.otherData.setCell(this.getTimerReset(), OtherDataTypes.TIMER_TICKS.index);
    }
}
