package com.puttysoftware.picturepicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.puttysoftware.images.BufferedImageIcon;

public final class PicturePicker {
    /**
     * @version 1.0.0
     */
    // Fields
    private BufferedImageIcon[] choices;
    private String[] choiceNames;
    private JLabel[] choiceArray;
    private final JPanel pickerPanel;
    private final JPanel choicePanel;
    private final JPanel radioPanel;
    private final JPanel choiceRadioPanel;
    private final ButtonGroup radioGroup;
    private JRadioButton[] radioButtons;
    private final JScrollPane scrollPane;
    int index;
    private Color savedSPColor;
    private Color savedPCColor;
    private Color savedCCColor;
    private Color savedRCColor;
    private Color savedCRCColor;
    private Color savedCHColor;
    private final EventHandler handler;

    // Constructor
    public PicturePicker(final BufferedImageIcon[] pictures,
            final String[] names) {
        this.handler = new EventHandler();
        this.pickerPanel = new JPanel();
        this.pickerPanel.setLayout(new BorderLayout());
        this.choicePanel = new JPanel();
        this.radioPanel = new JPanel();
        this.radioGroup = new ButtonGroup();
        this.choiceRadioPanel = new JPanel();
        this.choiceRadioPanel.setLayout(new BorderLayout());
        this.choiceRadioPanel.add(this.radioPanel, BorderLayout.WEST);
        this.choiceRadioPanel.add(this.choicePanel,
                BorderLayout.CENTER);
        this.scrollPane = new JScrollPane(this.choiceRadioPanel);
        this.scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.pickerPanel.add(this.scrollPane, BorderLayout.CENTER);
        this.updatePicker(pictures, names);
        this.index = 0;
        this.savedSPColor = null;
        this.savedPCColor = null;
        this.savedCCColor = null;
        this.savedRCColor = null;
        this.savedCRCColor = null;
        this.savedCHColor = null;
    }

    public PicturePicker(final BufferedImageIcon[] pictures,
            final String[] names, final Color choiceColor) {
        this.handler = new EventHandler();
        this.pickerPanel = new JPanel();
        this.pickerPanel.setLayout(new BorderLayout());
        this.choicePanel = new JPanel();
        this.radioPanel = new JPanel();
        this.radioGroup = new ButtonGroup();
        this.choiceRadioPanel = new JPanel();
        this.choiceRadioPanel.setLayout(new BorderLayout());
        this.choiceRadioPanel.add(this.radioPanel, BorderLayout.WEST);
        this.choiceRadioPanel.add(this.choicePanel,
                BorderLayout.CENTER);
        this.scrollPane = new JScrollPane(this.choiceRadioPanel);
        this.scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.pickerPanel.add(this.scrollPane, BorderLayout.CENTER);
        this.updatePicker(pictures, names);
        this.index = 0;
        this.savedSPColor = this.scrollPane.getBackground();
        this.savedPCColor = this.pickerPanel.getBackground();
        this.savedCCColor = this.choicePanel.getBackground();
        this.savedRCColor = this.radioPanel.getBackground();
        this.savedCRCColor = this.choiceRadioPanel.getBackground();
        this.savedCHColor = choiceColor;
    }

    public PicturePicker(final BufferedImageIcon[] pictures,
            final String[] names, final boolean[] enabled) {
        this.handler = new EventHandler();
        this.pickerPanel = new JPanel();
        this.pickerPanel.setLayout(new BorderLayout());
        this.choicePanel = new JPanel();
        this.radioPanel = new JPanel();
        this.radioGroup = new ButtonGroup();
        this.choiceRadioPanel = new JPanel();
        this.choiceRadioPanel.setLayout(new BorderLayout());
        this.choiceRadioPanel.add(this.radioPanel, BorderLayout.WEST);
        this.choiceRadioPanel.add(this.choicePanel,
                BorderLayout.CENTER);
        this.scrollPane = new JScrollPane(this.choiceRadioPanel);
        this.scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.pickerPanel.add(this.scrollPane, BorderLayout.CENTER);
        this.updatePicker(pictures, names, enabled);
        this.index = 0;
        this.savedSPColor = null;
        this.savedPCColor = null;
        this.savedCCColor = null;
        this.savedRCColor = null;
        this.savedCRCColor = null;
        this.savedCHColor = null;
    }

    public PicturePicker(final BufferedImageIcon[] pictures,
            final String[] names, final boolean[] enabled,
            final Color choiceColor) {
        this.handler = new EventHandler();
        this.pickerPanel = new JPanel();
        this.pickerPanel.setLayout(new BorderLayout());
        this.choicePanel = new JPanel();
        this.radioPanel = new JPanel();
        this.radioGroup = new ButtonGroup();
        this.choiceRadioPanel = new JPanel();
        this.choiceRadioPanel.setLayout(new BorderLayout());
        this.choiceRadioPanel.add(this.radioPanel, BorderLayout.WEST);
        this.choiceRadioPanel.add(this.choicePanel,
                BorderLayout.CENTER);
        this.scrollPane = new JScrollPane(this.choiceRadioPanel);
        this.scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.pickerPanel.add(this.scrollPane, BorderLayout.CENTER);
        this.updatePicker(pictures, names, enabled);
        this.index = 0;
        this.savedSPColor = this.scrollPane.getBackground();
        this.savedPCColor = this.pickerPanel.getBackground();
        this.savedCCColor = this.choicePanel.getBackground();
        this.savedRCColor = this.radioPanel.getBackground();
        this.savedCRCColor = this.choiceRadioPanel.getBackground();
        this.savedCHColor = choiceColor;
    }

