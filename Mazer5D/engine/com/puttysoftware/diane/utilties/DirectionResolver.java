/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2020 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.diane.utilties;

public class DirectionResolver {
	public static final int resolve(final int dirX, final int dirY) {
		final int fdX = (int) Math.signum(dirX);
		final int fdY = (int) Math.signum(dirY);
		if (fdX == 0 && fdY == 0) {
			return Directions.NONE;
		} else if (fdX == 0 && fdY == -1) {
			return Directions.NORTH;
		} else if (fdX == 0 && fdY == 1) {
			return Directions.SOUTH;
		} else if (fdX == -1 && fdY == 0) {
			return Directions.WEST;
		} else if (fdX == 1 && fdY == 0) {
			return Directions.EAST;
		} else if (fdX == 1 && fdY == 1) {
			return Directions.SOUTHEAST;
		} else if (fdX == -1 && fdY == 1) {
			return Directions.SOUTHWEST;
		} else if (fdX == -1 && fdY == -1) {
			return Directions.NORTHWEST;
		} else if (fdX == 1 && fdY == -1) {
			return Directions.NORTHEAST;
		} else {
			return Directions.INVALID;
		}
	}

	public static final int[] unresolve(final int dir) {
		int[] res = new int[2];
		if (dir == Directions.NONE) {
			res[0] = 0;
			res[1] = 0;
		} else if (dir == Directions.NORTH) {
			res[0] = 0;
			res[1] = -1;
		} else if (dir == Directions.SOUTH) {
			res[0] = 0;
			res[1] = 1;
		} else if (dir == Directions.WEST) {
			res[0] = -1;
			res[1] = 0;
		} else if (dir == Directions.EAST) {
			res[0] = 1;
			res[1] = 0;
		} else if (dir == Directions.SOUTHEAST) {
			res[0] = 1;
			res[1] = 1;
		} else if (dir == Directions.SOUTHWEST) {
			res[0] = -1;
			res[1] = 1;
		} else if (dir == Directions.NORTHWEST) {
			res[0] = -1;
			res[1] = -1;
		} else if (dir == Directions.NORTHEAST) {
			res[0] = 1;
			res[1] = -1;
		} else {
			res = null;
		}
		return res;
	}

	public static final String resolveToName(final int dir) {
		String res = null;
		if (dir == Directions.NORTH) {
			res = Directions.NORTH_NAME;
		} else if (dir == Directions.SOUTH) {
			res = Directions.SOUTH_NAME;
		} else if (dir == Directions.WEST) {
			res = Directions.WEST_NAME;
		} else if (dir == Directions.EAST) {
			res = Directions.EAST_NAME;
		} else if (dir == Directions.SOUTHEAST) {
			res = Directions.SOUTHEAST_NAME;
		} else if (dir == Directions.SOUTHWEST) {
			res = Directions.SOUTHWEST_NAME;
		} else if (dir == Directions.NORTHWEST) {
			res = Directions.NORTHWEST_NAME;
		} else if (dir == Directions.NORTHEAST) {
			res = Directions.NORTHEAST_NAME;
		}
		return res;
	}

	private DirectionResolver() {
		// Do nothing
	}
}
