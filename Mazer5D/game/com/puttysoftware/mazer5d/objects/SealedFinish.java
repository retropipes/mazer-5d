/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPassThroughObject;

class SealedFinish extends GenericPassThroughObject {
    // Constructors
    public SealedFinish() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Sealed Finish";
    }

    @Override
    protected String getPluralNameHook() {
	return null;
    }

    @Override
    protected String getDescriptionHook() {
	return null;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.SEALED_FINISH;
    }
}