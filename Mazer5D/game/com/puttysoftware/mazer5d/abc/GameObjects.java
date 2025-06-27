/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.abc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.puttysoftware.diane.image.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.file.version.MazeVersion;
import com.puttysoftware.mazer5d.loader.DataLoader;
import com.puttysoftware.mazer5d.loader.ObjectImageLoader;
import com.puttysoftware.mazer5d.locale.StaticStrings;

public class GameObjects {
	// Fields
	private static MazeObject[] allObjects;
	static {
		var vals = MazeObjects.values();
		int len = vals.length;
		GameObjects.allObjects = new MazeObject[len];
		for (int i = 0; i < len; i++) {
			GameObjects.allObjects[i] = new MazeObject(vals[i]);
		}
	}

	private GameObjects() {
		super();
	}

	public static MazeObject getEmptySpace() {
		return GameObjects.createObject(MazeObjects.EMPTY);
	}

	public static MazeObject[] getAllObjects() {
		return GameObjects.allObjects;
	}

	public static String[] getAllNames() {
		final String[] allNames = new String[GameObjects.allObjects.length];
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			allNames[x] = GameObjects.allObjects[x].getName();
		}
		return allNames;
	}

	public static String[] getAllDescriptions() {
		final String[] allDescriptions = new String[GameObjects.allObjects.length];
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			allDescriptions[x] = GameObjects.allObjects[x].getDescription();
		}
		return allDescriptions;
	}

	public static MazeObject[] getAllObjectsWithRuleSets() {
		final MazeObject[] tempAllObjectsWithRuleSets = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].hasRuleSet()) {
				tempAllObjectsWithRuleSets[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllObjectsWithRuleSet : tempAllObjectsWithRuleSets) {
			if (tempAllObjectsWithRuleSet != null) {
				objectCount++;
			}
		}
		final MazeObject[] allObjectsWithRuleSets = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllObjectsWithRuleSet : tempAllObjectsWithRuleSets) {
			if (tempAllObjectsWithRuleSet != null) {
				allObjectsWithRuleSets[objectCount] = tempAllObjectsWithRuleSet;
				objectCount++;
			}
		}
		return allObjectsWithRuleSets;
	}

	public static MazeObject[] getAllObjectsWithoutRuleSets() {
		final MazeObject[] tempAllObjectsWithoutRuleSets = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (!GameObjects.allObjects[x].hasRuleSet()) {
				tempAllObjectsWithoutRuleSets[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllObjectsWithoutRuleSet : tempAllObjectsWithoutRuleSets) {
			if (tempAllObjectsWithoutRuleSet != null) {
				objectCount++;
			}
		}
		final MazeObject[] allObjectsWithoutRuleSets = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllObjectsWithoutRuleSet : tempAllObjectsWithoutRuleSets) {
			if (tempAllObjectsWithoutRuleSet != null) {
				allObjectsWithoutRuleSets[objectCount] = tempAllObjectsWithoutRuleSet;
				objectCount++;
			}
		}
		return allObjectsWithoutRuleSets;
	}

	public static MazeObject[] getAllGroundLayerObjects() {
		final MazeObject[] tempAllGroundLayerObjects = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
				tempAllGroundLayerObjects[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllGroundLayerObject : tempAllGroundLayerObjects) {
			if (tempAllGroundLayerObject != null) {
				objectCount++;
			}
		}
		final MazeObject[] allGroundLayerObjects = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllGroundLayerObject : tempAllGroundLayerObjects) {
			if (tempAllGroundLayerObject != null) {
				allGroundLayerObjects[objectCount] = tempAllGroundLayerObject;
				objectCount++;
			}
		}
		return allGroundLayerObjects;
	}

	public static MazeObject[] getAllObjectLayerObjects() {
		final MazeObject[] tempAllObjectLayerObjects = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
				tempAllObjectLayerObjects[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllObjectLayerObject : tempAllObjectLayerObjects) {
			if (tempAllObjectLayerObject != null) {
				objectCount++;
			}
		}
		final MazeObject[] allObjectLayerObjects = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllObjectLayerObject : tempAllObjectLayerObjects) {
			if (tempAllObjectLayerObject != null) {
				allObjectLayerObjects[objectCount] = tempAllObjectLayerObject;
				objectCount++;
			}
		}
		return allObjectLayerObjects;
	}

	public static String[] getAllGroundLayerNames() {
		final String[] tempAllGroundLayerNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
				tempAllGroundLayerNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllGroundLayerName : tempAllGroundLayerNames) {
			if (tempAllGroundLayerName != null) {
				objectCount++;
			}
		}
		final String[] allGroundLayerNames = new String[objectCount];
		objectCount = 0;
		for (final String tempAllGroundLayerName : tempAllGroundLayerNames) {
			if (tempAllGroundLayerName != null) {
				allGroundLayerNames[objectCount] = tempAllGroundLayerName;
				objectCount++;
			}
		}
		return allGroundLayerNames;
	}

	public static String[] getAllObjectLayerNames() {
		final String[] tempAllObjectLayerNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
				tempAllObjectLayerNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllObjectLayerName : tempAllObjectLayerNames) {
			if (tempAllObjectLayerName != null) {
				objectCount++;
			}
		}
		final String[] allObjectLayerNames = new String[objectCount];
		objectCount = 0;
		for (final String tempAllObjectLayerName : tempAllObjectLayerNames) {
			if (tempAllObjectLayerName != null) {
				allObjectLayerNames[objectCount] = tempAllObjectLayerName;
				objectCount++;
			}
		}
		return allObjectLayerNames;
	}

	public static BufferedImageIcon[] getAllObjectHelpAppearances() {
		final String[] imageNames = DataLoader.loadAllObjectImageData();
		final BufferedImageIcon[] allObjectHelpAppearances = new BufferedImageIcon[imageNames.length];
		for (int x = 0; x < allObjectHelpAppearances.length; x++) {
			allObjectHelpAppearances[x] = ObjectImageLoader.loadHelp(imageNames[x]);
		}
		return allObjectHelpAppearances;
	}

	public static BufferedImageIcon[] getAllEditorAppearances() {
		final BufferedImageIcon[] allEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
		for (int x = 0; x < allEditorAppearances.length; x++) {
			allEditorAppearances[x] = ObjectImageLoader.getTransformedImage(GameObjects.allObjects[x], false);
		}
		return allEditorAppearances;
	}

	public static BufferedImageIcon[] getAllGroundLayerEditorAppearances() {
		final BufferedImageIcon[] tempAllGroundLayerEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
				tempAllGroundLayerEditorAppearances[x] = ObjectImageLoader
						.getTransformedImage(GameObjects.allObjects[x], false);
			}
		}
		for (final BufferedImageIcon tempAllGroundLayerEditorAppearance : tempAllGroundLayerEditorAppearances) {
			if (tempAllGroundLayerEditorAppearance != null) {
				objectCount++;
			}
		}
		final BufferedImageIcon[] allGroundLayerEditorAppearances = new BufferedImageIcon[objectCount];
		objectCount = 0;
		for (final BufferedImageIcon tempAllGroundLayerEditorAppearance : tempAllGroundLayerEditorAppearances) {
			if (tempAllGroundLayerEditorAppearance != null) {
				allGroundLayerEditorAppearances[objectCount] = tempAllGroundLayerEditorAppearance;
				objectCount++;
			}
		}
		return allGroundLayerEditorAppearances;
	}

	public static BufferedImageIcon[] getAllObjectLayerEditorAppearances() {
		final BufferedImageIcon[] tempAllObjectLayerEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
				tempAllObjectLayerEditorAppearances[x] = ObjectImageLoader
						.getTransformedImage(GameObjects.allObjects[x], false);
			}
		}
		for (final BufferedImageIcon tempAllObjectLayerEditorAppearance : tempAllObjectLayerEditorAppearances) {
			if (tempAllObjectLayerEditorAppearance != null) {
				objectCount++;
			}
		}
		final BufferedImageIcon[] allObjectLayerEditorAppearances = new BufferedImageIcon[objectCount];
		objectCount = 0;
		for (final BufferedImageIcon tempAllObjectLayerEditorAppearance : tempAllObjectLayerEditorAppearances) {
			if (tempAllObjectLayerEditorAppearance != null) {
				allObjectLayerEditorAppearances[objectCount] = tempAllObjectLayerEditorAppearance;
				objectCount++;
			}
		}
		return allObjectLayerEditorAppearances;
	}

	public static BufferedImageIcon[] getAllContainableObjectEditorAppearances() {
		final BufferedImageIcon[] tempAllContainableObjectEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_CONTAINABLE)) {
				tempAllContainableObjectEditorAppearances[x] = ObjectImageLoader
						.getTransformedImage(GameObjects.allObjects[x], false);
			}
		}
		for (final BufferedImageIcon tempAllContainableObjectEditorAppearance : tempAllContainableObjectEditorAppearances) {
			if (tempAllContainableObjectEditorAppearance != null) {
				objectCount++;
			}
		}
		final BufferedImageIcon[] allContainableObjectEditorAppearances = new BufferedImageIcon[objectCount];
		objectCount = 0;
		for (final BufferedImageIcon tempAllContainableObjectEditorAppearance : tempAllContainableObjectEditorAppearances) {
			if (tempAllContainableObjectEditorAppearance != null) {
				allContainableObjectEditorAppearances[objectCount] = tempAllContainableObjectEditorAppearance;
				objectCount++;
			}
		}
		return allContainableObjectEditorAppearances;
	}

	public static MazeObject[] getAllContainableObjects() {
		final MazeObject[] tempAllContainableObjects = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_CONTAINABLE)) {
				tempAllContainableObjects[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllContainableObject : tempAllContainableObjects) {
			if (tempAllContainableObject != null) {
				objectCount++;
			}
		}
		final MazeObject[] allContainableObjects = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllContainableObject : tempAllContainableObjects) {
			if (tempAllContainableObject != null) {
				allContainableObjects[objectCount] = tempAllContainableObject;
				objectCount++;
			}
		}
		return allContainableObjects;
	}

	public static String[] getAllContainableNames() {
		final String[] tempAllContainableNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_CONTAINABLE)) {
				tempAllContainableNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllContainableName : tempAllContainableNames) {
			if (tempAllContainableName != null) {
				objectCount++;
			}
		}
		final String[] allContainableNames = new String[objectCount];
		objectCount = 0;
		for (final String tempAllContainableName : tempAllContainableNames) {
			if (tempAllContainableName != null) {
				allContainableNames[objectCount] = tempAllContainableName;
				objectCount++;
			}
		}
		return allContainableNames;
	}

	public static MazeObject[] getAllInventoryableObjectsMinusSpecial() {
		final MazeObject[] tempAllInventoryableObjects = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isInventoryable()
					&& !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOOTS)
					&& !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)
					&& !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_AMULET)) {
				tempAllInventoryableObjects[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllInventoryableObject : tempAllInventoryableObjects) {
			if (tempAllInventoryableObject != null) {
				objectCount++;
			}
		}
		final MazeObject[] allInventoryableObjects = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllInventoryableObject : tempAllInventoryableObjects) {
			if (tempAllInventoryableObject != null) {
				allInventoryableObjects[objectCount] = tempAllInventoryableObject;
				objectCount++;
			}
		}
		return allInventoryableObjects;
	}

	public static String[] getAllInventoryableNamesMinusSpecial() {
		final String[] tempAllInventoryableNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isInventoryable()
					&& !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOOTS)
					&& !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)
					&& !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_AMULET)) {
				tempAllInventoryableNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllInventoryableName : tempAllInventoryableNames) {
			if (tempAllInventoryableName != null) {
				objectCount++;
			}
		}
		final String[] allInventoryableNames = new String[objectCount];
		objectCount = 0;
		for (final String tempAllInventoryableName : tempAllInventoryableNames) {
			if (tempAllInventoryableName != null) {
				allInventoryableNames[objectCount] = tempAllInventoryableName;
				objectCount++;
			}
		}
		return allInventoryableNames;
	}

	public static MazeObject[] getAllProgrammableKeys() {
		final MazeObject[] tempAllProgrammableKeys = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_PROGRAMMABLE_USE)) {
				tempAllProgrammableKeys[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllProgrammableKey : tempAllProgrammableKeys) {
			if (tempAllProgrammableKey != null) {
				objectCount++;
			}
		}
		final MazeObject[] allProgrammableKeys = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllProgrammableKey : tempAllProgrammableKeys) {
			if (tempAllProgrammableKey != null) {
				allProgrammableKeys[objectCount] = tempAllProgrammableKey;
				objectCount++;
			}
		}
		return allProgrammableKeys;
	}

	public static String[] getAllProgrammableKeyNames() {
		final String[] tempAllProgrammableKeyNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_PROGRAMMABLE_USE)) {
				tempAllProgrammableKeyNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllProgrammableKeyName : tempAllProgrammableKeyNames) {
			if (tempAllProgrammableKeyName != null) {
				objectCount++;
			}
		}
		final String[] allProgrammableKeyNames = new String[objectCount];
		objectCount = 0;
		for (final String tempAllProgrammableKeyName : tempAllProgrammableKeyNames) {
			if (tempAllProgrammableKeyName != null) {
				allProgrammableKeyNames[objectCount] = tempAllProgrammableKeyName;
				objectCount++;
			}
		}
		return allProgrammableKeyNames;
	}

	public static MazeObject[] getAllUsableObjects() {
		final MazeObject[] tempAllUsableObjects = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isUsable()) {
				tempAllUsableObjects[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllUsableObject : tempAllUsableObjects) {
			if (tempAllUsableObject != null) {
				objectCount++;
			}
		}
		final MazeObject[] allUsableObjects = new MazeObject[objectCount];
		objectCount = 0;
		for (final MazeObject tempAllUsableObject : tempAllUsableObjects) {
			if (tempAllUsableObject != null) {
				allUsableObjects[objectCount] = tempAllUsableObject;
				objectCount++;
			}
		}
		return allUsableObjects;
	}

	public static String[] getAllUsableNamesMinusSpecial() {
		final String[] tempAllUsableNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isUsable() && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)) {
				tempAllUsableNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllUsableName : tempAllUsableNames) {
			if (tempAllUsableName != null) {
				objectCount++;
			}
		}
		final String[] allUsableNames = new String[objectCount];
		objectCount = 0;
		for (final String tempAllUsableName : tempAllUsableNames) {
			if (tempAllUsableName != null) {
				allUsableNames[objectCount] = tempAllUsableName;
				objectCount++;
			}
		}
		return allUsableNames;
	}

	public static MazeObject[] getAllBows() {
		final MazeObject[] tempAllUsableObjects = new MazeObject[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)) {
				tempAllUsableObjects[x] = GameObjects.allObjects[x];
			}
		}
		for (final MazeObject tempAllUsableObject : tempAllUsableObjects) {
			if (tempAllUsableObject != null) {
				objectCount++;
			}
		}
		final MazeObject[] allUsableObjects = new MazeObject[objectCount + 1];
		objectCount = 0;
		for (int x = 0; x < tempAllUsableObjects.length - 1; x++) {
			if (tempAllUsableObjects[x] != null) {
				allUsableObjects[objectCount] = tempAllUsableObjects[x];
				objectCount++;
			}
		}
		allUsableObjects[allUsableObjects.length - 1] = new MazeObject(MazeObjects.BOW);
		return allUsableObjects;
	}

	public static String[] getAllBowNames() {
		final String[] tempAllUsableNames = new String[GameObjects.allObjects.length];
		int objectCount = 0;
		for (int x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)) {
				tempAllUsableNames[x] = GameObjects.allObjects[x].getName();
			}
		}
		for (final String tempAllUsableName : tempAllUsableNames) {
			if (tempAllUsableName != null) {
				objectCount++;
			}
		}
		final String[] allUsableNames = new String[objectCount + 1];
		objectCount = 0;
		for (int x = 0; x < tempAllUsableNames.length - 1; x++) {
			if (tempAllUsableNames[x] != null) {
				allUsableNames[objectCount] = tempAllUsableNames[x];
				objectCount++;
			}
		}
		allUsableNames[allUsableNames.length - 1] = new MazeObject(MazeObjects.BOW).getName();
		return allUsableNames;
	}

	public static MazeObject[] getAllRequired(final int layer) {
		final MazeObject[] tempAllRequired = new MazeObject[GameObjects.allObjects.length];
		int x;
		int count = 0;
		for (x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == layer && GameObjects.allObjects[x].isRequired()) {
				tempAllRequired[count] = GameObjects.allObjects[x];
				count++;
			}
		}
		if (count == 0) {
			return null;
		} else {
			final MazeObject[] allRequired = new MazeObject[count];
			for (x = 0; x < count; x++) {
				allRequired[x] = tempAllRequired[x];
			}
			return allRequired;
		}
	}

	public static MazeObject[] getAllWithoutPrerequisiteAndNotRequired(final int layer) {
		final MazeObject[] tempAllWithoutPrereq = new MazeObject[GameObjects.allObjects.length];
		int x;
		int count = 0;
		for (x = 0; x < GameObjects.allObjects.length; x++) {
			if (GameObjects.allObjects[x].getLayer() == layer && !GameObjects.allObjects[x].isRequired()) {
				tempAllWithoutPrereq[count] = GameObjects.allObjects[x];
				count++;
			}
		}
		if (count == 0) {
			return null;
		} else {
			final MazeObject[] allWithoutPrereq = new MazeObject[count];
			for (x = 0; x < count; x++) {
				allWithoutPrereq[x] = tempAllWithoutPrereq[x];
			}
			return allWithoutPrereq;
		}
	}

	public static MazeObject[] getAllRequiredSubset(final MazeObject[] objs, final int layer) {
		if (objs == null) {
			return null;
		}
		final MazeObject[] tempAllRequired = new MazeObject[objs.length];
		int x;
		int count = 0;
		for (x = 0; x < objs.length; x++) {
			if (objs[x].hasRuleSet()) {
				if (objs[x].getLayer() == layer && objs[x].getRuleSet().isRequired()) {
					tempAllRequired[count] = objs[x];
					count++;
				}
			} else {
				if (objs[x].getLayer() == layer && objs[x].isRequired()) {
					tempAllRequired[count] = objs[x];
					count++;
				}
			}
		}
		if (count == 0) {
			return null;
		} else {
			final MazeObject[] allRequired = new MazeObject[count];
			for (x = 0; x < count; x++) {
				allRequired[x] = tempAllRequired[x];
			}
			return allRequired;
		}
	}

	public static MazeObject[] getAllWithoutPrerequisiteAndNotRequiredSubset(final MazeObject[] objs, final int layer) {
		if (objs == null) {
			return null;
		}
		final MazeObject[] tempAllWithoutPrereq = new MazeObject[objs.length];
		int x;
		int count = 0;
		for (x = 0; x < objs.length; x++) {
			if (objs[x].hasRuleSet()) {
				if (objs[x].getLayer() == layer && !objs[x].getRuleSet().isRequired()) {
					tempAllWithoutPrereq[count] = objs[x];
					count++;
				}
			} else {
				if (objs[x].getLayer() == layer && !objs[x].isRequired()) {
					tempAllWithoutPrereq[count] = objs[x];
					count++;
				}
			}
		}
		if (count == 0) {
			return null;
		} else {
			final MazeObject[] allWithoutPrereq = new MazeObject[count];
			for (x = 0; x < count; x++) {
				allWithoutPrereq[x] = tempAllWithoutPrereq[x];
			}
			return allWithoutPrereq;
		}
	}

	public static MazeObjects getUIDForIndex(final int index) {
		return MazeObjects.values()[index];
	}

	public static MazeObject createObject(final MazeObjects uid) {
		return new MazeObject(uid);
	}

	public static MazeObject createContainerObject(final MazeObjects uid, final MazeObjects contentsUID) {
		return new MazeObject(uid, contentsUID);
	}

	public static MazeObject createTeleportObject(final MazeObjects uid, final int dr, final int dc) {
		return new MazeObject(uid, dr, dc);
	}

	public static MazeObject createTeleportObject(final MazeObjects uid, final int dr, final int dc, final int df) {
		return new MazeObject(uid, dr, dc, df);
	}

	public static MazeObject createTeleportObject(final MazeObjects uid, final int dr, final int dc, final int df,
			final int dl) {
		return new MazeObject(uid, dr, dc, df, dl);
	}

	public static MazeObject readObject(final MazeDataReader reader, final MazeVersion formatVersion)
			throws IOException {
		MazeObject o = null;
		String UID = StaticStrings.EMPTY;
		if (formatVersion == MazeVersion.V1) {
			UID = reader.readString();
		} else if (formatVersion == MazeVersion.V2) {
			UID = reader.readString();
		} else if (formatVersion == MazeVersion.V3) {
			UID = reader.readString();
		} else if (formatVersion == MazeVersion.V4) {
			UID = reader.readString();
		} else if (formatVersion == MazeVersion.V5) {
			UID = reader.readString();
		}
		for (final MazeObject allObject : GameObjects.allObjects) {
			try {
				final MazeObject instance = allObject.getClass().getConstructor().newInstance();
				if (formatVersion == MazeVersion.V1) {
					o = instance.readMazeObject(reader, UID, formatVersion);
				} else if (formatVersion == MazeVersion.V2) {
					o = instance.readMazeObject2(reader, UID, formatVersion);
				} else if (formatVersion == MazeVersion.V3) {
					o = instance.readMazeObject3(reader, UID, formatVersion);
				} else if (formatVersion == MazeVersion.V4) {
					o = instance.readMazeObject4(reader, UID, formatVersion);
				} else if (formatVersion == MazeVersion.V5) {
					o = instance.readMazeObject5(reader, UID, formatVersion);
				}
				if (o != null) {
					return o;
				}
			} catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				Mazer5D.logError(e);
			}
		}
		return null;
	}

	public static void readRuleSet(final MazeDataReader reader, final int rsFormat) throws IOException {
		// Read map length
		final int mapLen = reader.readInt();
		final boolean[] map = new boolean[mapLen];
		// Read map
		for (int x = 0; x < mapLen; x++) {
			map[x] = reader.readBoolean();
		}
		// Read data
		for (int x = 0; x < mapLen; x++) {
			if (map[x]) {
				GameObjects.allObjects[x].giveRuleSet();
				GameObjects.allObjects[x].getRuleSet().readRuleSet(reader, rsFormat);
			}
		}
	}

	public static void writeRuleSet(final MazeDataWriter writer) throws IOException {
		final boolean[] map = GameObjects.generateMap();
		// Write map length
		writer.writeInt(map.length);
		// Write map
		for (final boolean element : map) {
			writer.writeBoolean(element);
		}
		// Write data
		for (int x = 0; x < map.length; x++) {
			if (map[x]) {
				GameObjects.allObjects[x].getRuleSet().writeRuleSet(writer);
			}
		}
	}

	private static boolean[] generateMap() {
		final boolean[] map = new boolean[GameObjects.allObjects.length];
		for (int x = 0; x < map.length; x++) {
			if (GameObjects.allObjects[x].hasRuleSet()) {
				map[x] = true;
			} else {
				map[x] = false;
			}
		}
		return map;
	}
}
