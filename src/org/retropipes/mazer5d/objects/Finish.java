/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.abc.RandomGenerationRule;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.maze.Maze;
import org.retropipes.mazer5d.objects.abc.GenericTeleport;

class Finish extends GenericTeleport {
    // Constructors
    public Finish() {
	super(0, 0, 0);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	SoundPlayer.playSound(SoundIndex.FINISH, SoundGroup.GAME);
	app.getGameManager().solvedLevel();
    }

    @Override
    protected String getNameHook() {
	return "Finish";
    }

    @Override
    protected String getPluralNameHook() {
	return "Finishes";
    }

    @Override
    public boolean defersSetProperties() {
	return false;
    }

    @Override
    public void editorProbeHook() {
	Mazer5D.getBagOStuff().showMessage(this.getName());
    }

    @Override
    public MazeObject editorPropertiesHook() {
	return null;
    }

    @Override
    protected String getDescriptionHook() {
	return "Finishes lead to the next level, if one exists. Otherwise, entering one solves the maze.";
    }

    @Override
    public int getCustomFormat() {
	return 0;
    }

    // Random Generation Rules
    @Override
    public boolean isRequired() {
	return true;
    }

    @Override
    public int getMinimumRequiredQuantity(final Maze maze) {
	return 1;
    }

    @Override
    public int getMaximumRequiredQuantity(final Maze maze) {
	return RandomGenerationRule.NO_LIMIT;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.FINISH;
    }
}