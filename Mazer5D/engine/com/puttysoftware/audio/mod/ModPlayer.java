package com.puttysoftware.audio.mod;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class ModPlayer {
    private static final int SAMPLE_RATE = 41000;
    private Module module;
    IBXM ibxm;
    volatile boolean playing;
    private int interpolation;
    private Thread playThread;

    public ModPlayer() {
	// Do nothing
    }

    public boolean isPlaying() {
	return this.playThread != null && this.playThread.isAlive();
    }

    public static synchronized ModPlayer loadResource(final URL modRes) throws IOException {
	byte[] moduleData = null;
	try (InputStream inputStream = modRes.openStream()) {
	    moduleData = inputStream.readAllBytes();
	    ModPlayer player = new ModPlayer();
	    player.module = new Module(moduleData);
	    player.ibxm = new IBXM(player.module, ModPlayer.SAMPLE_RATE);
	    player.ibxm.setInterpolation(player.interpolation);
	    return player;
	} catch (final IOException ioe) {
	    throw ioe;
	}
    }

    public synchronized void play() {
	if (this.ibxm != null) {
	    this.playing = true;
	    this.playThread = new Thread(new Runnable() {
		@Override
		public void run() {
		    final int[] mixBuf = new int[ModPlayer.this.ibxm.getMixBufferLength()];
		    final byte[] outBuf = new byte[mixBuf.length * 4];
		    AudioFormat audioFormat = null;
		    audioFormat = new AudioFormat(ModPlayer.SAMPLE_RATE, 16, 2, true, true);
		    try (SourceDataLine audioLine = AudioSystem.getSourceDataLine(audioFormat)) {
			audioLine.open();
			audioLine.start();
			while (ModPlayer.this.playing) {
			    final int count = ModPlayer.this.getAudio(mixBuf);
			    int outIdx = 0;
			    for (int mixIdx = 0, mixEnd = count * 2; mixIdx < mixEnd; mixIdx++) {
				int ampl = mixBuf[mixIdx];
				if (ampl > 32767) {
				    ampl = 32767;
				}
				if (ampl < -32768) {
				    ampl = -32768;
				}
				outBuf[outIdx++] = (byte) (ampl >> 8);
				outBuf[outIdx++] = (byte) ampl;
			    }
			    audioLine.write(outBuf, 0, outIdx);
			}
			audioLine.drain();
		    } catch (final Exception e) {
			// Ignore
		    }
		}
	    });
	    this.playThread.start();
	}
    }

    public synchronized void stopPlaying() {
	this.playing = false;
	try {
	    if (this.playThread != null) {
		this.playThread.join();
	    }
	} catch (final InterruptedException e) {
	}
    }

    synchronized int getAudio(final int[] mixBuf) {
	final int count = this.ibxm.getAudio(mixBuf);
	return count;
    }
}