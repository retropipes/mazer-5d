package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;

public interface PrefixIO {
    void writePrefix(MazeDataWriter writer) throws IOException;

    int readPrefix(MazeDataReader reader) throws IOException;
}
