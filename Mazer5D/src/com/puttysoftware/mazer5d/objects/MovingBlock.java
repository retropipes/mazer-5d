package com.puttysoftware.mazer5d.objects;

import java.io.IOException;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;
import com.puttysoftware.mazer5d.objects.abc.GenericMovingObject;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.randomrange.RandomRange;

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
    public String getName() {
	return "Moving Block";
    }

    @Override
    public String getPluralName() {
	return "Moving Blocks";
    }

    @Override
    public String getDescription() {
	return "Moving Blocks move on their own. They cannot be pushed or pulled.";
    }

    @Override
    protected void writeMazeObjectHookXML(final MazeDataWriter writer) throws IOException {
	this.getSavedObject().writeMazeObjectXML(writer);
    }

    @Override
    protected MazeObject readMazeObjectHookXML(final MazeDataReader reader, final int formatVersion) throws IOException {
	this.setSavedObject(GameObjects.readObject(reader, formatVersion));
	return this;
    }

    @Override
    public MazeObjects getUniqueID() {
	return MazeObjects.MOVING_BLOCK;
    }
}
