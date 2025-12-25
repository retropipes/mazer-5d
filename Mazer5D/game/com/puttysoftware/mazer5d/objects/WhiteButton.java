/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericButton;

class WhiteButton extends GenericButton {
    public WhiteButton() {
	super(new WhiteWallOff(), new WhiteWallOn());
    }

    @Override
    protected String getNameHook() {
	return "White Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "White Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "White Buttons will cause all White Walls Off to become On, and all White Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.WHITE_BUTTON;
    }
}
