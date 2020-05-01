/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.utilities;

public class Directions {
    // Constants
    public static final int INVALID = -2;
    public static final int NONE = -1;
    public static final int NORTHWEST = 0;
    public static final int NORTH = 1;
    public static final int NORTHEAST = 2;
    public static final int EAST = 3;
    public static final int SOUTHEAST = 4;
    public static final int SOUTH = 5;
    public static final int SOUTHWEST = 6;
    public static final int WEST = 7;
    public static final int COUNT = 8;
    public static final String NORTHWEST_NAME = "Northwest";
    public static final String NORTH_NAME = "North";
    public static final String NORTHEAST_NAME = "Northeast";
    public static final String EAST_NAME = "East";
    public static final String SOUTHEAST_NAME = "Southeast";
    public static final String SOUTH_NAME = "South";
    public static final String SOUTHWEST_NAME = "Southwest";
    public static final String WEST_NAME = "West";

    private Directions() {
        // Do nothing
    }
}
