/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.objects.abc.GenericWand;

class RemoteActionWand extends GenericWand {
    // Constructors
    public RemoteActionWand() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Remote Action Wand";
    }

    @Override
    protected String getPluralNameHook() {
        return "Remote Action Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        this.useAction(null, x, y, z);
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().doRemoteAction(x, y, z);
    }

    @Override
    protected String getDescriptionHook() {
        return "Remote Action Wands will act on the target object as if you were there, on top of it.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.REMOTE_ACTION_WAND;
    }
}
