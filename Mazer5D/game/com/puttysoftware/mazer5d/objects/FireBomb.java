/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericUsableObject;

class FireBomb extends GenericUsableObject {
    // Constants
    private static final int EFFECT_RADIUS = 2;

    // Constructors
    public FireBomb() {
        super(1);
    }

    @Override
    protected String getNameHook() {
        return "Fire Bomb";
    }

    @Override
    protected String getPluralNameHook() {
        return "Fire Bombs";
    }

    @Override
    protected String getDescriptionHook() {
        return "Fire Bombs burn anything in an area of radius 2 centered on the target point.";
    }

    @Override
    protected boolean customArrowHitAction(final int locX, final int locY, final int locZ, final int dirX,
            final int dirY, final int arrowType, final ObjectInventory inv) {
        // Act as if bomb was used
        this.useAction(null, locX, locY, locZ);
        // Destroy bomb
        Mazer5D.getBagOStuff().getGameManager().morph(GameObjects.getEmptySpace(), locX, locY, locZ);
        // Stop arrow
        return false;
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y, final int z) {
        SoundPlayer.playSound(SoundIndex.EXPLODE, SoundGroup.GAME);
        // Enrage objects that react to fire
        Mazer5D.getBagOStuff().getMazeManager().getMaze().radialScanEnrageObjects(x, y, z, FireBomb.EFFECT_RADIUS);
        // Burn the ground, too
        Mazer5D.getBagOStuff().getMazeManager().getMaze().radialScanBurnGround(x, y, z, FireBomb.EFFECT_RADIUS);
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        this.useAction(null, x, y, z);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.FIRE_BOMB;
    }
}
