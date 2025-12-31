package org.retropipes.mazer5d.loader;

import org.retropipes.diane.asset.sound.DianeSoundPlayer;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.prefs.Prefs;

public class SoundPlayer {
    private SoundPlayer() {
	// Do nothing
    }

    public static void playSound(final SoundIndex sound, final SoundGroup group) {
	if (Prefs.isSoundGroupEnabled(group)) {
	    DianeSoundPlayer.play(sound);
	}
    }
}