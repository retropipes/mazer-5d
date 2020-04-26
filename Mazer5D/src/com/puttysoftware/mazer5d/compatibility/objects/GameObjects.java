/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.compatibility.objects;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.compatibility.abc.GenericSingleKey;
import com.puttysoftware.mazer5d.compatibility.abc.MazeObjectModel;
import com.puttysoftware.mazer5d.compatibility.files.xml.XMLFormatConstants;
import com.puttysoftware.mazer5d.compatibility.loaders.ObjectImageManager;
import com.puttysoftware.mazer5d.files.io.XDataReader;
import com.puttysoftware.mazer5d.files.io.XDataWriter;
import com.puttysoftware.mazer5d.objectmodel.Layers;
import com.puttysoftware.mazer5d.objectmodel.MazeObjectType;
import com.puttysoftware.mazer5d.objectmodel.MazeObjects;

public class GameObjects {
    // Fields
    private static final TreeMap<MazeObjects, MazeObjectModel> allObjectsLookup = new TreeMap<>();
    private static MazeObjectModel[] allObjects;
    static {
        GameObjects.allObjectsLookup.put(MazeObjects._NONE, null);
        GameObjects.allObjectsLookup.put(MazeObjects.AMETHYST, new Amethyst());
        GameObjects.allObjectsLookup.put(MazeObjects.ANNIHILATION_WAND,
                new AnnihilationWand());
        GameObjects.allObjectsLookup.put(MazeObjects.A_PLUG, new APlug());
        GameObjects.allObjectsLookup.put(MazeObjects.A_PORT, new APort());
        GameObjects.allObjectsLookup.put(MazeObjects.AQUA_BOOTS,
                new AquaBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.ARROW, new ArrowTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.ARROW_TRAP, null);
        GameObjects.allObjectsLookup.put(MazeObjects.AXE, new Axe());
        GameObjects.allObjectsLookup.put(MazeObjects.BARRIER_GENERATOR,
                new BarrierGenerator());
        GameObjects.allObjectsLookup.put(MazeObjects.BIO_HAZARD_BOOTS,
                new BioHazardBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.BLACK_CRYSTAL,
                new BlackCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_BUTTON,
                new BlueButton());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_CARPET,
                new BlueCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_CRYSTAL,
                new BlueCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_HOUSE,
                new BlueHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_KEY, new BlueKey());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_LOCK, new BlueLock());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_WALL_OFF,
                new BlueWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.BLUE_WALL_ON,
                new BlueWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.BOW, null);
        GameObjects.allObjectsLookup.put(MazeObjects.B_PLUG, new BPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.B_PORT, new BPort());
        GameObjects.allObjectsLookup.put(MazeObjects.BREAKABLE_WALL_HORIZONTAL,
                new BreakableWallHorizontal());
        GameObjects.allObjectsLookup.put(MazeObjects.BREAKABLE_WALL_VERTICAL,
                new BreakableWallVertical());
        GameObjects.allObjectsLookup.put(MazeObjects.BRICK_WALL,
                new BrickWall());
        GameObjects.allObjectsLookup.put(MazeObjects.BRIGHTNESS_GEM,
                new BrightnessGem());
        GameObjects.allObjectsLookup.put(MazeObjects.CHAIN_TELEPORT,
                new ChainTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.CLOCKWISE_ROTATION_TRAP,
                new ClockwiseRotationTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.CONDITIONAL_CHAIN_TELEPORT,
                new ConditionalChainTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.CONDITIONAL_TELEPORT,
                new ConditionalTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.CONFUSION_TRAP,
                new ConfusionTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.CONTROLLABLE_TELEPORT,
                new ControllableTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.COUNTERCLOCKWISE_ROTATION_TRAP,
                new CounterclockwiseRotationTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.COUNTERPOISON_AMULET,
                new CounterpoisonAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.C_PLUG, new CPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.C_PORT, new CPort());
        GameObjects.allObjectsLookup.put(MazeObjects.CRACKED_WALL,
                new CrackedWall());
        GameObjects.allObjectsLookup.put(MazeObjects.CREVASSE, new Crevasse());
        GameObjects.allObjectsLookup.put(MazeObjects.CRUMBLING_WALL,
                new CrumblingWall());
        GameObjects.allObjectsLookup.put(MazeObjects.CRYSTAL_WALL,
                new CrystalWall());
        GameObjects.allObjectsLookup.put(MazeObjects.CUT_TREE, new CutTree());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_BUTTON,
                new CyanButton());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_CARPET,
                new CyanCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_CRYSTAL,
                new CyanCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_HOUSE,
                new CyanHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_KEY, new CyanKey());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_LOCK, new CyanLock());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_WALL_OFF,
                new CyanWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.CYAN_WALL_ON,
                new CyanWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.DAMAGEABLE_WALL,
                new DamageableWall());
        GameObjects.allObjectsLookup.put(MazeObjects.DAMAGED_WALL,
                new DamagedWall());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_BLUE_CRYSTAL,
                new DarkBlueCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_CYAN_CRYSTAL,
                new DarkCyanCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_GEM, new DarkGem());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_GRAY_CRYSTAL,
                new DarkGrayCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_GREEN_CRYSTAL,
                new DarkGreenCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_MAGENTA_CRYSTAL,
                new DarkMagentaCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DARKNESS, null);
        GameObjects.allObjectsLookup.put(MazeObjects.DARKNESS_GEM,
                new DarknessGem());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_RED_CRYSTAL,
                new DarkRedCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_WAND, new DarkWand());
        GameObjects.allObjectsLookup.put(MazeObjects.DARK_YELLOW_CRYSTAL,
                new DarkYellowCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.DESTINATION, null);
        GameObjects.allObjectsLookup.put(MazeObjects.DIAMOND, new Diamond());
        GameObjects.allObjectsLookup.put(MazeObjects.DIMNESS_GEM,
                new DimnessGem());
        GameObjects.allObjectsLookup.put(MazeObjects.DIRT, new Dirt());
        GameObjects.allObjectsLookup.put(MazeObjects.DISARM_TRAP_WAND,
                new DisarmTrapWand());
        GameObjects.allObjectsLookup.put(MazeObjects.DIZZINESS_TRAP,
                new DizzinessTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.DOOR, new Door());
        GameObjects.allObjectsLookup.put(MazeObjects.DOUBLE_HOURGLASS,
                new DoubleHourglass());
        GameObjects.allObjectsLookup.put(MazeObjects.D_PLUG, new DPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.D_PORT, new DPort());
        GameObjects.allObjectsLookup.put(MazeObjects.DRUNK_TRAP,
                new DrunkTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.EMPTY, GameObjects
                .getEmptySpace());
        GameObjects.allObjectsLookup.put(MazeObjects.BOUNDS, new Bounds());
        GameObjects.allObjectsLookup.put(MazeObjects.ENERGY_SPHERE,
                new EnergySphere());
        GameObjects.allObjectsLookup.put(MazeObjects.ENRAGED_BARRIER_GENERATOR,
                new EnragedBarrierGenerator());
        GameObjects.allObjectsLookup.put(MazeObjects.E_PLUG, new EPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.E_PORT, new EPort());
        GameObjects.allObjectsLookup.put(MazeObjects.EXIT, new Exit());
        GameObjects.allObjectsLookup.put(MazeObjects.EXPLODING_WALL,
                new ExplodingWall());
        GameObjects.allObjectsLookup.put(MazeObjects.EXPLORE_TRAP,
                new ExploreTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.FADING_WALL,
                new FadingWall());
        GameObjects.allObjectsLookup.put(MazeObjects.FAKE_FINISH,
                new FakeFinish());
        GameObjects.allObjectsLookup.put(MazeObjects.FAKE_WALL, new FakeWall());
        GameObjects.allObjectsLookup.put(MazeObjects.FINISH, new Finish());
        GameObjects.allObjectsLookup.put(MazeObjects.FINISH_MAKING_WAND,
                new FinishMakingWand());
        GameObjects.allObjectsLookup.put(MazeObjects.FINISH_TO, new FinishTo());
        GameObjects.allObjectsLookup.put(MazeObjects.FIRE_AMULET,
                new FireAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.FIRE_ARROW, null);
        GameObjects.allObjectsLookup.put(MazeObjects.FIRE_BOMB, new FireBomb());
        GameObjects.allObjectsLookup.put(MazeObjects.FIRE_BOOTS,
                new FireBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.FIRE_BOW, new FireBow());
        GameObjects.allObjectsLookup.put(MazeObjects.FORCE_FIELD,
                new ForceField());
        GameObjects.allObjectsLookup.put(MazeObjects.F_PLUG, new FPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.F_PORT, new FPort());
        GameObjects.allObjectsLookup.put(MazeObjects.GARNET_SQUARE,
                new GarnetSquare());
        GameObjects.allObjectsLookup.put(MazeObjects.GARNET_WALL,
                new GarnetWall());
        GameObjects.allObjectsLookup.put(MazeObjects.GHOST_AMULET,
                new GhostAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.GHOST_ARROW, null);
        GameObjects.allObjectsLookup.put(MazeObjects.GHOST_BOW, new GhostBow());
        GameObjects.allObjectsLookup.put(MazeObjects.GLUE_BOOTS,
                new GlueBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.GOLDEN_SQUARE,
                new GoldenSquare());
        GameObjects.allObjectsLookup.put(MazeObjects.GOLDEN_WALL,
                new GoldenWall());
        GameObjects.allObjectsLookup.put(MazeObjects.G_PLUG, new GPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.G_PORT, new GPort());
        GameObjects.allObjectsLookup.put(MazeObjects.GRASS, new Grass());
        GameObjects.allObjectsLookup.put(MazeObjects.GRAY_CRYSTAL,
                new GrayCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_BUTTON,
                new GreenButton());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_CARPET,
                new GreenCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_CRYSTAL,
                new GreenCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_HOUSE,
                new GreenHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_KEY, new GreenKey());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_LOCK,
                new GreenLock());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_WALL_OFF,
                new GreenWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.GREEN_WALL_ON,
                new GreenWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.HALF_HOURGLASS,
                new HalfHourglass());
        GameObjects.allObjectsLookup.put(MazeObjects.HAMMER, new Hammer());
        GameObjects.allObjectsLookup.put(MazeObjects.HEAL_BOOTS,
                new HealBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.HEAL_TRAP, new HealTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.HORIZONTAL_BARRIER,
                new HorizontalBarrier());
        GameObjects.allObjectsLookup.put(MazeObjects.HOT_BOOTS, new HotBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.HOT_ROCK, new HotRock());
        GameObjects.allObjectsLookup.put(MazeObjects.HOURGLASS,
                new Hourglass());
        GameObjects.allObjectsLookup.put(MazeObjects.H_PLUG, new HPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.H_PORT, new HPort());
        GameObjects.allObjectsLookup.put(MazeObjects.HURT_TRAP, new HurtTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.ICE, new Ice());
        GameObjects.allObjectsLookup.put(MazeObjects.ICE_AMULET,
                new IceAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.ICE_ARROW, null);
        GameObjects.allObjectsLookup.put(MazeObjects.ICE_BOMB, new IceBomb());
        GameObjects.allObjectsLookup.put(MazeObjects.ICE_BOW, new IceBow());
        GameObjects.allObjectsLookup.put(MazeObjects.ICED_BARRIER_GENERATOR,
                new IcedBarrierGenerator());
        GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_CHAIN_TELEPORT,
                new InvisibleChainTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.INVISIBLE_CONDITIONAL_CHAIN_TELEPORT,
                new InvisibleConditionalChainTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.INVISIBLE_CONDITIONAL_TELEPORT,
                new InvisibleConditionalTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.INVISIBLE_ONE_SHOT_CHAIN_TELEPORT,
                new InvisibleOneShotChainTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.INVISIBLE_ONE_SHOT_CONDITIONAL_TELEPORT,
                new InvisibleOneShotConditionalTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.INVISIBLE_ONE_SHOT_TELEPORT,
                new InvisibleOneShotTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_PIT,
                new InvisiblePit());
        GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_SPRINGBOARD,
                new InvisibleSpringboard());
        GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_TELEPORT,
                new InvisibleTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.INVISIBLE_WALL,
                new InvisibleWall());
        GameObjects.allObjectsLookup.put(MazeObjects.I_PLUG, new IPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.I_PORT, new IPort());
        GameObjects.allObjectsLookup.put(MazeObjects.J_PLUG, new JPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.J_PORT, new JPort());
        GameObjects.allObjectsLookup.put(MazeObjects.KEY, new Key());
        GameObjects.allObjectsLookup.put(MazeObjects.K_PLUG, new KPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.K_PORT, new KPort());
        GameObjects.allObjectsLookup.put(MazeObjects.LAVA, new Lava());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_BLUE_CRYSTAL,
                new LightBlueCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_CYAN_CRYSTAL,
                new LightCyanCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_GEM, new LightGem());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_GRAY_CRYSTAL,
                new LightGrayCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_GREEN_CRYSTAL,
                new LightGreenCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_MAGENTA_CRYSTAL,
                new LightMagentaCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHTNESS_GEM,
                new LightnessGem());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_RED_CRYSTAL,
                new LightRedCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_WAND,
                new LightWand());
        GameObjects.allObjectsLookup.put(MazeObjects.LIGHT_YELLOW_CRYSTAL,
                new LightYellowCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.LOCK, new Lock());
        GameObjects.allObjectsLookup.put(MazeObjects.L_PLUG, new LPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.L_PORT, new LPort());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_BUTTON,
                new MagentaButton());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_CARPET,
                new MagentaCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_CRYSTAL,
                new MagentaCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_HOUSE,
                new MagentaHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_KEY,
                new MagentaKey());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_LOCK,
                new MagentaLock());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_WALL_OFF,
                new MagentaWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.MAGENTA_WALL_ON,
                new MagentaWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.MAJOR_HEAL_POTION,
                new MajorHealPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.MAJOR_HURT_POTION,
                new MajorHurtPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.MAJOR_UNKNOWN_POTION,
                new MajorUnknownPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.MASTER_TRAPPED_WALL,
                new MasterTrappedWall());
        GameObjects.allObjectsLookup.put(MazeObjects.MASTER_WALL_TRAP,
                new MasterWallTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.METAL_BOOTS,
                new MetalBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.METAL_BUTTON,
                new MetalButton());
        GameObjects.allObjectsLookup.put(MazeObjects.METAL_DOOR,
                new MetalDoor());
        GameObjects.allObjectsLookup.put(MazeObjects.METAL_KEY, new MetalKey());
        GameObjects.allObjectsLookup.put(MazeObjects.MINOR_HEAL_POTION,
                new MinorHealPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.MINOR_HURT_POTION,
                new MinorHurtPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.MINOR_UNKNOWN_POTION,
                new MinorUnknownPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.MOON_STONE,
                new MoonStone());
        GameObjects.allObjectsLookup.put(MazeObjects.MOVING_BLOCK,
                new MovingBlock());
        GameObjects.allObjectsLookup.put(MazeObjects.MOVING_FINISH,
                new MovingFinish());
        GameObjects.allObjectsLookup.put(MazeObjects.M_PLUG, new MPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.M_PORT, new MPort());
        GameObjects.allObjectsLookup.put(MazeObjects.NO_BOOTS, new NoBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.NO_EXPLORE_TRAP,
                new NoExploreTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.NORMAL_AMULET,
                new NormalAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.N_PLUG, new NPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.N_PORT, new NPort());
        GameObjects.allObjectsLookup.put(MazeObjects.ONE_SHOT_CHAIN_TELEPORT,
                new OneShotChainTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.ONE_SHOT_CONDITIONAL_TELEPORT,
                new OneShotConditionalTeleport());
        GameObjects.allObjectsLookup.put(
                MazeObjects.ONE_SHOT_CONTROLLABLE_TELEPORT,
                new OneShotControllableTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.ONE_SHOT_TELEPORT,
                new OneShotTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_EAST_WALL,
                new OneWayEastWall());
        GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_NORTH_WALL,
                new OneWayNorthWall());
        GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_SOUTH_WALL,
                new OneWaySouthWall());
        GameObjects.allObjectsLookup.put(MazeObjects.ONE_WAY_WEST_WALL,
                new OneWayWestWall());
        GameObjects.allObjectsLookup.put(MazeObjects.O_PLUG, new OPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.O_PORT, new OPort());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_BUTTON,
                new OrangeButton());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_CARPET,
                new OrangeCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_CRYSTAL,
                new OrangeCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_HOUSE,
                new OrangeHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_KEY,
                new OrangeKey());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_LOCK,
                new OrangeLock());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_WALL_OFF,
                new OrangeWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.ORANGE_WALL_ON,
                new OrangeWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.PASSWALL_BOOTS,
                new PasswallBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.PIT, new Pit());
        GameObjects.allObjectsLookup.put(MazeObjects.PLANT_CRYSTAL,
                new PlantCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.PLAYER, new Player());
        GameObjects.allObjectsLookup.put(MazeObjects.POISON_ARROW, null);
        GameObjects.allObjectsLookup.put(MazeObjects.POISON_BOMB,
                new PoisonBomb());
        GameObjects.allObjectsLookup.put(MazeObjects.POISON_BOW,
                new PoisonBow());
        GameObjects.allObjectsLookup.put(MazeObjects.POISONED_BARRIER_GENERATOR,
                new PoisonedBarrierGenerator());
        GameObjects.allObjectsLookup.put(MazeObjects.POISONOUS_AMULET,
                new PoisonousAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.P_PLUG, new PPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.P_PORT, new PPort());
        GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK,
                new PullableBlock());
        GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK_ONCE,
                new PullableBlockOnce());
        GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK_THRICE,
                new PullableBlockThrice());
        GameObjects.allObjectsLookup.put(MazeObjects.PULLABLE_BLOCK_TWICE,
                new PullableBlockTwice());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_BUTTON,
                new PurpleButton());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_CARPET,
                new PurpleCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_CRYSTAL,
                new PurpleCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_HOUSE,
                new PurpleHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_KEY,
                new PurpleKey());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_LOCK,
                new PurpleLock());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_WALL_OFF,
                new PurpleWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.PURPLE_WALL_ON,
                new PurpleWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK,
                new PushableBlock());
        GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK_ONCE,
                new PushableBlockOnce());
        GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK_THRICE,
                new PushableBlockThrice());
        GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_BLOCK_TWICE,
                new PushableBlockTwice());
        GameObjects.allObjectsLookup.put(MazeObjects.PUSHABLE_PULLABLE_BLOCK,
                new PushablePullableBlock());
        GameObjects.allObjectsLookup.put(MazeObjects.Q_PLUG, new QPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.Q_PORT, new QPort());
        GameObjects.allObjectsLookup.put(MazeObjects.QUAKE_BOMB,
                new QuakeBomb());
        GameObjects.allObjectsLookup.put(
                MazeObjects.RANDOM_INVISIBLE_ONE_SHOT_TELEPORT,
                new RandomInvisibleOneShotTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_INVISIBLE_TELEPORT,
                new RandomInvisibleTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_ONE_SHOT_TELEPORT,
                new RandomOneShotTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.RANDOM_TELEPORT,
                new RandomTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_BUTTON,
                new RedButton());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_CARPET,
                new RedCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_CRYSTAL,
                new RedCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_HOUSE, new RedHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_KEY, new RedKey());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_LOCK, new RedLock());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_WALL_OFF,
                new RedWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.RED_WALL_ON,
                new RedWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.REGULAR_BOOTS, null);
        GameObjects.allObjectsLookup.put(MazeObjects.REMOTE_ACTION_WAND,
                new RemoteActionWand());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_BUTTON,
                new RoseButton());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_CARPET,
                new RoseCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_CRYSTAL,
                new RoseCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_HOUSE,
                new RoseHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_KEY, new RoseKey());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_LOCK, new RoseLock());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_WALL_OFF,
                new RoseWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.ROSE_WALL_ON,
                new RoseWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.ROTATION_TRAP,
                new RotationTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.ROTATION_WAND,
                new RotationWand());
        GameObjects.allObjectsLookup.put(MazeObjects.R_PLUG, new RPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.R_PORT, new RPort());
        GameObjects.allObjectsLookup.put(MazeObjects.RUBY, new Ruby());
        GameObjects.allObjectsLookup.put(MazeObjects.RUBY_SQUARE,
                new RubySquare());
        GameObjects.allObjectsLookup.put(MazeObjects.RUBY_WALL, new RubyWall());
        GameObjects.allObjectsLookup.put(MazeObjects.SAND, new Sand());
        GameObjects.allObjectsLookup.put(MazeObjects.SAPPHIRE, new Sapphire());
        GameObjects.allObjectsLookup.put(MazeObjects.SAPPHIRE_SQUARE,
                new SapphireSquare());
        GameObjects.allObjectsLookup.put(MazeObjects.SAPPHIRE_WALL,
                new SapphireWall());
        GameObjects.allObjectsLookup.put(MazeObjects.SEALED_FINISH, null);
        GameObjects.allObjectsLookup.put(MazeObjects.SEALING_WALL,
                new SealingWall());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_BUTTON,
                new SeaweedButton());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_CARPET,
                new SeaweedCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_CRYSTAL,
                new SeaweedCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_HOUSE,
                new SeaweedHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_KEY,
                new SeaweedKey());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_LOCK,
                new SeaweedLock());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_WALL_OFF,
                new SeaweedWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.SEAWEED_WALL_ON,
                new SeaweedWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.SHOCK_ARROW, null);
        GameObjects.allObjectsLookup.put(MazeObjects.SHOCK_BOMB,
                new ShockBomb());
        GameObjects.allObjectsLookup.put(MazeObjects.SHOCK_BOW, new ShockBow());
        GameObjects.allObjectsLookup.put(MazeObjects.SHOCKED_BARRIER_GENERATOR,
                new ShockedBarrierGenerator());
        GameObjects.allObjectsLookup.put(MazeObjects.SHUFFLE_BOMB,
                new ShuffleBomb());
        GameObjects.allObjectsLookup.put(MazeObjects.SIGN, new Sign());
        GameObjects.allObjectsLookup.put(MazeObjects.SIGNAL_CRYSTAL, null);
        GameObjects.allObjectsLookup.put(MazeObjects.SILVER_SQUARE,
                new SilverSquare());
        GameObjects.allObjectsLookup.put(MazeObjects.SILVER_WALL,
                new SilverWall());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_BUTTON,
                new SkyButton());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_CARPET,
                new SkyCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_CRYSTAL,
                new SkyCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_HOUSE, new SkyHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_KEY, new SkyKey());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_LOCK, new SkyLock());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_WALL_OFF,
                new SkyWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.SKY_WALL_ON,
                new SkyWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.SLIME, new Slime());
        GameObjects.allObjectsLookup.put(MazeObjects.SLIPPERY_BOOTS,
                new SlipperyBoots());
        GameObjects.allObjectsLookup.put(MazeObjects.SNOW, new Snow());
        GameObjects.allObjectsLookup.put(MazeObjects.S_PLUG, new SPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.S_PORT, new SPort());
        GameObjects.allObjectsLookup.put(MazeObjects.SPRINGBOARD,
                new Springboard());
        GameObjects.allObjectsLookup.put(MazeObjects.STAIRS_DOWN,
                new StairsDown());
        GameObjects.allObjectsLookup.put(MazeObjects.STAIRS_UP, new StairsUp());
        GameObjects.allObjectsLookup.put(MazeObjects.STUMP, new Stump());
        GameObjects.allObjectsLookup.put(MazeObjects.SUNKEN_BLOCK,
                new SunkenBlock());
        GameObjects.allObjectsLookup.put(MazeObjects.SUN_STONE, new SunStone());
        GameObjects.allObjectsLookup.put(MazeObjects.SUPER_HEAL_POTION,
                new SuperHealPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.SUPER_HURT_POTION,
                new SuperHurtPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.SUPER_UNKNOWN_POTION,
                new SuperUnknownPotion());
        GameObjects.allObjectsLookup.put(MazeObjects.TABLET, new Tablet());
        GameObjects.allObjectsLookup.put(MazeObjects.TABLET_SLOT,
                new TabletSlot());
        GameObjects.allObjectsLookup.put(MazeObjects.TELEPORT, new Teleport());
        GameObjects.allObjectsLookup.put(MazeObjects.TELEPORT_WAND,
                new TeleportWand());
        GameObjects.allObjectsLookup.put(MazeObjects.TILE, new Tile());
        GameObjects.allObjectsLookup.put(MazeObjects.TOPAZ_SQUARE,
                new TopazSquare());
        GameObjects.allObjectsLookup.put(MazeObjects.TOPAZ_WALL,
                new TopazWall());
        GameObjects.allObjectsLookup.put(MazeObjects.T_PLUG, new TPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.T_PORT, new TPort());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_0,
                new TrappedWall0());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_1,
                new TrappedWall1());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_2,
                new TrappedWall2());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_3,
                new TrappedWall3());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_4,
                new TrappedWall4());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_5,
                new TrappedWall5());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_6,
                new TrappedWall6());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_7,
                new TrappedWall7());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_8,
                new TrappedWall8());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_9,
                new TrappedWall9());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_10,
                new TrappedWall10());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_11,
                new TrappedWall11());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_12,
                new TrappedWall12());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_13,
                new TrappedWall13());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_14,
                new TrappedWall14());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_15,
                new TrappedWall15());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_16,
                new TrappedWall16());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_17,
                new TrappedWall17());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_18,
                new TrappedWall18());
        GameObjects.allObjectsLookup.put(MazeObjects.TRAPPED_WALL_19,
                new TrappedWall19());
        GameObjects.allObjectsLookup.put(MazeObjects.TREASURE_CHEST,
                new TreasureChest());
        GameObjects.allObjectsLookup.put(MazeObjects.TREE, new Tree());
        GameObjects.allObjectsLookup.put(MazeObjects.TRUE_SIGHT_AMULET,
                new TrueSightAmulet());
        GameObjects.allObjectsLookup.put(MazeObjects.TUNDRA, new Tundra());
        GameObjects.allObjectsLookup.put(MazeObjects.TWO_WAY_TELEPORT,
                new TwoWayTeleport());
        GameObjects.allObjectsLookup.put(MazeObjects.U_PLUG, new UPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.U_PORT, new UPort());
        GameObjects.allObjectsLookup.put(MazeObjects.U_TURN_TRAP,
                new UTurnTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.VARIABLE_HEAL_TRAP,
                new VariableHealTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.VARIABLE_HURT_TRAP,
                new VariableHurtTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.VERTICAL_BARRIER,
                new VerticalBarrier());
        GameObjects.allObjectsLookup.put(MazeObjects.V_PLUG, new VPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.V_PORT, new VPort());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL, new Wall());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_BREAKING_WAND,
                new WallBreakingWand());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_MAKING_TRAP,
                new WallMakingTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_MAKING_WAND,
                new WallMakingWand());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_0,
                new WallTrap0());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_1,
                new WallTrap1());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_2,
                new WallTrap2());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_3,
                new WallTrap3());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_4,
                new WallTrap4());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_5,
                new WallTrap5());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_6,
                new WallTrap6());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_7,
                new WallTrap7());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_8,
                new WallTrap8());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_9,
                new WallTrap9());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_10,
                new WallTrap10());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_11,
                new WallTrap11());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_12,
                new WallTrap12());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_13,
                new WallTrap13());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_14,
                new WallTrap14());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_15,
                new WallTrap15());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_16,
                new WallTrap16());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_17,
                new WallTrap17());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_18,
                new WallTrap18());
        GameObjects.allObjectsLookup.put(MazeObjects.WALL_TRAP_19,
                new WallTrap19());
        GameObjects.allObjectsLookup.put(MazeObjects.WARP_BOMB, new WarpBomb());
        GameObjects.allObjectsLookup.put(MazeObjects.WARP_TRAP, new WarpTrap());
        GameObjects.allObjectsLookup.put(MazeObjects.WARP_WAND, new WarpWand());
        GameObjects.allObjectsLookup.put(MazeObjects.WATER, new Water());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_BUTTON,
                new WhiteButton());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_CARPET,
                new WhiteCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_CRYSTAL,
                new WhiteCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_HOUSE,
                new WhiteHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_KEY, new WhiteKey());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_LOCK,
                new WhiteLock());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_WALL_OFF,
                new WhiteWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.WHITE_WALL_ON,
                new WhiteWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.W_PLUG, new WPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.W_PORT, new WPort());
        GameObjects.allObjectsLookup.put(MazeObjects.X_PLUG, new XPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.X_PORT, new XPort());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_BUTTON,
                new YellowButton());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_CARPET,
                new YellowCarpet());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_CRYSTAL,
                new YellowCrystal());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_HOUSE,
                new YellowHouse());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_KEY,
                new YellowKey());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_LOCK,
                new YellowLock());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_WALL_OFF,
                new YellowWallOff());
        GameObjects.allObjectsLookup.put(MazeObjects.YELLOW_WALL_ON,
                new YellowWallOn());
        GameObjects.allObjectsLookup.put(MazeObjects.Y_PLUG, new YPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.Y_PORT, new YPort());
        GameObjects.allObjectsLookup.put(MazeObjects.Z_PLUG, new ZPlug());
        GameObjects.allObjectsLookup.put(MazeObjects.Z_PORT, new ZPort());
        final Collection<MazeObjectModel> values = GameObjects.removeNullValues(
                GameObjects.allObjectsLookup.values());
        GameObjects.allObjects = values.toArray(new MazeObjectModel[values
                .size()]);
    }

    private static Collection<MazeObjectModel> removeNullValues(
            Collection<MazeObjectModel> values) {
        return values.stream().filter(GameObjects.itemNotNull()).collect(
                Collectors.<MazeObjectModel> toList());
    }

    private static Predicate<? super MazeObjectModel> itemNotNull() {
        return p -> p != null;
    }

    private GameObjects() {
        super();
    }

    public static boolean isOfType(final MazeObjects obj,
            final MazeObjectType testType) {
        switch (obj) {
        case AMETHYST:
        case DIAMOND:
        case RUBY:
        case SAPPHIRE:
            return testType == MazeObjectType.SCORE_INCREASER
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case ANNIHILATION_WAND:
        case DARK_WAND:
        case DISARM_TRAP_WAND:
        case FINISH_MAKING_WAND:
        case LIGHT_WAND:
        case REMOTE_ACTION_WAND:
        case ROTATION_WAND:
        case TELEPORT_WAND:
        case WALL_BREAKING_WAND:
        case WALL_MAKING_WAND:
        case WARP_WAND:
            return testType == MazeObjectType.WAND
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case AQUA_BOOTS:
        case BIO_HAZARD_BOOTS:
        case FIRE_BOOTS:
        case GLUE_BOOTS:
        case HEAL_BOOTS:
        case HOT_BOOTS:
        case METAL_BOOTS:
        case PASSWALL_BOOTS:
        case SLIPPERY_BOOTS:
            return testType == MazeObjectType.BOOTS
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case ARROW:
        case FIRE_ARROW:
        case GHOST_ARROW:
        case ICE_ARROW:
        case POISON_ARROW:
        case SHOCK_ARROW:
            return testType == MazeObjectType.TRANSIENT
                    || testType == MazeObjectType.WALL;
        case ARROW_TRAP:
        case CLOCKWISE_ROTATION_TRAP:
        case CONFUSION_TRAP:
        case COUNTERCLOCKWISE_ROTATION_TRAP:
        case DIZZINESS_TRAP:
        case DRUNK_TRAP:
        case EXPLORE_TRAP:
        case HEAL_TRAP:
        case HURT_TRAP:
        case NO_EXPLORE_TRAP:
        case ROTATION_TRAP:
        case U_TURN_TRAP:
        case VARIABLE_HEAL_TRAP:
        case VARIABLE_HURT_TRAP:
        case WALL_MAKING_TRAP:
            return testType == MazeObjectType.TRAP;
        case AXE:
            return testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case A_PLUG:
        case B_PLUG:
        case C_PLUG:
        case D_PLUG:
        case E_PLUG:
        case F_PLUG:
        case G_PLUG:
        case H_PLUG:
        case I_PLUG:
        case J_PLUG:
        case K_PLUG:
        case L_PLUG:
        case M_PLUG:
        case N_PLUG:
        case O_PLUG:
        case P_PLUG:
        case Q_PLUG:
        case R_PLUG:
        case S_PLUG:
        case T_PLUG:
        case U_PLUG:
        case V_PLUG:
        case W_PLUG:
        case X_PLUG:
        case Y_PLUG:
        case Z_PLUG:
            return testType == MazeObjectType.LETTER
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case A_PORT:
        case B_PORT:
        case C_PORT:
        case D_PORT:
        case E_PORT:
        case F_PORT:
        case G_PORT:
        case H_PORT:
        case I_PORT:
        case J_PORT:
        case K_PORT:
        case L_PORT:
        case M_PORT:
        case N_PORT:
        case O_PORT:
        case P_PORT:
        case Q_PORT:
        case R_PORT:
        case S_PORT:
        case T_PORT:
        case U_PORT:
        case V_PORT:
        case W_PORT:
        case X_PORT:
        case Y_PORT:
        case Z_PORT:
            return testType == MazeObjectType.LETTER
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.WALL;
        case BARRIER_GENERATOR:
        case ENRAGED_BARRIER_GENERATOR:
        case ICED_BARRIER_GENERATOR:
        case POISONED_BARRIER_GENERATOR:
        case SHOCKED_BARRIER_GENERATOR:
            return testType == MazeObjectType.GENERATOR
                    || testType == MazeObjectType.FREEZABLE
                    || testType == MazeObjectType.BURNABLE
                    || testType == MazeObjectType.POISONABLE
                    || testType == MazeObjectType.SHOCKABLE
                    || testType == MazeObjectType.USED_ON_COLLIDE
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.WALL;
        case BLACK_CRYSTAL:
        case BLUE_CRYSTAL:
        case CYAN_CRYSTAL:
        case DARK_BLUE_CRYSTAL:
        case DARK_CYAN_CRYSTAL:
        case DARK_GRAY_CRYSTAL:
        case DARK_GREEN_CRYSTAL:
        case DARK_MAGENTA_CRYSTAL:
        case DARK_RED_CRYSTAL:
        case DARK_YELLOW_CRYSTAL:
        case GRAY_CRYSTAL:
        case GREEN_CRYSTAL:
        case LIGHT_BLUE_CRYSTAL:
        case LIGHT_CYAN_CRYSTAL:
        case LIGHT_GRAY_CRYSTAL:
        case LIGHT_GREEN_CRYSTAL:
        case LIGHT_MAGENTA_CRYSTAL:
        case LIGHT_RED_CRYSTAL:
        case LIGHT_YELLOW_CRYSTAL:
        case MAGENTA_CRYSTAL:
        case ORANGE_CRYSTAL:
        case PLANT_CRYSTAL:
        case PURPLE_CRYSTAL:
        case RED_CRYSTAL:
        case ROSE_CRYSTAL:
        case SEAWEED_CRYSTAL:
        case SKY_CRYSTAL:
        case WHITE_CRYSTAL:
        case YELLOW_CRYSTAL:
            return testType == MazeObjectType.PROGRAMMABLE_USE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case BLUE_BUTTON:
        case CYAN_BUTTON:
        case GREEN_BUTTON:
        case MAGENTA_BUTTON:
        case METAL_BUTTON:
        case ORANGE_BUTTON:
        case PURPLE_BUTTON:
        case RED_BUTTON:
        case ROSE_BUTTON:
        case SEAWEED_BUTTON:
        case SKY_BUTTON:
        case WHITE_BUTTON:
        case YELLOW_BUTTON:
            return testType == MazeObjectType.BUTTON
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.AUTO_USE;
        case BLUE_CARPET:
        case CYAN_CARPET:
        case GREEN_CARPET:
        case MAGENTA_CARPET:
        case ORANGE_CARPET:
        case PURPLE_CARPET:
        case RED_CARPET:
        case ROSE_CARPET:
        case SEAWEED_CARPET:
        case SKY_CARPET:
        case WHITE_CARPET:
        case YELLOW_CARPET:
        case CUT_TREE:
        case DIRT:
        case GRASS:
        case ICE:
        case SAND:
        case SNOW:
        case SUNKEN_BLOCK:
        case TUNDRA:
            return testType == MazeObjectType.GROUND;
        case HOT_ROCK:
            return testType == MazeObjectType.GROUND
                    || testType == MazeObjectType.BURNING;
        case TILE:
            return testType == MazeObjectType.GROUND
                    || testType == MazeObjectType.SUPPORTS_MOVING;
        case BLUE_HOUSE:
        case CYAN_HOUSE:
        case EXIT:
        case GREEN_HOUSE:
        case MAGENTA_HOUSE:
        case ORANGE_HOUSE:
        case PURPLE_HOUSE:
        case RED_HOUSE:
        case ROSE_HOUSE:
        case SEAWEED_HOUSE:
        case SKY_HOUSE:
        case WHITE_HOUSE:
        case YELLOW_HOUSE:
            return testType == MazeObjectType.LEVEL_CHANGE
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.TELEPORT;
        case BLUE_KEY:
        case CYAN_KEY:
        case GREEN_KEY:
        case HAMMER:
        case KEY:
        case MAGENTA_KEY:
        case METAL_KEY:
        case ORANGE_KEY:
        case PURPLE_KEY:
        case RED_KEY:
        case ROSE_KEY:
        case SEAWEED_KEY:
        case SKY_KEY:
        case TABLET:
        case WHITE_KEY:
        case YELLOW_KEY:
            return testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case BLUE_LOCK:
        case BRICK_WALL:
        case CYAN_LOCK:
        case GREEN_LOCK:
        case LOCK:
        case MAGENTA_LOCK:
        case METAL_DOOR:
        case ORANGE_LOCK:
        case PURPLE_LOCK:
        case RED_LOCK:
        case ROSE_LOCK:
        case SEAWEED_LOCK:
        case SKY_LOCK:
        case TABLET_SLOT:
        case WHITE_LOCK:
        case YELLOW_LOCK:
            return testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.WALL;
        case BLUE_WALL_OFF:
        case CYAN_WALL_OFF:
        case GREEN_WALL_OFF:
        case MAGENTA_WALL_OFF:
        case ORANGE_WALL_OFF:
        case PURPLE_WALL_OFF:
        case RED_WALL_OFF:
        case ROSE_WALL_OFF:
        case SEAWEED_WALL_OFF:
        case SKY_WALL_OFF:
        case WHITE_WALL_OFF:
        case YELLOW_WALL_OFF:
            return testType == MazeObjectType.TOGGLABLE;
        case BLUE_WALL_ON:
        case CYAN_WALL_ON:
        case GREEN_WALL_ON:
        case MAGENTA_WALL_ON:
        case ORANGE_WALL_ON:
        case PURPLE_WALL_ON:
        case RED_WALL_ON:
        case ROSE_WALL_ON:
        case SEAWEED_WALL_ON:
        case SKY_WALL_ON:
        case WHITE_WALL_ON:
        case YELLOW_WALL_ON:
            return testType == MazeObjectType.TOGGLABLE
                    || testType == MazeObjectType.WALL;
        case BOUNDS:
        case SEALING_WALL:
            return testType == MazeObjectType.IMMUTABLE
                    || testType == MazeObjectType.WALL;
        case BREAKABLE_WALL_HORIZONTAL:
        case BREAKABLE_WALL_VERTICAL:
        case EXPLODING_WALL:
            return testType == MazeObjectType.BREAKABLE
                    || testType == MazeObjectType.CHAIN_REACTION
                    || testType == MazeObjectType.USED_ON_COLLIDE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.WALL;
        case CHAIN_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case CONDITIONAL_CHAIN_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.CONDITIONAL
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case CONDITIONAL_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.CONDITIONAL
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case CONTROLLABLE_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case COUNTERPOISON_AMULET:
            return testType == MazeObjectType.AMULET
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case CRACKED_WALL:
        case CRUMBLING_WALL:
        case DAMAGEABLE_WALL:
        case DAMAGED_WALL:
            return testType == MazeObjectType.USED_ON_COLLIDE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.BREAKABLE
                    || testType == MazeObjectType.WALL;
        case CREVASSE:
        case STUMP:
            return testType == MazeObjectType.ALLOWS_ARROWS
                    || testType == MazeObjectType.WALL;
        case CRYSTAL_WALL:
            return testType == MazeObjectType.PROGRAMMABLE_USE
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.WALL;
        case DARKNESS:
            return testType == MazeObjectType.BLOCKS_SIGHT;
        case BRIGHTNESS_GEM:
        case DARKNESS_GEM:
        case DIMNESS_GEM:
        case LIGHTNESS_GEM:
            return testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.LIGHT_MODIFIER
                    || testType == MazeObjectType.CONTAINABLE;
        case DARK_GEM:
        case LIGHT_GEM:
            return testType == MazeObjectType.WALL
                    || testType == MazeObjectType.LIGHT_MODIFIER;
        case DOOR:
            return testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case DOUBLE_HOURGLASS:
        case HALF_HOURGLASS:
        case HOURGLASS:
            return testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.TIME_MODIFIER
                    || testType == MazeObjectType.CONTAINABLE;
        case ENERGY_SPHERE:
            return testType == MazeObjectType.PASS
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INFINITE_USE;
        case FADING_WALL:
            return testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.USED_ON_APPROACH
                    || testType == MazeObjectType.WALL;
        case FINISH:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.WINNER
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case FINISH_TO:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.WINNER
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.PROGRAMMABLE_USE;
        case FIRE_AMULET:
            return testType == MazeObjectType.AMULET
                    || testType == MazeObjectType.BURNING
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case FIRE_BOMB:
            return testType == MazeObjectType.BOMB
                    || testType == MazeObjectType.BURNING
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case FIRE_BOW:
            return testType == MazeObjectType.BOW
                    || testType == MazeObjectType.BURNING
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case FORCE_FIELD:
            return testType == MazeObjectType.FIELD
                    || testType == MazeObjectType.SHOCKING
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.WALL;
        case GARNET_SQUARE:
        case GOLDEN_SQUARE:
        case RUBY_SQUARE:
        case SAPPHIRE_SQUARE:
        case SILVER_SQUARE:
        case TOPAZ_SQUARE:
            return testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.KEY
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case GARNET_WALL:
        case GOLDEN_WALL:
        case RUBY_WALL:
        case SAPPHIRE_WALL:
        case SILVER_WALL:
        case TOPAZ_WALL:
            return testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.WALL;
        case GHOST_AMULET:
            return testType == MazeObjectType.AMULET
                    || testType == MazeObjectType.GHOSTLY
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case GHOST_BOW:
            return testType == MazeObjectType.BOW
                    || testType == MazeObjectType.GHOSTLY
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case HORIZONTAL_BARRIER:
        case VERTICAL_BARRIER:
            return testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.HEALTH_CHANGE
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.USED_ON_COLLIDE
                    || testType == MazeObjectType.WALL;
        case ICE_AMULET:
            return testType == MazeObjectType.BOMB
                    || testType == MazeObjectType.FREEZING
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case ICE_BOMB:
            return testType == MazeObjectType.BOMB
                    || testType == MazeObjectType.FREEZING
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case ICE_BOW:
            return testType == MazeObjectType.BOW
                    || testType == MazeObjectType.FREEZING
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case INVISIBLE_CHAIN_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case INVISIBLE_CONDITIONAL_CHAIN_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.CONDITIONAL
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case INVISIBLE_CONDITIONAL_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.CONDITIONAL
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case INVISIBLE_ONE_SHOT_CHAIN_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case INVISIBLE_ONE_SHOT_CONDITIONAL_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.CONDITIONAL
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case INVISIBLE_ONE_SHOT_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case INVISIBLE_PIT:
        case INVISIBLE_SPRINGBOARD:
        case INVISIBLE_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case INVISIBLE_WALL:
            return testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.WALL;
        case LAVA:
            return testType == MazeObjectType.GROUND
                    || testType == MazeObjectType.FIELD
                    || testType == MazeObjectType.BURNING;
        case MAJOR_HEAL_POTION:
        case MAJOR_HURT_POTION:
        case MAJOR_UNKNOWN_POTION:
        case MINOR_HEAL_POTION:
        case MINOR_HURT_POTION:
        case MINOR_UNKNOWN_POTION:
        case SUPER_HEAL_POTION:
        case SUPER_HURT_POTION:
        case SUPER_UNKNOWN_POTION:
            return testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.HEALTH_CHANGE
                    || testType == MazeObjectType.BREAKABLE
                    || testType == MazeObjectType.CONTAINABLE;
        case MASTER_TRAPPED_WALL:
        case TRAPPED_WALL_0:
        case TRAPPED_WALL_1:
        case TRAPPED_WALL_2:
        case TRAPPED_WALL_3:
        case TRAPPED_WALL_4:
        case TRAPPED_WALL_5:
        case TRAPPED_WALL_6:
        case TRAPPED_WALL_7:
        case TRAPPED_WALL_8:
        case TRAPPED_WALL_9:
        case TRAPPED_WALL_10:
        case TRAPPED_WALL_11:
        case TRAPPED_WALL_12:
        case TRAPPED_WALL_13:
        case TRAPPED_WALL_14:
        case TRAPPED_WALL_15:
        case TRAPPED_WALL_16:
        case TRAPPED_WALL_17:
        case TRAPPED_WALL_18:
        case TRAPPED_WALL_19:
            return testType == MazeObjectType.TRIGGER_TRAP;
        case MASTER_WALL_TRAP:
        case WALL_TRAP_0:
        case WALL_TRAP_1:
        case WALL_TRAP_2:
        case WALL_TRAP_3:
        case WALL_TRAP_4:
        case WALL_TRAP_5:
        case WALL_TRAP_6:
        case WALL_TRAP_7:
        case WALL_TRAP_8:
        case WALL_TRAP_9:
        case WALL_TRAP_10:
        case WALL_TRAP_11:
        case WALL_TRAP_12:
        case WALL_TRAP_13:
        case WALL_TRAP_14:
        case WALL_TRAP_15:
        case WALL_TRAP_16:
        case WALL_TRAP_17:
        case WALL_TRAP_18:
        case WALL_TRAP_19:
            return testType == MazeObjectType.WALL
                    || testType == MazeObjectType.TRIGGERED;
        case MOON_STONE:
        case SUN_STONE:
            return testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case MOVING_BLOCK:
            return testType == MazeObjectType.WALL
                    || testType == MazeObjectType.AUTO_MOVE;
        case MOVING_FINISH:
            return testType == MazeObjectType.AUTO_MOVE;
        case NORMAL_AMULET:
        case NO_BOOTS:
            return testType == MazeObjectType.INVENTORY_MODIFIER
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case ONE_SHOT_CHAIN_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.CHAIN_LINKED
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case ONE_SHOT_CONDITIONAL_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.CONDITIONAL
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case ONE_SHOT_CONTROLLABLE_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case ONE_SHOT_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case ONE_WAY_EAST_WALL:
        case ONE_WAY_NORTH_WALL:
        case ONE_WAY_SOUTH_WALL:
        case ONE_WAY_WEST_WALL:
            return testType == MazeObjectType.WALL
                    || testType == MazeObjectType.MOVE_RESTRICTION;
        case PIT:
        case SPRINGBOARD:
        case TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case PLAYER:
            return testType == MazeObjectType.CHARACTER;
        case POISONOUS_AMULET:
            return testType == MazeObjectType.AMULET
                    || testType == MazeObjectType.POISONOUS
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case POISON_BOMB:
            return testType == MazeObjectType.BOMB
                    || testType == MazeObjectType.POISONOUS
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case POISON_BOW:
            return testType == MazeObjectType.BOW
                    || testType == MazeObjectType.POISONOUS
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case PULLABLE_BLOCK:
        case PUSHABLE_BLOCK:
        case PUSHABLE_PULLABLE_BLOCK:
            return testType == MazeObjectType.MOVABLE;
        case PULLABLE_BLOCK_ONCE:
        case PUSHABLE_BLOCK_ONCE:
            return testType == MazeObjectType.MOVABLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case PULLABLE_BLOCK_THRICE:
        case PULLABLE_BLOCK_TWICE:
        case PUSHABLE_BLOCK_THRICE:
        case PUSHABLE_BLOCK_TWICE:
            return testType == MazeObjectType.MOVABLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.MULTIPLE_USE;
        case QUAKE_BOMB:
        case SHUFFLE_BOMB:
        case WARP_BOMB:
            return testType == MazeObjectType.BOMB
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case RANDOM_INVISIBLE_ONE_SHOT_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.RANDOM
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case RANDOM_INVISIBLE_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.RANDOM
                    || testType == MazeObjectType.INVISIBLE
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case RANDOM_ONE_SHOT_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.RANDOM
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.SINGLE_USE;
        case RANDOM_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.RANDOM
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE;
        case SEALED_FINISH:
            return testType == MazeObjectType.IMMUTABLE;
        case SHOCK_BOMB:
            return testType == MazeObjectType.BOMB
                    || testType == MazeObjectType.SHOCKING
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case SHOCK_BOW:
            return testType == MazeObjectType.BOW
                    || testType == MazeObjectType.SHOCKING
                    || testType == MazeObjectType.USABLE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.MULTIPLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case SIGN:
            return testType == MazeObjectType.TEXT_HOLDER;
        case SLIME:
            return testType == MazeObjectType.GROUND
                    || testType == MazeObjectType.FIELD
                    || testType == MazeObjectType.POISONOUS;
        case STAIRS_DOWN:
        case STAIRS_UP:
        case TWO_WAY_TELEPORT:
            return testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.TWO_WAY;
        case TREASURE_CHEST:
            return testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.CONTAINER
                    || testType == MazeObjectType.WALL;
        case TREE:
            return testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.LOCK
                    || testType == MazeObjectType.WALL;
        case TRUE_SIGHT_AMULET:
            return testType == MazeObjectType.AMULET
                    || testType == MazeObjectType.LIGHT_MODIFIER
                    || testType == MazeObjectType.AUTO_USE
                    || testType == MazeObjectType.INVENTORYABLE
                    || testType == MazeObjectType.SINGLE_USE
                    || testType == MazeObjectType.CONTAINABLE;
        case WALL:
            return testType == MazeObjectType.WALL;
        case WARP_TRAP:
            return testType == MazeObjectType.TRAP
                    || testType == MazeObjectType.TELEPORT
                    || testType == MazeObjectType.RANDOM
                    || testType == MazeObjectType.INFINITE_USE
                    || testType == MazeObjectType.AUTO_USE;
        case WATER:
            return testType == MazeObjectType.GROUND
                    || testType == MazeObjectType.FIELD
                    || testType == MazeObjectType.FREEZING;
        default:
            return false;
        }
    }

    public static MazeObjectModel getEmptySpace() {
        return GameObjects.createObject(MazeObjects.EMPTY);
    }

    public static MazeObjectModel[] getAllObjects() {
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

    public static MazeObjectModel[] getAllObjectsWithRuleSets() {
        final MazeObjectModel[] tempAllObjectsWithRuleSets = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].hasRuleSet()) {
                tempAllObjectsWithRuleSets[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllObjectsWithRuleSet : tempAllObjectsWithRuleSets) {
            if (tempAllObjectsWithRuleSet != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allObjectsWithRuleSets = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllObjectsWithRuleSet : tempAllObjectsWithRuleSets) {
            if (tempAllObjectsWithRuleSet != null) {
                allObjectsWithRuleSets[objectCount] = tempAllObjectsWithRuleSet;
                objectCount++;
            }
        }
        return allObjectsWithRuleSets;
    }

    public static MazeObjectModel[] getAllObjectsWithoutRuleSets() {
        final MazeObjectModel[] tempAllObjectsWithoutRuleSets = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (!GameObjects.allObjects[x].hasRuleSet()) {
                tempAllObjectsWithoutRuleSets[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllObjectsWithoutRuleSet : tempAllObjectsWithoutRuleSets) {
            if (tempAllObjectsWithoutRuleSet != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allObjectsWithoutRuleSets = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllObjectsWithoutRuleSet : tempAllObjectsWithoutRuleSets) {
            if (tempAllObjectsWithoutRuleSet != null) {
                allObjectsWithoutRuleSets[objectCount] = tempAllObjectsWithoutRuleSet;
                objectCount++;
            }
        }
        return allObjectsWithoutRuleSets;
    }

    public static MazeObjectModel[] getAllGroundLayerObjects() {
        final MazeObjectModel[] tempAllGroundLayerObjects = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
                tempAllGroundLayerObjects[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllGroundLayerObject : tempAllGroundLayerObjects) {
            if (tempAllGroundLayerObject != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allGroundLayerObjects = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllGroundLayerObject : tempAllGroundLayerObjects) {
            if (tempAllGroundLayerObject != null) {
                allGroundLayerObjects[objectCount] = tempAllGroundLayerObject;
                objectCount++;
            }
        }
        return allGroundLayerObjects;
    }

    public static MazeObjectModel[] getAllObjectLayerObjects() {
        final MazeObjectModel[] tempAllObjectLayerObjects = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].getLayer() == Layers.OBJECT) {
                tempAllObjectLayerObjects[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllObjectLayerObject : tempAllObjectLayerObjects) {
            if (tempAllObjectLayerObject != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allObjectLayerObjects = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllObjectLayerObject : tempAllObjectLayerObjects) {
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
                tempAllGroundLayerNames[x] = GameObjects.allObjects[x]
                        .getName();
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
                tempAllObjectLayerNames[x] = GameObjects.allObjects[x]
                        .getName();
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

    public static BufferedImageIcon[] getAllEditorAppearances() {
        final BufferedImageIcon[] allEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
        for (int x = 0; x < allEditorAppearances.length; x++) {
            allEditorAppearances[x] = ObjectImageManager.getTransformedImage(
                    GameObjects.allObjects[x], false);
        }
        return allEditorAppearances;
    }

    public static BufferedImageIcon[] getAllGroundLayerEditorAppearances() {
        final BufferedImageIcon[] tempAllGroundLayerEditorAppearances = new BufferedImageIcon[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].getLayer() == Layers.GROUND) {
                tempAllGroundLayerEditorAppearances[x] = ObjectImageManager
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
                tempAllObjectLayerEditorAppearances[x] = ObjectImageManager
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
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.CONTAINABLE)) {
                tempAllContainableObjectEditorAppearances[x] = ObjectImageManager
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

    public static MazeObjectModel[] getAllContainableObjects() {
        final MazeObjectModel[] tempAllContainableObjects = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.CONTAINABLE)) {
                tempAllContainableObjects[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllContainableObject : tempAllContainableObjects) {
            if (tempAllContainableObject != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allContainableObjects = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllContainableObject : tempAllContainableObjects) {
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
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.CONTAINABLE)) {
                tempAllContainableNames[x] = GameObjects.allObjects[x]
                        .getName();
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

    public static MazeObjectModel[] getAllInventoryableObjectsMinusSpecial() {
        final MazeObjectModel[] tempAllInventoryableObjects = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].isInventoryable() && !GameObjects
                    .isOfType(GameObjects.allObjects[x].getUniqueID(),
                            MazeObjectType.BOOTS) && !GameObjects.isOfType(
                                    GameObjects.allObjects[x].getUniqueID(),
                                    MazeObjectType.BOW) && !GameObjects
                                            .isOfType(GameObjects.allObjects[x]
                                                    .getUniqueID(),
                                                    MazeObjectType.AMULET)) {
                tempAllInventoryableObjects[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllInventoryableObject : tempAllInventoryableObjects) {
            if (tempAllInventoryableObject != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allInventoryableObjects = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllInventoryableObject : tempAllInventoryableObjects) {
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
            if (GameObjects.allObjects[x].isInventoryable() && !GameObjects
                    .isOfType(GameObjects.allObjects[x].getUniqueID(),
                            MazeObjectType.BOOTS) && !GameObjects.isOfType(
                                    GameObjects.allObjects[x].getUniqueID(),
                                    MazeObjectType.BOW) && !GameObjects
                                            .isOfType(GameObjects.allObjects[x]
                                                    .getUniqueID(),
                                                    MazeObjectType.AMULET)) {
                tempAllInventoryableNames[x] = GameObjects.allObjects[x]
                        .getName();
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

    public static MazeObjectModel[] getAllProgrammableKeys() {
        final MazeObjectModel[] tempAllProgrammableKeys = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.PROGRAMMABLE_USE)) {
                tempAllProgrammableKeys[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllProgrammableKey : tempAllProgrammableKeys) {
            if (tempAllProgrammableKey != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allProgrammableKeys = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllProgrammableKey : tempAllProgrammableKeys) {
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
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.PROGRAMMABLE_USE)) {
                tempAllProgrammableKeyNames[x] = GameObjects.allObjects[x]
                        .getName();
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

    public static MazeObjectModel[] getAllUsableObjects() {
        final MazeObjectModel[] tempAllUsableObjects = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].isUsable()) {
                tempAllUsableObjects[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllUsableObject : tempAllUsableObjects) {
            if (tempAllUsableObject != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allUsableObjects = new MazeObjectModel[objectCount];
        objectCount = 0;
        for (final MazeObjectModel tempAllUsableObject : tempAllUsableObjects) {
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
            if (GameObjects.allObjects[x].isUsable() && !GameObjects.isOfType(
                    GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.BOW)) {
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

    public static MazeObjectModel[] getAllBows() {
        final MazeObjectModel[] tempAllUsableObjects = new MazeObjectModel[GameObjects.allObjects.length];
        int objectCount = 0;
        for (int x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.BOW)) {
                tempAllUsableObjects[x] = GameObjects.allObjects[x];
            }
        }
        for (final MazeObjectModel tempAllUsableObject : tempAllUsableObjects) {
            if (tempAllUsableObject != null) {
                objectCount++;
            }
        }
        final MazeObjectModel[] allUsableObjects = new MazeObjectModel[objectCount
                + 1];
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
            if (GameObjects.isOfType(GameObjects.allObjects[x].getUniqueID(),
                    MazeObjectType.BOW)) {
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

    public static MazeObjectModel[] getAllRequired(final int layer) {
        final MazeObjectModel[] tempAllRequired = new MazeObjectModel[GameObjects.allObjects.length];
        int x;
        int count = 0;
        for (x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].getLayer() == layer
                    && GameObjects.allObjects[x].isRequired()) {
                tempAllRequired[count] = GameObjects.allObjects[x];
                count++;
            }
        }
        if (count == 0) {
            return null;
        } else {
            final MazeObjectModel[] allRequired = new MazeObjectModel[count];
            for (x = 0; x < count; x++) {
                allRequired[x] = tempAllRequired[x];
            }
            return allRequired;
        }
    }

    public static MazeObjectModel[] getAllWithoutPrerequisiteAndNotRequired(
            final int layer) {
        final MazeObjectModel[] tempAllWithoutPrereq = new MazeObjectModel[GameObjects.allObjects.length];
        int x;
        int count = 0;
        for (x = 0; x < GameObjects.allObjects.length; x++) {
            if (GameObjects.allObjects[x].getLayer() == layer
                    && !GameObjects.allObjects[x].isRequired()) {
                tempAllWithoutPrereq[count] = GameObjects.allObjects[x];
                count++;
            }
        }
        if (count == 0) {
            return null;
        } else {
            final MazeObjectModel[] allWithoutPrereq = new MazeObjectModel[count];
            for (x = 0; x < count; x++) {
                allWithoutPrereq[x] = tempAllWithoutPrereq[x];
            }
            return allWithoutPrereq;
        }
    }

    public static MazeObjectModel[] getAllRequiredSubset(
            final MazeObjectModel[] objs, final int layer) {
        if (objs == null) {
            return null;
        }
        final MazeObjectModel[] tempAllRequired = new MazeObjectModel[objs.length];
        int x;
        int count = 0;
        for (x = 0; x < objs.length; x++) {
            if (objs[x].hasRuleSet()) {
                if (objs[x].getLayer() == layer && objs[x].getRuleSet()
                        .isRequired()) {
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
            final MazeObjectModel[] allRequired = new MazeObjectModel[count];
            for (x = 0; x < count; x++) {
                allRequired[x] = tempAllRequired[x];
            }
            return allRequired;
        }
    }

    public static MazeObjectModel[] getAllWithoutPrerequisiteAndNotRequiredSubset(
            final MazeObjectModel[] objs, final int layer) {
        if (objs == null) {
            return null;
        }
        final MazeObjectModel[] tempAllWithoutPrereq = new MazeObjectModel[objs.length];
        int x;
        int count = 0;
        for (x = 0; x < objs.length; x++) {
            if (objs[x].hasRuleSet()) {
                if (objs[x].getLayer() == layer && !objs[x].getRuleSet()
                        .isRequired()) {
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
            final MazeObjectModel[] allWithoutPrereq = new MazeObjectModel[count];
            for (x = 0; x < count; x++) {
                allWithoutPrereq[x] = tempAllWithoutPrereq[x];
            }
            return allWithoutPrereq;
        }
    }

    public static GenericSingleKey createSignalCrystal() {
        return new SignalCrystal();
    }

    public static MazeObjectModel createObject(final MazeObjects uid) {
        MazeObjectModel instance = GameObjects.allObjectsLookup.get(uid);
        if (instance == null) {
            return null;
        } else {
            try {
                return instance.getClass().getConstructor().newInstance();
            } catch (final InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                return null;
            }
        }
    }

    public static MazeObjectModel createContainerObject(final MazeObjects uid,
            final MazeObjects contentsUID) {
        final MazeObjectModel contents = GameObjects.createObject(contentsUID);
        MazeObjectModel instance = GameObjects.allObjectsLookup.get(uid);
        if (instance == null) {
            return null;
        } else {
            try {
                return instance.getClass().getConstructor(contents.getClass())
                        .newInstance(contents);
            } catch (final InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                return null;
            }
        }
    }

    public static MazeObjectModel createTeleportObject(final MazeObjects uid,
            final int dr, final int dc) {
        MazeObjectModel instance = GameObjects.allObjectsLookup.get(uid);
        if (instance == null) {
            return null;
        } else {
            try {
                return instance.getClass().getConstructor(int.class, int.class)
                        .newInstance(dr, dc);
            } catch (final InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                return null;
            }
        }
    }

    public static MazeObjectModel createTeleportObject(final MazeObjects uid,
            final int dr, final int dc, final int df) {
        MazeObjectModel instance = GameObjects.allObjectsLookup.get(uid);
        if (instance == null) {
            return null;
        } else {
            try {
                return instance.getClass().getConstructor(int.class, int.class,
                        int.class).newInstance(dr, dc, df);
            } catch (final InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                return null;
            }
        }
    }

    public static MazeObjectModel createTeleportObject(final MazeObjects uid,
            final int dr, final int dc, final int df, final int dl) {
        MazeObjectModel instance = GameObjects.allObjectsLookup.get(uid);
        if (instance == null) {
            return null;
        } else {
            try {
                return instance.getClass().getConstructor(int.class, int.class,
                        int.class, int.class).newInstance(dr, dc, df, dl);
            } catch (final InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                return null;
            }
        }
    }

    public static MazeObjectModel readObject(final XDataReader reader,
            final int formatVersion) throws IOException {
        MazeObjectModel o = null;
        String UID = "";
        if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_1) {
            UID = reader.readString();
        } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_2) {
            UID = reader.readString();
        } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_3) {
            UID = reader.readString();
        } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_4) {
            UID = reader.readString();
        } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_5) {
            UID = reader.readString();
        }
        for (final MazeObjectModel allObject : GameObjects.allObjects) {
            try {
                final MazeObjectModel instance = allObject.getClass()
                        .getConstructor().newInstance();
                if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_1) {
                    o = instance.readMazeObjectXML(reader, UID, formatVersion);
                } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_2) {
                    o = instance.readMazeObjectXML2(reader, UID, formatVersion);
                } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_3) {
                    o = instance.readMazeObjectXML3(reader, UID, formatVersion);
                } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_4) {
                    o = instance.readMazeObjectXML4(reader, UID, formatVersion);
                } else if (formatVersion == XMLFormatConstants.XML_MAZE_FORMAT_5) {
                    o = instance.readMazeObjectXML5(reader, UID, formatVersion);
                }
                if (o != null) {
                    return o;
                }
            } catch (final InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException e) {
                Mazer5D.logError(e);
            }
        }
        return null;
    }

    public static void readRuleSet(final XDataReader reader, final int rsFormat)
            throws IOException {
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
                GameObjects.allObjects[x].getRuleSet().readRuleSetXML(reader,
                        rsFormat);
            }
        }
    }

    public static void writeRuleSet(final XDataWriter writer)
            throws IOException {
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
                GameObjects.allObjects[x].getRuleSet().writeRuleSetXML(writer);
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
