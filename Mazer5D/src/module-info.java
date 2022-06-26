module com.puttysoftware.mazer5d {
    requires com.github.vorbis;
    requires java.desktop;
    requires com.puttysoftware.audio.ogg;
    requires com.puttysoftware.audio.wav;
    requires com.puttysoftware.fileutils;
    requires com.puttysoftware.integration;
    requires com.puttysoftware.randomrange;
    requires com.puttysoftware.storage;
    requires com.puttysoftware.updater;

    uses javax.sound.sampled.spi.AudioFileReader;
    uses javax.sound.sampled.spi.FormatConversionProvider;
}