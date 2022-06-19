package com.puttysoftware.mazer5d.files.format;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;

public class XMLSuffixHandler implements XMLSuffixIO {
    @Override
    public void readSuffix(final MazeDataReader reader, final int formatVersion) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().loadGameHookXML(reader, formatVersion);
    }

    @Override
    public void writeSuffix(final MazeDataWriter writer) throws IOException {
	Mazer5D.getBagOStuff().getGameManager().saveGameHookXML(writer);
    }
}
