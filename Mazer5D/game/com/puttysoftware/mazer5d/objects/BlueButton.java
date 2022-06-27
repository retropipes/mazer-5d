/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericButton;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class BlueButton extends GenericButton {
    public BlueButton() {
	super(new BlueWallOff(), new BlueWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Blue Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Blue Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Blue Buttons will cause all Blue Walls Off to become On, and all Blue Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BLUE_BUTTON;
    }
}
