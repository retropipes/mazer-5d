package com.puttysoftware.mazer5d.files.io;

import java.io.IOException;

import com.puttysoftware.fileio.DataIOException;
import com.puttysoftware.fileio.XDataWriter;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class MazeDataWriter extends XDataWriter {
    // Constructors
    public MazeDataWriter(final String filename, final String docTag) throws IOException {
	super(filename, docTag);
    }

    // Methods
    public void writeMazeObjectID(final MazeObjects moid) throws DataIOException {
	this.writeString(moid.toString());
    }
}
