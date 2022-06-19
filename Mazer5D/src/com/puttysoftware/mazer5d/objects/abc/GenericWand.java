/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericWand extends GenericUsableObject {
    // Fields
    private static final long SCORE_USE = 5L;

    // Constructors
    protected GenericWand() {
	super(1);
	this.setType(TypeConstants.TYPE_WAND);
	this.setType(TypeConstants.TYPE_USABLE);
	this.setType(TypeConstants.TYPE_INVENTORYABLE);
	this.setType(TypeConstants.TYPE_CONTAINABLE);
    }

    @Override
    public abstract String getName();

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().morph(mo, x, y, z);
	Mazer5D.getBagOStuff().getGameManager().addToScore(GenericWand.SCORE_USE);
    }

    @Override
    public abstract void useHelper(int x, int y, int z);
}
