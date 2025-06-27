package com.puttysoftware.mazer5d.locale;

class LocalDataNames {
    // Static fields
    private static String[] LIST = new String[] { "objectname", "objectplural", "objectdesc", "extension",
            "extensionperiod" };

    // Private constructor
    private LocalDataNames() {
        // Do nothing
    }

    // Static methods
    static int getFileCount() {
        return LocalDataNames.LIST.length;
    }

    static String getFileName(final LocalData file) {
        return LocalDataNames.LIST[file.ordinal()];
    }
}
