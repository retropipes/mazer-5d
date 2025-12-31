/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.desktop.AboutEvent;
import java.awt.desktop.AboutHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.retropipes.diane.gui.MainContent;
import org.retropipes.diane.gui.MainWindow;
import org.retropipes.mazer5d.Modes;
import org.retropipes.mazer5d.asset.LogoImageIndex;
import org.retropipes.mazer5d.loader.LogoImageLoader;
import org.retropipes.mazer5d.locale.StaticStrings;
import org.retropipes.mazer5d.locale.Strings;
import org.retropipes.mazer5d.locale.Translations;

public class AboutThisGame implements AboutHandler {
    // Fields
    private MainWindow aboutFrame;
    private MainContent aboutPane;
    private JPanel textPane, buttonPane, logoPane;
    private JButton aboutOK;
    private EventHandler handler;
    private JLabel miniLabel;

    // Constructors
    public AboutThisGame(final String formatVersion) {
	this.setUpGUI(formatVersion);
    }

    // Methods
    public void showAboutDialog() {
	Modes.setInAbout();
	this.aboutFrame.setAndSave(this.aboutPane);
	this.aboutFrame.setTitle(Translations.load(Strings.ABOUT_TITLE));
    }

    void hideAboutDialog() {
	this.aboutFrame.restoreSaved();
	Modes.restore();
    }

    private void setUpGUI(final String formatVersion) {
	this.handler = new EventHandler();
	this.aboutFrame = MainWindow.mainWindow();
	this.aboutPane = MainWindow.createContent();
	this.textPane = new JPanel();
	this.buttonPane = new JPanel();
	this.logoPane = new JPanel();
	this.aboutOK = new JButton(Translations.load(Strings.OK_BUTTON));
	this.miniLabel = new JLabel(StaticStrings.EMPTY, LogoImageLoader.load(LogoImageIndex.MINI_LOGO),
		SwingConstants.LEFT);
	this.aboutOK.setDefaultCapable(true);
	this.aboutFrame.setDefaultButton(this.aboutOK);
	this.aboutPane.setLayout(new BorderLayout());
	this.logoPane.setLayout(new FlowLayout());
	this.logoPane.add(this.miniLabel);
	this.textPane.setLayout(new GridLayout(4, 1));
	this.textPane.add(new JLabel("Mazer5D Version: " + formatVersion));
	this.textPane.add(new JLabel("Author: Eric Ahnell"));
	this.textPane.add(new JLabel("Web Site: http://www.puttysoftware.com/mazer5d/"));
	this.textPane.add(new JLabel("E-mail bug reports to: products@puttysoftware.com  "));
	this.buttonPane.setLayout(new FlowLayout());
	this.buttonPane.add(this.aboutOK);
	this.aboutPane.add(this.logoPane, BorderLayout.WEST);
	this.aboutPane.add(this.textPane, BorderLayout.CENTER);
	this.aboutPane.add(this.buttonPane, BorderLayout.SOUTH);
	this.aboutOK.addActionListener(this.handler);
    }

    private class EventHandler implements ActionListener {
	public EventHandler() {
	    // Do nothing
	}

	// Handle buttons
	@Override
	public void actionPerformed(final ActionEvent e) {
	    final AboutThisGame ad = AboutThisGame.this;
	    final String cmd = e.getActionCommand();
	    if (cmd.equals(Translations.load(Strings.OK_BUTTON))) {
		ad.hideAboutDialog();
	    }
	}
    }

    @Override
    public void handleAbout(final AboutEvent inE) {
	this.showAboutDialog();
    }
}