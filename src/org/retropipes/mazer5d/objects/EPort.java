/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericPort;

class EPort extends GenericPort {
    // Constructors
    public EPort() {
	super(new EPlug(), 'E');
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.E_PORT;
    }
}