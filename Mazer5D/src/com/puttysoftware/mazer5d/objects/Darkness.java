/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericPassThroughObject;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class Darkness extends GenericPassThroughObject {
    // Constructors
    public Darkness() {
	super();
    }

    @Override
    public String getName() {
	return "Darkness";
    }

    @Override
    public String getPluralName() {
	return null;
    }

    @Override
    public String getDescription() {
	return null;
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.DARKNESS;
    }
}