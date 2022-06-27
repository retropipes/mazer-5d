/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d;

import com.puttysoftware.diane.ErrorHandler;
import com.puttysoftware.diane.ErrorLogger;
import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;

final class GameErrorHandler implements ErrorHandler {
    private static final String LOG_NAME = Translations.load(Strings.PROGRAM_NAME);
    private static final String ERROR_MESSAGE = Translations.load(Strings.ERROR_MESSAGE);
    private static final String ERROR_TITLE = Translations.load(Strings.ERROR_TITLE);
    private final ErrorLogger logger;

    GameErrorHandler() {
	this.logger = new ErrorLogger(GameErrorHandler.LOG_NAME);
    }

    @Override
    public void uncaughtException(final Thread t, final Throwable e) {
	CommonDialogs.showErrorDialog(GameErrorHandler.ERROR_MESSAGE, GameErrorHandler.ERROR_TITLE);
	this.logger.logError(e);
    }
}
