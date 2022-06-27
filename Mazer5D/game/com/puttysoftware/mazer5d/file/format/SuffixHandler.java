package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;

public class SuffixHandler implements SuffixIO {
    @Override
    public void readSuffix(final MazeDataReader reader, final int formatVersion) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().loadGameHook(reader, formatVersion);
    }

    @Override
    public void writeSuffix(final MazeDataWriter writer) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().saveGameHook(writer);
    }
}
