/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericProgrammableKey;

class PurpleCrystal extends GenericProgrammableKey {
    // Constructors
    public PurpleCrystal() {
	super("Purple");
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PURPLE_CRYSTAL;
    }
}