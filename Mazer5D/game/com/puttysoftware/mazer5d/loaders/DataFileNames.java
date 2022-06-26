package com.puttysoftware.mazer5d.loaders;

class DataFileNames {
    // Static fields
    private static String[] LIST = new String[] { "objectname", "objectplural", "objectdesc", "extension",
	    "extensionperiod" };

    // Private constructor
    private DataFileNames() {
	// Do nothing
    }

    // Static methods
    static int getFileCount() {
	return DataFileNames.LIST.length;
    }

    static String getFileName(final DataFile file) {
	return DataFileNames.LIST[file.ordinal()];
    }
}
