package com.puttysoftware.commondialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puttysoftware.images.BufferedImageIcon;

class GeneralDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static GeneralDialog dialog;

    /**
     * Set up and show the dialog. The first Component argument determines which
     * frame the dialog depends on; it should be a component in the dialog's
     * controlling frame. The second Component argument should be null if you
     * want the dialog to come up with its left corner in the center of the
     * screen; otherwise, it should be the component on top of which the dialog
     * should appear.
     */
    public static void showDialog(final String labelText, final String title,
            final BufferedImageIcon icon) {
        final Frame frame = MainWindow.owner();
        GeneralDialog.dialog = new GeneralDialog(frame, frame, labelText, title,
                icon);
        GeneralDialog.dialog.setVisible(true);
    }

    private GeneralDialog(final Frame frame, final Component locationComp,
            final String labelText, final String title,
            final BufferedImageIcon icon) {
        super(frame, title, true);
        // Create and initialize the buttons.
        final JButton setButton = new JButton("OK");
        setButton.setActionCommand("OK");
        setButton.addActionListener(this);
        this.getRootPane().setDefaultButton(setButton);
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
        final JPanel contentPane = new JPanel();
        contentPane.add(listPane, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
        this.setContentPane(contentPane);
        this.setLocationRelativeTo(locationComp);
    }

    // Handle clicks on the OK button.
    @Override
    public void actionPerformed(final ActionEvent e) {
        GeneralDialog.dialog.setVisible(false);
    }
}
