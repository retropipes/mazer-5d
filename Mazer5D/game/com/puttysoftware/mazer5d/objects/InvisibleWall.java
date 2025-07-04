/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class InvisibleWall extends GenericWall {
    // Constructors
    public InvisibleWall() {
        super();
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        // Display invisible wall message, if it's enabled
        Mazer5D.getBagOStuff().showMessage("Invisible Wall!");
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        // Disallow passing through Invisible Walls under ANY circumstances
        return true;
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
            final int dirY, final int arrowType, final ObjectInventory inv) {
        // Behave as if the wall was walked into
        Mazer5D.getBagOStuff().showMessage("Invisible Wall!");
        return false;
    }

    @Override
    protected String getNameHook() {
        return "Invisible Wall";
    }

    @Override
    public String getGameName() {
        return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
        return "Invisible Walls";
    }

    @Override
    protected String getDescriptionHook() {
        return "Invisible Walls look like any other open space, but block any attempt at moving into them.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.INVISIBLE_WALL;
    }
}