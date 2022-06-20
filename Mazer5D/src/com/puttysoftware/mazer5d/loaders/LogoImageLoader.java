/*  Fantastle: A World-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Any questions should be directed to the author via email at: fantastle@worldwizard.net
 */
package com.puttysoftware.mazer5d.loaders;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.assets.LogoImageIndex;
import com.puttysoftware.mazer5d.files.FileExtensions;

public class LogoImageLoader {
    private static String[] allFilenames;
    private static final int MAX_INDEX = 2;

    private static void preInit() {
	if (LogoImageLoader.allFilenames == null) {
	    LogoImageLoader.allFilenames = DataLoader.loadLogoImageData();
	    final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	    for (int i = 1; i <= LogoImageLoader.MAX_INDEX; i++) {
		final String name = "/assets/image/logo/" + LogoImageLoader.allFilenames[i] + imageExt;
		ImageLoader.load(name, LogoImageLoader.class.getResource(name));
	    }
	}
    }

    public static BufferedImageIcon load(final LogoImageIndex image) {
	LogoImageLoader.preInit();
	final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	final String name = "/assets/image/logo/" + LogoImageLoader.allFilenames[image.ordinal()] + imageExt;
	return ImageLoader.load(name, LogoImageLoader.class.getResource(name));
    }
}
