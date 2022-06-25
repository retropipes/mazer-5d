package com.puttysoftware.mazer5d.locale;

import java.util.ResourceBundle;

import com.puttysoftware.mazer5d.prefs.Prefs;

public final class GameResources {
    private static final String RESOURCES = "asset.locale.GameResources"; //$NON-NLS-1$

    // Class contains only static methods.
    private GameResources() {
    }

    public static String translate(GameResource item) {
	return ResourceBundle.getBundle(GameResources.RESOURCES, Prefs.activeLanguage()).getString(item.toString());
    }
}
