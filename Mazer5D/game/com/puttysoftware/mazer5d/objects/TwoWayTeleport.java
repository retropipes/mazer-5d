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

class TwoWayTeleport extends GenericTeleport {
    public TwoWayTeleport() {
        super(0, 0, 0);
    }

    public TwoWayTeleport(final int destRow, final int destCol, final int destFloor) {
        super(destRow, destCol, destFloor);
    }

    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        final BagOStuff app = Mazer5D.getBagOStuff();
        app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
                this.getDestinationFloor(), this.getDestinationLevel());
        SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    public MazeObject editorPropertiesHook() {
        final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
        final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_TWOWAY);
        return mo;
    }

    @Override
    protected String getNameHook() {
        return "Two-Way Teleport";
    }

    @Override
    protected String getPluralNameHook() {
        return "Two-Way Teleports";
    }

    @Override
    protected String getDescriptionHook() {
        return "Two-Way Teleports send you to their companion at their destination, and are linked such that stepping on the companion sends you back to the original.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.TWO_WAY_TELEPORT;
    }
}
