/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericButton;

class PurpleButton extends GenericButton {
    public PurpleButton() {
	super(new PurpleWallOff(), new PurpleWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Purple Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Purple Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Purple Buttons will cause all Purple Walls Off to become On, and all Purple Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PURPLE_BUTTON;
    }
}
