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
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericContainer;

class TreasureChest extends GenericContainer {
    // Constructors
    public TreasureChest() {
        super(new Key());
    }

    public TreasureChest(final MazeObject inside) {
        super(new Key(), inside);
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            Mazer5D.getBagOStuff().showMessage("You need a key");
        }
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
        return "Treasure Chest";
    }

    @Override
    protected String getPluralNameHook() {
        return "Treasure Chests";
    }

    @Override
    public MazeObject editorPropertiesHook() {
        return Mazer5D.getBagOStuff().getEditor().editTreasureChestContents();
    }

    @Override
    protected String getDescriptionHook() {
        return "Treasure Chests require Keys to open, and contain 1 other item.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.TREASURE_CHEST;
    }
}