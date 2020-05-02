/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.gui;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.integration.NativeIntegration;
import com.puttysoftware.mazer5d.Modes;
import com.puttysoftware.mazer5d.assets.MusicGroup;
import com.puttysoftware.mazer5d.assets.MusicIndex;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.editor.MazeEditor;
import com.puttysoftware.mazer5d.editor.rulesets.RuleSetPicker;
import com.puttysoftware.mazer5d.files.MazeManager;
import com.puttysoftware.mazer5d.game.GameManager;
import com.puttysoftware.mazer5d.loaders.MusicPlayer;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;

public class BagOStuff {
    // Fields
    private AboutThisGame about;
    private GameManager gameMgr;
    private MazeManager mazeMgr;
    private final MenuManager menuMgr;
    private ObjectHelpViewer oHelpMgr;
    private MazeEditor editor;
    private RuleSetPicker rsPicker;
    private final GUIManager guiMgr;
    private static final int VERSION_MAJOR = 11;
    private static final int VERSION_MINOR = 0;
    private static final int VERSION_BUGFIX = 0;
    private static final int VERSION_BETA = 3;

    // Constructors
    public BagOStuff(final NativeIntegration ni) {
        this.menuMgr = new MenuManager();
        this.menuMgr.configureMenus(ni);
        this.guiMgr = new GUIManager();
    }

    // Methods
    public void showMessage(final String msg) {
        if (Modes.inGame()) {
            this.getGameManager().setStatusMessage(msg);
        } else if (Modes.inEditor()) {
            this.getEditor().setStatusMessage(msg);
        } else {
            CommonDialogs.showDialog(msg);
        }
    }

    public MenuManager getMenuManager() {
        return this.menuMgr;
    }

    public GUIManager getGUIManager() {
        return this.guiMgr;
    }

    public GameManager getGameManager() {
        if (this.gameMgr == null) {
            this.gameMgr = new GameManager();
        }
        return this.gameMgr;
    }

    public MazeManager getMazeManager() {
        if (this.mazeMgr == null) {
            this.mazeMgr = new MazeManager();
        }
        return this.mazeMgr;
    }

    public ObjectHelpViewer getObjectHelpViewer() {
        if (this.oHelpMgr == null) {
            this.oHelpMgr = new ObjectHelpViewer();
        }
        return this.oHelpMgr;
    }

    public MazeEditor getEditor() {
        if (this.editor == null) {
            this.editor = new MazeEditor();
        }
        return this.editor;
    }

    public RuleSetPicker getRuleSetPicker() {
        if (this.rsPicker == null) {
            this.rsPicker = new RuleSetPicker();
        }
        return this.rsPicker;
    }

    public AboutThisGame getAboutThisGame() {
        if (this.about == null) {
            this.about = new AboutThisGame(this.getVersionString());
        }
        return this.about;
    }

    public void playHighScoreSound() {
        SoundPlayer.playSound(SoundIndex.HIGH_SCORE, SoundGroup.USER_INTERFACE);
    }

    public void playLogoSound() {
        MusicPlayer.playMusic(MusicIndex.TITLE, MusicGroup.USER_INTERFACE);
        SoundPlayer.playSound(SoundIndex.LOGO, SoundGroup.USER_INTERFACE);
    }

    private String getVersionString() {
        if (this.isBetaModeEnabled()) {
            return "" + BagOStuff.VERSION_MAJOR + "." + BagOStuff.VERSION_MINOR
                    + "." + BagOStuff.VERSION_BUGFIX + "b"
                    + BagOStuff.VERSION_BETA;
        } else {
            return "" + BagOStuff.VERSION_MAJOR + "." + BagOStuff.VERSION_MINOR
                    + "." + BagOStuff.VERSION_BUGFIX;
        }
    }

    public boolean isBetaModeEnabled() {
        return BagOStuff.VERSION_BETA > 0;
    }
}
