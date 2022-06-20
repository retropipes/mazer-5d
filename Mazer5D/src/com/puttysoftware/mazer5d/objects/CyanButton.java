/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericButton;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class CyanButton extends GenericButton {
    public CyanButton() {
	super(new CyanWallOff(), new CyanWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Cyan Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Cyan Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Cyan Buttons will cause all Cyan Walls Off to become On, and all Cyan Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CYAN_BUTTON;
    }
}
