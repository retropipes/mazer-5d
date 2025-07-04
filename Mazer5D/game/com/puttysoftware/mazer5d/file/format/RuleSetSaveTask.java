/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.file.format;

import java.io.IOException;

import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.editor.ruleset.RuleSetConstants;
import com.puttysoftware.mazer5d.file.FileExtensions;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;

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
		try (MazeDataWriter ruleSetFile = new MazeDataWriter(this.filename, "ruleset")) {
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
