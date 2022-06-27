package com.puttysoftware.mazer5d.file.version;

public class PrefsVersionException extends VersionException {
    /**
     *
     */
    private static final long serialVersionUID = 5865177350171161530L;

    public PrefsVersionException(final PrefsVersion actualVersion) {
	super("Unsupported settings formatVersion found: " + actualVersion + " (expected " + PrefsVersions.LATEST
		+ " or earlier)");
    }
}
