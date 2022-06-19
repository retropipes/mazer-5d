package com.puttysoftware.mazer5d.files.io;

import java.io.IOException;
import java.io.InputStream;

import com.puttysoftware.fileio.DataIOException;
import com.puttysoftware.fileio.XDataReader;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class MazeDataReader extends XDataReader {
    // Constructors
    public MazeDataReader(final String filename, final String newDocTag) throws IOException {
	super(filename, newDocTag);
    }

    public MazeDataReader(final InputStream stream, final String newDocTag) throws IOException {
	super(stream, newDocTag);
    }

    // Methods
    public MazeObjects readMazeObjectID() throws DataIOException {
	final String line = this.readRawString();
	if (line != null) {
	    final String[] split = XDataReader.splitLine(line);
	    XDataReader.validateOpeningTag(split[0], MazeDataConstants.OBJECT_ID_TAG);
	    XDataReader.validateClosingTag(split[2], MazeDataConstants.OBJECT_ID_TAG);
	    return MazeObjects.valueOf(split[1]);
	}
	throw new DataIOException("End of file!"); //$NON-NLS-1$
    }
}
