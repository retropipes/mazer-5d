package org.retropipes.mazer5d.file.format;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;
import org.retropipes.mazer5d.file.version.MazeVersion;
import org.retropipes.mazer5d.file.version.MazeVersionException;

public class PrefixHandler implements PrefixIO {
    private static final MazeVersion FORMAT_VERSION = MazeVersion.V5;

    @Override
    public MazeVersion readPrefix(final XDataReader reader) throws IOException {
	final int raw = PrefixHandler.readFormatVersion(reader);
	try {
	    return MazeVersion.values()[raw];
	} catch (ArrayIndexOutOfBoundsException e) {
	    throw new MazeVersionException(raw, e);
	}
    }

    @Override
    public void writePrefix(final XDataWriter writer) throws IOException {
	PrefixHandler.writeFormatVersion(writer);
    }

    private static int readFormatVersion(final XDataReader reader) throws IOException {
	return reader.readInt();
    }

    private static void writeFormatVersion(final XDataWriter writer) throws IOException {
	writer.writeInt(PrefixHandler.FORMAT_VERSION.ordinal());
    }
}
