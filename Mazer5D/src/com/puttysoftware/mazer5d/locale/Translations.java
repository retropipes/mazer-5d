package com.puttysoftware.mazer5d.locale;

import java.util.ResourceBundle;

import com.puttysoftware.mazer5d.prefs.Prefs;

public final class Translations {
    private static final String RESOURCES = "asset.locale.Translations"; //$NON-NLS-1$
    private static final String PLACEHOLDER = "$"; //$NON-NLS-1$

    // Class contains only static methods.
    private Translations() {
    }

    public static String load(Strings item) {
	return ResourceBundle.getBundle(Translations.RESOURCES, Prefs.activeLanguage()).getString(item.toString());
    }

    public static String load(Strings item, String... replacements) {
	String result = ResourceBundle.getBundle(Translations.RESOURCES, Prefs.activeLanguage())
		.getString(item.toString());
	for (int x = 0; x < replacements.length; x++) {
	    result = result.replace(Translations.PLACEHOLDER + x, replacements[x]);
	}
	return result;
    }
}
