/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.editor.ruleset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.diane.gui.dialog.MainWindow;
import com.puttysoftware.diane.gui.dialog.MainWindowContent;
import com.puttysoftware.diane.image.BufferedImageIcon;
import com.puttysoftware.diane.picturepicker.PicturePicker;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.file.RuleSetManager;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class RuleSetPicker {
    // Declarations
    private MainWindow outputFrame;
    private MainWindowContent borderPane;
    private JPanel outputPane;
    private final EventHandler handler;
    private PicturePicker picker;
    private final String[] names;
    private final MazeObject[] objects;
    private final BufferedImageIcon[] editorAppearances;
    private int index;
    private JButton create, destroy, edit, importFile, exportFile, done;
    private final RuleSetEditor rsEditor;

    public RuleSetPicker() {
	this.handler = new EventHandler();
	this.names = GameObjects.getAllNames();
	this.objects = GameObjects.getAllObjects();
	this.editorAppearances = GameObjects.getAllEditorAppearances();
	this.rsEditor = new RuleSetEditor();
	this.setUpGUI();
    }

    void createObjectRuleSet() {
	this.index = this.picker.getPicked();
	final MazeObject object = this.objects[this.index];
	object.giveRuleSet();
	CommonDialogs.showTitledDialog("Rule Set Created.", "Rule Set Picker");
    }

    void destroyObjectRuleSet() {
	this.index = this.picker.getPicked();
	final MazeObject object = this.objects[this.index];
	object.takeRuleSet();
	CommonDialogs.showTitledDialog("Rule Set Destroyed.", "Rule Set Picker");
    }

    void editObjectRuleSet() {
	this.index = this.picker.getPicked();
	final MazeObject object = this.objects[this.index];
	if (object.hasRuleSet()) {
	    this.rsEditor.setRuleSet(object.getRuleSet());
	    this.rsEditor.showRuleSetEditor();
	} else {
	    CommonDialogs.showTitledDialog("This object does not have a rule set to edit!", "Rule Set Picker");
	}
    }

    public void editRuleSets() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getEditor().hideOutput();
	this.showOutput();
    }

    public void showOutput() {
	Modes.setInRulePicker();
	this.outputFrame.attachAndSave(this.borderPane);
	this.outputFrame.setTitle("Rule Set Picker");
    }

    public void hideOutput() {
	this.outputFrame.restoreSaved();
	Modes.restore();
    }

    void exitRuleSetEditor() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	this.hideOutput();
	app.getEditor().showOutput();
    }

    private void setUpGUI() {
	this.outputFrame = MainWindow.getMainWindow();
	this.borderPane = this.outputFrame.createContent();
	this.outputPane = new JPanel();
	this.create = new JButton("Create");
	this.destroy = new JButton("Destroy");
	this.edit = new JButton("Edit");
	this.importFile = new JButton("Load");
	this.exportFile = new JButton("Save");
	this.done = new JButton("Done");
	this.borderPane.setLayout(new BorderLayout());
	this.borderPane.add(this.outputPane, BorderLayout.SOUTH);
	this.outputPane.setLayout(new FlowLayout());
	this.outputPane.add(this.create);
	this.outputPane.add(this.destroy);
	this.outputPane.add(this.edit);
	this.outputPane.add(this.importFile);
	this.outputPane.add(this.exportFile);
	this.outputPane.add(this.done);
	this.create.addActionListener(this.handler);
	this.destroy.addActionListener(this.handler);
	this.edit.addActionListener(this.handler);
	this.importFile.addActionListener(this.handler);
	this.exportFile.addActionListener(this.handler);
	this.done.addActionListener(this.handler);
	this.picker = new PicturePicker(this.editorAppearances, this.names);
	this.picker.changePickerColor(new Color(223, 223, 223));
	final int maxSize = Prefs.getViewingWindowSize();
	this.picker.updatePickerLayout(maxSize);
	this.borderPane.add(this.picker.getPicker(), BorderLayout.CENTER);
    }

    private class EventHandler implements ActionListener {
	public EventHandler() {
	    // Do nothing
	}

	// handle buttons
	@Override
	public void actionPerformed(final ActionEvent e) {
	    new Thread() {
		@Override
		public void run() {
		    final String cmd = e.getActionCommand();
		    final RuleSetPicker ge = RuleSetPicker.this;
		    if (cmd.equals("Create")) {
			ge.createObjectRuleSet();
		    } else if (cmd.equals("Destroy")) {
			ge.destroyObjectRuleSet();
		    } else if (cmd.equals("Edit")) {
			ge.editObjectRuleSet();
		    } else if (cmd.equals("Load")) {
			RuleSetManager.importRuleSet();
		    } else if (cmd.equals("Save")) {
			RuleSetManager.exportRuleSet();
		    } else if (cmd.equals("Done")) {
			ge.exitRuleSetEditor();
		    }
		}
	    }.start();
	}
    }
}
