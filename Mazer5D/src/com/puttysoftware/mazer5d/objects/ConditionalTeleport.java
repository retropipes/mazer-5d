/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.objects.abc.GenericConditionalTeleport;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class ConditionalTeleport extends GenericConditionalTeleport {
    // Constructors
    public ConditionalTeleport() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Conditional Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "Conditional Teleports";
    }

    @Override
    protected String getDescriptionHook() {
	return "Conditional Teleports send you to one of two predetermined destinations when stepped on, depending on how many Sun or Moon Stones are in your inventory.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.CONDITIONAL_TELEPORT;
    }
}