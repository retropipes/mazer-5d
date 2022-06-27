package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;

public interface SuffixIO {
    void writeSuffix(MazeDataWriter writer) throws IOException;

    void readSuffix(MazeDataReader reader, int formatVersion) throws IOException;
}
