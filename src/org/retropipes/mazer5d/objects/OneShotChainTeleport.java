/*  Mazer5D: A Maze-Solving Game
    Copyright (C) 2008-2013 Eric Ahnell

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either formatVersion 3 of the License, or
    (at your option) any later formatVersion.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.asset.SoundGroup;
import org.retropipes.mazer5d.asset.SoundIndex;
import org.retropipes.mazer5d.editor.MazeEditor;
import org.retropipes.mazer5d.game.ObjectInventory;
import org.retropipes.mazer5d.gui.BagOStuff;
import org.retropipes.mazer5d.loader.SoundPlayer;
import org.retropipes.mazer5d.objects.abc.GenericTeleport;

class OneShotChainTeleport extends GenericTeleport {
    // Constructors
    public OneShotChainTeleport() {
	super(0, 0, 0);
    }

    public OneShotChainTeleport(final int destinationRow, final int destinationColumn, final int destinationFloor) {
	super(destinationRow, destinationColumn, destinationFloor);
    }

    // Scriptability
    @Override
    protected void customPostMoveAction(final boolean ie, final int dirX, final int dirY, final ObjectInventory inv) {
	final BagOStuff app = Mazer5D.getBagOStuff();
	app.getGameManager().decay();
	app.getGameManager().updatePositionAbsoluteNoEvents(this.getDestinationRow(), this.getDestinationColumn(),
		this.getDestinationFloor(), this.getDestinationLevel());
	SoundPlayer.playSound(SoundIndex.TELEPORT, SoundGroup.GAME);
    }

    @Override
    protected String getNameHook() {
	return "One-Shot Chain Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "One-Shot Chain Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_ONESHOT);
	return mo;
    }

    @Override
    protected String getDescriptionHook() {
	return "One-Shot Chain Teleports behave like regular Teleports, except they only work once.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.ONE_SHOT_CHAIN_TELEPORT;
    }
}