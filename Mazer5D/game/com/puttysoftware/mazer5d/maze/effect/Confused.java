/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.maze.effect;

import com.puttysoftware.diane.utilties.Directions;
import com.puttysoftware.randomrange.RandomRange;

public class Confused extends MazeEffect {
	// Fields
	private int state;
	private static final int CONFUSED_STATE_UDRL = 1;
	private static final int CONFUSED_STATE_ULDR = 2;
	private static final int CONFUSED_STATE_ULRD = 3;
	private static final int CONFUSED_STATE_URDL = 4;
	private static final int CONFUSED_STATE_URLD = 5;
	private static final int CONFUSED_STATE_DULR = 6;
	private static final int CONFUSED_STATE_DURL = 7;
	private static final int CONFUSED_STATE_DLUR = 8;
	private static final int CONFUSED_STATE_DLRU = 9;
	private static final int CONFUSED_STATE_DRUL = 10;
	private static final int CONFUSED_STATE_DRLU = 11;
	private static final int CONFUSED_STATE_LDUR = 12;
	private static final int CONFUSED_STATE_LDRU = 13;
	private static final int CONFUSED_STATE_LUDR = 14;
	private static final int CONFUSED_STATE_LURD = 15;
	private static final int CONFUSED_STATE_LRDU = 16;
	private static final int CONFUSED_STATE_LRUD = 17;
	private static final int CONFUSED_STATE_RDLU = 18;
	private static final int CONFUSED_STATE_RDUL = 19;
	private static final int CONFUSED_STATE_RLDU = 20;
	private static final int CONFUSED_STATE_RLUD = 21;
	private static final int CONFUSED_STATE_RUDL = 22;
	private static final int CONFUSED_STATE_RULD = 23;
	private static final int MIN_CONFUSED_STATE = 1;
	private static final int MAX_CONFUSED_STATE = 23;

	// Constructor
	public Confused(final int newRounds) {
		super("Confused", newRounds);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.state;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Confused)) {
			return false;
		}
		final Confused other = (Confused) obj;
		if (this.state != other.state) {
			return false;
		}
		return true;
	}

	@Override
	public void customExtendLogic() {
		if (this.rounds == 0) {
			final RandomRange r = new RandomRange(Confused.MIN_CONFUSED_STATE, Confused.MAX_CONFUSED_STATE);
			this.state = r.generate();
		}
	}

	@Override
	public int modifyMove1(final int arg) {
		switch (arg) {
			case Directions.NORTH:
				switch (this.state) {
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_URLD:
						return Directions.NORTH;
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_DRLU:
						return Directions.SOUTH;
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_LUDR:
					case CONFUSED_STATE_LURD:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_LRUD:
						return Directions.WEST;
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RDUL:
					case CONFUSED_STATE_RLDU:
					case CONFUSED_STATE_RLUD:
					case CONFUSED_STATE_RUDL:
					case CONFUSED_STATE_RULD:
						return Directions.EAST;
					default:
						break;
				}
				break;
			case Directions.SOUTH:
				switch (this.state) {
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_LUDR:
					case CONFUSED_STATE_LURD:
					case CONFUSED_STATE_RUDL:
					case CONFUSED_STATE_RULD:
						return Directions.NORTH;
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RDUL:
						return Directions.SOUTH;
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_RLDU:
					case CONFUSED_STATE_RLUD:
						return Directions.WEST;
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_URLD:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_DRLU:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_LRUD:
						return Directions.EAST;
					default:
						break;
				}
				break;
			case Directions.WEST:
				switch (this.state) {
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LRUD:
					case CONFUSED_STATE_RDUL:
					case CONFUSED_STATE_RLUD:
						return Directions.NORTH;
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_LUDR:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_RLDU:
					case CONFUSED_STATE_RUDL:
						return Directions.SOUTH;
					case CONFUSED_STATE_URLD:
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DRLU:
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RULD:
						return Directions.WEST;
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_LURD:
						return Directions.EAST;
					default:
						break;
				}
				break;
			case Directions.EAST:
				switch (this.state) {
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_DRLU:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RLDU:
						return Directions.NORTH;
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_URLD:
					case CONFUSED_STATE_LURD:
					case CONFUSED_STATE_LRUD:
					case CONFUSED_STATE_RLUD:
					case CONFUSED_STATE_RULD:
						return Directions.SOUTH;
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_RDUL:
					case CONFUSED_STATE_RUDL:
						return Directions.WEST;
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LUDR:
						return Directions.EAST;
					default:
						break;
				}
				break;
			case Directions.NORTHWEST:
				switch (this.state) {
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_URLD:
						return Directions.NORTHWEST;
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_DRLU:
						return Directions.SOUTHEAST;
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_LUDR:
					case CONFUSED_STATE_LURD:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_LRUD:
						return Directions.SOUTHWEST;
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RDUL:
					case CONFUSED_STATE_RLDU:
					case CONFUSED_STATE_RLUD:
					case CONFUSED_STATE_RUDL:
					case CONFUSED_STATE_RULD:
						return Directions.NORTHEAST;
					default:
						break;
				}
				break;
			case Directions.NORTHEAST:
				switch (this.state) {
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_LUDR:
					case CONFUSED_STATE_LURD:
					case CONFUSED_STATE_RUDL:
					case CONFUSED_STATE_RULD:
						return Directions.NORTHWEST;
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RDUL:
						return Directions.SOUTHEAST;
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_RLDU:
					case CONFUSED_STATE_RLUD:
						return Directions.SOUTHWEST;
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_URLD:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_DRLU:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_LRUD:
						return Directions.NORTHEAST;
					default:
						break;
				}
				break;
			case Directions.SOUTHWEST:
				switch (this.state) {
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LRUD:
					case CONFUSED_STATE_RDUL:
					case CONFUSED_STATE_RLUD:
						return Directions.NORTHWEST;
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_LUDR:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_RLDU:
					case CONFUSED_STATE_RUDL:
						return Directions.SOUTHEAST;
					case CONFUSED_STATE_URLD:
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DRLU:
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RULD:
						return Directions.SOUTHWEST;
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_LURD:
						return Directions.NORTHEAST;
					default:
						break;
				}
				break;
			case Directions.SOUTHEAST:
				switch (this.state) {
					case CONFUSED_STATE_DLRU:
					case CONFUSED_STATE_DRLU:
					case CONFUSED_STATE_LDRU:
					case CONFUSED_STATE_LRDU:
					case CONFUSED_STATE_RDLU:
					case CONFUSED_STATE_RLDU:
						return Directions.NORTHWEST;
					case CONFUSED_STATE_ULRD:
					case CONFUSED_STATE_URLD:
					case CONFUSED_STATE_LURD:
					case CONFUSED_STATE_LRUD:
					case CONFUSED_STATE_RLUD:
					case CONFUSED_STATE_RULD:
						return Directions.SOUTHEAST;
					case CONFUSED_STATE_UDRL:
					case CONFUSED_STATE_URDL:
					case CONFUSED_STATE_DURL:
					case CONFUSED_STATE_DRUL:
					case CONFUSED_STATE_RDUL:
					case CONFUSED_STATE_RUDL:
						return Directions.SOUTHWEST;
					case CONFUSED_STATE_ULDR:
					case CONFUSED_STATE_DULR:
					case CONFUSED_STATE_DLUR:
					case CONFUSED_STATE_LDUR:
					case CONFUSED_STATE_LUDR:
						return Directions.NORTHEAST;
					default:
						break;
				}
				break;
			default:
				break;
		}
		return 0;
	}
}