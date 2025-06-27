/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericButton;

class RoseButton extends GenericButton {
    public RoseButton() {
        super(new RoseWallOff(), new RoseWallOn());
    }

    @Override
    protected String getNameHook() {
        return "Rose Button";
    }

    @Override
    protected String getPluralNameHook() {
        return "Rose Buttons";
    }

    @Override
    protected String getDescriptionHook() {
        return "Rose Buttons will cause all Rose Walls Off to become On, and all Rose Walls On to become Off.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.ROSE_BUTTON;
    }
}
