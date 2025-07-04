/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.file.locking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.fileutils.ZipUtilities;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.FileExtensions;
import com.puttysoftware.mazer5d.file.format.PrefixHandler;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.maze.Maze;

public class LockedSaveTask extends Thread {
	// Fields
	private String filename;

	// Constructors
	public LockedSaveTask(final String file) {
		this.filename = file;
		this.setName("Locked File Writer");
	}

	@Override
	public void run() {
		final BagOStuff app = Mazer5D.getBagOStuff();
		boolean success = true;
		final String sg = "Maze";
		// filename check
		final boolean hasExtension = LockedSaveTask.hasExtension(this.filename);
		if (!hasExtension) {
			this.filename += FileExtensions.getLockedMazeExtensionWithPeriod();
		}
		final File mazeFile = new File(this.filename);
		final File tempLock = new File(Maze.getMazeTempFolder() + "lock.tmp");
		try {
			// Set prefix handler
			app.getMazeManager().getMaze().setPrefixHandler(new PrefixHandler());
			// Set suffix handler
			app.getMazeManager().getMaze().setSuffixHandler(null);
			app.getMazeManager().getMaze().writeMaze();
			ZipUtilities.zipDirectory(new File(app.getMazeManager().getMaze().getBasePath()), tempLock);
			// Lock the file
			LockedWrapper.lock(tempLock, mazeFile);
			tempLock.delete();
		} catch (final FileNotFoundException fnfe) {
			CommonDialogs.showDialog("Writing the locked " + sg.toLowerCase()
					+ " file failed, probably due to illegal characters in the file name.");
			success = false;
		} catch (final IOException e) {
			Mazer5D.logError(e);
		}
		Mazer5D.getBagOStuff().showMessage("Locked " + sg + " file saved.");
		app.getMazeManager().handleDeferredSuccess(success);
	}

	private static boolean hasExtension(final String s) {
		String ext = null;
		final int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		if (ext == null) {
			return false;
		} else {
			return true;
		}
	}
}
