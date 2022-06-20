/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericButton;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class GreenButton extends GenericButton {
    public GreenButton() {
	super(new GreenWallOff(), new GreenWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Green Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Green Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Green Buttons will cause all Green Walls Off to become On, and all Green Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.GREEN_BUTTON;
    }
}
