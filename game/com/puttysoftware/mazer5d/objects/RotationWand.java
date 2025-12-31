/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import org.retropipes.diane.gui.dialog.CommonDialogs;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.mazer5d.objects.abc.GenericWand;

class RotationWand extends GenericWand {
    // Fields
    private static final boolean CLOCKWISE = true;
    private static final boolean COUNTERCLOCKWISE = false;

    // Constructors
    public RotationWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Rotation Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Rotation Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	this.useAction(null, x, y, z);
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().setRemoteAction(x, y, z);
	int r = 1;
	final String[] rChoices = new String[] { "1", "2", "3" };
	final String rres = CommonDialogs.showInputDialog("Rotation Radius:", Translations.load(Strings.PROGRAM_NAME),
		rChoices, rChoices[r - 1]);
	try {
	    r = Integer.parseInt(rres);
	} catch (final NumberFormatException nf) {
	    // Ignore
	}
	boolean d = RotationWand.CLOCKWISE;
	int di;
	if (d) {
	    di = 0;
	} else {
	    di = 1;
	}
	final String[] dChoices = new String[] { "Clockwise", "Counterclockwise" };
	final String dres = CommonDialogs.showInputDialog("Rotation Direction:",
		Translations.load(Strings.PROGRAM_NAME), dChoices, dChoices[di]);
	if (dres.equals(dChoices[0])) {
	    d = RotationWand.CLOCKWISE;
	} else {
	    d = RotationWand.COUNTERCLOCKWISE;
	}
	if (d) {
	    Mazer5D.getBagOStuff().getGameManager().doClockwiseRotate(r);
	} else {
	    Mazer5D.getBagOStuff().getGameManager().doCounterclockwiseRotate(r);
	}
	SoundPlayer.playSound(SoundIndex.CHANGE, SoundGroup.GAME);
    }

    @Override
    protected String getDescriptionHook() {
	return "Rotation Wands will rotate part of the maze. You can choose the area of effect and the direction.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ROTATION_WAND;
    }
}
