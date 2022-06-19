/* Ogg Player for Java
Licensed under Apache 2.0. See the LICENSE file for details.

All support is handled via the GitHub repository: https://github.com/wrldwzrd89/lib-java-audio-Ogg
 */
package com.puttysoftware.mazer5d.loaders.music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

class MusicFile extends MusicLoader {
    private final String filename;
    private int number;
    private MusicPlayer player;

    public MusicFile(final ThreadGroup group, final String Oggfile, final int taskNum) {
	super(group);
	this.filename = Oggfile;
	this.number = taskNum;
    }

    @Override
    public void run() {
	if (this.filename != null) {
	    final File soundFile = new File(this.filename);
	    if (!soundFile.exists()) {
		MusicLoader.taskCompleted(this.number);
		return;
	    }
	    try (AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile)) {
		this.player = new MusicPlayer(ais);
		this.player.playLoop();
		MusicLoader.taskCompleted(this.number);
	    } catch (final UnsupportedAudioFileException e1) {
		MusicLoader.taskCompleted(this.number);
	    } catch (final IOException e1) {
		MusicLoader.taskCompleted(this.number);
	    }
	}
    }

    @Override
    public boolean isPlaying() {
	return this.player != null && this.isAlive();
    }

    @Override
    public void stopLoop() {
	if (this.player != null) {
	    this.player.stopLoop();
	}
    }

    @Override
    int getNumber() {
	return this.number;
    }

    @Override
    protected void updateNumber(final int newNumber) {
	this.number = newNumber;
    }
}
