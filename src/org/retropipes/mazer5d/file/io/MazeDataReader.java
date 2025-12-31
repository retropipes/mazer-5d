package org.retropipes.mazer5d.file.io;

import org.retropipes.diane.fileio.DataIOException;
import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.mazer5d.abc.MazeObjects;

public class MazeDataReader {
    private MazeDataReader() {
    }

    // Methods
    public static MazeObjects readMazeObjectID(final XDataReader reader) throws DataIOException {
	final String line = reader.readString();
	if (line != null) {
	    return MazeObjects.valueOf(line);
	}
	throw new DataIOException("End of file!"); //$NON-NLS-1$
    }
}
