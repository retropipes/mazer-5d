/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import java.util.Random;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericRandomTeleport extends MazeObject {
    // Fields
    private final Random generator;

    // Constructors
    public GenericRandomTeleport(final int newRandomRangeY, final int newRandomRangeX) {
	super(false);
	this.addCustomCounters(this.getCustomFormat());
	this.setRandomRangeX(newRandomRangeX);
	this.setRandomRangeY(newRandomRangeY);
	this.generator = new Random();
	this.setType(TypeConstants.TYPE_RANDOM);
    }

    // Methods
    @Override
    public int getDestinationRow() {
	if (this.getRandomRangeY() == 0) {
	    return 0;
	} else {
	    int sign = this.generator.nextInt(2);
	    final int value = this.generator.nextInt(this.getRandomRangeY() + 1);
	    if (sign == 0) {
		sign = -1;
	    }
	    return sign * value;
	}
    }

    @Override
    public int getDestinationColumn() {
	if (this.getRandomRangeX() == 0) {
	    return 0;
	} else {
	    int sign = this.generator.nextInt(2);
	    final int value = this.generator.nextInt(this.getRandomRangeX() + 1);
	    if (sign == 0) {
		sign = -1;
	    }
	    return sign * value;
	}
    }

    @Override
    public int getLayer() {
	return Layers.OBJECT;
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int dr, dc;
	do {
	    dr = this.getDestinationRow();
	    dc = this.getDestinationColumn();
	} while (!app.getGameManager().tryUpdatePositionRelative(dr, dc));
	app.getGameManager().updatePositionRelative(dr, dc);
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(
		this.getName() + ": Row Radius " + getRandomRangeY() + ", Column Radius " + getRandomRangeX());
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_RANDOM);
	return mo;
    }

    @Override
    public int getCustomFormat() {
	return 2;
    }
}
