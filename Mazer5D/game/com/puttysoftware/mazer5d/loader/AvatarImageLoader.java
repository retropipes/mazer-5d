package com.puttysoftware.mazer5d.loader;

import java.io.IOException;

import com.puttysoftware.diane.image.BufferedImageIcon;
import com.puttysoftware.mazer5d.avatar.AvatarImageModel;
import com.puttysoftware.mazer5d.avatar.ColorReplaceRules;
import com.puttysoftware.mazer5d.file.FileExtensions;

public class AvatarImageLoader {
    public static BufferedImageIcon load(final int familyID, final ColorReplaceRules rules) throws IOException {
	final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	final String name = "/asset/image/avatar/" + Integer.toHexString(familyID).toUpperCase() + imageExt;
	return rules.applyAll(ImageLoader.load(name, AvatarImageLoader.class.getResource(name)));
    }

    public static BufferedImageIcon loadFromModel(final AvatarImageModel model) throws IOException {
	final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	final String name = "/asset/image/avatar/" + Integer.toHexString(model.getAvatarFamilyID()).toUpperCase()
		+ imageExt;
	BufferedImageIcon image = ImageLoader.load(name, AvatarImageLoader.class.getResource(name));
	image = model.getRules().applyAll(image);
	BufferedImageIcon weaponImage = AvatarImageLoader.loadWeapon(model.getAvatarWeaponID(), model.getWeaponRules());
	BufferedImageIcon accessoryImage = AvatarImageLoader.loadAccessory(model.getAvatarAccessoryID(),
		model.getAccessoryRules());
	return ImageCompositor.composite(image, accessoryImage, weaponImage);
    }

    public static BufferedImageIcon loadWeapon(final int weaponID, final ColorReplaceRules rules) throws IOException {
	final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	final String name = "/asset/image/avatar/weapon/" + Integer.toHexString(weaponID).toUpperCase() + imageExt;
	return rules.applyAll(ImageLoader.load(name, AvatarImageLoader.class.getResource(name)));
    }

    public static BufferedImageIcon loadAccessory(final int accessoryID, final ColorReplaceRules rules)
	    throws IOException {
	final String imageExt = FileExtensions.getImageExtensionWithPeriod();
	final String name = "/asset/image/avatar/accessory/" + Integer.toHexString(accessoryID).toUpperCase()
		+ imageExt;
	return rules.applyAll(ImageLoader.load(name, AvatarImageLoader.class.getResource(name)));
    }
}
