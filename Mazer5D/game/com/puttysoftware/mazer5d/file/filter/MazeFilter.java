/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.file.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.puttysoftware.mazer5d.file.FileExtensions;

public class MazeFilter extends FileFilter {
	@Override
	public boolean accept(final File f) {
		if (f.isDirectory()) {
			return true;
		}
		final String extension = MazeFilter.getExtension(f);
		if (extension != null) {
			if (extension.equals(FileExtensions.getMazeExtension())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Mazer5D  Mazes (" + FileExtensions.getMazeExtensionWithPeriod() + ")";
	}

	private static String getExtension(final File f) {
		String ext = null;
		final String s = f.getName();
		final int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
