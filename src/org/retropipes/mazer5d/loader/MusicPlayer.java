package org.retropipes.mazer5d.loader;

import java.io.IOException;

import org.retropipes.diane.asset.music.DianeMusicPlayer;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.asset.MusicGroup;
import org.retropipes.mazer5d.asset.MusicIndex;
import org.retropipes.mazer5d.prefs.Prefs;

public class MusicPlayer {
    private MusicPlayer() {
	// Do nothing
    }

    public static void playMusic(final MusicIndex music, final MusicGroup group) {
	if (Prefs.isMusicGroupEnabled(group)) {
	    try {
		DianeMusicPlayer.play(music);
	    } catch (IOException e) {
		Mazer5D.logError(e);
	    }
	}
    }
}