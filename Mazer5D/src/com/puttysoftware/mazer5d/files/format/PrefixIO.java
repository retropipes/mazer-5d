package com.puttysoftware.mazer5d.files.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;

public interface PrefixIO {
    void writePrefix(MazeDataWriter writer) throws IOException;

    int readPrefix(MazeDataReader reader) throws IOException;
}
