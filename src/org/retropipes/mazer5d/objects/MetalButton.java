/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.abc.TypeConstants;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericField;

class MetalButton extends GenericField {
    // Fields
    private final int targetRow;
    private final int targetCol;
    private final int targetFloor;

    // Constructors
    public MetalButton() {
	super(false, new MetalBoots());
	this.targetRow = 0;
	this.targetCol = 0;
	this.targetFloor = 0;
	this.setType(TypeConstants.TYPE_BUTTON);
	this.setType(TypeConstants.TYPE_FIELD);
	this.setType(TypeConstants.TYPE_UNLOCKED_KEEP_KEY);
	this.setType(TypeConstants.TYPE_LOCK);
    }

    public MetalButton(final int tRow, final int tCol, final int tFloor) {
	super(false, new MetalBoots());
	this.targetRow = tRow;
	this.targetCol = tCol;
	this.targetFloor = tFloor;
	this.setType(TypeConstants.TYPE_BUTTON);
	this.setType(TypeConstants.TYPE_FIELD);
	this.setType(TypeConstants.TYPE_UNLOCKED_KEEP_KEY);
	this.setType(TypeConstants.TYPE_LOCK);
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (this.getClass() != obj.getClass()) {
	    return false;
	}
	final MetalButton other = (MetalButton) obj;
	if (this.targetRow != other.targetRow) {
	    return false;
	}
	if (this.targetCol != other.targetCol) {
	    return false;
	}
	if (this.targetFloor != other.targetFloor) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 79 * hash + this.targetRow;
	hash = 79 * hash + this.targetCol;
	hash = 79 * hash + this.targetFloor;
	return hash;
    }

    public int getTargetRow() {
	return this.targetRow;
    }

    public int getTargetColumn() {
	return this.targetCol;
    }

    public int getTargetFloor() {
	return this.targetFloor;
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	if (inv.isItemThere(this.getKey().getUniqueID())) {
	    final BagOStuff app = Mazer5D.getBagOStuff();
	    final MazeObject there = app.getMazeManager().getMazeObject(this.getTargetRow(), this.getTargetColumn(),
		    this.getTargetFloor(), this.getLayerHook());
	    if (there != null) {
		if (there.getName().equals(new MetalDoor().getName())) {
		    app.getGameManager().morph(GameObjects.getEmptySpace(), this.getTargetRow(), this.getTargetColumn(),
			    this.getTargetFloor());
		} else {
		    app.getGameManager().morph(new MetalDoor(), this.getTargetRow(), this.getTargetColumn(),
			    this.getTargetFloor());
		}
	    }
	    SoundPlayer.playSound(SoundIndex.BUTTON, SoundGroup.GAME);
	} else {
	    SoundPlayer.playSound(SoundIndex.WALK, SoundGroup.GAME);
	}
    }

    @Override
    protected String getNameHook() {
	return "Metal Button";
    }

    @Override
    protected String getPluralNameHook() {
	return "Metal Buttons";
    }

    @Override
    public boolean isConditionallySolid(final ObjectInventory inv) {
	return this.isSolid();
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
	    final ObjectInventory inv) {
	// Handle passwall boots and ghost amulet
	if (inv.isItemThere(MazeObjects.PASSWALL_BOOTS) || inv.isItemThere(MazeObjects.GHOST_AMULET)) {
	    return false;
	} else {
	    return this.isDirectionallySolid(ie, dirX, dirY);
	}
    }

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }

    @Override
    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName() + ": Target (" + (this.targetCol + 1) + ","
		+ (this.targetRow + 1) + "," + (this.targetFloor + 1) + ")");
    }

    @Override
    public MazeObject editorPropertiesHook() {
	return Mazer5D.getBagOStuff().getEditor().editMetalButtonTarget();
    }

    @Override
    protected String getDescriptionHook() {
	return "Metal Buttons will not trigger without Metal Boots.";
    }

    @Override
    public boolean defersSetProperties() {
	return true;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.METAL_BUTTON;
    }
}
