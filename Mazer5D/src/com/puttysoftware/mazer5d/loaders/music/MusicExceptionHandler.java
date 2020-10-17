/* Ogg Player for Java
Licensed under Apache 2.0. See the LICENSE file for details.

All support is handled via the GitHub repository: https://github.com/wrldwzrd89/lib-java-audio-Ogg
 */
package com.puttysoftware.mazer5d.loaders.music;

import java.lang.Thread.UncaughtExceptionHandler;

public class MusicExceptionHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(final Thread thr, final Throwable exc) {
        try {
            if (thr instanceof MusicLoader) {
                final MusicLoader media = (MusicLoader) thr;
                MusicLoader.taskCompleted(media.getNumber());
            }
        } catch (Throwable t) {
            // Ignore
        }
    }
}
