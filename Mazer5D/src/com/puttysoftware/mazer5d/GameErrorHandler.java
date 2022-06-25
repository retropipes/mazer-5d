/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d;

import com.puttysoftware.mazer5d.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.errorlogger.ErrorLogger;
import com.puttysoftware.mazer5d.locale.GameResource;
import com.puttysoftware.mazer5d.locale.Translations;

final class GameErrorHandler implements Thread.UncaughtExceptionHandler {
    private static final String LOG_NAME = Translations.load(GameResource.PROGRAM_NAME);
    private static final String ERROR_MESSAGE = Translations.load(GameResource.ERROR_MESSAGE);
    private static final String ERROR_TITLE = Translations.load(GameResource.ERROR_TITLE);
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
