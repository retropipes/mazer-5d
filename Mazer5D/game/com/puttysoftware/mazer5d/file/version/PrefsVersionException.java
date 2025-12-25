package com.puttysoftware.mazer5d.file.version;

public class PrefsVersionException extends VersionException {
    /**
     *
     */
    private static final long serialVersionUID = 5865177350171161530L;

    public PrefsVersionException(final int actualVersion) {
	super("Unsupported settings formatVersion found: " + actualVersion + " (expected " + PrefsVersions.LATEST
		+ " or earlier)");
    }

    public PrefsVersionException(final int actualVersion, final Exception cause) {
	super("Unsupported settings formatVersion found: " + actualVersion + " (expected " + PrefsVersions.LATEST
		+ " or earlier)", cause);
    }
}
