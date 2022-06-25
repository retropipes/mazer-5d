/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

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

import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.assets.LogoImageIndex;
import com.puttysoftware.mazer5d.commondialogs.MainWindow;
import com.puttysoftware.mazer5d.commondialogs.MainWindowContent;
import com.puttysoftware.mazer5d.loaders.LogoImageLoader;
import com.puttysoftware.mazer5d.locale.Strings;

public class AboutThisGame implements AboutHandler {
    // Fields
    private MainWindow aboutFrame;
    private MainWindowContent aboutPane;
    private JPanel textPane, buttonPane, logoPane;
    private JButton aboutOK;
    private EventHandler handler;
    private JLabel miniLabel;

    // Constructors
    public AboutThisGame(final String ver) {
	this.setUpGUI(ver);
    }

    // Methods
    public void showAboutDialog() {
	Modes.setInAbout();
	this.aboutFrame.attachAndSave(this.aboutPane);
	this.aboutFrame.setTitle("About Mazer5D");
    }

    void hideAboutDialog() {
	this.aboutFrame.restoreSaved();
	Modes.restore();
    }

    private void setUpGUI(final String ver) {
	this.handler = new EventHandler();
	this.aboutFrame = MainWindow.getMainWindow();
	this.aboutPane = this.aboutFrame.createContent();
	this.textPane = new JPanel();
	this.buttonPane = new JPanel();
	this.logoPane = new JPanel();
	this.aboutOK = new JButton("OK");
	this.miniLabel = new JLabel(Strings.EMPTY, LogoImageLoader.load(LogoImageIndex.MINI_LOGO), SwingConstants.LEFT);
	this.aboutOK.setDefaultCapable(true);
	this.aboutFrame.setDefaultButton(this.aboutOK);
	this.aboutPane.setLayout(new BorderLayout());
	this.logoPane.setLayout(new FlowLayout());
	this.logoPane.add(this.miniLabel);
	this.textPane.setLayout(new GridLayout(4, 1));
	this.textPane.add(new JLabel("Mazer5D Version: " + ver));
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
	    if (cmd.equals("OK")) {
		ad.hideAboutDialog();
	    }
	}
    }

    @Override
    public void handleAbout(final AboutEvent inE) {
	this.showAboutDialog();
    }
}