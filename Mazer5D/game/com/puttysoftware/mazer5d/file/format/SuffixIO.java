package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;

public interface SuffixIO {
    void writeSuffix(MazeDataWriter writer) throws IOException;

    void readSuffix(MazeDataReader reader, MazeVersion formatVersion) throws IOException;
}
