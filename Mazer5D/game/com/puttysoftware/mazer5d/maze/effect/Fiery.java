/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.maze.effect;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;

public class Fiery extends MazeEffect {
    // Constructor
    public Fiery(final int newRounds) {
	super("Fiery", newRounds);
    }

    @Override
    public void customTerminateLogic() {
	// Remove item that granted effect from inventory
	Mazer5D.getBagOStuff().getGameManager().getObjectInventory().removeItem(MazeObjects.FIRE_AMULET);
    }
}