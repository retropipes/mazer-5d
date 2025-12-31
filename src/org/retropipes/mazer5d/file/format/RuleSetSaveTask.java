/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.file.format;

import java.io.IOException;

import org.retropipes.diane.fileio.DataIOFactory;
import org.retropipes.diane.fileio.XDataWriter;
import org.retropipes.diane.gui.dialog.CommonDialogs;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.editor.ruleset.RuleSetConstants;
import org.retropipes.mazer5d.file.FileExtensions;

public class RuleSetSaveTask extends Thread {
    // Fields
    private String filename;

    // Constructors
    public RuleSetSaveTask(final String file) {
	this.filename = file;
	this.setName(" Rule Set File Writer");
    }

    @Override
    public void run() {
	final String sg = "Rule Set";
	// filename check
	final boolean hasExtension = RuleSetSaveTask.hasExtension(this.filename);
	if (!hasExtension) {
	    this.filename += FileExtensions.getRuleSetExtensionWithPeriod();
	}
	try (XDataWriter ruleSetFile = DataIOFactory.createTagWriter(this.filename, "ruleset")) {
	    ruleSetFile.writeInt(RuleSetConstants.MAGIC_NUMBER_2);
	    GameObjects.writeRuleSet(ruleSetFile);
	    CommonDialogs.showTitledDialog(sg + " file saved.", "Rule Set Picker");
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	}
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
