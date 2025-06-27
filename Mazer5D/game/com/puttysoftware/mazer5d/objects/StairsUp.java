/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericTeleport;

class StairsUp extends GenericTeleport {
    // Constructors
    public StairsUp() {
        super(0, 0, 0);
    }

    // For derived classes only
    protected StairsUp(final boolean doesAcceptPushInto) {
        super(doesAcceptPushInto);
    }

    @Override
    protected String getNameHook() {
        return "Stairs Up";
    }

    @Override
    protected String getPluralNameHook() {
        return "Sets of Stairs Up";
    }

    @Override
    public int getDestinationRow() {
        final BagOStuff app = Mazer5D.getBagOStuff();
        return app.getGameManager().getPlayerManager().getPlayerLocationX();
    }

    @Override
    public int getDestinationColumn() {
        final BagOStuff app = Mazer5D.getBagOStuff();
        return app.getGameManager().getPlayerManager().getPlayerLocationY();
    }

    @Override
    public int getDestinationFloor() {
        final BagOStuff app = Mazer5D.getBagOStuff();
        return app.getGameManager().getPlayerManager().getPlayerLocationZ() + 1;
    }

    @Override
    public int getDestinationLevel() {
        final BagOStuff app = Mazer5D.getBagOStuff();
        return app.getGameManager().getPlayerManager().getPlayerLocationW();
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
                this.getDestinationFloor(), this.getDestinationLevel());
        SoundPlayer.playSound(SoundIndex.UP, SoundGroup.GAME);
    }

    @Override
    public void editorPlaceHook() {
        final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
        me.pairStairs(MazeEditor.STAIRS_UP);
    }

    @Override
    public MazeObject editorPropertiesHook() {
        return null;
    }

    @Override
    protected String getDescriptionHook() {
        return "Stairs Up lead to the floor above.";
    }

    @Override
    public int getCustomFormat() {
        return 0;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.STAIRS_UP;
    }
}
