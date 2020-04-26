/*  Mazer5D: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objectmodel;

import java.io.IOException;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.mazer5d.files.io.XDataReader;
import com.puttysoftware.mazer5d.files.io.XDataWriter;
import com.puttysoftware.mazer5d.files.versions.MazeVersion;
import com.puttysoftware.mazer5d.files.versions.MazeVersions;

public final class GameObjects {
    // Constructor
    private GameObjects() {
    }

    // Methods
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
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllNames() {
        // FIXME: Stub
        return new String[0];
    }

    public static String[] getAllDescriptions() {
        // FIXME: Stub
        return new String[0];
    }

    public static MazeObjectModel[] getAllObjectsWithRuleSets() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static MazeObjectModel[] getAllObjectsWithoutRuleSets() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static MazeObjectModel[] getAllGroundLayerObjects() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static MazeObjectModel[] getAllObjectLayerObjects() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllGroundLayerNames() {
        // FIXME: Stub
        return new String[0];
    }

    public static String[] getAllObjectLayerNames() {
        // FIXME: Stub
        return new String[0];
    }

    public static BufferedImageIcon[] getAllEditorAppearances() {
        // FIXME: Stub
        return new BufferedImageIcon[0];
    }

    public static BufferedImageIcon[] getAllGroundLayerEditorAppearances() {
        // FIXME: Stub
        return new BufferedImageIcon[0];
    }

    public static BufferedImageIcon[] getAllObjectLayerEditorAppearances() {
        // FIXME: Stub
        return new BufferedImageIcon[0];
    }

    public static BufferedImageIcon[] getAllContainableObjectEditorAppearances() {
        // FIXME: Stub
        return new BufferedImageIcon[0];
    }

    public static MazeObjectModel[] getAllContainableObjects() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllContainableNames() {
        // FIXME: Stub
        return new String[0];
    }

    public static MazeObjectModel[] getAllInventoryableObjectsMinusSpecial() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllInventoryableNamesMinusSpecial() {
        // FIXME: Stub
        return new String[0];
    }

    public static MazeObjectModel[] getAllProgrammableKeys() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllProgrammableKeyNames() {
        // FIXME: Stub
        return new String[0];
    }

    public static MazeObjectModel[] getAllUsableObjects() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllUsableNamesMinusSpecial() {
        // FIXME: Stub
        return new String[0];
    }

    public static MazeObjectModel[] getAllBows() {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static String[] getAllBowNames() {
        // FIXME: Stub
        return new String[0];
    }

    public static MazeObjectModel[] getAllRequired(final int layer) {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static MazeObjectModel[] getAllWithoutPrerequisiteAndNotRequired(
            final int layer) {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static MazeObjectModel[] getAllRequiredSubset(
            final MazeObjectModel[] objs, final int layer) {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static MazeObjectModel[] getAllWithoutPrerequisiteAndNotRequiredSubset(
            final MazeObjectModel[] objs, final int layer) {
        // FIXME: Stub
        return new MazeObjectModel[0];
    }

    public static void readRuleSet(final XDataReader reader, final int rsFormat)
            throws IOException {
        // FIXME: Stub
    }

    public static void writeRuleSet(final XDataWriter writer)
            throws IOException {
        // FIXME: Stub
    }

    public static MazeObjectModel createObject(final MazeObjects uid) {
        return new MazeObject(uid);
    }

    public static MazeObjectModel createContainerObject(final MazeObjects uid,
            final MazeObjects contentsUID) {
        // FIXME: Stub
        return new MazeObject(uid);
    }

    public static MazeObjectModel createTeleportObject(final MazeObjects uid,
            final int dr, final int dc) {
        // FIXME: Stub
        return new MazeObject(uid);
    }

    public static MazeObjectModel createTeleportObject(final MazeObjects uid,
            final int dr, final int dc, final int df) {
        // FIXME: Stub
        return new MazeObject(uid);
    }

    public static MazeObjectModel createTeleportObject(final MazeObjects uid,
            final int dr, final int dc, final int df, final int dl) {
        // FIXME: Stub
        return new MazeObject(uid);
    }

    public static MazeObjectModel readObject(final XDataReader reader,
            final MazeVersion formatVersion) throws IOException {
        MazeObjects UID = MazeObjects._NONE;
        if (formatVersion == MazeVersions.LATEST) {
            UID = reader.readMazeObjectID();
        }
        final MazeObjectModel o = new MazeObject(UID);
        o.loadState(reader, UID);
        return o;
    }
}
