/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericCarpet;

class MagentaCarpet extends GenericCarpet {
    // Constructors
    public MagentaCarpet() {
        super("Magenta");
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.MAGENTA_CARPET;
    }
}