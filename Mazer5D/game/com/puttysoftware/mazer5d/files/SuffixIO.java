package com.puttysoftware.mazer5d.files;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;
import com.puttysoftware.mazer5d.files.versions.MazeVersion;

public class SuffixIO {
    public void writeSuffix(final MazeDataWriter writer) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().saveGameHook(writer);
    }

    public void readSuffix(final MazeDataReader reader, final MazeVersion formatVersion) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().loadGameHook(reader, formatVersion.ordinal());
    }
}
