package org.jetpack.controller.state;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.RandomArenaBuilder;

import java.io.IOException;

public class MainMenuState extends GameState {
    //private MenuViewer viewer;

    public MainMenuState(GameLoop gameLoop, GUI gui) {
        super(gameLoop, gui);
    }

    @Override
    public void update(GUI.ACTION action, long elapsed) {
        if (action == GUI.ACTION.ENTER) this.setGameState(new ArenaState(getGameLoop(), getGui(), new RandomArenaBuilder(30, 30)));
    }

    @Override
    public void render() throws IOException {
        // The code below was used only to test
        getGui().clear();
        getGui().drawText(new Position(5, 14), "this is the MAIN MENU");
        getGui().drawText(new Position(7, 16), "please press ENTER");
        getGui().refresh();
    }
}
