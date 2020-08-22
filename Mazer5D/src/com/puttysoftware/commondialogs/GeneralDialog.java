package com.puttysoftware.commondialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puttysoftware.images.BufferedImageIcon;

class GeneralDialog {
    private static MainWindow dialogFrame;
    private static MainWindowContent dialogPane;

    /**
     * Set up and show the dialog.
     */
    public static void showDialog(final String labelText, final String title,
            final BufferedImageIcon icon) {
        // Create and initialize the dialog.
        dialogFrame = MainWindow.getMainWindow();
        dialogPane = dialogFrame.createContent();
        // Create and initialize the buttons.
        final JButton setButton = new JButton("OK");
        setButton.setActionCommand("OK");
        setButton.addActionListener(h -> dialogFrame.restoreSaved());
        // main part of the dialog
        final JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        final JLabel label = new JLabel(icon);
        listPane.add(label);
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Lay out the buttons from left to right.
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);
        // Put everything together, using the content pane's BorderLayout.
        dialogPane.add(listPane, BorderLayout.NORTH);
        dialogPane.add(buttonPane, BorderLayout.PAGE_END);
        dialogFrame.attachAndSave(dialogPane);
    }
}
