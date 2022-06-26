/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericConditionalTeleport extends GenericTeleport {
    // Fields
    public static final int TRIGGER_SUN = 1;
    public static final int TRIGGER_MOON = 2;

    // Constructors
    protected GenericConditionalTeleport() {
	super();
	this.addCustomCounters(this.getCustomFormat());
	this.setSunMoon(GenericConditionalTeleport.TRIGGER_SUN);
	this.setType(TypeConstants.TYPE_TELEPORT);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	int testVal;
	if (this.getSunMoon() == GenericConditionalTeleport.TRIGGER_SUN) {
	    testVal = inv.getItemCount(MazeObjects.SUN_STONE);
	} else if (this.getSunMoon() == GenericConditionalTeleport.TRIGGER_MOON) {
	    testVal = inv.getItemCount(MazeObjects.MOON_STONE);
	} else {
	    testVal = 0;
	}
	if (testVal >= this.getTriggerValue()) {
	    app.getGameManager().updatePositionAbsolute(this.getDestinationRow2(), this.getDestinationColumn2(),
		    this.getDestinationFloor2());
	} else {
	    app.getGameManager().updatePositionAbsolute(this.getDestinationRow(), this.getDestinationColumn(),
		    this.getDestinationFloor());
	}
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
	this.postMoveActionHook();
    }

    public void postMoveActionHook() {
	// Do nothing
    }

    @Override
    protected abstract String getNameHook();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }

    @Override
    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName() + ": Trigger Value " + this.getTriggerValue());
    }

    @Override
    public final MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	me.editConditionalTeleportDestination(this);
	return this;
    }

    @Override
    public boolean defersSetProperties() {
	return true;
    }

    @Override
    public int getCustomFormat() {
	if (Mazer5D.getBagOStuff().getMazeManager().isMaze4Compatible()) {
	    return 7;
	} else {
	    return 8;
	}
    }
}
