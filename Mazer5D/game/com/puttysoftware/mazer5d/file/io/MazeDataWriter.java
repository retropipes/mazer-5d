package com.puttysoftware.mazer5d.file.io;

import java.io.IOException;

import org.retropipes.diane.fileio.DataIOException;
import org.retropipes.diane.fileio.XDataWriter;

import com.puttysoftware.mazer5d.abc.MazeObjects;

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
