package com.puttysoftware.mazer5d.loaders;

import java.util.Properties;

import com.puttysoftware.audio.ogg.OggPlayer;
import com.puttysoftware.mazer5d.assets.MusicGroup;
import com.puttysoftware.mazer5d.assets.MusicIndex;
import com.puttysoftware.mazer5d.files.FileExtensions;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class MusicPlayer {
    private MusicPlayer() {
	// Do nothing
    }

    private static String[] allFilenames;
    private static Properties fileExtensions;
    private static OggPlayer MUSIC;

    private static String getMusicFilename(final MusicIndex music) {
	if (MusicPlayer.allFilenames == null && MusicPlayer.fileExtensions == null) {
	    MusicPlayer.allFilenames = DataLoader.loadMusicData();
	}
	final String musicExt = FileExtensions.getMusicExtensionWithPeriod();
	return MusicPlayer.allFilenames[music.ordinal()] + musicExt;
    }

    public static void playMusic(final MusicIndex music, final MusicGroup group) {
	if (Prefs.isMusicGroupEnabled(group)) {
	    if (music != null && music != MusicIndex._NONE) {
		final String filename = MusicPlayer.getMusicFilename(music);
		if (MusicPlayer.MUSIC != null && MusicPlayer.MUSIC.isPlaying()) {
		    OggPlayer.stopPlaying();
		}
		MusicPlayer.MUSIC = OggPlayer
			.loadLoopedResource(MusicPlayer.class.getResource("/assets/music/" + filename));
		MusicPlayer.MUSIC.play();
	    }
	}
    }
}