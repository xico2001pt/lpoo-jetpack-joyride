package org.jetpack.controller.state;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;

import java.io.IOException;

public abstract class GameState {

    private final GameLoop gameLoop;
    private final GUI gui;

    public GameState(GameLoop gameLoop, GUI gui) {
        this.gameLoop = gameLoop;
        this.gui = gui;
    }

    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

    public GUI getGui() {
        return this.gui;
    }

    protected void setGameState(GameState state) {
        this.gameLoop.setGameState(state);
    }

    public abstract void update(GUI.ACTION action, long elapsed);

    public abstract void render() throws IOException;
}
