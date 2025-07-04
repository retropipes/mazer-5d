/*  Diane Game Engine
Copyleft (C) 2019 Eric Ahnell

Any questions should be directed to the author via email at: support@puttysoftware.com
 */
package com.puttysoftware.diane.utilties;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	private Hash() {
		// Do nothing
	}

	public static byte[] hash(final byte[] input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512"); //$NON-NLS-1$
		} catch (final NoSuchAlgorithmException nsae) {
			return null;
		}
		return md.digest(input);
	}

	public static byte[] hash(final byte[] input, final String algorithm) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException nsae) {
			return null;
		}
		return md.digest(input);
	}
}
