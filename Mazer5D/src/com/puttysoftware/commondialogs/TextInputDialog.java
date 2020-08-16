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
import javax.swing.JTextField;

import com.puttysoftware.images.BufferedImageIcon;

class TextInputDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static TextInputDialog dialog;
    private static JTextField input;
    private static String value;

    /**
     * Set up and show the dialog. The first Component argument determines which
     * frame the dialog depends on; it should be a component in the dialog's
     * controlling frame. The second Component argument should be null if you
     * want the dialog to come up with its left corner in the center of the
     * screen; otherwise, it should be the component on top of which the dialog
     * should appear.
     */
    public static String showDialog(final String labelText, final String title,
            final BufferedImageIcon icon, final String initialValue) {
        final Frame frame = MainWindow.owner();
        TextInputDialog.dialog = new TextInputDialog(frame, frame, labelText,
                title, icon, initialValue);
        TextInputDialog.dialog.setVisible(true);
        return TextInputDialog.value;
    }

    private static void setValue(final String newValue) {
        TextInputDialog.value = newValue;
    }

    private TextInputDialog(final Frame frame, final Component locationComp,
            final String labelText, final String title,
            final BufferedImageIcon icon, final String initialValue) {
        super(frame, title, true);
        // Create and initialize the buttons.
        final JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        //
        final JButton setButton = new JButton("OK");
        setButton.setActionCommand("OK");
        setButton.addActionListener(this);
        this.getRootPane().setDefaultButton(setButton);
        // main part of the dialog
        TextInputDialog.input = new JTextField(initialValue);
        final JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        final JLabel label = new JLabel(icon);
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));
        listPane.add(input);
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Lay out the buttons from left to right.
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);
        // Put everything together, using the content pane's BorderLayout.
        final JPanel contentPane = new JPanel();
        contentPane.add(listPane, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
        // Initialize values.
        TextInputDialog.setValue(initialValue);
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(locationComp);
    }

    // Handle clicks on the Set and Cancel buttons.
    @Override
    public void actionPerformed(final ActionEvent e) {
        if ("OK".equals(e.getActionCommand())) {
            TextInputDialog.setValue(TextInputDialog.input.getText());
        } else if ("Cancel".equals(e.getActionCommand())) {
            TextInputDialog.setValue(null);
        }
        TextInputDialog.dialog.setVisible(false);
    }
}
