/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericConditionalTeleport;

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