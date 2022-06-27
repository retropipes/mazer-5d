package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;

public class PrefixHandler implements PrefixIO {
    private static final MazeVersion FORMAT_VERSION = MazeVersion.V5;

    @Override
    public MazeVersion readPrefix(final MazeDataReader reader) throws IOException {
	return MazeVersion.values()[PrefixHandler.readFormatVersion(reader)];
    }

    @Override
    public void writePrefix(final MazeDataWriter writer) throws IOException {
	PrefixHandler.writeFormatVersion(writer);
    }

    private static int readFormatVersion(final MazeDataReader reader) throws IOException {
	return reader.readInt();
    }

    private static void writeFormatVersion(final MazeDataWriter writer) throws IOException {
	writer.writeInt(PrefixHandler.FORMAT_VERSION.ordinal());
    }
}
