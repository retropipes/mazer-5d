/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericTextHolder;

class Sign extends GenericTextHolder {
    // Constructors
    public Sign() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Sign";
    }

    @Override
    protected String getPluralNameHook() {
	return "Signs";
    }

    @Override
    protected String getDescriptionHook() {
	return "Signs display their message when walked into.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SIGN;
    }
}