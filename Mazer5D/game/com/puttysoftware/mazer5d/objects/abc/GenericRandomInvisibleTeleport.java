/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects.abc;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.abc.TypeConstants;
import com.puttysoftware.mazer5d.asset.SoundGroup;
import com.puttysoftware.mazer5d.asset.SoundIndex;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.BagOStuff;
import com.puttysoftware.mazer5d.loader.SoundPlayer;

public abstract class GenericRandomInvisibleTeleport extends GenericRandomTeleport {
	// Constructors
	public GenericRandomInvisibleTeleport(final int newRandomRangeY, final int newRandomRangeX) {
		super(newRandomRangeY, newRandomRangeX);
		this.setType(TypeConstants.TYPE_RANDOM_INVISIBLE_TELEPORT);
		this.setType(TypeConstants.TYPE_RANDOM);
	}

	// Scriptability
	@Override
	protected abstract String getNameHook();

	@Override
	protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
		final BagOStuff app = Mazer5D.getBagOStuff();
		int dr, dc;
		do {
			dr = this.getDestinationRow();
			dc = this.getDestinationColumn();
		} while (!app.getGameManager().tryUpdatePositionRelative(dr, dc));
		app.getGameManager().updatePositionRelative(dr, dc);
		Mazer5D.getBagOStuff().showMessage("Invisible Teleport!");
		SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
	}

	@Override
	public MazeObject editorPropertiesHook() {
		final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
		final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_RANDOM_INVISIBLE);
		return mo;
	}
}