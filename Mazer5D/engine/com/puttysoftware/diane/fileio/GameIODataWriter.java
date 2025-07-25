package com.puttysoftware.diane.fileio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class GameIODataWriter implements DataIOWriter {
	// Fields
	private final RandomAccessFile raf;
	private final File file;

	// Constructors
	public GameIODataWriter(final String filename) throws IOException {
		this.raf = new RandomAccessFile(filename, "rwd");
		this.file = new File(filename);
	}

	// Methods
	@Override
	public DataMode getDataIOMode() {
		return DataMode.GAME_IO;
	}

	@Override
	public File getFile() {
		return this.file;
	}

	@Override
	public void close() throws DataIOException {
		try {
			this.raf.close();
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeBoolean(final boolean value) throws DataIOException {
		try {
			this.raf.writeBoolean(value);
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeByte(final byte value) throws DataIOException {
		try {
			this.raf.writeByte(value);
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeDouble(final double value) throws DataIOException {
		try {
			this.raf.writeDouble(value);
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeInt(final int value) throws DataIOException {
		try {
			this.raf.writeInt(value);
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeLong(final long value) throws DataIOException {
		try {
			this.raf.writeLong(value);
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeString(final String value) throws DataIOException {
		try {
			this.raf.writeUTF(value);
		} catch (IOException e) {
			throw new DataIOException(e);
		}
	}

	@Override
	public void writeUnsignedByte(final int value) throws DataIOException {
		this.writeInt(value);
	}
}
