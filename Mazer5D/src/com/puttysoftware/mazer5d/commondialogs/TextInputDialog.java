package com.puttysoftware.mazer5d.commondialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.puttysoftware.images.BufferedImageIcon;

class TextInputDialog {
    private static MainWindow dialogFrame;
    private static MainWindowContent dialogPane;
    private static JTextField input;
    private static CompletableFuture<String> completer = new CompletableFuture<>();

    /**
     * Set up and show the dialog. The first Component argument determines which
     * frame the dialog depends on; it should be a component in the dialog's
     * controlling frame. The second Component argument should be null if you
     * want the dialog to come up with its left corner in the center of the
     * screen; otherwise, it should be the component on top of which the dialog
     * should appear.
     */
    public static Future<String> showDialog(final String labelText,
            final String title, final BufferedImageIcon icon,
            final String initialValue) {
        Executors.newSingleThreadExecutor().submit(() -> {
            // Create and initialize the dialog.
            dialogFrame = MainWindow.getMainWindow();
            dialogPane = dialogFrame.createContent();
            // Create and initialize the buttons.
            final JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(h -> {
                TextInputDialog.setValue(null);
                dialogFrame.restoreSaved();
            });
            final JButton setButton = new JButton("OK");
            setButton.setActionCommand("OK");
            setButton.addActionListener(h -> {
                TextInputDialog.setValue(TextInputDialog.input.getText());
                dialogFrame.restoreSaved();
            });
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
            buttonPane
                    .setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
            buttonPane
                    .setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            buttonPane.add(Box.createHorizontalGlue());
            buttonPane.add(cancelButton);
            buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPane.add(setButton);
            // Put everything together, using the content pane's BorderLayout.
            dialogPane.add(listPane, BorderLayout.NORTH);
            dialogPane.add(buttonPane, BorderLayout.PAGE_END);
            // Initialize values.
            TextInputDialog.setValue(initialValue);
            dialogFrame.attachAndSave(dialogPane);
        });
        return completer;
    }

    private static void setValue(final String newValue) {
        completer.complete(newValue);
    }
}
