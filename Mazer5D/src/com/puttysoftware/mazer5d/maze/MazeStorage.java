package com.puttysoftware.mazer5d.maze;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.storage.ObjectStorage;

class MazeStorage extends ObjectStorage {
    // Constructor
    public MazeStorage(final int... shape) {
	super(shape);
    }

    // Methods
    public MazeObject getMazeCell(final int... loc) {
	return (MazeObject) this.getCell(loc);
    }
}
