package com.puttysoftware.mazer5d.loaders;

import java.net.URL;
import java.util.Properties;

import com.puttysoftware.audio.wav.WAVPlayer;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.files.FileExtensions;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class SoundPlayer {
    private SoundPlayer() {
	// Do nothing
    }

    private static String[] allFilenames;
    private static Properties fileExtensions;
    private static WAVPlayer player;

    private static String getSoundFilename(final SoundIndex sound) {
	if (SoundPlayer.allFilenames == null && SoundPlayer.fileExtensions == null) {
	    SoundPlayer.allFilenames = DataLoader.loadSoundData();
	}
	final String soundExt = FileExtensions.getSoundExtensionWithPeriod();
	return SoundPlayer.allFilenames[sound.ordinal()] + soundExt;
    }

    public static void playSound(final SoundIndex sound, final SoundGroup group) {
	if (Prefs.isSoundGroupEnabled(group)) {
	    if (sound != null && sound != SoundIndex._NONE) {
		final String filename = SoundPlayer.getSoundFilename(sound);
		SoundPlayer.play(SoundPlayer.class.getResource("/assets/sound/" + filename));
	    }
	}
    }

    private static void play(final URL soundURL) {
	SoundPlayer.player = WAVPlayer.loadResource(soundURL);
	if (SoundPlayer.player != null) {
	    SoundPlayer.player.play();
	}
    }
}