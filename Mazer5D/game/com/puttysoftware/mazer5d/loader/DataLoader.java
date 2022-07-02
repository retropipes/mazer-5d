package com.puttysoftware.mazer5d.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.puttysoftware.fileutils.ResourceStreamReader;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.FileExtensions;
import com.puttysoftware.mazer5d.utility.MazeObjectActions;

public class DataLoader {
    private static MazeObjectActions[] ACTION_CACHE;
    private static int[][] ACTION_ADDON_CACHE;
    private static int[] OBJECT_LAYER_CACHE;
    private static long[] OBJECT_TYPE_CACHE;
    private static String[] OBJECT_IMAGE_NAME_CACHE;
    private static final int ACTION_ADDON_LENGTH = 33;

    private DataLoader() {
	// Do nothing
    }

    public static String loadFileExtension(final int extID) {
	return ResourceBundle.getBundle("asset.data.extension").getString(Integer.toString(extID));
    }

    public static String loadFileExtensionWithPeriod(final int extID) {
	return ResourceBundle.getBundle("asset.data.extensionperiod").getString(Integer.toString(extID));
    }

    public static String[] loadMusicData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class
		.getResourceAsStream("/asset/data/music/files" + FileExtensions.getDataFileExtensionWithPeriod()))) {
	    // Fetch data
	    final ArrayList<String> data = new ArrayList<>();
	    String raw = "0";
	    while (raw != null) {
		raw = rsr.readString();
		if (raw != null) {
		    data.add(raw);
		}
	    }
	    return data.toArray(new String[data.size()]);
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	    return null;
	}
    }

    public static String[] loadSoundData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class
		.getResourceAsStream("/asset/data/sound/files" + FileExtensions.getDataFileExtensionWithPeriod()))) {
	    // Fetch data
	    final ArrayList<String> data = new ArrayList<>();
	    String raw = "0";
	    while (raw != null) {
		raw = rsr.readString();
		if (raw != null) {
		    data.add(raw);
		}
	    }
	    return data.toArray(new String[data.size()]);
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	    return null;
	}
    }

    public static MazeObjectActions loadObjectActionData(final int objectID) {
	if (DataLoader.ACTION_CACHE == null) {
	    try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		    "/asset/data/object/actions" + FileExtensions.getDataFileExtensionWithPeriod()))) {
		// Fetch data
		final ArrayList<MazeObjectActions> data = new ArrayList<>();
		String raw = "0";
		while (raw != null) {
		    raw = rsr.readString();
		    if (raw != null) {
			data.add(new MazeObjectActions(Long.parseLong(raw)));
		    }
		}
		DataLoader.ACTION_CACHE = data.toArray(new MazeObjectActions[data.size()]);
	    } catch (final IOException e) {
		Mazer5D.logError(e);
		return null;
	    }
	}
	return DataLoader.ACTION_CACHE[objectID];
    }

    public static int loadObjectActionAddonData(final int objectID, final int actionID) {
	if (DataLoader.ACTION_ADDON_CACHE == null) {
	    DataLoader.ACTION_ADDON_CACHE = new int[DataLoader.ACTION_ADDON_LENGTH][];
	}
	if (DataLoader.ACTION_ADDON_CACHE[actionID] == null) {
	    final String name = "action-" + Integer.toString(actionID);
	    try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		    "/asset/data/object/" + name + FileExtensions.getDataFileExtensionWithPeriod()))) {
		// Fetch data
		final ArrayList<Integer> rawData = new ArrayList<>();
		String raw = "0";
		while (raw != null) {
		    raw = rsr.readString();
		    if (raw != null) {
			rawData.add(Integer.parseInt(raw));
		    }
		}
		int index = 0;
		final int[] data = new int[rawData.size()];
		for (final Integer rawItem : rawData) {
		    data[index] = rawItem;
		    index++;
		}
		DataLoader.ACTION_ADDON_CACHE[actionID] = data;
	    } catch (final IOException e) {
		Mazer5D.logError(e);
		return 0;
	    }
	}
	return DataLoader.ACTION_ADDON_CACHE[actionID][objectID];
    }

    public static int loadObjectLayerData(final int objectID) {
	if (DataLoader.OBJECT_LAYER_CACHE == null) {
	    try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		    "/asset/data/object/layer" + FileExtensions.getDataFileExtensionWithPeriod()))) {
		// Fetch data
		final ArrayList<Integer> rawData = new ArrayList<>();
		String raw = "0";
		while (raw != null) {
		    raw = rsr.readString();
		    if (raw != null) {
			rawData.add(Integer.parseInt(raw));
		    }
		}
		int index = 0;
		final int[] data = new int[rawData.size()];
		for (final Integer rawItem : rawData) {
		    data[index] = rawItem;
		    index++;
		}
		DataLoader.OBJECT_LAYER_CACHE = data;
	    } catch (final IOException e) {
		Mazer5D.logError(e);
		return 0;
	    }
	}
	return DataLoader.OBJECT_LAYER_CACHE[objectID];
    }

    public static long loadObjectTypeData(final int objectID) {
	if (DataLoader.OBJECT_TYPE_CACHE == null) {
	    try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		    "/asset/data/object/type" + FileExtensions.getDataFileExtensionWithPeriod()))) {
		// Fetch data
		final ArrayList<Long> rawData = new ArrayList<>();
		String raw = "0";
		while (raw != null) {
		    raw = rsr.readString();
		    if (raw != null) {
			rawData.add(Long.parseLong(raw));
		    }
		}
		int index = 0;
		final long[] data = new long[rawData.size()];
		for (final Long rawItem : rawData) {
		    data[index] = rawItem;
		    index++;
		}
		DataLoader.OBJECT_TYPE_CACHE = data;
	    } catch (final IOException e) {
		Mazer5D.logError(e);
		return 0;
	    }
	}
	return DataLoader.OBJECT_TYPE_CACHE[objectID];
    }

    public static String[] loadEffectImageData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class
		.getResourceAsStream("/asset/data/image/effect" + FileExtensions.getDataFileExtensionWithPeriod()))) {
	    // Fetch data
	    final ArrayList<String> data = new ArrayList<>();
	    String raw = "0";
	    while (raw != null) {
		raw = rsr.readString();
		if (raw != null) {
		    data.add(raw);
		}
	    }
	    return data.toArray(new String[data.size()]);
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	    return null;
	}
    }

    public static String loadObjectImageData(final int objectID) {
	DataLoader.updateObjectImageDataCache();
	return DataLoader.OBJECT_IMAGE_NAME_CACHE[objectID];
    }

    public static String[] loadAllObjectImageData() {
	DataLoader.updateObjectImageDataCache();
	return DataLoader.OBJECT_IMAGE_NAME_CACHE;
    }

    public static String[] loadAllObjectHelpDescriptions() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		"/asset/data/image/objecthelpdesc" + FileExtensions.getDataFileExtensionWithPeriod()))) {
	    // Fetch data
	    final ArrayList<String> data = new ArrayList<>();
	    String raw = "0";
	    while (raw != null) {
		raw = rsr.readString();
		if (raw != null) {
		    data.add(raw);
		}
	    }
	    return data.toArray(new String[data.size()]);
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	    return null;
	}
    }

    public static String[] loadLogoImageData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class
		.getResourceAsStream("/asset/data/image/logo" + FileExtensions.getDataFileExtensionWithPeriod()))) {
	    // Fetch data
	    final ArrayList<String> data = new ArrayList<>();
	    String raw = "0";
	    while (raw != null) {
		raw = rsr.readString();
		if (raw != null) {
		    data.add(raw);
		}
	    }
	    return data.toArray(new String[data.size()]);
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	    return null;
	}
    }

    private static void updateObjectImageDataCache() {
	if (DataLoader.OBJECT_IMAGE_NAME_CACHE == null) {
	    try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		    "/asset/data/image/object" + FileExtensions.getDataFileExtensionWithPeriod()))) {
		// Fetch data
		final ArrayList<String> data = new ArrayList<>();
		String raw = "0";
		while (raw != null) {
		    raw = rsr.readString();
		    if (raw != null) {
			data.add(raw);
		    }
		}
		DataLoader.OBJECT_IMAGE_NAME_CACHE = data.toArray(new String[data.size()]);
	    } catch (final IOException e) {
		Mazer5D.logError(e);
	    }
	}
    }
}
