package com.puttysoftware.mazer5d.files.io;

import java.io.IOException;

import com.puttysoftware.fileio.DataIOException;
import com.puttysoftware.fileio.JDataWriter;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class MazeDataWriter extends JDataWriter {
    // Constructors
    public MazeDataWriter(final String filename, final String newDocTag) throws IOException {
	super(filename);
    }

    // Methods
    public void writeMazeObjectID(final MazeObjects moid) throws DataIOException {
	this.writeString(moid.toString());
    }
}
