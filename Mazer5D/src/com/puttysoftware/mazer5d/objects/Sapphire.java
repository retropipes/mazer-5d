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
	super(false);
    }

    @Override
    public String getName() {
	return "Sapphire";
    }

    @Override
    public String getPluralName() {
	return "Sapphires";
    }

    @Override
    public String getDescription() {
	return "Sapphires increase your score when picked up.";
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.SAPPHIRE;
    }
}
