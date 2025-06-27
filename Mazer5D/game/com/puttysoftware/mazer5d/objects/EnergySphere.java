/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.abc.MazeObjects;
import com.puttysoftware.mazer5d.objects.abc.GenericPass;

class EnergySphere extends GenericPass {
    // Constructors
    public EnergySphere() {
        super();
    }

    @Override
    protected String getNameHook() {
        return "Energy Sphere";
    }

    @Override
    protected String getPluralNameHook() {
        return "Energy Spheres";
    }

    @Override
    protected String getDescriptionHook() {
        return "Energy Spheres permit walking on Force Fields.";
    }

    @Override
    protected MazeObjects getUniqueIDHook() {
        return MazeObjects.ENERGY_SPHERE;
    }
}
