package com.puttysoftware.diane.fileio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataReader implements AutoCloseable {
	// Fields
	private final DataMode dataMode;
	private BufferedReader br;
	private DataInputStream dis;
	private final File file;

	// Constructors
	public DataReader(final String filename) throws IOException {
		this.dataMode = DataMode.TEXT;
		this.br = new BufferedReader(new FileReader(filename));
		this.dis = null;
		this.file = new File(filename);
	}

	public DataReader(final File filename) throws IOException {
		this.dataMode = DataMode.TEXT;
		this.br = new BufferedReader(new FileReader(filename));
		this.dis = null;
		this.file = filename;
	}

	public DataReader(final String filename, final DataMode mode) throws IOException {
		this.dataMode = mode;
		if (mode != DataMode.BINARY) {
			this.br = new BufferedReader(new FileReader(filename));
		} else {
			this.dis = new DataInputStream(new FileInputStream(filename));
		}
		this.file = new File(filename);
	}

	public DataReader(final File filename, final DataMode mode) throws IOException {
		this.dataMode = mode;
		if (mode != DataMode.BINARY) {
			this.br = new BufferedReader(new FileReader(filename));
		} else {
			this.dis = new DataInputStream(new FileInputStream(filename));
		}
		this.file = filename;
	}

	protected DataReader(final InputStream stream, final DataMode mode) throws IOException {
		this.dataMode = mode;
		if (mode != DataMode.BINARY) {
			this.br = new BufferedReader(new InputStreamReader(stream));
		} else {
			this.dis = new DataInputStream(stream);
		}
		this.file = null;
	}

	// Methods
	public File getFile() {
		return this.file;
	}

	@Override
	public void close() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			this.br.close();
		} else {
			this.dis.close();
		}
	}

	public int readInt() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Integer.parseInt(this.br.readLine());
		} else {
			return this.dis.readInt();
		}
	}

	public float readFloat() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Float.parseFloat(this.br.readLine());
		} else {
			return this.dis.readFloat();
		}
	}

	public double readDouble() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Double.parseDouble(this.br.readLine());
		} else {
			return this.dis.readDouble();
		}
	}

	public long readLong() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Long.parseLong(this.br.readLine());
		} else {
			return this.dis.readLong();
		}
	}

	public byte readByte() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Byte.parseByte(this.br.readLine());
		} else {
			return this.dis.readByte();
		}
	}

	public boolean readBoolean() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Boolean.parseBoolean(this.br.readLine());
		} else {
			return this.dis.readBoolean();
		}
	}

	public short readShort() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return Short.parseShort(this.br.readLine());
		} else {
			return this.dis.readShort();
		}
	}

	public String readString() throws IOException {
		if (this.dataMode != DataMode.BINARY) {
			return this.br.readLine();
		} else {
			return this.dis.readUTF();
		}
	}
}
