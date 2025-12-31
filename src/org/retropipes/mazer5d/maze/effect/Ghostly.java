/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.maze.effect;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;

public class Ghostly extends MazeEffect {
    // Constructor
    public Ghostly(final int newRounds) {
	super("Ghostly", newRounds);
    }

    @Override
    public void customTerminateLogic() {
	// Remove item that granted effect from inventory
	Mazer5D.getBagOStuff().getGameManager().getObjectInventory().removeItem(MazeObjects.GHOST_AMULET);
    }
}