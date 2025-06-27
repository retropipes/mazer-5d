/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.maze.Maze;
import com.puttysoftware.mazer5d.objects.abc.GenericCharacter;

class Player extends GenericCharacter {
    // Constructors
    public Player() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Player";
    }

    @Override
    protected String getPluralNameHook() {
        return "Players";
    }

    @Override
    public void editorPlaceHook() {
        final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
        me.setPlayerLocation();
    }

    @Override
    public void editorGenerateHook(final int x, final int y, final int z) {
        final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
        me.setPlayerLocation(x, y, z);
    }

    @Override
    protected String getDescriptionHook() {
        return "This is you - the Player.";
    }

    // Random Generation Rules
    @Override
    public boolean isRequired() {
        return true;
    }

    @Override
    public int getMinimumRequiredQuantity(final Maze maze) {
        return 1;
    }

    @Override
    public int getMaximumRequiredQuantity(final Maze maze) {
        return 1;
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.PLAYER;
    }
}