/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

final class CustomCounters {
	// Fields
	private final ArrayList<Integer> counters;

	// Constructor
	public CustomCounters() {
		this.counters = new ArrayList<>();
	}

	public CustomCounters(final CustomCounters source) {
		this.counters = new ArrayList<>(source.counters);
	}

	// Methods
	public int length() {
		return this.counters.size();
	}

	public boolean add(final int count) {
		if (this.counters.size() != 0) {
			return false;
		}
		this.counters.addAll(Collections.nCopies(count, 0));
		return true;
	}

	public void addOne() {
		if (this.counters.size() == 0) {
			this.counters.add(0);
		}
	}

	public int get(final int index) {
		return this.counters.get(index);
	}

	public boolean decrement(final int index) {
		if (this.counters.size() <= index) {
			return false;
		}
		this.counters.set(index, this.counters.get(index) - 1);
		return true;
	}

	public boolean increment(final int index) {
		if (this.counters.size() <= index) {
			return false;
		}
		this.counters.set(index, this.counters.get(index) + 1);
		return true;
	}

	public boolean offset(final int index, final int value) {
		if (this.counters.size() <= index) {
			return false;
		}
		this.counters.set(index, this.counters.get(index) + value);
		return true;
	}

	public boolean set(final int index, final int value) {
		if (this.counters.size() <= index) {
			return false;
		}
		this.counters.set(index, value);
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.counters);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CustomCounters)) {
			return false;
		}
		final CustomCounters other = (CustomCounters) obj;
		return Objects.equals(this.counters, other.counters);
	}
}
