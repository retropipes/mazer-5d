package com.puttysoftware.mazer5d.file;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.versions.MazeVersion;

public class SuffixIO {
    public void writeSuffix(final MazeDataWriter writer) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().saveGameHook(writer);
    }

    public void readSuffix(final MazeDataReader reader, final MazeVersion formatVersion) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().loadGameHook(reader, formatVersion.ordinal());
    }
}
