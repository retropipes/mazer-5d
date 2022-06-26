module com.puttysoftware.mazer5d {
    requires com.github.vorbis;
    requires java.desktop;
    requires com.puttysoftware.audio.ogg;

    uses javax.sound.sampled.spi.AudioFileReader;
    uses javax.sound.sampled.spi.FormatConversionProvider;
}