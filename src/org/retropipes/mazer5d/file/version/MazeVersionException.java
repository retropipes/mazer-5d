package org.retropipes.mazer5d.file.version;

public class MazeVersionException extends VersionException {
    /**
     *
     */
    private static final long serialVersionUID = -9019684655962854211L;

    public MazeVersionException(final int actualVersion, final Exception cause) {
	super("Unsupported maze formatVersion found: " + actualVersion + " (expected " + MazeVersions.LATEST
		+ " or earlier)", cause);
    }
}
