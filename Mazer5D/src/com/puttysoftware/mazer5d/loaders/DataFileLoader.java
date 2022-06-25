package com.puttysoftware.mazer5d.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

public class DataFileLoader {
    private static final String LOAD_PATH = "/asset/data/"; //$NON-NLS-1$
    private static Class<?> LOAD_CLASS = DataFileLoader.class;
    private static ArrayList<Properties> CACHE;

    private static void cacheFile(final DataFile file) {
	final String filename = DataFileNames.getFileName(file);
	try (final InputStream is = DataFileLoader.LOAD_CLASS.getResourceAsStream(
		DataFileLoader.LOAD_PATH + filename + Translations.load(Strings.BUNDLE_EXTENSION))) {
	    Properties loaded = new Properties();
	    loaded.load(is);
	    DataFileLoader.CACHE.add(loaded);
	} catch (final IOException ioe) {
	    System.err.println(Translations.load(Strings.FILE_LOAD_ERROR, filename));
	    ioe.printStackTrace();
	    System.exit(2);
	}
    }

    private static Properties getFromCache(final DataFile file) {
	final int fileID = file.ordinal();
	return DataFileLoader.CACHE.get(fileID);
    }

    public static void init() {
	DataFileLoader.CACHE = new ArrayList<>();
	DataFileLoader.cacheFile(DataFile.OBJECT_NAME);
	DataFileLoader.cacheFile(DataFile.OBJECT_PLURAL);
	DataFileLoader.cacheFile(DataFile.OBJECT_DESCRIPTION);
	DataFileLoader.cacheFile(DataFile.EXTENSION);
	DataFileLoader.cacheFile(DataFile.EXTENSION_PERIOD);
    }

    public static String loadObjectName(final MazeObjects uid) {
	return DataFileLoader.getFromCache(DataFile.OBJECT_NAME).getProperty(Integer.toString(uid.ordinal()));
    }

    public static String loadObjectPluralName(final MazeObjects uid) {
	return DataFileLoader.getFromCache(DataFile.OBJECT_PLURAL).getProperty(Integer.toString(uid.ordinal()));
    }

    public static String loadObjectDescription(final MazeObjects uid) {
	return DataFileLoader.getFromCache(DataFile.OBJECT_DESCRIPTION).getProperty(Integer.toString(uid.ordinal()));
    }

    public static String loadFileExtension(final int extID) {
	return DataFileLoader.getFromCache(DataFile.EXTENSION).getProperty(Integer.toString(extID));
    }

    public static String loadFileExtensionWithPeriod(final int extID) {
	return DataFileLoader.getFromCache(DataFile.EXTENSION_PERIOD).getProperty(Integer.toString(extID));
    }
}
