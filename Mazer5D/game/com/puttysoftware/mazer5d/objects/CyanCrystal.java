/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericProgrammableKey;

class CyanCrystal extends GenericProgrammableKey {
    // Constructors
    public CyanCrystal() {
        super("Cyan");
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.CYAN_CRYSTAL;
    }
}