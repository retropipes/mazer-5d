package com.puttysoftware.mazer5d.asset;

import java.net.URL;
import java.util.Properties;

import org.retropipes.diane.asset.music.DianeMusicIndex;

import com.puttysoftware.mazer5d.file.FileExtensions;
import com.puttysoftware.mazer5d.loader.DataLoader;

public enum MusicIndex implements DianeMusicIndex {
    TITLE,
    EXPLORING,
    _NONE;

    private static String[] allFilenames;
    private static Properties fileExtensions;

    @Override
    public String getName() {
	if (MusicIndex.allFilenames == null && MusicIndex.fileExtensions == null) {
	    MusicIndex.allFilenames = DataLoader.loadMusicData();
	}
	final String musicExt = FileExtensions.getMusicExtensionWithPeriod();
	return MusicIndex.allFilenames[this.ordinal()] + musicExt;
    }

    @Override
    public URL getURL() {
	return MusicIndex.class.getResource("/asset/music/" + this.getName());
    }
}
