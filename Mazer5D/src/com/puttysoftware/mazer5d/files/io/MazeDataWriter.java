package com.puttysoftware.mazer5d.files.io;

import java.io.IOException;

import com.puttysoftware.fileio.DataIOException;
import com.puttysoftware.fileio.XDataWriter;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class MazeDataWriter extends XDataWriter {
    // Fields
    private static final String END_OF_LINE = "\r\n"; //$NON-NLS-1$

    // Constructors
    public MazeDataWriter(final String filename, final String newDocTag) throws IOException {
	super(filename, newDocTag);
    }

    // Methods
    public void writeMazeObjectID(final MazeObjects moid) throws DataIOException {
	this.writeRawString("<" + MazeDataConstants.OBJECT_ID_TAG + ">" + moid.toString() //$NON-NLS-1$ //$NON-NLS-2$
		+ "</" + MazeDataConstants.OBJECT_ID_TAG + ">" //$NON-NLS-1$ //$NON-NLS-2$
		+ MazeDataWriter.END_OF_LINE);
    }
}
