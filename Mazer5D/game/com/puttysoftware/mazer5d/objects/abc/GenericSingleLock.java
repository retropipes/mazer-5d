/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.utility.TypeConstants;

public abstract class GenericSingleLock extends GenericLock {
    protected GenericSingleLock(final GenericSingleKey mgk) {
	super(mgk);
	this.setType(TypeConstants.TYPE_UNLOCKED_LOSE_KEY);
	this.setType(TypeConstants.TYPE_LOCK);
    }
}
