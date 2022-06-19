/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericButton;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class OrangeButton extends GenericButton {
    public OrangeButton() {
	super(new OrangeWallOff(), new OrangeWallOn());
    }

    @Override
    public String getName() {
	return "Orange Button";
    }

    @Override
    public String getPluralName() {
	return "Orange Buttons";
    }

    @Override
    public String getDescription() {
	return "Orange Buttons will cause all Orange Walls Off to become On, and all Orange Walls On to become Off.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.ORANGE_BUTTON;
    }
}
