/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.maze;

import java.io.IOException;

import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.Layers;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;

class SavedState {
	// Properties
	private final int r, c, f;
	private final MazeStorage saveData;

	// Constructors
	public SavedState(final int rows, final int cols, final int floors) {
		this.saveData = new MazeStorage(cols, rows, floors, Layers.COUNT);
		this.c = cols;
		this.r = rows;
		this.f = floors;
	}

	public MazeObject getDataCell(final int x, final int y, final int z, final int e) {
		return this.saveData.getMazeCell(x, y, z, e);
	}

	public void setDataCell(final MazeObject newData, final int x, final int y, final int z, final int e) {
		this.saveData.setCell(newData, x, y, z, e);
	}

	public void writeSavedTowerState(final MazeDataWriter writer) throws IOException {
		int x, y, z, e;
		writer.writeInt(this.c);
		writer.writeInt(this.r);
		writer.writeInt(this.f);
		for (x = 0; x < this.c; x++) {
			for (y = 0; y < this.r; y++) {
				for (z = 0; z < this.f; z++) {
					for (e = 0; e < Layers.COUNT; e++) {
						this.saveData.getMazeCell(x, y, z, e).writeMazeObject(writer);
					}
				}
			}
		}
	}

	public static SavedState readSavedState(final MazeDataReader reader, final MazeVersion formatVersion)
			throws IOException {
		int x, y, z, e, sizeX, sizeY, sizeZ;
		sizeX = reader.readInt();
		sizeY = reader.readInt();
		sizeZ = reader.readInt();
		final SavedState sts = new SavedState(sizeY, sizeX, sizeZ);
		for (x = 0; x < sts.c; x++) {
			for (y = 0; y < sts.r; y++) {
				for (z = 0; z < sts.f; z++) {
					for (e = 0; e < Layers.COUNT; e++) {
						sts.saveData.setCell(GameObjects.readObject(reader, formatVersion), x, y, z, e);
					}
				}
			}
		}
		return sts;
	}
}
