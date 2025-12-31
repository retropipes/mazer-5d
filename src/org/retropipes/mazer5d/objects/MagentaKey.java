/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericSingleKey;

class MagentaKey extends GenericSingleKey {
    // Constructors
    public MagentaKey() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Magenta Key";
    }

    @Override
    protected String getPluralNameHook() {
	return "Magenta Keys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Magenta Keys will unlock Magenta Locks, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MAGENTA_KEY;
    }
}