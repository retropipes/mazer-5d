/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericProgrammableKey;

public class SignalCrystal extends GenericProgrammableKey {
    // Constructors
    public SignalCrystal() {
	super("Signal");
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SIGNAL_CRYSTAL;
    }
}