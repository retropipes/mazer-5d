/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.file.format;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.retropipes.diane.fileio.DataIOFactory;
import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.gui.dialog.CommonDialogs;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.editor.ruleset.RuleSetConstants;
import org.retropipes.mazer5d.gui.BagOStuff;

public class RuleSetLoadTask extends Thread {
    // Fields
    private final String filename;

    // Constructors
    public RuleSetLoadTask(final String file) {
	this.filename = file;
	this.setName(" Rule Set File Reader");
    }

    // Methods
    @Override
    public void run() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	final String sg = "Rule Set";
	try (XDataReader ruleSetFile = DataIOFactory.createTagReader(this.filename, "ruleset")) {
	    final int magic = ruleSetFile.readInt();
	    if (magic == RuleSetConstants.MAGIC_NUMBER_2) {
		// Format 2 file
		GameObjects.readRuleSet(ruleSetFile, RuleSetConstants.FORMAT_2);
	    }
	    ruleSetFile.close();
	    CommonDialogs.showTitledDialog(sg + " file loaded.", "Rule Set Picker");
	} catch (final FileNotFoundException fnfe) {
	    CommonDialogs.showDialog("Loading the " + sg.toLowerCase()
		    + " file failed, probably due to illegal characters in the file name.");
	    app.getMazeManager().handleDeferredSuccess(false);
	} catch (final IOException e) {
	    Mazer5D.logError(e);
	}
    }
}
