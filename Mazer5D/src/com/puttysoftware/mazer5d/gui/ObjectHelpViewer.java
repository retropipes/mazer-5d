/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.puttysoftware.help.GraphicalHelpViewer;
import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.dialog.MainWindow;
import com.puttysoftware.mazer5d.objects.GameObjects;
import com.puttysoftware.mazer5d.prefs.Prefs;

public class ObjectHelpViewer {
    // Fields
    private MainWindow helpFrame;
    private JPanel helpPane;
    private JButton export;
    private String[] objectNames;
    private BufferedImageIcon[] objectAppearances;
    GraphicalHelpViewer hv;
    private ButtonHandler buttonHandler;
    private boolean inited = false;

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
        if (!this.inited) {
            this.buttonHandler = new ButtonHandler();
            this.objectNames = GameObjects.getAllDescriptions();
            this.objectAppearances = GameObjects.getAllEditorAppearances();
            this.hv = new GraphicalHelpViewer(this.objectAppearances,
                    this.objectNames, new Color(223, 223, 223));
            this.export = new JButton("Export");
            this.export.addActionListener(this.buttonHandler);
            this.helpFrame = MainWindow.getMainWindow();
            this.helpPane = new JPanel();
            this.helpPane.setLayout(new BorderLayout());
            this.helpPane.add(this.hv.getHelp(), BorderLayout.CENTER);
            this.helpPane.add(this.export, BorderLayout.SOUTH);
            final int maxSize = Prefs.getViewingWindowSize();
            this.hv.setHelpSize(maxSize, maxSize);
            this.helpFrame.pack();
            // Mac OS X-specific fixes
            if (System.getProperty("os.name").startsWith("Mac OS X")) {
                Mazer5D.getBagOStuff().getMenuManager().setHelpMenus();
            }
            this.inited = true;
        }
    }

    private class ButtonHandler implements ActionListener, WindowListener {
        public ButtonHandler() {
            // Do nothing
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            ObjectHelpViewer.this.hv.exportHelp();
        }

        @Override
        public void windowOpened(WindowEvent inE) {
            // Do nothing
        }

        @Override
        public void windowClosing(WindowEvent inE) {
            // Do nothing
        }

        @Override
        public void windowClosed(WindowEvent inE) {
            ObjectHelpViewer.this.hideHelp();
        }

        @Override
        public void windowIconified(WindowEvent inE) {
            // Do nothing
        }

        @Override
        public void windowDeiconified(WindowEvent inE) {
            // Do nothing
        }

        @Override
        public void windowActivated(WindowEvent inE) {
            // Do nothing
        }

        @Override
        public void windowDeactivated(WindowEvent inE) {
            // Do nothing
        }
    }
}
