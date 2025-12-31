/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericButton;

class MagentaButton extends GenericButton {
    public MagentaButton() {
	super(new MagentaWallOff(), new MagentaWallOn());
    }

    @Override
    protected String getNameHook() {
	return "Magenta Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Magenta Buttons";
    }

    @Override
    protected String getDescriptionHook() {
	return "Magenta Buttons will cause all Magenta Walls Off to become On, and all Magenta Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MAGENTA_BUTTON;
    }
}
