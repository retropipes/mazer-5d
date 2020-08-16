package com.puttysoftware.picturepicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.puttysoftware.images.BufferedImageIcon;

public final class StackedPicturePicker {
    /**
     * @version 2.0.0
     */
    // Fields
    private BufferedImageIcon[] choices;
    private JLabel[] choiceArray;
    private final JPanel pickerPanel;
    private final ButtonGroup radioGroup;
    private JRadioButton[] radioButtons;
    int index;
    private Color savedCRCColor;
    private Color savedCHColor;
    private final EventHandler handler;
    private final int stackCount;
    private final int imageSize;

    // Constructor
    public StackedPicturePicker(final BufferedImageIcon[] pictures,
            final boolean[] enabled, final Color choiceColor,
            final int newStackCount, final int placeholderSize) {
        this.imageSize = placeholderSize;
        this.savedCHColor = choiceColor;
        this.stackCount = newStackCount;
        this.handler = new EventHandler();
        this.radioGroup = new ButtonGroup();
        this.pickerPanel = new JPanel();
        this.updatePicker(pictures, enabled);
        this.index = 0;
        this.savedCRCColor = this.pickerPanel.getBackground();
    }

    // Methods
    public JPanel getPicker() {
        return this.pickerPanel;
    }

    public void changePickerColor(final Color c) {
        this.pickerPanel.setBackground(c);
        for (int x = 0; x < this.choiceArray.length; x++) {
            this.choiceArray[x].setBackground(c);
            this.radioButtons[x].setBackground(c);
        }
        // Update saved colors
        this.savedCRCColor = c;
        this.savedCHColor = c;
    }

    public void disablePicker() {
        this.pickerPanel.setEnabled(false);
        this.pickerPanel.setBackground(Color.gray);
        for (final JRadioButton radioButton : this.radioButtons) {
            radioButton.setEnabled(false);
        }
    }

    public void enablePicker() {
        this.pickerPanel.setEnabled(true);
        this.pickerPanel.setBackground(this.savedCRCColor);
        for (final JRadioButton radioButton : this.radioButtons) {
            radioButton.setEnabled(true);
        }
    }

    public void updatePicker(final BufferedImageIcon[] newImages,
            final boolean[] enabled) {
        this.choices = newImages;
        this.pickerPanel.removeAll();
        int rows = this.choices.length / (this.stackCount / 2);
        final int extra = this.choices.length % (this.stackCount / 2);
        if (extra != 0) {
            rows += 2;
        }
        this.radioButtons = new JRadioButton[this.choices.length];
        this.choiceArray = new JLabel[this.choices.length];
        this.pickerPanel.setLayout(new GridLayout(0, this.stackCount));
        int picCounter = 0;
        int radioCounter = 0;
        int rowCounter = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < this.stackCount; y++) {
                if (picCounter < this.choices.length) {
                    this.choiceArray[picCounter] = new JLabel("", //$NON-NLS-1$
                            this.choices[picCounter], SwingConstants.LEFT);
                    this.choiceArray[picCounter].setOpaque(true);
                    this.choiceArray[picCounter].setBackground(
                            this.savedCHColor);
                    this.pickerPanel.add(this.choiceArray[picCounter]);
                } else if (rowCounter == rows - 2) {
                    // Add spacer
                    final JLabel spacer = new JLabel("", //$NON-NLS-1$
                            new BufferedImageIcon(this.imageSize,
                                    this.savedCHColor), SwingConstants.LEFT);
                    this.pickerPanel.add(spacer);
                }
                picCounter++;
            }
            rowCounter++;
            for (int y = 0; y < this.stackCount; y++) {
                if (radioCounter < this.choices.length) {
                    this.radioButtons[radioCounter] = new JRadioButton();
                    this.radioButtons[radioCounter].setHorizontalAlignment(
                            SwingConstants.CENTER);
                    this.radioButtons[radioCounter].setOpaque(true);
                    this.radioButtons[radioCounter].setBackground(
                            this.savedCHColor);
                    this.radioButtons[radioCounter].setActionCommand(Integer
                            .valueOf(radioCounter).toString());
                    this.radioGroup.add(this.radioButtons[radioCounter]);
                    this.radioButtons[radioCounter].addActionListener(
                            this.handler);
                    this.radioButtons[x].setEnabled(enabled[x]);
                    this.pickerPanel.add(this.radioButtons[radioCounter]);
                } else if (rowCounter == rows - 1) {
                    // Add spacer
                    final JLabel spacer = new JLabel("", //$NON-NLS-1$
                            new BufferedImageIcon(this.imageSize,
                                    this.savedCHColor), SwingConstants.LEFT);
                    this.pickerPanel.add(spacer);
                }
                radioCounter++;
            }
            rowCounter++;
        }
        for (int x = 0; x < this.choices.length; x++) {
            if (enabled[x]) {
                this.radioButtons[x].setSelected(true);
                this.index = x;
                break;
            }
        }
    }

    public void updatePickerLayout(final int maxHeight) {
        final int newPreferredWidth = this.pickerPanel.getLayout()
                .preferredLayoutSize(this.pickerPanel).width;
        final int newPreferredHeight = Math.min(maxHeight, this.pickerPanel
                .getLayout().preferredLayoutSize(this.pickerPanel).height);
        this.pickerPanel.setPreferredSize(new Dimension(newPreferredWidth,
                newPreferredHeight));
    }

    public void selectLastPickedChoice(final int lastPicked) {
        this.radioButtons[lastPicked].setSelected(true);
    }

    /**
     *
     * @return the index of the picture picked
     */
    public int getPicked() {
        return this.index;
    }

    private class EventHandler implements ActionListener {
        public EventHandler() {
            // Do nothing
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            final String cmd = e.getActionCommand();
            // A radio button
            StackedPicturePicker.this.index = Integer.parseInt(cmd);
        }
    }
}