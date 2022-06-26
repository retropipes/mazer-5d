/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.puttysoftware.diane.asset.BufferedImageIcon;
import com.puttysoftware.diane.gui.dialog.MainWindow;
import com.puttysoftware.diane.gui.dialog.MainWindowContent;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.help.GraphicalHelpViewer;
import com.puttysoftware.mazer5d.loaders.DataLoader;
import com.puttysoftware.mazer5d.objects.GameObjects;

public class ObjectHelpViewer {
    // Fields
    private MainWindow helpFrame;
    private MainWindowContent helpPane;
    private JPanel buttonPane;
    private JButton export, done;
    private String[] objectNames;
    private BufferedImageIcon[] objectAppearances;
    GraphicalHelpViewer hv;
    private ButtonHandler buttonHandler;

    // Constructors
    public ObjectHelpViewer() {
	this.initHelp();
    }

    // Methods
    public void showHelp() {
	Modes.setInHelp();
	this.helpFrame.attachAndSave(this.helpPane);
	this.helpFrame.setTitle("Mazer5D Object Help");
    }

    void hideHelp() {
	this.helpFrame.restoreSaved();
	Modes.restore();
    }

    private void initHelp() {
	this.buttonHandler = new ButtonHandler();
	this.objectNames = DataLoader.loadAllObjectHelpDescriptions();
	this.objectAppearances = GameObjects.getAllObjectHelpAppearances();
	this.hv = new GraphicalHelpViewer(this.objectAppearances, this.objectNames, new Color(223, 223, 223));
	this.export = new JButton("Export");
	this.export.addActionListener(this.buttonHandler);
	this.done = new JButton("Done");
	this.done.addActionListener(this.buttonHandler);
	this.helpFrame = MainWindow.getMainWindow();
	this.helpPane = this.helpFrame.createContent();
	this.helpPane.setLayout(new BorderLayout());
	this.buttonPane = new JPanel();
	this.buttonPane.setLayout(new FlowLayout());
	this.buttonPane.add(this.export);
	this.buttonPane.add(this.done);
	this.helpPane.add(this.hv.getHelp(), BorderLayout.CENTER);
	this.helpPane.add(this.buttonPane, BorderLayout.SOUTH);
	final int maxSize = 700;
	this.hv.setHelpSize(maxSize, maxSize);
    }

    private class ButtonHandler implements ActionListener {
	public ButtonHandler() {
	    // Do nothing
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
	    final String cmd = e.getActionCommand();
	    if (cmd.equals("Export")) {
		ObjectHelpViewer.this.hv.exportHelp();
	    } else if (cmd.equals("Done")) {
		ObjectHelpViewer.this.hideHelp();
	    }
	}
    }
}
