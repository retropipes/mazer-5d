package org.retropipes.mazer5d.file.format;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;
import org.retropipes.mazer5d.file.version.MazeVersion;

public interface SuffixIO {
    void writeSuffix(XDataWriter writer) throws IOException;

    void readSuffix(XDataReader reader, MazeVersion formatVersion) throws IOException;
}
