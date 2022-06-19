package com.puttysoftware.mazer5d.files.io;

import java.io.IOException;
import java.io.InputStream;

import com.puttysoftware.fileio.DataIOException;
import com.puttysoftware.fileio.JDataReader;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class MazeDataReader extends JDataReader {
    // Constructors
    public MazeDataReader(final String filename, final String newDocTag) throws IOException {
	super(filename);
    }

    public MazeDataReader(final InputStream stream, final String newDocTag) throws IOException {
	super(stream);
    }

    // Methods
    public MazeObjects readMazeObjectID() throws DataIOException {
	final String line = this.readString();
	if (line != null) {
	    return MazeObjects.valueOf(line);
	}
	throw new DataIOException("End of file!"); //$NON-NLS-1$
    }
}
