package com.puttysoftware.mazer5d.loaders;

import java.io.IOException;
import java.util.ArrayList;

import com.puttysoftware.fileutils.ResourceStreamReader;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.files.FileExtensions;
import com.puttysoftware.mazer5d.utilities.MazeObjectActions;

public class DataLoader {
    private static MazeObjectActions[] ACTION_CACHE;
    private static int[][] ACTION_ADDON_CACHE;
    private static int[] LAYER_CACHE;
    private static final int ACTION_ADDON_LENGTH = 33;

    private DataLoader() {
	// Do nothing
    }

    public static String[] loadMusicData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		"/assets/data/music/files" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		"/assets/data/sound/files" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
		    "/assets/data/object/actions" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
		    "/assets/data/object/" + name + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
	if (DataLoader.LAYER_CACHE == null) {
	    try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		    "/assets/data/object/layer" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
		DataLoader.LAYER_CACHE = data;
	    } catch (final IOException e) {
		Mazer5D.logError(e);
		return 0;
	    }
	}
	return DataLoader.LAYER_CACHE[objectID];
    }

    public static String[] loadEffectImageData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		"/assets/data/image/effect" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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

    public static String[] loadObjectImageData() {
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		"/assets/data/image/object" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
	try (final ResourceStreamReader rsr = new ResourceStreamReader(DataLoader.class.getResourceAsStream(
		"/assets/data/image/logo" + FileExtensions.getDataFileExtensionWithPeriod()))) {
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
}
