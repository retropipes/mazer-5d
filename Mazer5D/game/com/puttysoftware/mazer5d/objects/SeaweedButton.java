/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericButton;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class SeaweedButton extends GenericButton {
    public SeaweedButton() {
	super(new SeaweedWallOff(), new SeaweedWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Seaweed Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Seaweed Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Seaweed Buttons will cause all Seaweed Walls Off to become On, and all Seaweed Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SEAWEED_BUTTON;
    }
}