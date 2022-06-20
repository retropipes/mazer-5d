/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTeleport;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class StairsDown extends GenericTeleport {
    // Constructors
    public StairsDown() {
	super(0, 0, 0);
    }

    // For derived classes only
    protected StairsDown(final boolean doesAcceptPushInto) {
	super(doesAcceptPushInto);
    }

    @Override
    protected String getNameHook() {
	return "Stairs Down";
    }

    @Override
    protected String getPluralNameHook() {
	return "Sets of Stairs Down";
    }

    @Override
    public int getDestinationRow() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	return app.getGameManager().getPlayerManager().getPlayerLocationX();
    }

    @Override
    public int getDestinationColumn() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	return app.getGameManager().getPlayerManager().getPlayerLocationY();
    }

    @Override
    public int getDestinationFloor() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	return app.getGameManager().getPlayerManager().getPlayerLocationZ() - 1;
    }

    @Override
    public int getDestinationLevel() {
	final BagOStuff app = Mazer5D.getBagOStuff();
	return app.getGameManager().getPlayerManager().getPlayerLocationW();
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor(), this.getDestinationLevel());
	SoundPlayer.playSound(SoundIndex.DOWN, SoundGroup.GAME);
    }

    @Override
    public void editorPlaceHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	me.pairStairs(MazeEditor.STAIRS_DOWN);
    }

    @Override
    public MazeObject editorPropertiesHook() {
	return null;
    }

    @Override
    protected String getDescriptionHook() {
	return "Stairs Down lead to the floor below.";
    }

    @Override
    public int getCustomFormat() {
	return 0;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.STAIRS_DOWN;
    }
}
