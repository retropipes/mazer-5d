/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPort;

class XPort extends GenericPort {
    // Constructors
    public XPort() {
        super(new XPlug(), 'X');
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.X_PORT;
    }
}
