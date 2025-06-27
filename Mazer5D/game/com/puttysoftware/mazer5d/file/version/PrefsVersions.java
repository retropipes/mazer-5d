package com.puttysoftware.mazer5d.file.version;

public class PrefsVersions {
    public static final int LATEST = 2;
    private static final int MINIMUM = 1;

    private PrefsVersions() {
        // Do nothing
    }

    public static boolean isCompatible(final PrefsVersion formatVersion) {
        return formatVersion.ordinal() >= PrefsVersions.MINIMUM && formatVersion.ordinal() <= PrefsVersions.LATEST;
    }
}