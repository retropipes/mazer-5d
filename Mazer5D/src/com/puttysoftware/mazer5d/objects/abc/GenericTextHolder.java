/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import java.io.IOException;

import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.commondialogs.CommonDialogs;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public abstract class GenericTextHolder extends MazeObject {
    // Constructors
    protected GenericTextHolder() {
	super(true);
	this.addOneCustomText();
	this.setSignText("Empty");
	this.setType(TypeConstants.TYPE_TEXT_HOLDER);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	// Do nothing
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	CommonDialogs.showDialog(this.getSignText());
    }

    @Override
    public abstract String getName();

    @Override
    protected int getLayerHook() {
	return Layers.OBJECT;
    }

    @Override
    public MazeObject editorPropertiesHook() {
	this.setSignText(CommonDialogs.showTextInputDialogWithDefault("Set Text for " + this.getName(), "Editor",
		this.getSignText()));
	return this;
    }

    @Override
    protected MazeObject readMazeObjectHookXML(final MazeDataReader reader, final int formatVersion)
	    throws IOException {
	this.setSignText(reader.readString());
	return this;
    }

    @Override
    protected void writeMazeObjectHookXML(final MazeDataWriter writer) throws IOException {
	writer.writeString(this.getSignText());
    }
}