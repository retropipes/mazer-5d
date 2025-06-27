package com.puttysoftware.mazer5d.locale;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.puttysoftware.mazer5d.abc.MazeObjects;

public class LocalDataLoader {
	private static final String LOAD_PATH = "/asset/data/"; //$NON-NLS-1$
	private static Class<?> LOAD_CLASS = LocalDataLoader.class;
	private static ArrayList<Properties> CACHE;

	private static void cacheFile(final LocalData file) {
		final String filename = LocalDataNames.getFileName(file);
		try (final InputStream is = LocalDataLoader.LOAD_CLASS.getResourceAsStream(
				LocalDataLoader.LOAD_PATH + filename + Translations.load(Strings.BUNDLE_EXTENSION))) {
			Properties loaded = new Properties();
			loaded.load(is);
			LocalDataLoader.CACHE.add(loaded);
		} catch (final IOException ioe) {
			System.err.println(Translations.load(Strings.FILE_LOAD_ERROR, filename));
			ioe.printStackTrace();
			System.exit(2);
		}
	}

	private static Properties getFromCache(final LocalData file) {
		final int fileID = file.ordinal();
		return LocalDataLoader.CACHE.get(fileID);
	}

	public static void init() {
		LocalDataLoader.CACHE = new ArrayList<>();
		LocalDataLoader.cacheFile(LocalData.OBJECT_NAME);
		LocalDataLoader.cacheFile(LocalData.OBJECT_PLURAL);
		LocalDataLoader.cacheFile(LocalData.OBJECT_DESCRIPTION);
		LocalDataLoader.cacheFile(LocalData.EXTENSION);
		LocalDataLoader.cacheFile(LocalData.EXTENSION_PERIOD);
	}

	public static String loadObjectName(final MazeObjects uid) {
		return LocalDataLoader.getFromCache(LocalData.OBJECT_NAME).getProperty(Integer.toString(uid.ordinal()));
	}

	public static String loadObjectPluralName(final MazeObjects uid) {
		return LocalDataLoader.getFromCache(LocalData.OBJECT_PLURAL).getProperty(Integer.toString(uid.ordinal()));
	}

	public static String loadObjectDescription(final MazeObjects uid) {
		return LocalDataLoader.getFromCache(LocalData.OBJECT_DESCRIPTION).getProperty(Integer.toString(uid.ordinal()));
	}
}
