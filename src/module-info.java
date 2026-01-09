module org.retropipes.mazer5d {
    requires java.desktop;
    requires org.retropipes.diane;
    requires org.retropipes.diane.fileio;
    requires org.retropipes.diane.internal;

    uses javax.sound.sampled.spi.AudioFileReader;
    uses javax.sound.sampled.spi.FormatConversionProvider;
}