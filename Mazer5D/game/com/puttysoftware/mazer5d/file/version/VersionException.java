package com.puttysoftware.mazer5d.file.version;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class VersionException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 6414696022962587634L;

    VersionException(final String message) {
	super(message);
    }
    
    VersionException(final String message, final Exception cause) {
	super(message, cause);
    }

    @SuppressWarnings("static-method")
    private void writeObject(final ObjectOutputStream out) throws IOException {
	throw new NotSerializableException();
    }

    @SuppressWarnings("static-method")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
	throw new NotSerializableException();
    }
}
