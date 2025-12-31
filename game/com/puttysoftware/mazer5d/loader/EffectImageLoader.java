/*  Fantastle: A World-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either formatVersion 3 of the License, or
(at your option) any later formatVersion.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Any questions should be directed to the author via email at: fantastle@worldwizard.net
 */
package com.puttysoftware.mazer5d.loader;

import org.retropipes.diane.asset.image.BufferedImageIcon;

import com.puttysoftware.mazer5d.asset.EffectImageIndex;
import com.puttysoftware.mazer5d.file.FileExtensions;

public class EffectImageLoader {
    private static String[] allFilenames;
    private static boolean cached = false;
    private static final int MAX_INDEX = 21;

    public static BufferedImageIcon load(final EffectImageIndex image) {
	if (!EffectImageLoader.cached) {
	    EffectImageLoader.cacheAll();
	    EffectImageLoader.cached = true;
	}
	if (image != EffectImageIndex._NONE) {
	    final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	    final String name = "/asset/image/effect/" + EffectImageLoader.allFilenames[image.ordinal()] + imageExt;
	    return ImageLoader.load(name, EffectImageLoader.class.getResource(name));
	}
	return null;
    }

    public static BufferedImageIcon loadTime(final int ticks, final int maxTicks) {
	final int tickDiff = (maxTicks - ticks) % 20;
	EffectImageIndex timeImage;
	switch (tickDiff) {
	case 0:
	    timeImage = EffectImageIndex.TIME_00;
	    break;
	case 1:
	    timeImage = EffectImageIndex.TIME_01;
	    break;
	case 2:
	    timeImage = EffectImageIndex.TIME_02;
	    break;
	case 3:
	    timeImage = EffectImageIndex.TIME_03;
	    break;
	case 4:
	    timeImage = EffectImageIndex.TIME_04;
	    break;
	case 5:
	    timeImage = EffectImageIndex.TIME_05;
	    break;
	case 6:
	    timeImage = EffectImageIndex.TIME_06;
	    break;
	case 7:
	    timeImage = EffectImageIndex.TIME_07;
	    break;
	case 8:
	    timeImage = EffectImageIndex.TIME_08;
	    break;
	case 9:
	    timeImage = EffectImageIndex.TIME_09;
	    break;
	case 10:
	    timeImage = EffectImageIndex.TIME_10;
	    break;
	case 11:
	    timeImage = EffectImageIndex.TIME_11;
	    break;
	case 12:
	    timeImage = EffectImageIndex.TIME_12;
	    break;
	case 13:
	    timeImage = EffectImageIndex.TIME_13;
	    break;
	case 14:
	    timeImage = EffectImageIndex.TIME_14;
	    break;
	case 15:
	    timeImage = EffectImageIndex.TIME_15;
	    break;
	case 16:
	    timeImage = EffectImageIndex.TIME_16;
	    break;
	case 17:
	    timeImage = EffectImageIndex.TIME_17;
	    break;
	case 18:
	    timeImage = EffectImageIndex.TIME_18;
	    break;
	case 19:
	default:
	    timeImage = EffectImageIndex.TIME_19;
	    break;
	}
	return EffectImageLoader.load(timeImage);
    }

    private static void cacheAll() {
	EffectImageLoader.allFilenames = DataLoader.loadEffectImageData();
	final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	for (int i = 0; i <= EffectImageLoader.MAX_INDEX; i++) {
	    final String name = "/asset/image/effect/" + EffectImageLoader.allFilenames[i] + imageExt;
	    try {
		ImageLoader.load(name, EffectImageLoader.class.getResource(name));
	    } catch (final IllegalArgumentException iae) {
		// Ignore - image unused
	    }
	}
    }
}
