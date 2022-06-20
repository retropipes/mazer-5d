package com.puttysoftware.mazer5d.locale;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class LocaleLoader {
    private static final String LOAD_PATH = "/locale/";
    private static Class<?> LOAD_CLASS = LocaleLoader.class;
    private static ArrayList<Properties> CACHE;

    private static void cacheFile(final LocaleFile file) {
	final int fileID = file.ordinal();
	final String filename = LocaleFileNames.getFileName(file);
	try (final InputStream is = LocaleLoader.LOAD_CLASS
		.getResourceAsStream(LocaleLoader.LOAD_PATH + filename + ".properties")) {
	    LocaleLoader.CACHE.get(fileID).load(is);
	} catch (final IOException ioe) {
	    Mazer5D.logError(ioe);
	}
    }

    private static Properties getFromCache(final LocaleFile file) {
	final int fileID = file.ordinal();
	return LocaleLoader.CACHE.get(fileID);
    }

    public static void init() {
	LocaleLoader.cacheFile(LocaleFile.OBJECT_NAME);
	LocaleLoader.cacheFile(LocaleFile.OBJECT_PLURAL);
	LocaleLoader.cacheFile(LocaleFile.OBJECT_DESCRIPTION);
    }

    public static String loadObjectName(final MazeObjects uid) {
	return LocaleLoader.getFromCache(LocaleFile.OBJECT_NAME).getProperty(Integer.toString(uid.ordinal()));
    }

    public static String loadObjectPluralName(final MazeObjects uid) {
	return LocaleLoader.getFromCache(LocaleFile.OBJECT_PLURAL).getProperty(Integer.toString(uid.ordinal()));
    }

    public static String loadObjectDescription(final MazeObjects uid) {
	return LocaleLoader.getFromCache(LocaleFile.OBJECT_DESCRIPTION).getProperty(Integer.toString(uid.ordinal()));
    }
}
