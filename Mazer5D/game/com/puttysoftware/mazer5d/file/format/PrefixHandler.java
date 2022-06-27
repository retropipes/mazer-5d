package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;

public class PrefixHandler implements PrefixIO {
    private static final byte FORMAT_VERSION_MAJOR = (byte) 5;
    private static final byte FORMAT_VERSION_MINOR = (byte) 0;

    @Override
    public int readPrefix(final MazeDataReader reader) throws IOException {
	final byte[] formatVer = PrefixHandler.readFormatVersion(reader);
	final boolean res = PrefixHandler.checkFormatVersion(formatVer);
	if (!res) {
	    throw new IOException("Unsupported  maze format version.");
	}
	return formatVer[0];
    }

    @Override
    public void writePrefix(final MazeDataWriter writer) throws IOException {
	PrefixHandler.writeFormatVersion(writer);
    }

    private static byte[] readFormatVersion(final MazeDataReader reader) throws IOException {
	final byte major = reader.readByte();
	final byte minor = reader.readByte();
	return new byte[] { major, minor };
    }

    private static boolean checkFormatVersion(final byte[] version) {
	final byte major = version[0];
	final byte minor = version[1];
	if (major > PrefixHandler.FORMAT_VERSION_MAJOR) {
	    return false;
	} else {
	    if (minor > PrefixHandler.FORMAT_VERSION_MINOR) {
		return false;
	    } else {
		return true;
	    }
	}
    }

    private static void writeFormatVersion(final MazeDataWriter writer) throws IOException {
	writer.writeByte(PrefixHandler.FORMAT_VERSION_MAJOR);
	writer.writeByte(PrefixHandler.FORMAT_VERSION_MINOR);
    }
}
