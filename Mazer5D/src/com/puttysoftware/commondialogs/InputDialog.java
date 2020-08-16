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

class InputDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    static String[] descs;
    private static InputDialog dialog;
    private static int value = CommonDialogs.DEFAULT_OPTION;

    /**
     * Set up and show the dialog. The first Component argument determines which
     * frame the dialog depends on; it should be a component in the dialog's
     * controlling frame. The second Component argument should be null if you
     * want the dialog to come up with its left corner in the center of the
     * screen; otherwise, it should be the component on top of which the dialog
     * should appear.
     */
    public static int showConfirmDialog(final String labelText,
            final String title, final BufferedImageIcon icon) {
        InputDialog.value = CommonDialogs.CANCEL;
        final Frame frame = MainWindow.owner();
        final String[] possibleValues = new String[] { "Yes", "No" };
        InputDialog.dialog = new InputDialog(frame, frame, labelText, title,
                icon, possibleValues);
        InputDialog.dialog.setVisible(true);
        return InputDialog.value;
    }

    public static int showYNCConfirmDialog(final String labelText,
            final String title, final BufferedImageIcon icon) {
        InputDialog.value = CommonDialogs.CANCEL;
        final Frame frame = MainWindow.owner();
        final String[] possibleValues = new String[] { "Yes", "No", "Cancel" };
        InputDialog.dialog = new InputDialog(frame, frame, labelText, title,
                icon, possibleValues);
        InputDialog.dialog.setVisible(true);
        return InputDialog.value;
    }

    public static int showDialog(final String labelText, final String title,
            final BufferedImageIcon icon, final String[] possibleValues) {
        InputDialog.value = CommonDialogs.CANCEL;
        final Frame frame = MainWindow.owner();
        InputDialog.dialog = new InputDialog(frame, frame, labelText, title,
                icon, possibleValues);
        InputDialog.dialog.setVisible(true);
        return InputDialog.value;
    }

    private static void setValue(final int newValue) {
        InputDialog.value = newValue;
    }

    private InputDialog(final Frame frame, final Component locationComp,
            final String labelText, final String title,
            final BufferedImageIcon icon, final String[] data) {
        super(frame, title, true);
        // main part of the dialog
        final JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        final JLabel label = new JLabel(labelText);
        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0, 5)));
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Lay out the buttons from left to right.
        final JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        // Create and initialize the buttons.
        for (int i = 0; i < data.length; i++) {
            final JButton button = new JButton(data[i]);
            button.setActionCommand(Integer.toString(i));
            button.addActionListener(this);
            buttonPane.add(button);
            if (i != data.length - 1) {
                buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            }
        }
        // Put everything together, using the content pane's BorderLayout.
        final JPanel contentPane = new JPanel();
        contentPane.add(listPane, BorderLayout.NORTH);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(locationComp);
    }

    // Handle clicks on the Set and Cancel buttons.
    @Override
    public void actionPerformed(final ActionEvent e) {
        InputDialog.setValue(Integer.parseInt(e.getActionCommand()));
        InputDialog.dialog.setVisible(false);
    }
}
