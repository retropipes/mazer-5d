package org.retropipes.mazer5d.file.io;

import org.retropipes.diane.fileio.DataIOException;
import org.retropipes.diane.fileio.XDataWriter;
import org.retropipes.mazer5d.abc.MazeObjects;

public class MazeDataWriter {
    private MazeDataWriter() {
    }

    // Methods
    public static void writeMazeObjectID(final MazeObjects moid, final XDataWriter writer) throws DataIOException {
	writer.writeString(moid.toString());
    }
}
