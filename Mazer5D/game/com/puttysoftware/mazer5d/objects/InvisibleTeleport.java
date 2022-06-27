/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.objects.abc.GenericInvisibleTeleport;
import com.puttysoftware.mazer5d.utility.MazeObjects;

class InvisibleTeleport extends GenericInvisibleTeleport {
    // Constructors
    public InvisibleTeleport() {
	super(0, 0, 0);
    }

    public InvisibleTeleport(final int destinationRow, final int destinationColumn, final int destinationFloor) {
	super(destinationRow, destinationColumn, destinationFloor);
    }

    // Scriptability
    @Override
    protected String getNameHook() {
	return "Invisible Teleport";
    }

    @Override
    public String getGameName() {
	return "Empty";
    }

    @Override
    protected String getPluralNameHook() {
	return "Invisible Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
	final MazeEditor me = Mazer5D.getBagOStuff().getEditor();
	final MazeObject mo = me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_INVISIBLE_GENERIC);
	return mo;
    }

    @Override
    protected String getDescriptionHook() {
	return "Invisible Teleports behave like regular teleports, except for the fact that they can't be seen.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
	return MazeObjects.INVISIBLE_TELEPORT;
    }
}