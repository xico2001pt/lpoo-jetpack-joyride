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
        getGui().drawText(new Position(1, 14), "This is supposed to be a Menu!");
        getGui().drawText(new Position(5, 16), "Just press ENTER, dude");
        getGui().refresh();
    }
}
