/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericButton;

class RedButton extends GenericButton {
    public RedButton() {
	super(new RedWallOff(), new RedWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Red Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Red Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Red Buttons will cause all Red Walls Off to become On, and all Red Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.RED_BUTTON;
    }
}
