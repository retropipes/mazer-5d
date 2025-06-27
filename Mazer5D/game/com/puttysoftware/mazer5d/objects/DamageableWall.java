/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWall;

class DamageableWall extends GenericWall {
    // Constructors
    public DamageableWall() {
        super();
        this.setType(TypeConstants.TYPE_PLAIN_WALL);
        this.setType(TypeConstants.TYPE_WALL);
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
            final int dirY, final int arrowType, final ObjectInventory inv) {
        this.moveFailedAction(true, locX, locY, inv);
        return false;
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        // Crack the wall
        final int pz = Mazer5D.getBagOStuff().getGameManager().getPlayerManager().getPlayerLocationZ();
        Mazer5D.getBagOStuff().getGameManager().morph(new CrackedWall(), dirX, dirY, pz);
        SoundPlayer.playSound(SoundIndex.CRACK, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
        return "Damageable Wall";
    }

    @Override
    protected String getPluralNameHook() {
        return "Damageable Walls";
    }

    @Override
    public String getGameName() {
        return "Wall";
    }

    @Override
    protected String getDescriptionHook() {
        return "Damageable Walls turn into Cracked Walls when hit.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.DAMAGEABLE_WALL;
    }
}