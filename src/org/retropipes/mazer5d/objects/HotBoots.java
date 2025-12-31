/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBoots;

class HotBoots extends GenericBoots {
    // Constructors
    public HotBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Hot Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Hot Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return "Hot Boots transform any ground into Hot Rock as you walk. Note that you can only wear one pair of boots at once.";
    }

    @Override
    public void stepAction() {
	final int x = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationX();
	final int y = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationY();
	final int z = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationZ();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().hotGround(x, y, z);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.HOT_BOOTS;
    }
}
