package com.puttysoftware.mazer5d.avatar;

import java.awt.Color;
import java.io.IOException;

import com.puttysoftware.diane.image.BufferedImageIcon;
import com.puttysoftware.mazer5d.loader.AvatarImageLoader;
import com.puttysoftware.mazer5d.locale.StaticStrings;

public final class AvatarImageModel {
	// Fields
	private final int familyID;
	private final int weaponID;
	private final int accessoryID;
	private final int miscID;
	private final Color hairColor;
	private final Color skinColor;
	private final Color torsoColor;
	private final Color legsColor;
	private final Color feetColor;
	private final Color eyesColor;
	private final Color weaponColor1;
	private final Color weaponColor2;
	private final Color accessoryColor1;
	private final Color accessoryColor2;
	private final ColorReplaceRules rules;
	private final ColorReplaceRules weaponRules;
	private final ColorReplaceRules accessoryRules;

	public AvatarImageModel(final int family, final int weapon, final int accessory, final int misc, final Color hair,
			final Color skin, final Color torso, final Color legs, final Color feet, final Color eyes,
			final Color weapon1, final Color weapon2, final Color accessory1, final Color accessory2) {
		this.familyID = family;
		this.weaponID = weapon;
		this.accessoryID = accessory;
		this.miscID = misc;
		this.hairColor = hair;
		this.skinColor = skin;
		this.torsoColor = torso;
		this.legsColor = legs;
		this.feetColor = feet;
		this.eyesColor = eyes;
		this.weaponColor1 = weapon1;
		this.weaponColor2 = weapon2;
		this.accessoryColor1 = accessory1;
		this.accessoryColor2 = accessory2;
		this.rules = new ColorReplaceRules();
		this.weaponRules = new ColorReplaceRules();
		this.accessoryRules = new ColorReplaceRules();
		this.addRules();
	}

	private void addRules() {
		this.rules.add(AvatarColors.hairBase, this.hairColor);
		this.rules.add(AvatarColors.skinBase, this.skinColor);
		this.rules.add(AvatarColors.bodyBase, this.torsoColor);
		this.rules.add(AvatarColors.pantsBase, this.legsColor);
		this.rules.add(AvatarColors.shoesBase, this.feetColor);
		this.rules.add(AvatarColors.eyesBase, this.eyesColor);
		this.weaponRules.add(AvatarColors.weapon1Base, this.weaponColor1);
		this.weaponRules.add(AvatarColors.weapon2Base, this.weaponColor2);
		this.accessoryRules.add(AvatarColors.accessory1Base, this.accessoryColor1);
		this.accessoryRules.add(AvatarColors.accessory2Base, this.accessoryColor2);
	}

	public int getAvatarFamilyID() {
		return this.familyID;
	}

	public int getAvatarWeaponID() {
		return this.weaponID;
	}

	public int getAvatarAccessoryID() {
		return this.accessoryID;
	}

	public Color getAvatarHairColor() {
		return this.hairColor;
	}

	public Color getAvatarSkinColor() {
		return this.skinColor;
	}

	public Color getAvatarTorsoColor() {
		return this.torsoColor;
	}

	public Color getAvatarLegsColor() {
		return this.legsColor;
	}

	public Color getAvatarFeetColor() {
		return this.feetColor;
	}

	public Color getAvatarEyesColor() {
		return this.eyesColor;
	}

	public Color getAvatarWeaponColor1() {
		return this.weaponColor1;
	}

	public Color getAvatarWeaponColor2() {
		return this.weaponColor2;
	}

	public Color getAvatarAccessoryColor1() {
		return this.accessoryColor1;
	}

	public Color getAvatarAccessoryColor2() {
		return this.accessoryColor2;
	}

	public ColorReplaceRules getRules() {
		return this.rules;
	}

	public ColorReplaceRules getWeaponRules() {
		return this.weaponRules;
	}

	public ColorReplaceRules getAccessoryRules() {
		return this.accessoryRules;
	}

	public String getAvatarImageID() {
		StringBuilder builder = new StringBuilder();
		builder.append(intToHex4(this.familyID));
		builder.append(intToHex4(this.accessoryID * 4 + this.weaponID));
		builder.append(colorToHex24(this.hairColor));
		builder.append(colorToHex24(this.skinColor));
		builder.append(colorToHex24(this.torsoColor));
		builder.append(colorToHex24(this.legsColor));
		builder.append(colorToHex24(this.feetColor));
		builder.append(colorToHex24(this.eyesColor));
		builder.append(colorToHex24(this.weaponColor1));
		builder.append(colorToHex24(this.weaponColor2));
		builder.append(colorToHex24(this.accessoryColor1));
		builder.append(colorToHex24(this.accessoryColor2));
		builder.append(intToHex8(this.miscID));
		return builder.toString();
	}

	public BufferedImageIcon generateAvatarImage() throws IOException {
		return AvatarImageLoader.loadFromModel(this);
	}

	private static String intToHex4(int value) {
		return String.format(StaticStrings.HEX_4, value);
	}

	private static String intToHex8(int value) {
		return String.format(StaticStrings.HEX_8, value);
	}

	private static String colorToHex24(Color value) {
		return String.format(StaticStrings.HEX_24, value.getRGB() << 8 >>> 8);
	}
}
