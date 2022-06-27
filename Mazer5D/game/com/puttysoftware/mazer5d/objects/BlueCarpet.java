/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericCarpet;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class BlueCarpet extends GenericCarpet {
    // Constructors
    public BlueCarpet() {
	super("Blue");
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.BLUE_CARPET;
    }
}