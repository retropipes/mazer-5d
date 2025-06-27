/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.GameObjects;
import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.objects.abc.GenericWand;

class AnnihilationWand extends GenericWand {
    // Constructors
    public AnnihilationWand() {
        super();
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        this.useAction(GameObjects.getEmptySpace(), x, y, z);
        SoundPlayer.playSound(SoundIndex.DESTROY, SoundGroup.GAME);
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.ANNIHILATION_WAND;
    }
}
