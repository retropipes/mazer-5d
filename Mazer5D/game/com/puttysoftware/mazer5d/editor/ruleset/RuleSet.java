package com.puttysoftware.mazer5d.editor.ruleset;

import java.io.IOException;
import java.util.Objects;

import com.puttysoftware.mazer5d.abc.RandomGenerationRule;
import com.puttysoftware.mazer5d.file.io.MazeDataReader;
import com.puttysoftware.mazer5d.file.io.MazeDataWriter;
import com.puttysoftware.mazer5d.maze.Maze;
import com.puttysoftware.randomrange.RandomRange;

public final class RuleSet implements Cloneable, RandomGenerationRule {
	// Fields
	private int minQuantity;
	private int maxQuantity;
	private boolean percentageFlag;
	private boolean required;
	private int generateQuantity;
	private final RandomRange rng;

	// Constructor
	public RuleSet() {
		this.maxQuantity = 0;
		this.minQuantity = 0;
		this.percentageFlag = false;
		this.required = true;
		this.generateQuantity = 100;
		this.rng = new RandomRange(1, 100);
	}

	public RuleSet(final RuleSet source) {
		this.maxQuantity = source.maxQuantity;
		this.minQuantity = source.minQuantity;
		this.percentageFlag = source.percentageFlag;
		this.required = source.required;
		this.generateQuantity = source.generateQuantity;
		this.rng = source.rng;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.generateQuantity, this.maxQuantity, this.minQuantity, this.percentageFlag,
				this.required);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RuleSet)) {
			return false;
		}
		RuleSet other = (RuleSet) obj;
		return this.generateQuantity == other.generateQuantity && this.maxQuantity == other.maxQuantity
				&& this.minQuantity == other.minQuantity && this.percentageFlag == other.percentageFlag
				&& this.required == other.required;
	}

	// Methods
	public void setQuantityAbsolute(final int min, final int max) {
		// Check for valid arguments
		if (min < 0) {
			throw new IllegalArgumentException("Minimum must be at least zero");
		}
		if (max < 0) {
			throw new IllegalArgumentException("Maximum must be at least zero");
		}
		if (max < min) {
			throw new IllegalArgumentException("Minimum must be less than Maximum");
		}
		this.minQuantity = min;
		this.maxQuantity = max;
		this.percentageFlag = false;
	}

	public void setQuantityRelative(final int min, final int max) {
		// Check for valid arguments
		if (min < 0) {
			throw new IllegalArgumentException("Minimum must be at least zero");
		}
		if (max < 0) {
			throw new IllegalArgumentException("Maximum must be at least zero");
		}
		if (max < min) {
			throw new IllegalArgumentException("Minimum must be less than Maximum");
		}
		if (min > 100) {
			throw new IllegalArgumentException("Minimum must not be more than 100%");
		}
		if (max > 100) {
			throw new IllegalArgumentException("Maximum must not be more than 100%");
		}
		if (max - min > 100) {
			throw new IllegalArgumentException("Difference between Maximum and Minimum must not be more than 100%");
		}
		this.minQuantity = min;
		this.maxQuantity = max;
		this.percentageFlag = true;
	}

	public void setGenerateQuantity(final int value) {
		// Check for valid arguments
		if (value < 0) {
			throw new IllegalArgumentException("Value must be at least zero");
		}
		if (value > 100) {
			throw new IllegalArgumentException("Value must be less than 100");
		}
		this.generateQuantity = value;
	}

	public void setRequired(final boolean newReq) {
		this.required = newReq;
	}

	public boolean getPercentageFlag() {
		return this.percentageFlag;
	}

	public int getGenerateQuantity() {
		return this.generateQuantity;
	}

	public void readRuleSet(final MazeDataReader reader, final int rsFormat) throws IOException {
		this.maxQuantity = reader.readInt();
		this.minQuantity = reader.readInt();
		this.percentageFlag = reader.readBoolean();
		if (rsFormat == RuleSetConstants.FORMAT_2) {
			this.required = reader.readBoolean();
			this.generateQuantity = reader.readInt();
		} else {
			this.required = true;
			this.generateQuantity = 100;
		}
	}

	public void writeRuleSet(final MazeDataWriter writer) throws IOException {
		writer.writeInt(this.maxQuantity);
		writer.writeInt(this.minQuantity);
		writer.writeBoolean(this.percentageFlag);
		writer.writeBoolean(this.required);
		writer.writeInt(this.generateQuantity);
	}

	public int getMaximumRequiredQuantity() {
		return this.maxQuantity;
	}

	public int getMinimumRequiredQuantity() {
		return this.minQuantity;
	}

	/**
	 * Methods implementing RandomGenerationRule
	 */
	@Override
	public int getMaximumRequiredQuantity(final Maze maze) {
		if (this.percentageFlag) {
			final int base = maze.getRows() * maze.getColumns();
			final double factor = this.maxQuantity / 100.0;
			return (int) (base * factor);
		} else {
			return this.maxQuantity;
		}
	}

	@Override
	public int getMinimumRequiredQuantity(final Maze maze) {
		if (this.percentageFlag) {
			final int base = maze.getRows() * maze.getColumns();
			final double factor = this.minQuantity / 100.0;
			return (int) (base * factor);
		} else {
			return this.minQuantity;
		}
	}

	@Override
	public boolean isRequired() {
		return this.required;
	}

	@Override
	public boolean shouldGenerateObject(final Maze maze, final int row, final int col, final int floor, final int level,
			final int layer) {
		final int genval = this.rng.generate();
		return genval <= this.generateQuantity;
	}
}
