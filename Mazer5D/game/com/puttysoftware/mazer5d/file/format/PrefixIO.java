package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;

public interface PrefixIO {
    void writePrefix(MazeDataWriter writer) throws IOException;

    MazeVersion readPrefix(MazeDataReader reader) throws IOException;
}
