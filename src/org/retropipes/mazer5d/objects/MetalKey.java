/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericSingleKey;

class MetalKey extends GenericSingleKey {
    // Constructors
    public MetalKey() {
	super();
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Metal Key";
    }

    @Override
    protected String getPluralNameHook() {
	return "Metal Keys";
    }

    @Override
    protected String getDescriptionHook() {
	return "Metal Keys will open Metal Doors, and can only be used once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.METAL_KEY;
    }
}