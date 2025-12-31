/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.game;

import org.retropipes.diane.direction.DirectionResolver;
import org.retropipes.diane.direction.DirectionStrings;
import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.ArrowTypes;
import org.retropipes.mazer5d.abc.GameObjects;
import org.retropipes.mazer5d.abc.Layers;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.maze.Maze;
import org.retropipes.mazer5d.objects.abc.GenericTransientObject;

public class ArrowTask extends Thread {
    // Fields
    private int x, y;
    private final int at;

    // Constructors
    public ArrowTask(final int newX, final int newY, final int newAT) {
	this.x = newX;
	this.y = newY;
	this.at = newAT;
	this.setName("Arrow Handler");
    }

    @Override
    public void run() {
	boolean res = true;
	final BagOStuff app = Mazer5D.getBagOStuff();
	final PlayerLocationManager plMgr = app.getGameManager().getPlayerManager();
	final ObjectInventory inv = app.getGameManager().getObjectInventory();
	final int px = plMgr.getPlayerLocationX();
	final int py = plMgr.getPlayerLocationY();
	final int pz = plMgr.getPlayerLocationZ();
	final int[] mod = app.getGameManager().doEffects(this.x, this.y);
	this.x = mod[0];
	this.y = mod[1];
	int cumX = this.x;
	int cumY = this.y;
	final int incX = this.x;
	final int incY = this.y;
	final Maze m = app.getMazeManager().getMaze();
	m.tickTimers(pz);
	MazeObject o = null;
	try {
	    o = m.getCell(px + cumX, py + cumY, pz, Layers.OBJECT);
	} catch (final ArrayIndexOutOfBoundsException ae) {
	    o = GameObjects.createObject(MazeObjects.WALL);
	}
	final GenericTransientObject a = ArrowTask.createArrowForType(this.at);
	final String suffix = DirectionStrings.directionSuffix(DirectionResolver.resolve(incX, incY));
	a.setNameSuffix(suffix);
	SoundPlayer.playSound(SoundIndex.ARROW_FIRED, SoundGroup.GAME);
	while (res) {
	    res = o.arrowHitAction(px + cumX, py + cumY, pz, incX, incY, this.at, inv);
	    if (!res) {
		break;
	    }
	    app.getGameManager().redrawOneSquare(px + cumX, py + cumY, true, a);
	    app.getGameManager().redrawOneSquare(px + cumX, py + cumY, false, GameObjects.getEmptySpace());
	    cumX += incX;
	    cumY += incY;
	    try {
		o = m.getCell(px + cumX, py + cumY, pz, Layers.OBJECT);
	    } catch (final ArrayIndexOutOfBoundsException ae) {
		res = false;
	    }
	}
	// Fire arrow hit action for final object, if it hasn't already been
	// fired
	if (res) {
	    o.arrowHitAction(px + cumX, py + cumY, pz, incX, incY, this.at, inv);
	}
	SoundPlayer.playSound(SoundIndex.ARROW_DEAD, SoundGroup.GAME);
	app.getGameManager().arrowDone();
    }

    private static GenericTransientObject createArrowForType(final int type) {
	switch (type) {
	case ArrowTypes.PLAIN:
	    return (GenericTransientObject) GameObjects.createObject(MazeObjects.ARROW);
	case ArrowTypes.ICE:
	    return (GenericTransientObject) GameObjects.createObject(MazeObjects.ICE_ARROW);
	case ArrowTypes.FIRE:
	    return (GenericTransientObject) GameObjects.createObject(MazeObjects.FIRE_ARROW);
	case ArrowTypes.POISON:
	    return (GenericTransientObject) GameObjects.createObject(MazeObjects.POISON_ARROW);
	case ArrowTypes.SHOCK:
	    return (GenericTransientObject) GameObjects.createObject(MazeObjects.SHOCK_ARROW);
	case ArrowTypes.GHOST:
	    return (GenericTransientObject) GameObjects.createObject(MazeObjects.GHOST_ARROW);
	default:
	    return null;
	}
    }
}
