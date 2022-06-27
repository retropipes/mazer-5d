package com.puttysoftware.mazer5d.file;

import java.io.File;

public class CommonPaths {
    private static final String MAC_PREFIX = "HOME";
    private static final String WIN_PREFIX = "APPDATA";
    private static final String UNIX_PREFIX = "XDG_CONFIG_HOME";
    private static final String UNIX_FALLBACK_PREFIX = "HOME";
    private static final String MAC_PREFS_DIR = "/Library/Preferences/com.puttysoftware.mazer5d.formatVersioned";
    private static final String WIN_PREFS_DIR = "\\com.puttysoftware.mazer5d\\prefs.formatVersioned";
    private static final String UNIX_PREFS_DIR = "/com.puttysoftware.mazer5d/prefs.formatVersioned";
    private static final String UNIX_FALLBACK_PREFS_DIR = "/.config/com.puttysoftware.mazer5d/prefs.formatVersioned";

    private CommonPaths() {
	super();
    }

    private static boolean useFallback() {
	return System.getenv(CommonPaths.UNIX_PREFIX) == null;
    }

    private static String getDirectoryPrefix(final boolean fallback) {
	final String osName = System.getProperty("os.name");
	if (osName.indexOf("Mac OS X") != -1) {
	    // Mac OS X
	    return System.getenv(CommonPaths.MAC_PREFIX);
	} else if (osName.indexOf("Windows") != -1) {
	    // Windows
	    return System.getenv(CommonPaths.WIN_PREFIX);
	} else {
	    // Other - assume UNIX-like
	    if (fallback) {
		return System.getenv(CommonPaths.UNIX_FALLBACK_PREFIX);
	    }
	    return System.getenv(CommonPaths.UNIX_PREFIX);
	}
    }

    private static String getPrefsDirectoryName(final boolean fallback) {
	final String osName = System.getProperty("os.name");
	if (osName.indexOf("Mac OS X") != -1) {
	    // Mac OS X
	    return CommonPaths.MAC_PREFS_DIR;
	} else if (osName.indexOf("Windows") != -1) {
	    // Windows
	    return CommonPaths.WIN_PREFS_DIR;
	} else {
	    // Other - assume UNIX-like
	    if (fallback) {
		return System.getenv(CommonPaths.UNIX_FALLBACK_PREFS_DIR);
	    }
	    return CommonPaths.UNIX_PREFS_DIR;
	}
    }

    public static File getPrefsFile() {
	final boolean fallback = CommonPaths.useFallback();
	final StringBuilder b = new StringBuilder();
	b.append(CommonPaths.getDirectoryPrefix(fallback));
	b.append(CommonPaths.getPrefsDirectoryName(fallback));
	b.append(FileExtensions.getPrefsExtensionWithPeriod());
	return new File(b.toString());
    }
}
