/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.objects.abc.GenericConditionalTeleport;
import com.puttysoftware.mazer5d.utilities.MazeObjects;

class InvisibleOneShotConditionalTeleport extends GenericConditionalTeleport {
    // Constructors
    public InvisibleOneShotConditionalTeleport() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Invisible One-Shot Conditional Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "Invisible One-Shot Conditional Teleports";
    }

    @Override
    protected String getDescriptionHook() {
	return "Invisible One-Shot Conditional Teleports send you to one of two predetermined destinations when stepped on, depending on how many Sun or Moon Stones are in your inventory, then disappear, and cannot be seen.";
    }

    @Override
    public void postMoveActionHook() {
	Mazer5D.getBagOStuff().getGameManager().decay();
    }

    @Override
    public String getGameName() {
	return "Empty";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.INVISIBLE_ONE_SHOT_CONDITIONAL_TELEPORT;
    }
}