package org.retropipes.mazer5d.asset;

import java.net.URL;
import java.util.Properties;

import org.retropipes.diane.asset.sound.DianeSoundIndex;
import org.retropipes.mazer5d.file.FileExtensions;
import org.retropipes.mazer5d.loader.DataLoader;

public enum SoundIndex implements DianeSoundIndex {
    ACTION_FAILED,
    ARROW_FIRED,
    ARROW_DEAD,
    BARRIER,
    BUTTON,
    CHANGE,
    CONFUSED,
    CRACK,
    CREATE,
    DARKNESS,
    DESTROY,
    DIZZY,
    DOWN,
    DRUNK,
    EXPLODE,
    FINISH,
    FORCE_FIELD,
    GAME_OVER,
    GENERATE,
    GRAB,
    HEAL,
    HIGH_SCORE,
    HURT,
    IDENTIFY,
    FALL_INTO_PIT,
    LAVA,
    LIGHT,
    LOGO,
    PUSH_PULL,
    SHATTER,
    SINK_BLOCK,
    SLIME,
    SPRINGBOARD,
    SUN_STONE,
    TELEPORT,
    UNLOCK,
    UP,
    WALK,
    WALK_FAILED,
    WALK_ON_ICE,
    WALK_ON_LAVA,
    WALK_ON_SLIME,
    WALK_ON_WATER,
    WALL_TRAP,
    WATER,
    WIN_GAME,
    _NONE;

    private static String[] allFilenames;
    private static Properties fileExtensions;

    @Override
    public String getName() {
	if (SoundIndex.allFilenames == null && SoundIndex.fileExtensions == null) {
	    SoundIndex.allFilenames = DataLoader.loadSoundData();
	}
	final String soundExt = FileExtensions.getSoundExtensionWithPeriod();
	return SoundIndex.allFilenames[this.ordinal()] + soundExt;
    }

    @Override
    public URL getURL() {
	return SoundIndex.class.getResource("/asset/sound/" + this.getName());
    }
}
