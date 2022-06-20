package com.puttysoftware.mazer5d.locale;

class LocaleFileNames {
    // Static fields
    private static String[] LIST = new String[] { "objectname", "objectplural",
            "objectdesc", "extension", "extensionperiod" };

    // Private constructor
    private LocaleFileNames() {
        // Do nothing
    }

    // Static methods
    static int getFileCount() {
        return LocaleFileNames.LIST.length;
    }

    static String getFileName(final LocaleFile file) {
        return LocaleFileNames.LIST[file.ordinal()];
    }
}
