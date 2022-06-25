/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.abc.MazeObject;
import com.puttysoftware.mazer5d.files.format.FormatConstants;
import com.puttysoftware.mazer5d.files.io.MazeDataReader;
import com.puttysoftware.mazer5d.files.io.MazeDataWriter;
import com.puttysoftware.mazer5d.loaders.DataLoader;
import com.puttysoftware.mazer5d.loaders.ObjectImageLoader;
import com.puttysoftware.mazer5d.locale.StaticStrings;
import com.puttysoftware.mazer5d.objects.abc.GenericSingleKey;
import com.puttysoftware.mazer5d.utilities.Layers;
import com.puttysoftware.mazer5d.utilities.MazeObjects;
import com.puttysoftware.mazer5d.utilities.TypeConstants;

public class GameObjects {
    // Fields
    private static final TreeMap<MazeObjects, MazeObject> allObjectsLookup = new TreeMap<>();
    private static MazeObject[] allObjects;
    private static MazeObjects[] allObjectUIDs;
    static {
	GameObjects.allObjectUIDs = new MazeObjects[] { MazeObjects.AMETHYST, MazeObjects.ANNIHILATION_WAND,
		MazeObjects.A_PLUG, MazeObjects.A_PORT, MazeObjects.AQUA_BOOTS, MazeObjects.ARROW,
		MazeObjects.ARROW_TRAP, MazeObjects.AXE, MazeObjects.BARRIER_GENERATOR, MazeObjects.BIO_HAZARD_BOOTS,
		MazeObjects.BLACK_CRYSTAL, MazeObjects.BLUE_BUTTON, MazeObjects.BLUE_CARPET, MazeObjects.BLUE_CRYSTAL,
		MazeObjects.BLUE_HOUSE, MazeObjects.BLUE_KEY, MazeObjects.BLUE_LOCK, MazeObjects.BLUE_WALL_OFF,
		MazeObjects.BLUE_WALL_ON, MazeObjects.BOW, MazeObjects.B_PLUG, MazeObjects.B_PORT,
		MazeObjects.BREAKABLE_WALL_HORIZONTAL, MazeObjects.BREAKABLE_WALL_VERTICAL, MazeObjects.BRICK_WALL,
		MazeObjects.BRIGHTNESS_GEM, MazeObjects.CHAIN_TELEPORT, MazeObjects.CLOCKWISE_ROTATION_TRAP,
		MazeObjects.CONDITIONAL_CHAIN_TELEPORT, MazeObjects.CONDITIONAL_TELEPORT, MazeObjects.CONFUSION_TRAP,
		MazeObjects.CONTROLLABLE_TELEPORT, MazeObjects.COUNTERCLOCKWISE_ROTATION_TRAP,
		MazeObjects.COUNTERPOISON_AMULET, MazeObjects.C_PLUG, MazeObjects.C_PORT, MazeObjects.CRACKED_WALL,
		MazeObjects.CREVASSE, MazeObjects.CRUMBLING_WALL, MazeObjects.CRYSTAL_WALL, MazeObjects.CUT_TREE,
		MazeObjects.CYAN_BUTTON, MazeObjects.CYAN_CARPET, MazeObjects.CYAN_CRYSTAL, MazeObjects.CYAN_HOUSE,
		MazeObjects.CYAN_KEY, MazeObjects.CYAN_LOCK, MazeObjects.CYAN_WALL_OFF, MazeObjects.CYAN_WALL_ON,
		MazeObjects.DAMAGEABLE_WALL, MazeObjects.DAMAGED_WALL, MazeObjects.DARK_BLUE_CRYSTAL,
		MazeObjects.DARK_CYAN_CRYSTAL, MazeObjects.DARK_GEM, MazeObjects.DARK_GRAY_CRYSTAL,
		MazeObjects.DARK_GREEN_CRYSTAL, MazeObjects.DARK_MAGENTA_CRYSTAL, MazeObjects.DARKNESS,
		MazeObjects.DARKNESS_GEM, MazeObjects.DARK_RED_CRYSTAL, MazeObjects.DARK_WAND,
		MazeObjects.DARK_YELLOW_CRYSTAL, MazeObjects.DESTINATION, MazeObjects.DIAMOND, MazeObjects.DIMNESS_GEM,
		MazeObjects.DIRT, MazeObjects.DISARM_TRAP_WAND, MazeObjects.DIZZINESS_TRAP, MazeObjects.DOOR,
		MazeObjects.DOUBLE_HOURGLASS, MazeObjects.D_PLUG, MazeObjects.D_PORT, MazeObjects.DRUNK_TRAP,
		MazeObjects.EMPTY, MazeObjects.BOUNDS, MazeObjects.ENERGY_SPHERE, MazeObjects.ENRAGED_BARRIER_GENERATOR,
		MazeObjects.E_PLUG, MazeObjects.E_PORT, MazeObjects.EXIT, MazeObjects.EXPLODING_WALL,
		MazeObjects.EXPLORE_TRAP, MazeObjects.FADING_WALL, MazeObjects.FAKE_FINISH, MazeObjects.FAKE_WALL,
		MazeObjects.FINISH, MazeObjects.FINISH_MAKING_WAND, MazeObjects.FINISH_TO, MazeObjects.FIRE_AMULET,
		MazeObjects.FIRE_ARROW, MazeObjects.FIRE_BOMB, MazeObjects.FIRE_BOOTS, MazeObjects.FIRE_BOW,
		MazeObjects.FORCE_FIELD, MazeObjects.F_PLUG, MazeObjects.F_PORT, MazeObjects.GARNET_SQUARE,
		MazeObjects.GARNET_WALL, MazeObjects.GHOST_AMULET, MazeObjects.GHOST_ARROW, MazeObjects.GHOST_BOW,
		MazeObjects.GLUE_BOOTS, MazeObjects.GOLDEN_SQUARE, MazeObjects.GOLDEN_WALL, MazeObjects.G_PLUG,
		MazeObjects.G_PORT, MazeObjects.GRASS, MazeObjects.GRAY_CRYSTAL, MazeObjects.GREEN_BUTTON,
		MazeObjects.GREEN_CARPET, MazeObjects.GREEN_CRYSTAL, MazeObjects.GREEN_HOUSE, MazeObjects.GREEN_KEY,
		MazeObjects.GREEN_LOCK, MazeObjects.GREEN_WALL_OFF, MazeObjects.GREEN_WALL_ON,
		MazeObjects.HALF_HOURGLASS, MazeObjects.HAMMER, MazeObjects.HEAL_BOOTS, MazeObjects.HEAL_TRAP,
		MazeObjects.HORIZONTAL_BARRIER, MazeObjects.HOT_BOOTS, MazeObjects.HOT_ROCK, MazeObjects.HOURGLASS,
		MazeObjects.H_PLUG, MazeObjects.H_PORT, MazeObjects.HURT_TRAP, MazeObjects.ICE, MazeObjects.ICE_AMULET,
		MazeObjects.ICE_ARROW, MazeObjects.ICE_BOMB, MazeObjects.ICE_BOW, MazeObjects.ICED_BARRIER_GENERATOR,
		MazeObjects.INVISIBLE_CHAIN_TELEPORT, MazeObjects.INVISIBLE_CONDITIONAL_CHAIN_TELEPORT,
		MazeObjects.INVISIBLE_CONDITIONAL_TELEPORT, MazeObjects.INVISIBLE_ONE_SHOT_CHAIN_TELEPORT,
		MazeObjects.INVISIBLE_ONE_SHOT_CONDITIONAL_TELEPORT, MazeObjects.INVISIBLE_ONE_SHOT_TELEPORT,
		MazeObjects.INVISIBLE_PIT, MazeObjects.INVISIBLE_SPRINGBOARD, MazeObjects.INVISIBLE_TELEPORT,
		MazeObjects.INVISIBLE_WALL, MazeObjects.I_PLUG, MazeObjects.I_PORT, MazeObjects.J_PLUG,
		MazeObjects.J_PORT, MazeObjects.KEY, MazeObjects.K_PLUG, MazeObjects.K_PORT, MazeObjects.LAVA,
		MazeObjects.LIGHT_BLUE_CRYSTAL, MazeObjects.LIGHT_CYAN_CRYSTAL, MazeObjects.LIGHT_GEM,
		MazeObjects.LIGHT_GRAY_CRYSTAL, MazeObjects.LIGHT_GREEN_CRYSTAL, MazeObjects.LIGHT_MAGENTA_CRYSTAL,
		MazeObjects.LIGHTNESS_GEM, MazeObjects.LIGHT_RED_CRYSTAL, MazeObjects.LIGHT_WAND,
		MazeObjects.LIGHT_YELLOW_CRYSTAL, MazeObjects.LOCK, MazeObjects.L_PLUG, MazeObjects.L_PORT,
		MazeObjects.MAGENTA_BUTTON, MazeObjects.MAGENTA_CARPET, MazeObjects.MAGENTA_CRYSTAL,
		MazeObjects.MAGENTA_HOUSE, MazeObjects.MAGENTA_KEY, MazeObjects.MAGENTA_LOCK,
		MazeObjects.MAGENTA_WALL_OFF, MazeObjects.MAGENTA_WALL_ON, MazeObjects.MAJOR_HEAL_POTION,
		MazeObjects.MAJOR_HURT_POTION, MazeObjects.MAJOR_UNKNOWN_POTION, MazeObjects.MASTER_TRAPPED_WALL,
		MazeObjects.MASTER_WALL_TRAP, MazeObjects.METAL_BOOTS, MazeObjects.METAL_BUTTON, MazeObjects.METAL_DOOR,
		MazeObjects.METAL_KEY, MazeObjects.MINOR_HEAL_POTION, MazeObjects.MINOR_HURT_POTION,
		MazeObjects.MINOR_UNKNOWN_POTION, MazeObjects.MOON_STONE, MazeObjects.MOVING_BLOCK,
		MazeObjects.MOVING_FINISH, MazeObjects.M_PLUG, MazeObjects.M_PORT, MazeObjects.NO_BOOTS,
		MazeObjects.NO_EXPLORE_TRAP, MazeObjects.NORMAL_AMULET, MazeObjects.N_PLUG, MazeObjects.N_PORT,
		MazeObjects.ONE_SHOT_CHAIN_TELEPORT, MazeObjects.ONE_SHOT_CONDITIONAL_TELEPORT,
		MazeObjects.ONE_SHOT_CONTROLLABLE_TELEPORT, MazeObjects.ONE_SHOT_TELEPORT,
		MazeObjects.ONE_WAY_EAST_WALL, MazeObjects.ONE_WAY_NORTH_WALL, MazeObjects.ONE_WAY_SOUTH_WALL,
		MazeObjects.ONE_WAY_WEST_WALL, MazeObjects.O_PLUG, MazeObjects.O_PORT, MazeObjects.ORANGE_BUTTON,
		MazeObjects.ORANGE_CARPET, MazeObjects.ORANGE_CRYSTAL, MazeObjects.ORANGE_HOUSE, MazeObjects.ORANGE_KEY,
		MazeObjects.ORANGE_LOCK, MazeObjects.ORANGE_WALL_OFF, MazeObjects.ORANGE_WALL_ON,
		MazeObjects.PASSWALL_BOOTS, MazeObjects.PIT, MazeObjects.PLANT_CRYSTAL, MazeObjects.PLAYER,
		MazeObjects.POISON_ARROW, MazeObjects.POISON_BOMB, MazeObjects.POISON_BOW,
		MazeObjects.POISONED_BARRIER_GENERATOR, MazeObjects.POISONOUS_AMULET, MazeObjects.P_PLUG,
		MazeObjects.P_PORT, MazeObjects.PULLABLE_BLOCK, MazeObjects.PULLABLE_BLOCK_ONCE,
		MazeObjects.PULLABLE_BLOCK_THRICE, MazeObjects.PULLABLE_BLOCK_TWICE, MazeObjects.PURPLE_BUTTON,
		MazeObjects.PURPLE_CARPET, MazeObjects.PURPLE_CRYSTAL, MazeObjects.PURPLE_HOUSE, MazeObjects.PURPLE_KEY,
		MazeObjects.PURPLE_LOCK, MazeObjects.PURPLE_WALL_OFF, MazeObjects.PURPLE_WALL_ON,
		MazeObjects.PUSHABLE_BLOCK, MazeObjects.PUSHABLE_BLOCK_ONCE, MazeObjects.PUSHABLE_BLOCK_THRICE,
		MazeObjects.PUSHABLE_BLOCK_TWICE, MazeObjects.PUSHABLE_PULLABLE_BLOCK, MazeObjects.Q_PLUG,
		MazeObjects.Q_PORT, MazeObjects.QUAKE_BOMB, MazeObjects.RANDOM_INVISIBLE_ONE_SHOT_TELEPORT,
		MazeObjects.RANDOM_INVISIBLE_TELEPORT, MazeObjects.RANDOM_ONE_SHOT_TELEPORT,
		MazeObjects.RANDOM_TELEPORT, MazeObjects.RED_BUTTON, MazeObjects.RED_CARPET, MazeObjects.RED_CRYSTAL,
		MazeObjects.RED_HOUSE, MazeObjects.RED_KEY, MazeObjects.RED_LOCK, MazeObjects.RED_WALL_OFF,
		MazeObjects.RED_WALL_ON, MazeObjects.REGULAR_BOOTS, MazeObjects.REMOTE_ACTION_WAND,
		MazeObjects.ROSE_BUTTON, MazeObjects.ROSE_CARPET, MazeObjects.ROSE_CRYSTAL, MazeObjects.ROSE_HOUSE,
		MazeObjects.ROSE_KEY, MazeObjects.ROSE_LOCK, MazeObjects.ROSE_WALL_OFF, MazeObjects.ROSE_WALL_ON,
		MazeObjects.ROTATION_TRAP, MazeObjects.ROTATION_WAND, MazeObjects.R_PLUG, MazeObjects.R_PORT,
		MazeObjects.RUBY, MazeObjects.RUBY_SQUARE, MazeObjects.RUBY_WALL, MazeObjects.SAND,
		MazeObjects.SAPPHIRE, MazeObjects.SAPPHIRE_SQUARE, MazeObjects.SAPPHIRE_WALL, MazeObjects.SEALED_FINISH,
		MazeObjects.SEALING_WALL, MazeObjects.SEAWEED_BUTTON, MazeObjects.SEAWEED_CARPET,
		MazeObjects.SEAWEED_CRYSTAL, MazeObjects.SEAWEED_HOUSE, MazeObjects.SEAWEED_KEY,
		MazeObjects.SEAWEED_LOCK, MazeObjects.SEAWEED_WALL_OFF, MazeObjects.SEAWEED_WALL_ON,
		MazeObjects.SHOCK_ARROW, MazeObjects.SHOCK_BOMB, MazeObjects.SHOCK_BOW,
		MazeObjects.SHOCKED_BARRIER_GENERATOR, MazeObjects.SHUFFLE_BOMB, MazeObjects.SIGN,
		MazeObjects.SIGNAL_CRYSTAL, MazeObjects.SILVER_SQUARE, MazeObjects.SILVER_WALL, MazeObjects.SKY_BUTTON,
		MazeObjects.SKY_CARPET, MazeObjects.SKY_CRYSTAL, MazeObjects.SKY_HOUSE, MazeObjects.SKY_KEY,
		MazeObjects.SKY_LOCK, MazeObjects.SKY_WALL_OFF, MazeObjects.SKY_WALL_ON, MazeObjects.SLIME,
		MazeObjects.SLIPPERY_BOOTS, MazeObjects.SNOW, MazeObjects.S_PLUG, MazeObjects.S_PORT,
		MazeObjects.SPRINGBOARD, MazeObjects.STAIRS_DOWN, MazeObjects.STAIRS_UP, MazeObjects.STUMP,
		MazeObjects.SUNKEN_BLOCK, MazeObjects.SUN_STONE, MazeObjects.SUPER_HEAL_POTION,
		MazeObjects.SUPER_HURT_POTION, MazeObjects.SUPER_UNKNOWN_POTION, MazeObjects.TABLET,
		MazeObjects.TABLET_SLOT, MazeObjects.TELEPORT, MazeObjects.TELEPORT_WAND, MazeObjects.TILE,
		MazeObjects.TOPAZ_SQUARE, MazeObjects.TOPAZ_WALL, MazeObjects.T_PLUG, MazeObjects.T_PORT,
		MazeObjects.TRAPPED_WALL_0, MazeObjects.TRAPPED_WALL_1, MazeObjects.TRAPPED_WALL_2,
		MazeObjects.TRAPPED_WALL_3, MazeObjects.TRAPPED_WALL_4, MazeObjects.TRAPPED_WALL_5,
		MazeObjects.TRAPPED_WALL_6, MazeObjects.TRAPPED_WALL_7, MazeObjects.TRAPPED_WALL_8,
		MazeObjects.TRAPPED_WALL_9, MazeObjects.TRAPPED_WALL_10, MazeObjects.TRAPPED_WALL_11,
		MazeObjects.TRAPPED_WALL_12, MazeObjects.TRAPPED_WALL_13, MazeObjects.TRAPPED_WALL_14,
		MazeObjects.TRAPPED_WALL_15, MazeObjects.TRAPPED_WALL_16, MazeObjects.TRAPPED_WALL_17,
		MazeObjects.TRAPPED_WALL_18, MazeObjects.TRAPPED_WALL_19, MazeObjects.TREASURE_CHEST, MazeObjects.TREE,
		MazeObjects.TRUE_SIGHT_AMULET, MazeObjects.TUNDRA, MazeObjects.TWO_WAY_TELEPORT, MazeObjects.U_PLUG,
		MazeObjects.U_PORT, MazeObjects.U_TURN_TRAP, MazeObjects.VARIABLE_HEAL_TRAP,
		MazeObjects.VARIABLE_HURT_TRAP, MazeObjects.VERTICAL_BARRIER, MazeObjects.V_PLUG, MazeObjects.V_PORT,
		MazeObjects.WALL, MazeObjects.WALL_BREAKING_WAND, MazeObjects.WALL_MAKING_TRAP,
		MazeObjects.WALL_MAKING_WAND, MazeObjects.WALL_TRAP_0, MazeObjects.WALL_TRAP_1, MazeObjects.WALL_TRAP_2,
		MazeObjects.WALL_TRAP_3, MazeObjects.WALL_TRAP_4, MazeObjects.WALL_TRAP_5, MazeObjects.WALL_TRAP_6,
		MazeObjects.WALL_TRAP_7, MazeObjects.WALL_TRAP_8, MazeObjects.WALL_TRAP_9, MazeObjects.WALL_TRAP_10,
		MazeObjects.WALL_TRAP_11, MazeObjects.WALL_TRAP_12, MazeObjects.WALL_TRAP_13, MazeObjects.WALL_TRAP_14,
		MazeObjects.WALL_TRAP_15, MazeObjects.WALL_TRAP_16, MazeObjects.WALL_TRAP_17, MazeObjects.WALL_TRAP_18,
		MazeObjects.WALL_TRAP_19, MazeObjects.WARP_BOMB, MazeObjects.WARP_TRAP, MazeObjects.WARP_WAND,
		MazeObjects.WATER, MazeObjects.WHITE_BUTTON, MazeObjects.WHITE_CARPET, MazeObjects.WHITE_CRYSTAL,
		MazeObjects.WHITE_HOUSE, MazeObjects.WHITE_KEY, MazeObjects.WHITE_LOCK, MazeObjects.WHITE_WALL_OFF,
		MazeObjects.WHITE_WALL_ON, MazeObjects.W_PLUG, MazeObjects.W_PORT, MazeObjects.X_PLUG,
		MazeObjects.X_PORT, MazeObjects.YELLOW_BUTTON, MazeObjects.YELLOW_CARPET, MazeObjects.YELLOW_CRYSTAL,
		MazeObjects.YELLOW_HOUSE, MazeObjects.YELLOW_KEY, MazeObjects.YELLOW_LOCK, MazeObjects.YELLOW_WALL_OFF,
		MazeObjects.YELLOW_WALL_ON, MazeObjects.Y_PLUG, MazeObjects.Y_PORT, MazeObjects.Z_PLUG,
		MazeObjects.Z_PORT, MazeObjects._NONE };
	GameObjects.allObjectsLookup.put(MazeObjects._NONE, null);
	GameObjects.allObjectsLookup.put(MazeObjects.AMETHYST, new MazeObject(MazeObjects.AMETHYST));
	GameObjects.allObjectsLookup.put(MazeObjects.ANNIHILATION_WAND, new AnnihilationWand());
	GameObjects.allObjectsLookup.put(MazeObjects.A_PLUG, new APlug());
	GameObjects.allObjectsLookup.put(MazeObjects.A_PORT, new APort());
	GameObjects.allObjectsLookup.put(MazeObjects.AQUA_BOOTS, new AquaBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.ARROW, null);
	GameObjects.allObjectsLookup.put(MazeObjects.ARROW_TRAP, new ArrowTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.AXE, new Axe());
	GameObjects.allObjectsLookup.put(MazeObjects.BARRIER_GENERATOR, new BarrierGenerator());
	GameObjects.allObjectsLookup.put(MazeObjects.BIO_HAZARD_BOOTS, new BioHazardBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.BLACK_CRYSTAL, new BlackCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_BUTTON, new BlueButton());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_CARPET, new BlueCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_CRYSTAL, new BlueCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_HOUSE, new BlueHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_KEY, new BlueKey());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_LOCK, new BlueLock());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_WALL_OFF, new BlueWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.BLUE_WALL_ON, new BlueWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.BOW, new Bow());
	GameObjects.allObjectsLookup.put(MazeObjects.B_PLUG, new BPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.B_PORT, new BPort());
	GameObjects.allObjectsLookup.put(MazeObjects.BREAKABLE_WALL_HORIZONTAL, new BreakableWallHorizontal());
	GameObjects.allObjectsLookup.put(MazeObjects.BREAKABLE_WALL_VERTICAL, new BreakableWallVertical());
	GameObjects.allObjectsLookup.put(MazeObjects.BRICK_WALL, new BrickWall());
	GameObjects.allObjectsLookup.put(MazeObjects.BRIGHTNESS_GEM, new BrightnessGem());
	GameObjects.allObjectsLookup.put(MazeObjects.CHAIN_TELEPORT, new ChainTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.CLOCKWISE_ROTATION_TRAP, new ClockwiseRotationTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.CONDITIONAL_CHAIN_TELEPORT, new ConditionalChainTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.CONDITIONAL_TELEPORT, new ConditionalTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.CONFUSION_TRAP, new ConfusionTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.CONTROLLABLE_TELEPORT, new ControllableTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.COUNTERCLOCKWISE_ROTATION_TRAP,
		new CounterclockwiseRotationTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.COUNTERPOISON_AMULET, new CounterpoisonAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.C_PLUG, new CPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.C_PORT, new CPort());
	GameObjects.allObjectsLookup.put(MazeObjects.CRACKED_WALL, new CrackedWall());
	GameObjects.allObjectsLookup.put(MazeObjects.CREVASSE, new Crevasse());
	GameObjects.allObjectsLookup.put(MazeObjects.CRUMBLING_WALL, new CrumblingWall());
	GameObjects.allObjectsLookup.put(MazeObjects.CRYSTAL_WALL, new CrystalWall());
	GameObjects.allObjectsLookup.put(MazeObjects.CUT_TREE, new CutTree());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_BUTTON, new CyanButton());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_CARPET, new CyanCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_CRYSTAL, new CyanCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_HOUSE, new CyanHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_KEY, new CyanKey());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_LOCK, new CyanLock());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_WALL_OFF, new CyanWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.CYAN_WALL_ON, new CyanWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.DAMAGEABLE_WALL, new DamageableWall());
	GameObjects.allObjectsLookup.put(MazeObjects.DAMAGED_WALL, new DamagedWall());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_BLUE_CRYSTAL, new DarkBlueCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_CYAN_CRYSTAL, new DarkCyanCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_GEM, new DarkGem());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_GRAY_CRYSTAL, new DarkGrayCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_GREEN_CRYSTAL, new DarkGreenCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_MAGENTA_CRYSTAL, new DarkMagentaCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DARKNESS, null);
	GameObjects.allObjectsLookup.put(MazeObjects.DARKNESS_GEM, new DarknessGem());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_RED_CRYSTAL, new DarkRedCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_WAND, new DarkWand());
	GameObjects.allObjectsLookup.put(MazeObjects.DARK_YELLOW_CRYSTAL, new DarkYellowCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.DESTINATION, null);
	GameObjects.allObjectsLookup.put(MazeObjects.DIAMOND, new MazeObject(MazeObjects.DIAMOND));
	GameObjects.allObjectsLookup.put(MazeObjects.DIMNESS_GEM, new DimnessGem());
	GameObjects.allObjectsLookup.put(MazeObjects.DIRT, new Dirt());
	GameObjects.allObjectsLookup.put(MazeObjects.DISARM_TRAP_WAND, new DisarmTrapWand());
	GameObjects.allObjectsLookup.put(MazeObjects.DIZZINESS_TRAP, new DizzinessTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.DOOR, new Door());
	GameObjects.allObjectsLookup.put(MazeObjects.DOUBLE_HOURGLASS, new DoubleHourglass());
	GameObjects.allObjectsLookup.put(MazeObjects.D_PLUG, new DPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.D_PORT, new DPort());
	GameObjects.allObjectsLookup.put(MazeObjects.DRUNK_TRAP, new DrunkTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.EMPTY, GameObjects.getEmptySpace());
	GameObjects.allObjectsLookup.put(MazeObjects.BOUNDS, new Bounds());
	GameObjects.allObjectsLookup.put(MazeObjects.ENERGY_SPHERE, new EnergySphere());
	GameObjects.allObjectsLookup.put(MazeObjects.ENRAGED_BARRIER_GENERATOR, new EnragedBarrierGenerator());
	GameObjects.allObjectsLookup.put(MazeObjects.E_PLUG, new EPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.E_PORT, new EPort());
	GameObjects.allObjectsLookup.put(MazeObjects.EXIT, new Exit());
	GameObjects.allObjectsLookup.put(MazeObjects.EXPLODING_WALL, new ExplodingWall());
	GameObjects.allObjectsLookup.put(MazeObjects.EXPLORE_TRAP, new ExploreTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.FADING_WALL, new FadingWall());
	GameObjects.allObjectsLookup.put(MazeObjects.FAKE_FINISH, new FakeFinish());
	GameObjects.allObjectsLookup.put(MazeObjects.FAKE_WALL, new FakeWall());
	GameObjects.allObjectsLookup.put(MazeObjects.FINISH, new Finish());
	GameObjects.allObjectsLookup.put(MazeObjects.FINISH_MAKING_WAND, new FinishMakingWand());
	GameObjects.allObjectsLookup.put(MazeObjects.FINISH_TO, new FinishTo());
	GameObjects.allObjectsLookup.put(MazeObjects.FIRE_AMULET, new FireAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.FIRE_ARROW, null);
	GameObjects.allObjectsLookup.put(MazeObjects.FIRE_BOMB, new FireBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.FIRE_BOOTS, new FireBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.FIRE_BOW, new FireBow());
	GameObjects.allObjectsLookup.put(MazeObjects.FORCE_FIELD, new ForceField());
	GameObjects.allObjectsLookup.put(MazeObjects.F_PLUG, new FPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.F_PORT, new FPort());
	GameObjects.allObjectsLookup.put(MazeObjects.GARNET_SQUARE, new GarnetSquare());
	GameObjects.allObjectsLookup.put(MazeObjects.GARNET_WALL, new GarnetWall());
	GameObjects.allObjectsLookup.put(MazeObjects.GHOST_AMULET, new GhostAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.GHOST_ARROW, null);
	GameObjects.allObjectsLookup.put(MazeObjects.GHOST_BOW, new GhostBow());
	GameObjects.allObjectsLookup.put(MazeObjects.GLUE_BOOTS, new GlueBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.GOLDEN_SQUARE, new GoldenSquare());
	GameObjects.allObjectsLookup.put(MazeObjects.GOLDEN_WALL, new GoldenWall());
	GameObjects.allObjectsLookup.put(MazeObjects.G_PLUG, new GPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.G_PORT, new GPort());
	GameObjects.allObjectsLookup.put(MazeObjects.GRASS, new Grass());
	GameObjects.allObjectsLookup.put(MazeObjects.GRAY_CRYSTAL, new GrayCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_BUTTON, new GreenButton());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_CARPET, new GreenCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_CRYSTAL, new GreenCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_HOUSE, new GreenHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_KEY, new GreenKey());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_LOCK, new GreenLock());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_WALL_OFF, new GreenWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.GREEN_WALL_ON, new GreenWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.HALF_HOURGLASS, new HalfHourglass());
	GameObjects.allObjectsLookup.put(MazeObjects.HAMMER, new Hammer());
	GameObjects.allObjectsLookup.put(MazeObjects.HEAL_BOOTS, new HealBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.HEAL_TRAP, new HealTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.HORIZONTAL_BARRIER, new HorizontalBarrier());
	GameObjects.allObjectsLookup.put(MazeObjects.HOT_BOOTS, new HotBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.HOT_ROCK, new HotRock());
	GameObjects.allObjectsLookup.put(MazeObjects.HOURGLASS, new Hourglass());
	GameObjects.allObjectsLookup.put(MazeObjects.H_PLUG, new HPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.H_PORT, new HPort());
	GameObjects.allObjectsLookup.put(MazeObjects.HURT_TRAP, new HurtTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.ICE, new Ice());
	GameObjects.allObjectsLookup.put(MazeObjects.ICE_AMULET, new IceAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.ICE_ARROW, null);
	GameObjects.allObjectsLookup.put(MazeObjects.ICE_BOMB, new IceBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.ICE_BOW, new IceBow());
	GameObjects.allObjectsLookup.put(MazeObjects.ICED_BARRIER_GENERATOR, new IcedBarrierGenerator());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_CHAIN_TELEPORT, new InvisibleChainTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_CONDITIONAL_CHAIN_TELEPORT,
		new InvisibleConditionalChainTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_CONDITIONAL_TELEPORT,
		new InvisibleConditionalTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_ONE_SHOT_CHAIN_TELEPORT,
		new InvisibleOneShotChainTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_ONE_SHOT_CONDITIONAL_TELEPORT,
		new InvisibleOneShotConditionalTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_ONE_SHOT_TELEPORT, new InvisibleOneShotTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_PIT, new InvisiblePit());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_SPRINGBOARD, new InvisibleSpringboard());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_TELEPORT, new InvisibleTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_WALL, new InvisibleWall());
	GameObjects.allObjectsLookup.put(MazeObjects.I_PLUG, new IPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.I_PORT, new IPort());
	GameObjects.allObjectsLookup.put(MazeObjects.J_PLUG, new JPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.J_PORT, new JPort());
	GameObjects.allObjectsLookup.put(MazeObjects.KEY, new Key());
	GameObjects.allObjectsLookup.put(MazeObjects.K_PLUG, new KPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.K_PORT, new KPort());
	GameObjects.allObjectsLookup.put(MazeObjects.LAVA, new Lava());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_BLUE_CRYSTAL, new LightBlueCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_CYAN_CRYSTAL, new LightCyanCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_GEM, new LightGem());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_GRAY_CRYSTAL, new LightGrayCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_GREEN_CRYSTAL, new LightGreenCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_MAGENTA_CRYSTAL, new LightMagentaCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHTNESS_GEM, new LightnessGem());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_RED_CRYSTAL, new LightRedCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_WAND, new LightWand());
	GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_YELLOW_CRYSTAL, new LightYellowCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.LOCK, new Lock());
	GameObjects.allObjectsLookup.put(MazeObjects.L_PLUG, new LPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.L_PORT, new LPort());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_BUTTON, new MagentaButton());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_CARPET, new MagentaCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_CRYSTAL, new MagentaCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_HOUSE, new MagentaHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_KEY, new MagentaKey());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_LOCK, new MagentaLock());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_WALL_OFF, new MagentaWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_WALL_ON, new MagentaWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.MAJOR_HEAL_POTION, new MajorHealPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.MAJOR_HURT_POTION, new MajorHurtPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.MAJOR_UNKNOWN_POTION, new MajorUnknownPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.MASTER_TRAPPED_WALL, new MasterTrappedWall());
	GameObjects.allObjectsLookup.put(MazeObjects.MASTER_WALL_TRAP, new MasterWallTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.METAL_BOOTS, new MetalBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.METAL_BUTTON, new MetalButton());
	GameObjects.allObjectsLookup.put(MazeObjects.METAL_DOOR, new MetalDoor());
	GameObjects.allObjectsLookup.put(MazeObjects.METAL_KEY, new MetalKey());
	GameObjects.allObjectsLookup.put(MazeObjects.MINOR_HEAL_POTION, new MinorHealPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.MINOR_HURT_POTION, new MinorHurtPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.MINOR_UNKNOWN_POTION, new MinorUnknownPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.MOON_STONE, new MoonStone());
	GameObjects.allObjectsLookup.put(MazeObjects.MOVING_BLOCK, new MovingBlock());
	GameObjects.allObjectsLookup.put(MazeObjects.MOVING_FINISH, new MovingFinish());
	GameObjects.allObjectsLookup.put(MazeObjects.M_PLUG, new MPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.M_PORT, new MPort());
	GameObjects.allObjectsLookup.put(MazeObjects.NO_BOOTS, new NoBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.NO_EXPLORE_TRAP, new NoExploreTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.NORMAL_AMULET, new NormalAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.N_PLUG, new NPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.N_PORT, new NPort());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_SHOT_CHAIN_TELEPORT, new OneShotChainTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_SHOT_CONDITIONAL_TELEPORT, new OneShotConditionalTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_SHOT_CONTROLLABLE_TELEPORT, new OneShotControllableTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_SHOT_TELEPORT, new OneShotTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_EAST_WALL, new OneWayEastWall());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_NORTH_WALL, new OneWayNorthWall());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_SOUTH_WALL, new OneWaySouthWall());
	GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_WEST_WALL, new OneWayWestWall());
	GameObjects.allObjectsLookup.put(MazeObjects.O_PLUG, new OPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.O_PORT, new OPort());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_BUTTON, new OrangeButton());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_CARPET, new OrangeCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_CRYSTAL, new OrangeCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_HOUSE, new OrangeHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_KEY, new OrangeKey());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_LOCK, new OrangeLock());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_WALL_OFF, new OrangeWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_WALL_ON, new OrangeWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.PASSWALL_BOOTS, new PasswallBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.PIT, new Pit());
	GameObjects.allObjectsLookup.put(MazeObjects.PLANT_CRYSTAL, new PlantCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.PLAYER, new Player());
	GameObjects.allObjectsLookup.put(MazeObjects.POISON_ARROW, null);
	GameObjects.allObjectsLookup.put(MazeObjects.POISON_BOMB, new PoisonBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.POISON_BOW, new PoisonBow());
	GameObjects.allObjectsLookup.put(MazeObjects.POISONED_BARRIER_GENERATOR, new PoisonedBarrierGenerator());
	GameObjects.allObjectsLookup.put(MazeObjects.POISONOUS_AMULET, new PoisonousAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.P_PLUG, new PPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.P_PORT, new PPort());
	GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK, new PullableBlock());
	GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK_ONCE, new PullableBlockOnce());
	GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK_THRICE, new PullableBlockThrice());
	GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK_TWICE, new PullableBlockTwice());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_BUTTON, new PurpleButton());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_CARPET, new PurpleCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_CRYSTAL, new PurpleCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_HOUSE, new PurpleHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_KEY, new PurpleKey());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_LOCK, new PurpleLock());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_WALL_OFF, new PurpleWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_WALL_ON, new PurpleWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK, new PushableBlock());
	GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK_ONCE, new PushableBlockOnce());
	GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK_THRICE, new PushableBlockThrice());
	GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK_TWICE, new PushableBlockTwice());
	GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_PULLABLE_BLOCK, new PushablePullableBlock());
	GameObjects.allObjectsLookup.put(MazeObjects.Q_PLUG, new QPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.Q_PORT, new QPort());
	GameObjects.allObjectsLookup.put(MazeObjects.QUAKE_BOMB, new QuakeBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_INVISIBLE_ONE_SHOT_TELEPORT,
		new RandomInvisibleOneShotTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_INVISIBLE_TELEPORT, new RandomInvisibleTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_ONE_SHOT_TELEPORT, new RandomOneShotTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_TELEPORT, new RandomTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_BUTTON, new RedButton());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_CARPET, new RedCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_CRYSTAL, new RedCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_HOUSE, new RedHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_KEY, new RedKey());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_LOCK, new RedLock());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_WALL_OFF, new RedWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.RED_WALL_ON, new RedWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.REGULAR_BOOTS, null);
	GameObjects.allObjectsLookup.put(MazeObjects.REMOTE_ACTION_WAND, new RemoteActionWand());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_BUTTON, new RoseButton());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_CARPET, new RoseCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_CRYSTAL, new RoseCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_HOUSE, new RoseHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_KEY, new RoseKey());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_LOCK, new RoseLock());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_WALL_OFF, new RoseWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.ROSE_WALL_ON, new RoseWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.ROTATION_TRAP, new RotationTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.ROTATION_WAND, new RotationWand());
	GameObjects.allObjectsLookup.put(MazeObjects.R_PLUG, new RPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.R_PORT, new RPort());
	GameObjects.allObjectsLookup.put(MazeObjects.RUBY, new MazeObject(MazeObjects.RUBY));
	GameObjects.allObjectsLookup.put(MazeObjects.RUBY_SQUARE, new RubySquare());
	GameObjects.allObjectsLookup.put(MazeObjects.RUBY_WALL, new RubyWall());
	GameObjects.allObjectsLookup.put(MazeObjects.SAND, new Sand());
	GameObjects.allObjectsLookup.put(MazeObjects.SAPPHIRE, new MazeObject(MazeObjects.SAPPHIRE));
	GameObjects.allObjectsLookup.put(MazeObjects.SAPPHIRE_SQUARE, new SapphireSquare());
	GameObjects.allObjectsLookup.put(MazeObjects.SAPPHIRE_WALL, new SapphireWall());
	GameObjects.allObjectsLookup.put(MazeObjects.SEALED_FINISH, null);
	GameObjects.allObjectsLookup.put(MazeObjects.SEALING_WALL, new SealingWall());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_BUTTON, new SeaweedButton());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_CARPET, new SeaweedCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_CRYSTAL, new SeaweedCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_HOUSE, new SeaweedHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_KEY, new SeaweedKey());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_LOCK, new SeaweedLock());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_WALL_OFF, new SeaweedWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_WALL_ON, new SeaweedWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.SHOCK_ARROW, null);
	GameObjects.allObjectsLookup.put(MazeObjects.SHOCK_BOMB, new ShockBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.SHOCK_BOW, new ShockBow());
	GameObjects.allObjectsLookup.put(MazeObjects.SHOCKED_BARRIER_GENERATOR, new ShockedBarrierGenerator());
	GameObjects.allObjectsLookup.put(MazeObjects.SHUFFLE_BOMB, new ShuffleBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.SIGN, new Sign());
	GameObjects.allObjectsLookup.put(MazeObjects.SIGNAL_CRYSTAL, null);
	GameObjects.allObjectsLookup.put(MazeObjects.SILVER_SQUARE, new SilverSquare());
	GameObjects.allObjectsLookup.put(MazeObjects.SILVER_WALL, new SilverWall());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_BUTTON, new SkyButton());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_CARPET, new SkyCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_CRYSTAL, new SkyCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_HOUSE, new SkyHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_KEY, new SkyKey());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_LOCK, new SkyLock());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_WALL_OFF, new SkyWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.SKY_WALL_ON, new SkyWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.SLIME, new Slime());
	GameObjects.allObjectsLookup.put(MazeObjects.SLIPPERY_BOOTS, new SlipperyBoots());
	GameObjects.allObjectsLookup.put(MazeObjects.SNOW, new Snow());
	GameObjects.allObjectsLookup.put(MazeObjects.S_PLUG, new SPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.S_PORT, new SPort());
	GameObjects.allObjectsLookup.put(MazeObjects.SPRINGBOARD, new Springboard());
	GameObjects.allObjectsLookup.put(MazeObjects.STAIRS_DOWN, new StairsDown());
	GameObjects.allObjectsLookup.put(MazeObjects.STAIRS_UP, new StairsUp());
	GameObjects.allObjectsLookup.put(MazeObjects.STUMP, new Stump());
	GameObjects.allObjectsLookup.put(MazeObjects.SUNKEN_BLOCK, new SunkenBlock());
	GameObjects.allObjectsLookup.put(MazeObjects.SUN_STONE, new SunStone());
	GameObjects.allObjectsLookup.put(MazeObjects.SUPER_HEAL_POTION, new SuperHealPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.SUPER_HURT_POTION, new SuperHurtPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.SUPER_UNKNOWN_POTION, new SuperUnknownPotion());
	GameObjects.allObjectsLookup.put(MazeObjects.TABLET, new Tablet());
	GameObjects.allObjectsLookup.put(MazeObjects.TABLET_SLOT, new TabletSlot());
	GameObjects.allObjectsLookup.put(MazeObjects.TELEPORT, new Teleport());
	GameObjects.allObjectsLookup.put(MazeObjects.TELEPORT_WAND, new TeleportWand());
	GameObjects.allObjectsLookup.put(MazeObjects.TILE, new Tile());
	GameObjects.allObjectsLookup.put(MazeObjects.TOPAZ_SQUARE, new TopazSquare());
	GameObjects.allObjectsLookup.put(MazeObjects.TOPAZ_WALL, new TopazWall());
	GameObjects.allObjectsLookup.put(MazeObjects.T_PLUG, new TPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.T_PORT, new TPort());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_0, new TrappedWall0());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_1, new TrappedWall1());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_2, new TrappedWall2());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_3, new TrappedWall3());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_4, new TrappedWall4());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_5, new TrappedWall5());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_6, new TrappedWall6());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_7, new TrappedWall7());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_8, new TrappedWall8());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_9, new TrappedWall9());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_10, new TrappedWall10());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_11, new TrappedWall11());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_12, new TrappedWall12());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_13, new TrappedWall13());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_14, new TrappedWall14());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_15, new TrappedWall15());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_16, new TrappedWall16());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_17, new TrappedWall17());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_18, new TrappedWall18());
	GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_19, new TrappedWall19());
	GameObjects.allObjectsLookup.put(MazeObjects.TREASURE_CHEST, new TreasureChest());
	GameObjects.allObjectsLookup.put(MazeObjects.TREE, new Tree());
	GameObjects.allObjectsLookup.put(MazeObjects.TRUE_SIGHT_AMULET, new TrueSightAmulet());
	GameObjects.allObjectsLookup.put(MazeObjects.TUNDRA, new Tundra());
	GameObjects.allObjectsLookup.put(MazeObjects.TWO_WAY_TELEPORT, new TwoWayTeleport());
	GameObjects.allObjectsLookup.put(MazeObjects.U_PLUG, new UPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.U_PORT, new UPort());
	GameObjects.allObjectsLookup.put(MazeObjects.U_TURN_TRAP, new UTurnTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.VARIABLE_HEAL_TRAP, new VariableHealTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.VARIABLE_HURT_TRAP, new VariableHurtTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.VERTICAL_BARRIER, new VerticalBarrier());
	GameObjects.allObjectsLookup.put(MazeObjects.V_PLUG, new VPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.V_PORT, new VPort());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL, new Wall());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_BREAKING_WAND, new WallBreakingWand());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_MAKING_TRAP, new WallMakingTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_MAKING_WAND, new WallMakingWand());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_0, new WallTrap0());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_1, new WallTrap1());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_2, new WallTrap2());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_3, new WallTrap3());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_4, new WallTrap4());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_5, new WallTrap5());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_6, new WallTrap6());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_7, new WallTrap7());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_8, new WallTrap8());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_9, new WallTrap9());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_10, new WallTrap10());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_11, new WallTrap11());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_12, new WallTrap12());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_13, new WallTrap13());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_14, new WallTrap14());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_15, new WallTrap15());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_16, new WallTrap16());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_17, new WallTrap17());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_18, new WallTrap18());
	GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_19, new WallTrap19());
	GameObjects.allObjectsLookup.put(MazeObjects.WARP_BOMB, new WarpBomb());
	GameObjects.allObjectsLookup.put(MazeObjects.WARP_TRAP, new WarpTrap());
	GameObjects.allObjectsLookup.put(MazeObjects.WARP_WAND, new WarpWand());
	GameObjects.allObjectsLookup.put(MazeObjects.WATER, new Water());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_BUTTON, new WhiteButton());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_CARPET, new WhiteCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_CRYSTAL, new WhiteCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_HOUSE, new WhiteHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_KEY, new WhiteKey());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_LOCK, new WhiteLock());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_WALL_OFF, new WhiteWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.WHITE_WALL_ON, new WhiteWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.W_PLUG, new WPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.W_PORT, new WPort());
	GameObjects.allObjectsLookup.put(MazeObjects.X_PLUG, new XPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.X_PORT, new XPort());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_BUTTON, new YellowButton());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_CARPET, new YellowCarpet());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_CRYSTAL, new YellowCrystal());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_HOUSE, new YellowHouse());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_KEY, new YellowKey());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_LOCK, new YellowLock());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_WALL_OFF, new YellowWallOff());
	GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_WALL_ON, new YellowWallOn());
	GameObjects.allObjectsLookup.put(MazeObjects.Y_PLUG, new YPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.Y_PORT, new YPort());
	GameObjects.allObjectsLookup.put(MazeObjects.Z_PLUG, new ZPlug());
	GameObjects.allObjectsLookup.put(MazeObjects.Z_PORT, new ZPort());
	final Collection<MazeObject> values = GameObjects.removeNullValues(GameObjects.allObjectsLookup.values());
	GameObjects.allObjects = values.toArray(new MazeObject[values.size()]);
    }

    private static Collection<MazeObject> removeNullValues(final Collection<MazeObject> values) {
	return values.stream().filter(GameObjects.itemNotNull()).collect(Collectors.<MazeObject>toList());
    }

    private static Predicate<? super MazeObject> itemNotNull() {
	return p -> p != null;
    }

    private GameObjects() {
	super();
    }

    public static MazeObject getEmptySpace() {
	return GameObjects.createObject(MazeObjects.EMPTY);
    }

    public static MazeObject[] getAllObjects() {
	return GameObjects.allObjects;
    }

    public static String[] getAllNames() {
	final String[] allNames = new String[GameObjects.allObjects.length];
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    allNames[x] = GameObjects.allObjects[x].getName();
	}
	return allNames;
    }

    public static String[] getAllDescriptions() {
	final String[] allDescriptions = new String[GameObjects.allObjects.length];
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    allDescriptions[x] = GameObjects.allObjects[x].getDescription();
	}
	return allDescriptions;
    }

    public static MazeObject[] getAllObjectsWithRuleSets() {
	final MazeObject[] tempAllObjectsWithRuleSets = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].hasRuleSet()) {
		tempAllObjectsWithRuleSets[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllObjectsWithRuleSet : tempAllObjectsWithRuleSets) {
	    if (tempAllObjectsWithRuleSet != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allObjectsWithRuleSets = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllObjectsWithRuleSet : tempAllObjectsWithRuleSets) {
	    if (tempAllObjectsWithRuleSet != null) {
		allObjectsWithRuleSets[objectCount] = tempAllObjectsWithRuleSet;
		objectCount++;
	    }
	}
	return allObjectsWithRuleSets;
    }

    public static MazeObject[] getAllObjectsWithoutRuleSets() {
	final MazeObject[] tempAllObjectsWithoutRuleSets = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (!GameObjects.allObjects[x].hasRuleSet()) {
		tempAllObjectsWithoutRuleSets[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllObjectsWithoutRuleSet : tempAllObjectsWithoutRuleSets) {
	    if (tempAllObjectsWithoutRuleSet != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allObjectsWithoutRuleSets = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllObjectsWithoutRuleSet : tempAllObjectsWithoutRuleSets) {
	    if (tempAllObjectsWithoutRuleSet != null) {
		allObjectsWithoutRuleSets[objectCount] = tempAllObjectsWithoutRuleSet;
		objectCount++;
	    }
	}
	return allObjectsWithoutRuleSets;
    }

    public static MazeObject[] getAllGroundLayerObjects() {
	final MazeObject[] tempAllGroundLayerObjects = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
		tempAllGroundLayerObjects[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllGroundLayerObject : tempAllGroundLayerObjects) {
	    if (tempAllGroundLayerObject != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allGroundLayerObjects = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllGroundLayerObject : tempAllGroundLayerObjects) {
	    if (tempAllGroundLayerObject != null) {
		allGroundLayerObjects[objectCount] = tempAllGroundLayerObject;
		objectCount++;
	    }
	}
	return allGroundLayerObjects;
    }

    public static MazeObject[] getAllObjectLayerObjects() {
	final MazeObject[] tempAllObjectLayerObjects = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
		tempAllObjectLayerObjects[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllObjectLayerObject : tempAllObjectLayerObjects) {
	    if (tempAllObjectLayerObject != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allObjectLayerObjects = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllObjectLayerObject : tempAllObjectLayerObjects) {
	    if (tempAllObjectLayerObject != null) {
		allObjectLayerObjects[objectCount] = tempAllObjectLayerObject;
		objectCount++;
	    }
	}
	return allObjectLayerObjects;
    }

    public static String[] getAllGroundLayerNames() {
	final String[] tempAllGroundLayerNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
		tempAllGroundLayerNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllGroundLayerName : tempAllGroundLayerNames) {
	    if (tempAllGroundLayerName != null) {
		objectCount++;
	    }
	}
	final String[] allGroundLayerNames = new String[objectCount];
	objectCount = 0;
	for (final String tempAllGroundLayerName : tempAllGroundLayerNames) {
	    if (tempAllGroundLayerName != null) {
		allGroundLayerNames[objectCount] = tempAllGroundLayerName;
		objectCount++;
	    }
	}
	return allGroundLayerNames;
    }

    public static String[] getAllObjectLayerNames() {
	final String[] tempAllObjectLayerNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
		tempAllObjectLayerNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllObjectLayerName : tempAllObjectLayerNames) {
	    if (tempAllObjectLayerName != null) {
		objectCount++;
	    }
	}
	final String[] allObjectLayerNames = new String[objectCount];
	objectCount = 0;
	for (final String tempAllObjectLayerName : tempAllObjectLayerNames) {
	    if (tempAllObjectLayerName != null) {
		allObjectLayerNames[objectCount] = tempAllObjectLayerName;
		objectCount++;
	    }
	}
	return allObjectLayerNames;
    }

    public static BufferedImageIcon[] getAllObjectHelpAppearances() {
	final String[] imageNames = DataLoader.loadAllObjectImageData();
	final BufferedImageIcon[] allObjectHelpAppearances = new BufferedImageIcon[imageNames.length];
	for (int x = 0; x < allObjectHelpAppearances.length; x++) {
	    allObjectHelpAppearances[x] = ObjectImageLoader.loadHelp(imageNames[x]);
	}
	return allObjectHelpAppearances;
    }

    public static BufferedImageIcon[] getAllEditorAppearances() {
	final BufferedImageIcon[] allEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
	for (int x = 0; x < allEditorAppearances.length; x++) {
	    allEditorAppearances[x] = ObjectImageLoader.getTransformedImage(GameObjects.allObjects[x], false);
	}
	return allEditorAppearances;
    }

    public static BufferedImageIcon[] getAllGroundLayerEditorAppearances() {
	final BufferedImageIcon[] tempAllGroundLayerEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
		tempAllGroundLayerEditorAppearances[x] = ObjectImageLoader
			.getTransformedImage(GameObjects.allObjects[x], false);
	    }
	}
	for (final BufferedImageIcon tempAllGroundLayerEditorAppearance : tempAllGroundLayerEditorAppearances) {
	    if (tempAllGroundLayerEditorAppearance != null) {
		objectCount++;
	    }
	}
	final BufferedImageIcon[] allGroundLayerEditorAppearances = new BufferedImageIcon[objectCount];
	objectCount = 0;
	for (final BufferedImageIcon tempAllGroundLayerEditorAppearance : tempAllGroundLayerEditorAppearances) {
	    if (tempAllGroundLayerEditorAppearance != null) {
		allGroundLayerEditorAppearances[objectCount] = tempAllGroundLayerEditorAppearance;
		objectCount++;
	    }
	}
	return allGroundLayerEditorAppearances;
    }

    public static BufferedImageIcon[] getAllObjectLayerEditorAppearances() {
	final BufferedImageIcon[] tempAllObjectLayerEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
		tempAllObjectLayerEditorAppearances[x] = ObjectImageLoader
			.getTransformedImage(GameObjects.allObjects[x], false);
	    }
	}
	for (final BufferedImageIcon tempAllObjectLayerEditorAppearance : tempAllObjectLayerEditorAppearances) {
	    if (tempAllObjectLayerEditorAppearance != null) {
		objectCount++;
	    }
	}
	final BufferedImageIcon[] allObjectLayerEditorAppearances = new BufferedImageIcon[objectCount];
	objectCount = 0;
	for (final BufferedImageIcon tempAllObjectLayerEditorAppearance : tempAllObjectLayerEditorAppearances) {
	    if (tempAllObjectLayerEditorAppearance != null) {
		allObjectLayerEditorAppearances[objectCount] = tempAllObjectLayerEditorAppearance;
		objectCount++;
	    }
	}
	return allObjectLayerEditorAppearances;
    }

    public static BufferedImageIcon[] getAllContainableObjectEditorAppearances() {
	final BufferedImageIcon[] tempAllContainableObjectEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_CONTAINABLE)) {
		tempAllContainableObjectEditorAppearances[x] = ObjectImageLoader
			.getTransformedImage(GameObjects.allObjects[x], false);
	    }
	}
	for (final BufferedImageIcon tempAllContainableObjectEditorAppearance : tempAllContainableObjectEditorAppearances) {
	    if (tempAllContainableObjectEditorAppearance != null) {
		objectCount++;
	    }
	}
	final BufferedImageIcon[] allContainableObjectEditorAppearances = new BufferedImageIcon[objectCount];
	objectCount = 0;
	for (final BufferedImageIcon tempAllContainableObjectEditorAppearance : tempAllContainableObjectEditorAppearances) {
	    if (tempAllContainableObjectEditorAppearance != null) {
		allContainableObjectEditorAppearances[objectCount] = tempAllContainableObjectEditorAppearance;
		objectCount++;
	    }
	}
	return allContainableObjectEditorAppearances;
    }

    public static MazeObject[] getAllContainableObjects() {
	final MazeObject[] tempAllContainableObjects = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_CONTAINABLE)) {
		tempAllContainableObjects[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllContainableObject : tempAllContainableObjects) {
	    if (tempAllContainableObject != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allContainableObjects = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllContainableObject : tempAllContainableObjects) {
	    if (tempAllContainableObject != null) {
		allContainableObjects[objectCount] = tempAllContainableObject;
		objectCount++;
	    }
	}
	return allContainableObjects;
    }

    public static String[] getAllContainableNames() {
	final String[] tempAllContainableNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_CONTAINABLE)) {
		tempAllContainableNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllContainableName : tempAllContainableNames) {
	    if (tempAllContainableName != null) {
		objectCount++;
	    }
	}
	final String[] allContainableNames = new String[objectCount];
	objectCount = 0;
	for (final String tempAllContainableName : tempAllContainableNames) {
	    if (tempAllContainableName != null) {
		allContainableNames[objectCount] = tempAllContainableName;
		objectCount++;
	    }
	}
	return allContainableNames;
    }

    public static MazeObject[] getAllInventoryableObjectsMinusSpecial() {
	final MazeObject[] tempAllInventoryableObjects = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isInventoryable()
		    && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOOTS)
		    && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)
		    && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_AMULET)) {
		tempAllInventoryableObjects[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllInventoryableObject : tempAllInventoryableObjects) {
	    if (tempAllInventoryableObject != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allInventoryableObjects = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllInventoryableObject : tempAllInventoryableObjects) {
	    if (tempAllInventoryableObject != null) {
		allInventoryableObjects[objectCount] = tempAllInventoryableObject;
		objectCount++;
	    }
	}
	return allInventoryableObjects;
    }

    public static String[] getAllInventoryableNamesMinusSpecial() {
	final String[] tempAllInventoryableNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isInventoryable()
		    && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOOTS)
		    && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)
		    && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_AMULET)) {
		tempAllInventoryableNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllInventoryableName : tempAllInventoryableNames) {
	    if (tempAllInventoryableName != null) {
		objectCount++;
	    }
	}
	final String[] allInventoryableNames = new String[objectCount];
	objectCount = 0;
	for (final String tempAllInventoryableName : tempAllInventoryableNames) {
	    if (tempAllInventoryableName != null) {
		allInventoryableNames[objectCount] = tempAllInventoryableName;
		objectCount++;
	    }
	}
	return allInventoryableNames;
    }

    public static MazeObject[] getAllProgrammableKeys() {
	final MazeObject[] tempAllProgrammableKeys = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_PROGRAMMABLE_USE)) {
		tempAllProgrammableKeys[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllProgrammableKey : tempAllProgrammableKeys) {
	    if (tempAllProgrammableKey != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allProgrammableKeys = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllProgrammableKey : tempAllProgrammableKeys) {
	    if (tempAllProgrammableKey != null) {
		allProgrammableKeys[objectCount] = tempAllProgrammableKey;
		objectCount++;
	    }
	}
	return allProgrammableKeys;
    }

    public static String[] getAllProgrammableKeyNames() {
	final String[] tempAllProgrammableKeyNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_PROGRAMMABLE_USE)) {
		tempAllProgrammableKeyNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllProgrammableKeyName : tempAllProgrammableKeyNames) {
	    if (tempAllProgrammableKeyName != null) {
		objectCount++;
	    }
	}
	final String[] allProgrammableKeyNames = new String[objectCount];
	objectCount = 0;
	for (final String tempAllProgrammableKeyName : tempAllProgrammableKeyNames) {
	    if (tempAllProgrammableKeyName != null) {
		allProgrammableKeyNames[objectCount] = tempAllProgrammableKeyName;
		objectCount++;
	    }
	}
	return allProgrammableKeyNames;
    }

    public static MazeObject[] getAllUsableObjects() {
	final MazeObject[] tempAllUsableObjects = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isUsable()) {
		tempAllUsableObjects[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllUsableObject : tempAllUsableObjects) {
	    if (tempAllUsableObject != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allUsableObjects = new MazeObject[objectCount];
	objectCount = 0;
	for (final MazeObject tempAllUsableObject : tempAllUsableObjects) {
	    if (tempAllUsableObject != null) {
		allUsableObjects[objectCount] = tempAllUsableObject;
		objectCount++;
	    }
	}
	return allUsableObjects;
    }

    public static String[] getAllUsableNamesMinusSpecial() {
	final String[] tempAllUsableNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isUsable() && !GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)) {
		tempAllUsableNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllUsableName : tempAllUsableNames) {
	    if (tempAllUsableName != null) {
		objectCount++;
	    }
	}
	final String[] allUsableNames = new String[objectCount];
	objectCount = 0;
	for (final String tempAllUsableName : tempAllUsableNames) {
	    if (tempAllUsableName != null) {
		allUsableNames[objectCount] = tempAllUsableName;
		objectCount++;
	    }
	}
	return allUsableNames;
    }

    public static MazeObject[] getAllBows() {
	final MazeObject[] tempAllUsableObjects = new MazeObject[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)) {
		tempAllUsableObjects[x] = GameObjects.allObjects[x];
	    }
	}
	for (final MazeObject tempAllUsableObject : tempAllUsableObjects) {
	    if (tempAllUsableObject != null) {
		objectCount++;
	    }
	}
	final MazeObject[] allUsableObjects = new MazeObject[objectCount + 1];
	objectCount = 0;
	for (int x = 0; x < tempAllUsableObjects.length - 1; x++) {
	    if (tempAllUsableObjects[x] != null) {
		allUsableObjects[objectCount] = tempAllUsableObjects[x];
		objectCount++;
	    }
	}
	allUsableObjects[allUsableObjects.length - 1] = new Bow();
	return allUsableObjects;
    }

    public static String[] getAllBowNames() {
	final String[] tempAllUsableNames = new String[GameObjects.allObjects.length];
	int objectCount = 0;
	for (int x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].isOfType(TypeConstants.TYPE_BOW)) {
		tempAllUsableNames[x] = GameObjects.allObjects[x].getName();
	    }
	}
	for (final String tempAllUsableName : tempAllUsableNames) {
	    if (tempAllUsableName != null) {
		objectCount++;
	    }
	}
	final String[] allUsableNames = new String[objectCount + 1];
	objectCount = 0;
	for (int x = 0; x < tempAllUsableNames.length - 1; x++) {
	    if (tempAllUsableNames[x] != null) {
		allUsableNames[objectCount] = tempAllUsableNames[x];
		objectCount++;
	    }
	}
	allUsableNames[allUsableNames.length - 1] = new Bow().getName();
	return allUsableNames;
    }

    public static MazeObject[] getAllRequired(final int layer) {
	final MazeObject[] tempAllRequired = new MazeObject[GameObjects.allObjects.length];
	int x;
	int count = 0;
	for (x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == layer && GameObjects.allObjects[x].isRequired()) {
		tempAllRequired[count] = GameObjects.allObjects[x];
		count++;
	    }
	}
	if (count == 0) {
	    return null;
	} else {
	    final MazeObject[] allRequired = new MazeObject[count];
	    for (x = 0; x < count; x++) {
		allRequired[x] = tempAllRequired[x];
	    }
	    return allRequired;
	}
    }

    public static MazeObject[] getAllWithoutPrerequisiteAndNotRequired(final int layer) {
	final MazeObject[] tempAllWithoutPrereq = new MazeObject[GameObjects.allObjects.length];
	int x;
	int count = 0;
	for (x = 0; x < GameObjects.allObjects.length; x++) {
	    if (GameObjects.allObjects[x].getLayer() == layer && !GameObjects.allObjects[x].isRequired()) {
		tempAllWithoutPrereq[count] = GameObjects.allObjects[x];
		count++;
	    }
	}
	if (count == 0) {
	    return null;
	} else {
	    final MazeObject[] allWithoutPrereq = new MazeObject[count];
	    for (x = 0; x < count; x++) {
		allWithoutPrereq[x] = tempAllWithoutPrereq[x];
	    }
	    return allWithoutPrereq;
	}
    }

    public static MazeObject[] getAllRequiredSubset(final MazeObject[] objs, final int layer) {
	if (objs == null) {
	    return null;
	}
	final MazeObject[] tempAllRequired = new MazeObject[objs.length];
	int x;
	int count = 0;
	for (x = 0; x < objs.length; x++) {
	    if (objs[x].hasRuleSet()) {
		if (objs[x].getLayer() == layer && objs[x].getRuleSet().isRequired()) {
		    tempAllRequired[count] = objs[x];
		    count++;
		}
	    } else {
		if (objs[x].getLayer() == layer && objs[x].isRequired()) {
		    tempAllRequired[count] = objs[x];
		    count++;
		}
	    }
	}
	if (count == 0) {
	    return null;
	} else {
	    final MazeObject[] allRequired = new MazeObject[count];
	    for (x = 0; x < count; x++) {
		allRequired[x] = tempAllRequired[x];
	    }
	    return allRequired;
	}
    }

    public static MazeObject[] getAllWithoutPrerequisiteAndNotRequiredSubset(final MazeObject[] objs, final int layer) {
	if (objs == null) {
	    return null;
	}
	final MazeObject[] tempAllWithoutPrereq = new MazeObject[objs.length];
	int x;
	int count = 0;
	for (x = 0; x < objs.length; x++) {
	    if (objs[x].hasRuleSet()) {
		if (objs[x].getLayer() == layer && !objs[x].getRuleSet().isRequired()) {
		    tempAllWithoutPrereq[count] = objs[x];
		    count++;
		}
	    } else {
		if (objs[x].getLayer() == layer && !objs[x].isRequired()) {
		    tempAllWithoutPrereq[count] = objs[x];
		    count++;
		}
	    }
	}
	if (count == 0) {
	    return null;
	} else {
	    final MazeObject[] allWithoutPrereq = new MazeObject[count];
	    for (x = 0; x < count; x++) {
		allWithoutPrereq[x] = tempAllWithoutPrereq[x];
	    }
	    return allWithoutPrereq;
	}
    }

    public static GenericSingleKey createSignalCrystal() {
	return new SignalCrystal();
    }

    public static MazeObjects getUIDForIndex(final int index) {
	return GameObjects.allObjectUIDs[index];
    }

    public static MazeObject createObject(final MazeObjects uid) {
	final MazeObject instance = GameObjects.allObjectsLookup.get(uid);
	if (instance == null) {
	    return null;
	} else {
	    try {
		return instance.getClass().getConstructor().newInstance();
	    } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		return null;
	    }
	}
    }

    public static MazeObject createContainerObject(final MazeObjects uid, final MazeObjects contentsUID) {
	final MazeObject contents = GameObjects.createObject(contentsUID);
	final MazeObject instance = GameObjects.allObjectsLookup.get(uid);
	if (instance == null) {
	    return null;
	} else {
	    try {
		return instance.getClass().getConstructor(contents.getClass()).newInstance(contents);
	    } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		return null;
	    }
	}
    }

    public static MazeObject createTeleportObject(final MazeObjects uid, final int dr, final int dc) {
	final MazeObject instance = GameObjects.allObjectsLookup.get(uid);
	if (instance == null) {
	    return null;
	} else {
	    try {
		return instance.getClass().getConstructor(int.class, int.class).newInstance(dr, dc);
	    } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		return null;
	    }
	}
    }

    public static MazeObject createTeleportObject(final MazeObjects uid, final int dr, final int dc, final int df) {
	final MazeObject instance = GameObjects.allObjectsLookup.get(uid);
	if (instance == null) {
	    return null;
	} else {
	    try {
		return instance.getClass().getConstructor(int.class, int.class, int.class).newInstance(dr, dc, df);
	    } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		return null;
	    }
	}
    }

    public static MazeObject createTeleportObject(final MazeObjects uid, final int dr, final int dc, final int df,
	    final int dl) {
	final MazeObject instance = GameObjects.allObjectsLookup.get(uid);
	if (instance == null) {
	    return null;
	} else {
	    try {
		return instance.getClass().getConstructor(int.class, int.class, int.class, int.class).newInstance(dr,
			dc, df, dl);
	    } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		return null;
	    }
	}
    }

    public static MazeObject readObject(final MazeDataReader reader, final int formatVersion) throws IOException {
	MazeObject o = null;
	String UID = StaticStrings.EMPTY;
	if (formatVersion == FormatConstants._MAZE_FORMAT_1) {
	    UID = reader.readString();
	} else if (formatVersion == FormatConstants._MAZE_FORMAT_2) {
	    UID = reader.readString();
	} else if (formatVersion == FormatConstants._MAZE_FORMAT_3) {
	    UID = reader.readString();
	} else if (formatVersion == FormatConstants._MAZE_FORMAT_4) {
	    UID = reader.readString();
	} else if (formatVersion == FormatConstants._MAZE_FORMAT_5) {
	    UID = reader.readString();
	}
	for (final MazeObject allObject : GameObjects.allObjects) {
	    try {
		final MazeObject instance = allObject.getClass().getConstructor().newInstance();
		if (formatVersion == FormatConstants._MAZE_FORMAT_1) {
		    o = instance.readMazeObject(reader, UID, formatVersion);
		} else if (formatVersion == FormatConstants._MAZE_FORMAT_2) {
		    o = instance.readMazeObject2(reader, UID, formatVersion);
		} else if (formatVersion == FormatConstants._MAZE_FORMAT_3) {
		    o = instance.readMazeObject3(reader, UID, formatVersion);
		} else if (formatVersion == FormatConstants._MAZE_FORMAT_4) {
		    o = instance.readMazeObject4(reader, UID, formatVersion);
		} else if (formatVersion == FormatConstants._MAZE_FORMAT_5) {
		    o = instance.readMazeObject5(reader, UID, formatVersion);
		}
		if (o != null) {
		    return o;
		}
	    } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
		Mazer5D.logError(e);
	    }
	}
	return null;
    }

    public static void readRuleSet(final MazeDataReader reader, final int rsFormat) throws IOException {
	// Read map length
	final int mapLen = reader.readInt();
	final boolean[] map = new boolean[mapLen];
	// Read map
	for (int x = 0; x < mapLen; x++) {
	    map[x] = reader.readBoolean();
	}
	// Read data
	for (int x = 0; x < mapLen; x++) {
	    if (map[x]) {
		GameObjects.allObjects[x].giveRuleSet();
		GameObjects.allObjects[x].getRuleSet().readRuleSet(reader, rsFormat);
	    }
	}
    }

    public static void writeRuleSet(final MazeDataWriter writer) throws IOException {
	final boolean[] map = GameObjects.generateMap();
	// Write map length
	writer.writeInt(map.length);
	// Write map
	for (final boolean element : map) {
	    writer.writeBoolean(element);
	}
	// Write data
	for (int x = 0; x < map.length; x++) {
	    if (map[x]) {
		GameObjects.allObjects[x].getRuleSet().writeRuleSet(writer);
	    }
	}
    }

    private static boolean[] generateMap() {
	final boolean[] map = new boolean[GameObjects.allObjects.length];
	for (int x = 0; x < map.length; x++) {
	    if (GameObjects.allObjects[x].hasRuleSet()) {
		map[x] = true;
	    } else {
		map[x] = false;
	    }
	}
	return map;
    }
}
