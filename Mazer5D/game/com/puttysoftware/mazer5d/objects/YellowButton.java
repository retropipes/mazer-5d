/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericButton;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class YellowButton extends GenericButton {
    public YellowButton() {
	super(new YellowWallOff(), new YellowWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Yellow Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Yellow Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Yellow Buttons will cause all Yellow Walls Off to become On, and all Yellow Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.YELLOW_BUTTON;
    }
}
