package com.puttysoftware.mazer5d.file.version;

public class MazeVersions {
    private static final MazeVersion MINIMUM = MazeVersion.V1;
    public static final MazeVersion LATEST = MazeVersion.V6;

    private MazeVersions() {
	// Do nothing
    }

    public static boolean isCompatible(final MazeVersion formatVersion) {
	return formatVersion.ordinal() >= MazeVersions.MINIMUM.ordinal()
		&& formatVersion.ordinal() <= MazeVersions.LATEST.ordinal();
    }
}