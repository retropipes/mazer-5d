package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.version.MazeVersion;

public class SuffixHandler implements SuffixIO {
    @Override
    public void readSuffix(final XDataReader reader, final MazeVersion formatVersion) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().loadGameHook(reader, formatVersion);
    }

    @Override
    public void writeSuffix(final XDataWriter writer) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().saveGameHook(writer);
    }
}
