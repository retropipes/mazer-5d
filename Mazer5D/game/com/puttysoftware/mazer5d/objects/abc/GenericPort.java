/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.loader.SoundPlayer;
import com.puttysoftware.mazer5d.locale.StaticStrings;

public abstract class GenericPort extends GenericInfiniteLock {
    // Fields
    private char letter;

    protected GenericPort(final GenericPlug mgk, final char newLetter) {
        super(mgk);
        this.letter = Character.toUpperCase(newLetter);
        this.setType(TypeConstants.TYPE_PORT);
        this.setType(TypeConstants.TYPE_UNLOCKED_KEEP_KEY);
        this.setType(TypeConstants.TYPE_LOCK);
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
        String fill = StaticStrings.EMPTY;
        if (this.isLetterVowel()) {
            fill = "an";
        } else {
            fill = "a";
        }
        Mazer5D.getBagOStuff().showMessage("You need " + fill + " " + this.letter + " plug");
        SoundPlayer.playSound(SoundIndex.WALK_FAILED, SoundGroup.GAME);
    }

    // FIXME: Hack
    @Override
    protected String getNameHook() {
        return this.getName();
    }

    // FIXME: Hack
    @Override
    protected String getPluralNameHook() {
        return this.getPluralName();
    }

    private boolean isLetterVowel() {
        if (this.letter == 'A' || this.letter == 'E' || this.letter == 'I' || this.letter == 'O'
                || this.letter == 'U') {
            return true;
        } else {
            return false;
        }
    }

    // FIXME: Hack
    @Override
    protected String getDescriptionHook() {
        return this.getDescription();
    }
}
