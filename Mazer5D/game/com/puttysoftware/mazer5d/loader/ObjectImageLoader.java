/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.loader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.puttysoftware.diane.image.BufferedImageIcon;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.file.FileExtensions;

public class ObjectImageLoader {
	private static final String DEFAULT_LOAD_PATH = "/asset/image/object/";
	private static String LOAD_PATH = ObjectImageLoader.DEFAULT_LOAD_PATH;
	private static Class<?> LOAD_CLASS = ObjectImageLoader.class;
	private static final Color TRANSPARENT = new Color(200, 100, 100);
	private static Color REPLACE = new Color(223, 223, 223);

	public static BufferedImageIcon getTransformedImage(final MazeObject obj, final boolean game) {
		try {
			final BufferedImageIcon icon = ObjectImageCache.getCachedObjectImage(obj, game);
			final BufferedImageIcon result = new BufferedImageIcon(icon);
			if (icon != null) {
				for (int x = 0; x < ImageConstants.SIZE; x++) {
					for (int y = 0; y < ImageConstants.SIZE; y++) {
						final int pixel = icon.getRGB(x, y);
						final Color c = new Color(pixel);
						if (c.equals(ObjectImageLoader.TRANSPARENT)) {
							result.setRGB(x, y, ObjectImageLoader.REPLACE.getRGB());
						}
					}
				}
				return result;
			} else {
				return null;
			}
		} catch (final NullPointerException np) {
			return null;
		} catch (final IllegalArgumentException ia) {
			return null;
		}
	}

	public static BufferedImageIcon getCompositeImage(final MazeObject obj1, final MazeObject obj2,
			final boolean game) {
		try {
			final BufferedImageIcon icon1 = ObjectImageCache.getCachedObjectImage(obj1, game);
			final BufferedImageIcon icon2 = ObjectImageCache.getCachedObjectImage(obj2, game);
			final BufferedImageIcon result = new BufferedImageIcon(icon2);
			if (icon1 != null && icon2 != null) {
				for (int x = 0; x < ImageConstants.SIZE; x++) {
					for (int y = 0; y < ImageConstants.SIZE; y++) {
						final int pixel = icon2.getRGB(x, y);
						final Color c = new Color(pixel);
						if (c.equals(ObjectImageLoader.TRANSPARENT)) {
							result.setRGB(x, y, icon1.getRGB(x, y));
						}
					}
				}
				return result;
			} else {
				return null;
			}
		} catch (final NullPointerException np) {
			return null;
		} catch (final IllegalArgumentException ia) {
			return null;
		}
	}

	public static BufferedImageIcon getVirtualCompositeImage(final MazeObject obj1, final MazeObject obj2,
			final MazeObject obj3, final boolean game) {
		try {
			final BufferedImageIcon icon3 = ObjectImageCache.getCachedObjectImage(obj3, game);
			final BufferedImageIcon icon2 = ObjectImageLoader.getCompositeImage(obj1, obj2, game);
			final BufferedImageIcon result = new BufferedImageIcon(icon3);
			if (icon3 != null && icon2 != null) {
				for (int x = 0; x < ImageConstants.SIZE; x++) {
					for (int y = 0; y < ImageConstants.SIZE; y++) {
						final int pixel = icon3.getRGB(x, y);
						final Color c = new Color(pixel);
						if (c.equals(ObjectImageLoader.TRANSPARENT)) {
							result.setRGB(x, y, icon2.getRGB(x, y));
						}
					}
				}
				return result;
			} else {
				return null;
			}
		} catch (final NullPointerException np) {
			return null;
		} catch (final IllegalArgumentException ia) {
			return null;
		}
	}

	public static BufferedImageIcon load(final MazeObject obj, final boolean game) {
		// Get it from the cache
		return ObjectImageCache.getCachedObjectImage(obj, game);
	}

	public static BufferedImageIcon loadHelp(final String name) {
		// Get it from the cache
		return ObjectImageCache.getCachedHelpImage(name);
	}

	static BufferedImageIcon getUncachedHelpImage(final String name) {
		try {
			final String normalName = ObjectImageLoader.normalizeName(name);
			final URL url = ObjectImageLoader.LOAD_CLASS.getResource(
					ObjectImageLoader.LOAD_PATH + normalName + FileExtensions.getImageExtensionWithPeriod());
			final BufferedImage image = ImageIO.read(url);
			final BufferedImageIcon icon = new BufferedImageIcon(image);
			return icon;
		} catch (final IOException ie) {
			return null;
		} catch (final NullPointerException np) {
			return null;
		} catch (final IllegalArgumentException ia) {
			return null;
		}
	}

	static BufferedImageIcon getUncachedObjectImage(final MazeObject obj) {
		try {
			String name = obj.getImageName();
			final String normalName = ObjectImageLoader.normalizeName(name);
			final URL url = ObjectImageLoader.LOAD_CLASS.getResource(
					ObjectImageLoader.LOAD_PATH + normalName + FileExtensions.getImageExtensionWithPeriod());
			final BufferedImage image = ImageIO.read(url);
			final BufferedImageIcon icon = new BufferedImageIcon(image);
			return icon;
		} catch (final IOException ie) {
			return null;
		} catch (final NullPointerException np) {
			return null;
		} catch (final IllegalArgumentException ia) {
			return null;
		}
	}

	private static String normalizeName(final String name) {
		final StringBuffer sb = new StringBuffer(name);
		for (int x = 0; x < sb.length(); x++) {
			if (!Character.isLetter(sb.charAt(x)) && !Character.isDigit(sb.charAt(x))) {
				sb.setCharAt(x, '_');
			}
		}
		return sb.toString().toLowerCase();
	}
}
