package com.puttysoftware.mazer5d.maze;

import com.puttysoftware.mazer5d.abc.MazeObjectModel;
import com.puttysoftware.storage.ObjectStorage;

class MazeStorage extends ObjectStorage {
    // Constructor
    public MazeStorage(final int... shape) {
        super(shape);
    }

    // Methods
    public MazeObjectModel getMazeCell(final int... loc) {
        return (MazeObjectModel) this.getCell(loc);
    }
}
