package org.retropipes.mazer5d.objects;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;
import org.retropipes.diane.random.RandomRange;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.file.version.MazeVersion;
import org.retropipes.mazer5d.objects.abc.GenericMovingObject;

class MovingBlock extends GenericMovingObject implements Cloneable {
    // Constructors
    public MovingBlock() {
	super(true);
	this.setSavedObject(GameObjects.getEmptySpace());
	final RandomRange t = new RandomRange(1, 2);
	this.activateTimer(t.generate());
    }

    @Override
    public void timerExpiredAction(final int dirX, final int dirY) {
	// Move the block
	final RandomRange r = new RandomRange(0, 7);
	final int move = r.generate();
	Mazer5D.getBagOStuff().getMazeManager().getMaze().updateMovingBlockPosition(move, dirX, dirY, this);
	final RandomRange t = new RandomRange(1, 2);
	this.activateTimer(t.generate());
    }

    @Override
    protected String getNameHook() {
	return "Moving Block";
    }

    @Override
    protected String getPluralNameHook() {
	return "Moving Blocks";
    }

    @Override
    protected String getDescriptionHook() {
	return "Moving Blocks move on their own. They cannot be pushed or pulled.";
    }

    @Override
    protected void writeMazeObjectHook(final XDataWriter writer) throws IOException {
	this.getSavedObject().writeMazeObject(writer);
    }

    @Override
    protected MazeObject readMazeObjectHook(final XDataReader reader, final MazeVersion formatVersion)
	    throws IOException {
	this.setSavedObject(GameObjects.readObject(reader, formatVersion));
	return this;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.MOVING_BLOCK;
    }
}
