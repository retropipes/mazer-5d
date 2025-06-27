package com.puttysoftware.diane.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public final class EngineTranslations {
    private static final String RESOURCES = "locale.engine.Translations"; //$NON-NLS-1$
    private static final String PLACEHOLDER = "$"; //$NON-NLS-1$
    private static Locale ACTIVE = Locale.getDefault();

    // Class contains only static methods.
    private EngineTranslations() {
    }

    public static String load(EngineStrings item) {
        return ResourceBundle.getBundle(EngineTranslations.RESOURCES, EngineTranslations.ACTIVE)
                .getString(item.toString());
    }

    public static String load(EngineStrings item, String... replacements) {
        String result = ResourceBundle.getBundle(EngineTranslations.RESOURCES, EngineTranslations.ACTIVE)
                .getString(item.toString());
        for (int x = 0; x < replacements.length; x++) {
            result = result.replace(EngineTranslations.PLACEHOLDER + x, replacements[x]);
        }
        return result;
    }

    public static void setActiveLanguage(final Locale newActive) {
        EngineTranslations.ACTIVE = newActive;
    }
}
