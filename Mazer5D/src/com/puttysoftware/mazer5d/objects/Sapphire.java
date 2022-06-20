/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Sapphire extends MazeObject {
    // Constructors
    public Sapphire() {
	super(MazeObjects.SAPPHIRE);
    }

    @Override
    protected String getNameHook() {
	return "Sapphire";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sapphires";
    }

    @Override
    protected String getDescriptionHook() {
	return "Sapphires increase your score when picked up.";
    }
}
