/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.objects.abc.GenericBoots;

class PasswallBoots extends GenericBoots {
    // Constructors
    public PasswallBoots() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Passwall Boots";
    }

    @Override
    protected String getPluralNameHook() {
	return "Pairs of Passwall Boots";
    }

    @Override
    protected String getDescriptionHook() {
	return "Passwall Boots allow you to pass through most walls as you walk. Note that you can only wear one pair of boots at once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.PASSWALL_BOOTS;
    }
}
