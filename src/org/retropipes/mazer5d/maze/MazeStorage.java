package org.retropipes.mazer5d.maze;

import org.retropipes.diane.storage.ObjectStorage;
import org.retropipes.mazer5d.abc.MazeObject;

class MazeStorage extends ObjectStorage<MazeObject> {
    // Constructor
    public MazeStorage(final int... shape) {
	super(shape);
    }

    // Methods
    public MazeObject getMazeCell(final int... loc) {
	return this.getCell(loc);
    }
}
