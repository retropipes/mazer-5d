package org.retropipes.mazer5d.file;

import java.io.File;

import org.retropipes.diane.fileio.utility.DirectoryUtilities;
import org.retropipes.mazer5d.maze.Maze;

public class TempDirCleanup extends Thread {
    @Override
    public void run() {
	try {
	    final File dirToDelete = new File(Maze.getMazeTempFolder());
	    DirectoryUtilities.removeDirectory(dirToDelete);
	} catch (final Throwable t) {
	    // Ignore
	}
    }
}
