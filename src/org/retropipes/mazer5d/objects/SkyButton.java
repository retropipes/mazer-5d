/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericButton;

class SkyButton extends GenericButton {
    public SkyButton() {
	super(new SkyWallOff(), new SkyWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Sky Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sky Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Sky Buttons will cause all Sky Walls Off to become On, and all Sky Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SKY_BUTTON;
    }
}
