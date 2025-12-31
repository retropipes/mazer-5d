/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.maze.effect;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObjects;

public class Icy extends MazeEffect {
    // Constructor
    public Icy(final int newRounds) {
	super("Icy", newRounds);
    }

    @Override
    public void customTerminateLogic() {
	// Remove item that granted effect from inventory
	Mazer5D.getBagOStuff().getGameManager().getObjectInventory().removeItem(MazeObjects.ICE_AMULET);
    }
}