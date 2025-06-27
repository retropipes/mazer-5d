package com.puttysoftware.mazer5d.file;

import java.io.File;

import com.puttysoftware.fileutils.DirectoryUtilities;
import com.puttysoftware.mazer5d.maze.Maze;

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
