package com.puttysoftware.mazer5d.help;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public final class HTMLHelpViewer {
    // Fields
    private JEditorPane helpContents;
    private final JPanel helpPanel;
    private final JScrollPane scrollPane;

    // Constructor
    public HTMLHelpViewer(final URL helpPage) {
        this.helpPanel = new JPanel();
        this.helpPanel.setLayout(new FlowLayout());
        try {
            this.helpContents = new JEditorPane(helpPage);
        } catch (final Exception e) {
            this.helpContents = new JEditorPane("text/plain", //$NON-NLS-1$
                    "An error occurred while fetching the help contents."); //$NON-NLS-1$
        }
        this.helpContents.setEditable(false);
        this.scrollPane = new JScrollPane(this.helpContents);
        this.helpPanel.add(this.scrollPane);
    }

    // Methods
    public JPanel getHelp() {
        return this.helpPanel;
    }

    public void setHelpSize(final int horz, final int vert) {
        this.helpContents.setPreferredSize(new Dimension(horz, vert));
        this.scrollPane.setPreferredSize(new Dimension(horz, vert));
    }
}
