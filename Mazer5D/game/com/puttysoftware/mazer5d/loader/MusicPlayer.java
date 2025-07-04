package com.puttysoftware.mazer5d.loader;

import java.io.IOException;
import java.util.Properties;

import com.puttysoftware.audio.mod.ModPlayer;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.asset.MusicGroup;
import com.puttysoftware.mazer5d.asset.MusicIndex;
import com.puttysoftware.mazer5d.file.FileExtensions;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class MusicPlayer {
	private MusicPlayer() {
		// Do nothing
	}

	private static String[] allFilenames;
	private static Properties fileExtensions;
	private static ModPlayer MUSIC;

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
					MusicPlayer.MUSIC.stopPlaying();
				}
				try {
					MusicPlayer.MUSIC = ModPlayer
							.loadResource(MusicPlayer.class.getResource("/asset/music/" + filename));
					MusicPlayer.MUSIC.play();
				} catch (final IOException e) {
					Mazer5D.logError(e);
				}
			}
		}
	}
}