/* Ogg Player for Java
Licensed under Apache 2.0. See the LICENSE file for details.

All support is handled via the GitHub repository: https://github.com/wrldwzrd89/lib-java-audio-Ogg
 */
package com.puttysoftware.mazer5d.loaders.music;

import java.net.URL;

public abstract class MusicLoader extends Thread {
    // Constants
    protected static final int EXTERNAL_BUFFER_SIZE = 4096; // 4Kb
    private static int ACTIVE_MEDIA_COUNT = 0;
    private static int MAX_MEDIA_ACTIVE = 5;
    private static MusicLoader[] ACTIVE_MEDIA = new MusicLoader[MusicLoader.MAX_MEDIA_ACTIVE];
    private static ThreadGroup MEDIA_GROUP = new ThreadGroup("Ogg Media Players");
    private static MusicExceptionHandler meh = new MusicExceptionHandler();

    // Constructor
    protected MusicLoader(final ThreadGroup group) {
	super(group, "Ogg Media Player " + MusicLoader.ACTIVE_MEDIA_COUNT);
    }

    // Methods
    public abstract void stopLoop();

    public abstract boolean isPlaying();

    protected abstract void updateNumber(int newNumber);

    abstract int getNumber();

    // Factories
    public static MusicLoader loadFile(final String file) {
	return MusicLoader.provisionMedia(new MusicFile(MusicLoader.MEDIA_GROUP, file, MusicLoader.ACTIVE_MEDIA_COUNT));
    }

    public static MusicLoader loadResource(final URL resource) {
	return MusicLoader
		.provisionMedia(new MusicResource(MusicLoader.MEDIA_GROUP, resource, MusicLoader.ACTIVE_MEDIA_COUNT));
    }

    private static MusicLoader provisionMedia(final MusicLoader src) {
	if (MusicLoader.ACTIVE_MEDIA_COUNT >= MusicLoader.MAX_MEDIA_ACTIVE) {
	    MusicLoader.killAllMediaPlayers();
	}
	try {
	    if (src != null) {
		src.setUncaughtExceptionHandler(MusicLoader.meh);
		MusicLoader.ACTIVE_MEDIA[MusicLoader.ACTIVE_MEDIA_COUNT] = src;
		MusicLoader.ACTIVE_MEDIA_COUNT++;
	    }
	} catch (final ArrayIndexOutOfBoundsException aioob) {
	    // Do nothing
	}
	return src;
    }

    private static void killAllMediaPlayers() {
	MusicLoader.MEDIA_GROUP.interrupt();
    }

    static synchronized void taskCompleted(final int taskNum) {
	MusicLoader.ACTIVE_MEDIA[taskNum] = null;
	for (int z = taskNum + 1; z < MusicLoader.ACTIVE_MEDIA.length; z++) {
	    if (MusicLoader.ACTIVE_MEDIA[z] != null) {
		MusicLoader.ACTIVE_MEDIA[z - 1] = MusicLoader.ACTIVE_MEDIA[z];
		if (MusicLoader.ACTIVE_MEDIA[z - 1].isAlive()) {
		    MusicLoader.ACTIVE_MEDIA[z - 1].updateNumber(z - 1);
		}
	    }
	}
	MusicLoader.ACTIVE_MEDIA_COUNT--;
    }
}
