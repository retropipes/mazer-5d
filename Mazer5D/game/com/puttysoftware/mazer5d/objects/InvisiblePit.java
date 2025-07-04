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
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;

class InvisiblePit extends Pit {
    // Constructors
    public InvisiblePit() {
        super();
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        Mazer5D.getBagOStuff().showMessage("Some unseen force prevents movement that way...");
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().updatePositionAbsolute(this.getDestinationRow(), this.getDestinationColumn(),
                this.getDestinationFloor());
        SoundPlayer.playSound(SoundIndex.FALL_INTO_PIT, SoundGroup.GAME);
        Mazer5D.getBagOStuff().showMessage("Invisible Pit!");
    }

    @Override
    protected String getNameHook() {
        return "Invisible Pit";
    }

    @Override
    public String getGameName() {
        return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
        return "Invislble Pits";
    }

    @Override
    protected String getDescriptionHook() {
        return "Invisible Pits dump anything that wanders in to the floor below. If one of these is placed on the bottom-most floor, it is impassable.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.INVISIBLE_PIT;
    }
}
