/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericProgrammableKey;

class LightGrayCrystal extends GenericProgrammableKey {
    // Constructors
    public LightGrayCrystal() {
	super("Light Gray");
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.LIGHT_GRAY_CRYSTAL;
    }
}