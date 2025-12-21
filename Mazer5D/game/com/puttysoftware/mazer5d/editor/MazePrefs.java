/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.maze.Maze;

public class MazePrefs {
	// Fields
	private MainWindow prefFrame;
	private MainWindowContent mainPrefPane;
	private JPanel contentPane, buttonPane;
	private JButton prefsOK, prefsCancel;
	private JComboBox<String> startLevelChoices;
	private String[] startLevelChoiceArray;
	private JTextField health;
	private JTextField mazeTitle;
	private JTextArea mazeStartMessage;
	private JTextArea mazeEndMessage;
	private EventHandler handler;

	// Constructors
	public MazePrefs() {
		this.setUpGUI();
	}

	// Methods
	public void showPrefs() {
		this.loadPrefs();
		Mazer5D.getBagOStuff().getEditor().disableOutput();
		Modes.setInMazePrefs();
		this.prefFrame.attachAndSave(this.mainPrefPane);
		this.prefFrame.setTitle("Maze Preferences");
	}

	public void hidePrefs() {
		this.prefFrame.restoreSaved();
		Modes.restore();
		Mazer5D.getBagOStuff().getEditor().enableOutput();
		Mazer5D.getBagOStuff().getMazeManager().setDirty(true);
		Mazer5D.getBagOStuff().getEditor().redrawEditor();
	}

	void setPrefs() {
		final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
		m.setStartLevel(this.startLevelChoices.getSelectedIndex());
		try {
			m.setMaximumHP(Integer.parseInt(this.health.getText()));
			m.healFully();
		} catch (final NumberFormatException nf) {
			// Ignore
		}
		m.setMazeTitle(this.mazeTitle.getText());
		m.setMazeStartMessage(this.mazeStartMessage.getText());
		m.setMazeEndMessage(this.mazeEndMessage.getText());
	}

	private void loadPrefs() {
		final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
		this.startLevelChoiceArray = new String[m.getLevels()];
		for (int x = 0; x < m.getLevels(); x++) {
			this.startLevelChoiceArray[x] = Integer.toString(x + 1);
		}
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(this.startLevelChoiceArray);
		this.startLevelChoices.setModel(model);
		this.startLevelChoices.setSelectedIndex(m.getStartLevel());
		this.health.setText(Integer.toString(m.getMaximumHP()));
		this.mazeTitle.setText(m.getMazeTitle());
		this.mazeStartMessage.setText(m.getMazeStartMessage());
		this.mazeEndMessage.setText(m.getMazeEndMessage());
	}

	private void setUpGUI() {
		this.handler = new EventHandler();
		this.prefFrame = MainWindow.getMainWindow();
		this.mainPrefPane = this.prefFrame.createContent();
		this.contentPane = new JPanel();
		this.buttonPane = new JPanel();
		this.prefsOK = new JButton(Translations.load(Strings.OK_BUTTON));
		this.prefsOK.setDefaultCapable(true);
		this.prefFrame.setDefaultButton(this.prefsOK);
		this.prefsCancel = new JButton(Translations.load(Strings.CANCEL_BUTTON));
		this.prefsCancel.setDefaultCapable(false);
		this.startLevelChoices = new JComboBox<>();
		this.health = new JTextField(StaticStrings.EMPTY);
		this.mazeTitle = new JTextField(StaticStrings.EMPTY);
		this.mazeStartMessage = new JTextArea(StaticStrings.EMPTY);
		this.mazeEndMessage = new JTextArea(StaticStrings.EMPTY);
		this.mainPrefPane.setLayout(new BorderLayout());
		this.contentPane.setLayout(new GridLayout(10, 1));
		this.contentPane.add(new JLabel("Starting Level"));
		this.contentPane.add(this.startLevelChoices);
		this.contentPane.add(new JLabel("Starting Health"));
		this.contentPane.add(this.health);
		this.contentPane.add(new JLabel("Maze Title"));
		this.contentPane.add(this.mazeTitle);
		this.contentPane.add(new JLabel("Maze Start Message"));
		this.contentPane.add(this.mazeStartMessage);
		this.contentPane.add(new JLabel("Maze End Message"));
		this.contentPane.add(this.mazeEndMessage);
		this.buttonPane.setLayout(new FlowLayout());
		this.buttonPane.add(this.prefsOK);
		this.buttonPane.add(this.prefsCancel);
		this.mainPrefPane.add(this.contentPane, BorderLayout.CENTER);
		this.mainPrefPane.add(this.buttonPane, BorderLayout.SOUTH);
		this.prefsOK.addActionListener(this.handler);
		this.prefsCancel.addActionListener(this.handler);
	}

	private class EventHandler implements ActionListener {
		public EventHandler() {
			// Do nothing
		}

		// Handle buttons
		@Override
		public void actionPerformed(final ActionEvent e) {
			new Thread() {
				@Override
				public void run() {
					final MazePrefs mpm = MazePrefs.this;
					final String cmd = e.getActionCommand();
					if (cmd.equals(Translations.load(Strings.OK_BUTTON))) {
						mpm.setPrefs();
						mpm.hidePrefs();
					} else if (cmd.equals(Translations.load(Strings.CANCEL_BUTTON))) {
						mpm.hidePrefs();
					}
				}
			}.start();
		}
	}
}
