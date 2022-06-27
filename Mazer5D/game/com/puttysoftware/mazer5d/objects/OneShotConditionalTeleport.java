/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.objects.abc.GenericConditionalTeleport;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class OneShotConditionalTeleport extends GenericConditionalTeleport {
    // Constructors
    public OneShotConditionalTeleport() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "One-Shot Conditional Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "One-Shot Conditional Teleports";
    }

    @Override
    protected String getDescriptionHook() {
	return "One-Shot Conditional Teleports send you to one of two predetermined destinations when stepped on, depending on how many Sun or Moon Stones are in your inventory, then disappear.";
    }

    @Override
    public void postMoveActionHook() {
	Mazer5D.getBagOStuff().getGameManager().decay();
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ONE_SHOT_CONDITIONAL_TELEPORT;
    }
}