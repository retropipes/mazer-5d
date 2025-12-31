/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package org.retropipes.mazer5d.objects;

import org.retropipes.mazer5d.Mazer5D;
import org.retropipes.mazer5d.abc.MazeObject;
import org.retropipes.mazer5d.abc.MazeObjects;
import org.retropipes.mazer5d.editor.MazeEditor;
import org.retropipes.mazer5d.objects.abc.GenericTeleport;

class Teleport extends GenericTeleport {
    // Constructors
    public Teleport() {
	super(0, 0, 0);
    }

    public Teleport(final int destinationRow, final int destinationColumn, final int destinationFloor) {
	super(destinationRow, destinationColumn, destinationFloor);
    }

    @Override
    protected String getNameHook() {
	return "Teleport";
    }

    @Override
    protected String getPluralNameHook() {
	return "Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_GENERIC);
	return mo;
    }

    @Override
    protected String getDescriptionHook() {
	return "Teleports send you to a predetermined destination when stepped on.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.TELEPORT;
    }
}