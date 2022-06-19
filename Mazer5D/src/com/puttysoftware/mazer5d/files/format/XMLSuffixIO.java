package com.puttysoftware.mazer5d.files.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;

public interface XMLSuffixIO {
    void writeSuffix(MazeDataWriter writer) throws IOException;

    void readSuffix(MazeDataReader reader, int formatVersion) throws IOException;
}
