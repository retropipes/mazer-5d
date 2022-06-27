/*  Mazer5D: A RuleSet-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.file;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;

import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.fileutils.FilenameChecker;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.filter.RuleSetFilter;
import com.puttysoftware.mazer5d.file.format.RuleSetLoadTask;
import com.puttysoftware.mazer5d.file.format.RuleSetSaveTask;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.locale.StaticStrings;

public class RuleSetManager {
    // Constructors
    private RuleSetManager() {
	// Do nothing
    }

    // Methods
    public static void importRuleSet() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	String filename, extension;
	final JFileChooser fc = new JFileChooser();
	final RuleSetFilter xrsf = new RuleSetFilter();
	fc.setAcceptAllFileFilterUsed(false);
	fc.addChoosableFileFilter(xrsf);
	final int returnVal = fc.showOpenDialog((Frame) null);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    final File file = fc.getSelectedFile();
	    filename = file.getAbsolutePath();
	    extension = RuleSetManager.getExtension(file);
	    app.getGameManager().resetObjectInventory();
	    if (extension.equals(FileExtensions.getRuleSetExtension())) {
		RuleSetManager.importFile(filename);
	    } else {
		CommonDialogs.showDialog(
			"You opened something other than a rule set file. Select a rule set file, and try again.");
	    }
	}
    }

    private static void importFile(final String filename) {
	if (!FilenameChecker
		.isFilenameOK(RuleSetManager.getNameWithoutExtension(RuleSetManager.getFileNameOnly(filename)))) {
	    CommonDialogs.showErrorDialog("The file you selected contains illegal characters in its\n"
		    + "name. These characters are not allowed: /?<>\\:|\"\n"
		    + "Files named con, nul, or prn are illegal, as are files\n"
		    + "named com1 through com9 and lpt1 through lpt9.", "Load");
	} else {
	    final RuleSetLoadTask xrslt = new RuleSetLoadTask(filename);
	    xrslt.start();
	}
    }

    public static boolean exportRuleSet() {
	String filename = StaticStrings.EMPTY;
	String fileOnly = "\\";
	String extension;
	final JFileChooser fc = new JFileChooser();
	final RuleSetFilter xrsf = new RuleSetFilter();
	fc.setAcceptAllFileFilterUsed(false);
	fc.addChoosableFileFilter(xrsf);
	while (!FilenameChecker.isFilenameOK(fileOnly)) {
	    final int returnVal = fc.showSaveDialog((Frame) null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		final File file = fc.getSelectedFile();
		extension = RuleSetManager.getExtension(file);
		filename = file.getAbsolutePath();
		final String dirOnly = fc.getCurrentDirectory().getAbsolutePath();
		fileOnly = filename.substring(dirOnly.length() + 1);
		if (!FilenameChecker.isFilenameOK(fileOnly)) {
		    CommonDialogs.showErrorDialog("The file name you entered contains illegal characters.\n"
			    + "These characters are not allowed: /?<>\\:|\"\n"
			    + "Files named con, nul, or prn are illegal, as are files\n"
			    + "named com1 through com9 and lpt1 through lpt9.", "Save");
		} else {
		    if (extension != null) {
			if (!extension.equals(FileExtensions.getRuleSetExtension())) {
			    filename = RuleSetManager.getNameWithoutExtension(file)
				    + FileExtensions.getRuleSetExtensionWithPeriod();
			}
		    } else {
			filename += FileExtensions.getRuleSetExtensionWithPeriod();
		    }
		    RuleSetManager.exportFile(filename);
		}
	    } else {
		break;
	    }
	}
	return false;
    }

    private static void exportFile(final String filename) {
	final RuleSetSaveTask xrsst = new RuleSetSaveTask(filename);
	xrsst.start();
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

    private static String getNameWithoutExtension(final File f) {
	String ext = null;
	final String s = f.getAbsolutePath();
	final int i = s.lastIndexOf('.');
	if (i > 0 && i < s.length() - 1) {
	    ext = s.substring(0, i);
	} else {
	    ext = s;
	}
	return ext;
    }

    private static String getNameWithoutExtension(final String s) {
	String ext = null;
	final int i = s.lastIndexOf('.');
	if (i > 0 && i < s.length() - 1) {
	    ext = s.substring(0, i);
	} else {
	    ext = s;
	}
	return ext;
    }

    private static String getFileNameOnly(final String s) {
	String fno = null;
	final int i = s.lastIndexOf(File.separatorChar);
	if (i > 0 && i < s.length() - 1) {
	    fno = s.substring(i + 1);
	} else {
	    fno = s;
	}
	return fno;
    }
}