    // Methods
    public JPanel getPicker() {
        return this.pickerPanel;
    }

    public void changePickerColor(final Color c) {
        if (this.savedCHColor != null) {
            this.pickerPanel.setBackground(c);
            this.choicePanel.setBackground(c);
            this.radioPanel.setBackground(c);
            this.choiceRadioPanel.setBackground(c);
            this.scrollPane.setBackground(c);
            for (int x = 0; x < this.choiceArray.length; x++) {
                this.choiceArray[x].setBackground(c);
                this.radioButtons[x].setBackground(c);
            }
            // Update saved colors
            this.savedSPColor = c;
            this.savedPCColor = c;
            this.savedCCColor = c;
            this.savedRCColor = c;
            this.savedCRCColor = c;
            this.savedCHColor = c;
        }
    }

    public void disablePicker() {
        this.pickerPanel.setEnabled(false);
        if (this.savedCHColor != null) {
            this.pickerPanel.setBackground(Color.gray);
            this.choicePanel.setBackground(Color.gray);
            this.radioPanel.setBackground(Color.gray);
            this.choiceRadioPanel.setBackground(Color.gray);
            this.scrollPane.setBackground(Color.gray);
        }
        for (final JRadioButton radioButton : this.radioButtons) {
            radioButton.setEnabled(false);
        }
    }

    public void enablePicker() {
        this.pickerPanel.setEnabled(true);
        if (this.savedCHColor != null) {
            this.pickerPanel.setBackground(this.savedPCColor);
            this.choicePanel.setBackground(this.savedCCColor);
            this.radioPanel.setBackground(this.savedRCColor);
            this.choiceRadioPanel.setBackground(this.savedCRCColor);
            this.scrollPane.setBackground(this.savedSPColor);
        }
        for (final JRadioButton radioButton : this.radioButtons) {
            radioButton.setEnabled(true);
        }
    }

    public void updatePicker(final BufferedImageIcon[] newImages,
            final String[] newNames) {
        this.choices = newImages;
        this.choiceNames = newNames;
        this.choicePanel.removeAll();
        this.radioPanel.removeAll();
        this.radioButtons = new JRadioButton[this.choices.length];
        this.choicePanel.setLayout(new GridLayout(this.choices.length, 1));
        this.radioPanel.setLayout(new GridLayout(this.choices.length, 1));
        this.choiceArray = new JLabel[this.choices.length];
        for (int x = 0; x < this.choices.length; x++) {
            this.choiceArray[x] = new JLabel(this.choiceNames[x],
                    this.choices[x], SwingConstants.LEFT);
            this.choiceArray[x].setOpaque(true);
            if (this.savedCHColor != null) {
                this.choiceArray[x].setBackground(this.savedCHColor);
            }
            this.choicePanel.add(this.choiceArray[x]);
            this.radioButtons[x] = new JRadioButton();
            this.radioButtons[x].setOpaque(true);
            if (this.savedCHColor != null) {
                this.radioButtons[x].setBackground(this.savedCHColor);
            }
            this.radioButtons[x].setActionCommand(Integer.valueOf(x)
                    .toString());
            this.radioGroup.add(this.radioButtons[x]);
            this.radioButtons[x].addActionListener(this.handler);
            this.radioButtons[x].setEnabled(true);
            this.radioPanel.add(this.radioButtons[x]);
        }
        for (int x = 0; x < this.choices.length; x++) {
            this.radioButtons[x].setSelected(true);
            this.index = x;
        }
    }

    public void updatePicker(final BufferedImageIcon[] newImages,
            final String[] newNames, final boolean[] enabled) {
        this.choices = newImages;
        this.choiceNames = newNames;
        this.choicePanel.removeAll();
        this.radioPanel.removeAll();
        this.radioButtons = new JRadioButton[this.choices.length];
        this.choicePanel.setLayout(new GridLayout(this.choices.length, 1));
        this.radioPanel.setLayout(new GridLayout(this.choices.length, 1));
        this.choiceArray = new JLabel[this.choices.length];
        for (int x = 0; x < this.choices.length; x++) {
            this.choiceArray[x] = new JLabel(this.choiceNames[x],
                    this.choices[x], SwingConstants.LEFT);
            this.choiceArray[x].setOpaque(true);
            if (this.savedCHColor != null) {
                this.choiceArray[x].setBackground(this.savedCHColor);
            }
            this.choicePanel.add(this.choiceArray[x]);
            this.radioButtons[x] = new JRadioButton();
            this.radioButtons[x].setOpaque(true);
            if (this.savedCHColor != null) {
                this.radioButtons[x].setBackground(this.savedCHColor);
            }
            this.radioButtons[x].setActionCommand(Integer.valueOf(x)
                    .toString());
            this.radioGroup.add(this.radioButtons[x]);
            this.radioButtons[x].addActionListener(this.handler);
            this.radioButtons[x].setEnabled(enabled[x]);
            this.radioPanel.add(this.radioButtons[x]);
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
                .preferredLayoutSize(this.pickerPanel).width
                + this.scrollPane.getVerticalScrollBar().getWidth();
        final int newPreferredHeight = Math.min(maxHeight, this.pickerPanel
                .getLayout().preferredLayoutSize(this.pickerPanel).height);
        this.pickerPanel.setPreferredSize(new Dimension(newPreferredWidth,
                newPreferredHeight));
    }

    public void selectLastPickedChoice(final int lastPicked) {
        this.radioButtons[lastPicked].setSelected(true);
        this.index = lastPicked;
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
            PicturePicker.this.index = Integer.parseInt(cmd);
        }
    }
}
