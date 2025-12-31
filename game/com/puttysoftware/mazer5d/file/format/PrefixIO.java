package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;

import com.puttysoftware.mazer5d.file.version.MazeVersion;

public interface PrefixIO {
    void writePrefix(XDataWriter writer) throws IOException;

    MazeVersion readPrefix(XDataReader reader) throws IOException;
}
