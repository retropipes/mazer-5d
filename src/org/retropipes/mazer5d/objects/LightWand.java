/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.maze.Maze;
import org.retropipes.mazer5d.objects.abc.GenericWand;

class LightWand extends GenericWand {
    // Constructors
    public LightWand() {
	super();
    }

    @Override
    protected String getNameHook() {
	return "Light Wand";
    }

    @Override
    protected String getPluralNameHook() {
	return "Light Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
	final Maze m = Mazer5D.getBagOStuff().getMazeManager().getMaze();
	final MazeObject obj = m.getCell(x, y, z, Layers.OBJECT);
	if (obj.getName().equals("Empty")) {
	    // Create a Light Gem
	    this.useAction(new LightGem(), x, y, z);
	    SoundPlayer.playSound(SoundIndex.LIGHT, SoundGroup.GAME);
	} else if (obj.getName().equals("Dark Gem")) {
	    // Destroy the Dark Gem
	    this.useAction(GameObjects.getEmptySpace(), x, y, z);
	    SoundPlayer.playSound(SoundIndex.SHATTER, SoundGroup.GAME);
	}
    }

    @Override
    protected String getDescriptionHook() {
	return "Light Wands have 2 uses. When aimed at an empty space, they create a Light Gem. When aimed at a Dark Gem, it is destroyed.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.LIGHT_WAND;
    }
}
