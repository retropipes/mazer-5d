/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.game;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.puttysoftware.diane.asset.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.EffectImageIndex;
import com.puttysoftware.mazer5d.loaders.EffectImageLoader;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.maze.Maze;

class StatGUI {
    // Fields
    private JPanel statsPane;
    private JLabel hpLabel;
    private JLabel poisonLabel;
    private JLabel timeLabel;

    // Constructors
    public StatGUI() {
	this.setUpGUI();
    }

    // Methods
    public JPanel getStatsPane() {
	return this.statsPane;
    }

    public void updateStats() {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	this.hpLabel.setText(m.getHPString());
	this.poisonLabel.setText(m.getPoisonString());
	this.timeLabel.setText(m.getTimeString());
    }

    private void setUpGUI() {
	this.statsPane = new JPanel();
	this.statsPane.setLayout(new GridLayout(3, 1));
	final BufferedImageIcon hpImage = EffectImageLoader.load(EffectImageIndex.HEALTH);
	this.hpLabel = new JLabel(StaticStrings.EMPTY, hpImage, SwingConstants.LEFT);
	this.statsPane.add(this.hpLabel);
	final BufferedImageIcon poisonImage = EffectImageLoader.load(EffectImageIndex.POISON);
	this.poisonLabel = new JLabel(StaticStrings.EMPTY, poisonImage, SwingConstants.LEFT);
	this.statsPane.add(this.poisonLabel);
	final BufferedImageIcon timeImage = EffectImageLoader.load(EffectImageIndex.TIME_19);
	this.timeLabel = new JLabel(StaticStrings.EMPTY, timeImage, SwingConstants.LEFT);
	this.statsPane.add(this.timeLabel);
    }

    public void updateImages() {
	final BufferedImageIcon hpImage = EffectImageLoader.load(EffectImageIndex.HEALTH);
	this.hpLabel.setIcon(hpImage);
	final BufferedImageIcon poisonImage = EffectImageLoader.load(EffectImageIndex.POISON);
	this.poisonLabel.setIcon(poisonImage);
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	final BufferedImageIcon timeImage = EffectImageLoader.loadTime(m.getTimerValue(), m.getInitialTimerValue());
	this.timeLabel.setIcon(timeImage);
    }
}
