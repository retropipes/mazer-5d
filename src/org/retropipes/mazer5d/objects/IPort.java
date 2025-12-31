/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008  Iric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericPort;

class IPort extends GenericPort {
    // Constructors
    public IPort() {
	super(new IPlug(), 'I');
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.I_PORT;
    }
}