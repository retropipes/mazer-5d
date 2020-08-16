/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.files.format;

import java.io.File;
import java.io.IOException;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.fileutils.ZipUtilities;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.files.InvalidMazeException;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.maze.Maze;

public class XMLLoadTask extends Thread {
    // Fields
    private Maze gameMaze;
    private final String filename;
    private final boolean isSavedGame;

    // Constructors
    public XMLLoadTask(final String file, final boolean saved) {
        this.filename = file;
        this.isSavedGame = saved;
        this.setName("XML File Loader");
    }

    // Methods
    @Override
    public void run() {
        final BagOStuff app = Mazer5D.getBagOStuff();
        int startW;
        String sg;
        if (this.isSavedGame) {
            app.getGameManager().setSavedGameFlag(true);
            sg = "Saved Game";
        } else {
            app.getGameManager().setSavedGameFlag(false);
            sg = "Maze";
        }
        try {
            final File mazeFile = new File(this.filename);
            this.gameMaze = new Maze();
            ZipUtilities.unzipDirectory(mazeFile, new File(this.gameMaze
                    .getBasePath()));
            // Set prefix handler
            this.gameMaze.setXMLPrefixHandler(new XMLPrefixHandler());
            // Set suffix handler
            if (this.isSavedGame) {
                this.gameMaze.setXMLSuffixHandler(new XMLSuffixHandler());
            } else {
                this.gameMaze.setXMLSuffixHandler(null);
            }
            this.gameMaze = this.gameMaze.readMazeXML();
            if (this.gameMaze == null) {
                throw new InvalidMazeException("Unknown object encountered.");
            }
            app.getMazeManager().setMaze(this.gameMaze);
            startW = this.gameMaze.getStartLevel();
            this.gameMaze.switchLevel(startW);
            final boolean playerExists = this.gameMaze.doesPlayerExist();
            if (playerExists) {
                app.getGameManager().getPlayerManager().setPlayerLocation(
                        this.gameMaze.getStartColumn(), this.gameMaze
                                .getStartRow(), this.gameMaze.getStartFloor(),
                        startW);
                app.getGameManager().resetViewingWindow();
            }
            if (!this.isSavedGame) {
                this.gameMaze.save();
            }
            // Final cleanup
            final String lum = app.getMazeManager().getLastUsedMaze();
            final String lug = app.getMazeManager().getLastUsedGame();
            app.getMazeManager().clearLastUsedFilenames();
            if (this.isSavedGame) {
                app.getMazeManager().setLastUsedGame(lug);
            } else {
                app.getMazeManager().setLastUsedMaze(lum);
            }
            app.getMenuManager().clearLockedFlag();
            app.getEditor().mazeChanged();
            app.getGameManager().stateChanged();
            CommonDialogs.showDialog(sg + " file loaded.");
            app.getMazeManager().handleDeferredSuccess(true);
        } catch (final IOException | InvalidMazeException e) {
            Mazer5D.logError(e);
        }
    }
}
