/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import org.retropipes.mazer5d.locale.StaticStrings;

final class CustomTexts {
    // Fields
    private final ArrayList<String> texts;

    // Constructor
    public CustomTexts() {
	this.texts = new ArrayList<>();
    }

    public CustomTexts(final CustomTexts source) {
	this.texts = new ArrayList<>(source.texts);
    }

    // Methods
    public int length() {
	return this.texts.size();
    }

    public boolean add(final int count) {
	if (this.texts.size() != 0) {
	    return false;
	}
	this.texts.addAll(Collections.nCopies(count, StaticStrings.EMPTY));
	return true;
    }

    public void addOne() {
	if (this.texts.size() == 0) {
	    this.texts.add(StaticStrings.EMPTY);
	}
    }

    public String get(final int index) {
	return this.texts.get(index);
    }

    public boolean set(final int index, final String value) {
	if (this.texts.size() <= index) {
	    return false;
	}
	this.texts.set(index, value);
	return true;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.texts);
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof CustomTexts)) {
	    return false;
	}
	final CustomTexts other = (CustomTexts) obj;
	return Objects.equals(this.texts, other.texts);
    }
}
