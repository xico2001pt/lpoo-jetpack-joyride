package org.jetpack.controller.state;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;

public abstract class State {

    private GameLoop gameLoop;
    private GUI gui;

    public State(GameLoop gameLoop, GUI gui) {
        this.gameLoop = gameLoop;
        this.gui = gui;
    }

    public abstract GUI.ACTION processInput();

    public abstract void update(GUI.ACTION action, long elapsed);

    public abstract void render();
}
