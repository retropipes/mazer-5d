package com.puttysoftware.mazer5d.avatar;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JColorChooser;

import com.puttysoftware.diane.asset.BufferedImageIcon;
import com.puttysoftware.diane.gui.dialog.CommonDialogs;
import com.puttysoftware.mazer5d.loaders.AvatarImageLoader;
import com.puttysoftware.mazer5d.locale.Strings;
import com.puttysoftware.mazer5d.locale.Translations;
import com.puttysoftware.randomrange.RandomRange;

public class AvatarPicker {
    // Fields
    private static final int randomFamilyID = RandomRange.generate(0, 15);
    private static final Color randomSkinColor = new Color(RandomRange.generate(0, 255), RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255));
    private static final Color randomHairColor = new Color(RandomRange.generate(0, 255), RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255));
    private static final Color randomTorsoColor = new Color(RandomRange.generate(0, 255), RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255));
    private static final Color randomLegsColor = new Color(RandomRange.generate(0, 255), RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255));
    private static final Color randomEyesColor = new Color(RandomRange.generate(0, 255), RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255));
    private static final Color randomFeetColor = new Color(RandomRange.generate(0, 255), RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255));
    private static final Color randomWeaponColor1 = new Color(RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255), RandomRange.generate(0, 255));
    private static final Color randomWeaponColor2 = new Color(RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255), RandomRange.generate(0, 255));
    private static final Color randomAccessoryColor1 = new Color(RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255), RandomRange.generate(0, 255));
    private static final Color randomAccessoryColor2 = new Color(RandomRange.generate(0, 255),
	    RandomRange.generate(0, 255), RandomRange.generate(0, 255));
    private static final int randomWeaponID = RandomRange.generate(0, 3);
    private static final int randomAccessoryID = RandomRange.generate(0, 3);
    private static int currentFamilyID = randomFamilyID;
    private static Color currentSkinColor = randomSkinColor;
    private static Color currentHairColor = randomHairColor;
    private static Color currentTorsoColor = randomTorsoColor;
    private static Color currentLegsColor = randomLegsColor;
    private static Color currentEyesColor = randomEyesColor;
    private static Color currentFeetColor = randomFeetColor;
    private static Color currentWeaponColor1 = randomWeaponColor1;
    private static Color currentWeaponColor2 = randomWeaponColor2;
    private static Color currentAccessoryColor1 = randomAccessoryColor1;
    private static Color currentAccessoryColor2 = randomAccessoryColor2;
    private static int currentWeaponID = randomWeaponID;
    private static int currentAccessoryID = randomAccessoryID;
    private static int miscBits = 0;
    private static final ColorReplaceRules rules = new ColorReplaceRules();
    private static final ColorReplaceRules weaponRules = new ColorReplaceRules();
    private static final ColorReplaceRules accessoryRules = new ColorReplaceRules();

    // Constructors
    private AvatarPicker() {
	// Do nothing
    }

    // Methods
    public static AvatarImageModel constructAvatar() throws IOException {
	// Populate rules
	rules.add(AvatarColors.hairBase, currentHairColor);
	rules.add(AvatarColors.skinBase, currentSkinColor);
	rules.add(AvatarColors.bodyBase, currentTorsoColor);
	rules.add(AvatarColors.pantsBase, currentLegsColor);
	rules.add(AvatarColors.shoesBase, currentFeetColor);
	rules.add(AvatarColors.eyesBase, currentEyesColor);
	weaponRules.add(AvatarColors.weapon1Base, currentWeaponColor1);
	weaponRules.add(AvatarColors.weapon2Base, currentWeaponColor2);
	accessoryRules.add(AvatarColors.accessory1Base, currentAccessoryColor1);
	accessoryRules.add(AvatarColors.accessory2Base, currentAccessoryColor2);
	// Construct avatar
	currentFamilyID = AvatarPicker.pickAvatarFamily();
	if (currentFamilyID == CommonDialogs.CANCEL) {
	    return null;
	}
	currentSkinColor = AvatarPicker.pickAvatarSkinColor();
	if (currentSkinColor == null) {
	    return null;
	}
	currentHairColor = AvatarPicker.pickAvatarHairColor();
	if (currentHairColor == null) {
	    return null;
	}
	currentEyesColor = AvatarPicker.pickAvatarEyesColor();
	if (currentEyesColor == null) {
	    return null;
	}
	currentTorsoColor = AvatarPicker.pickAvatarTorsoColor();
	if (currentTorsoColor == null) {
	    return null;
	}
	currentLegsColor = AvatarPicker.pickAvatarLegsColor();
	if (currentLegsColor == null) {
	    return null;
	}
	currentFeetColor = AvatarPicker.pickAvatarFeetColor();
	if (currentFeetColor == null) {
	    return null;
	}
	currentWeaponID = AvatarPicker.pickAvatarWeapon();
	if (currentWeaponID == CommonDialogs.CANCEL) {
	    return null;
	}
	currentWeaponColor1 = AvatarPicker.pickAvatarWeaponColor1();
	if (currentWeaponColor1 == null) {
	    return null;
	}
	currentWeaponColor2 = AvatarPicker.pickAvatarWeaponColor2();
	if (currentWeaponColor2 == null) {
	    return null;
	}
	currentAccessoryID = AvatarPicker.pickAvatarAccessory();
	if (currentAccessoryID == CommonDialogs.CANCEL) {
	    return null;
	}
	currentAccessoryColor1 = AvatarPicker.pickAvatarAccessoryColor1();
	if (currentAccessoryColor1 == null) {
	    return null;
	}
	currentAccessoryColor2 = AvatarPicker.pickAvatarAccessoryColor2();
	if (currentAccessoryColor2 == null) {
	    return null;
	}
	return new AvatarImageModel(currentFamilyID, currentWeaponID, currentAccessoryID, miscBits, currentHairColor,
		currentSkinColor, currentTorsoColor, currentLegsColor, currentFeetColor, currentEyesColor,
		currentWeaponColor1, currentWeaponColor2, currentAccessoryColor1, currentAccessoryColor2);
    }

    private static int pickAvatarFamily() throws IOException {
	final String labelText = Translations.load(Strings.AVATAR_FAMILIES);
	final String title = Translations.load(Strings.PICK_AVATAR_FAMILY);
	final BufferedImageIcon[] input = new BufferedImageIcon[] { AvatarImageLoader.load(0, rules),
		AvatarImageLoader.load(1, rules), AvatarImageLoader.load(2, rules), AvatarImageLoader.load(3, rules),
		AvatarImageLoader.load(4, rules), AvatarImageLoader.load(5, rules), AvatarImageLoader.load(6, rules),
		AvatarImageLoader.load(7, rules), AvatarImageLoader.load(8, rules), AvatarImageLoader.load(9, rules),
		AvatarImageLoader.load(10, rules), AvatarImageLoader.load(11, rules), AvatarImageLoader.load(12, rules),
		AvatarImageLoader.load(13, rules), AvatarImageLoader.load(14, rules),
		AvatarImageLoader.load(15, rules) };
	return CommonDialogs.showImageListDialog(labelText, title, input, currentFamilyID);
    }

    private static int pickAvatarWeapon() throws IOException {
	final String labelText = Translations.load(Strings.AVATAR_WEAPONS);
	final String title = Translations.load(Strings.PICK_AVATAR_WEAPON);
	final BufferedImageIcon[] input = new BufferedImageIcon[] { AvatarImageLoader.loadWeapon(0, weaponRules),
		AvatarImageLoader.loadWeapon(1, weaponRules), AvatarImageLoader.loadWeapon(2, weaponRules),
		AvatarImageLoader.loadWeapon(3, weaponRules) };
	final String[] descriptions = new String[] { "Bow", "Stone", "Sword", "Wand" };
	return CommonDialogs.showImageListWithDescDialog(labelText, title, input, 0, descriptions[0], descriptions);
    }

    private static int pickAvatarAccessory() throws IOException {
	final String labelText =  Translations.load(Strings.AVATAR_ACCESSORIES);
	final String title = Translations.load(Strings.PICK_AVATAR_ACCESSORY);
	final BufferedImageIcon[] input = new BufferedImageIcon[] { AvatarImageLoader.loadAccessory(0, accessoryRules),
		AvatarImageLoader.loadAccessory(1, accessoryRules), AvatarImageLoader.loadAccessory(2, accessoryRules),
		AvatarImageLoader.loadAccessory(3, accessoryRules) };
	final String[] descriptions = new String[] { "Amulet", "Arrow", "Shard", "Shield" };
	return CommonDialogs.showImageListWithDescDialog(labelText, title, input, 0, descriptions[0], descriptions);
    }

    private static Color pickAvatarSkinColor() {
	final String title = Translations.load(Strings.PICK_AVATAR_SKIN_COLOR);
	return JColorChooser.showDialog(null, title, currentSkinColor, false);
    }

    private static Color pickAvatarHairColor() {
	final String title = Translations.load(Strings.PICK_AVATAR_HAIR_COLOR);
	return JColorChooser.showDialog(null, title, currentHairColor, false);
    }

    private static Color pickAvatarEyesColor() {
	final String title = Translations.load(Strings.PICK_AVATAR_EYES_COLOR);
	return JColorChooser.showDialog(null, title, currentEyesColor, false);
    }

    private static Color pickAvatarTorsoColor() {
	final String title = Translations.load(Strings.PICK_AVATAR_TORSO_COLOR);
	return JColorChooser.showDialog(null, title, currentTorsoColor, false);
    }

    private static Color pickAvatarLegsColor() {
	final String title = Translations.load(Strings.PICK_AVATAR_LEGS_COLOR);
	return JColorChooser.showDialog(null, title, currentLegsColor, false);
    }

    private static Color pickAvatarFeetColor() {
	final String title = Translations.load(Strings.PICK_AVATAR_FEET_COLOR);
	return JColorChooser.showDialog(null, title, currentFeetColor, false);
    }

    private static Color pickAvatarWeaponColor1() {
	final String title = Translations.load(Strings.PICK_AVATAR_FIRST_WEAPON_COLOR);
	return JColorChooser.showDialog(null, title, currentWeaponColor1, false);
    }

    private static Color pickAvatarWeaponColor2() {
	final String title = Translations.load(Strings.PICK_AVATAR_SECOND_WEAPON_COLOR);
	return JColorChooser.showDialog(null, title, currentWeaponColor2, false);
    }

    private static Color pickAvatarAccessoryColor1() {
	final String title = Translations.load(Strings.PICK_AVATAR_FIRST_ACCESSORY_COLOR);
	return JColorChooser.showDialog(null, title, currentAccessoryColor1, false);
    }

    private static Color pickAvatarAccessoryColor2() {
	final String title = Translations.load(Strings.PICK_AVATAR_SECOND_ACCESSORY_COLOR);
	return JColorChooser.showDialog(null, title, currentAccessoryColor2, false);
    }
}
