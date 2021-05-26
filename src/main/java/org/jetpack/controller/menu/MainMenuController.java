package org.jetpack.controller.menu;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.states.ArenaState;

public class MainMenuController extends Controller<MainMenu> {
    public static final int INFO_SIZE = 1;

    public MainMenuController(MainMenu menu) {
        super(menu);
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {

        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case ENTER:
                if (getModel().isSelectedStart()) gameLoop.setState(new ArenaState
                        (new RandomArenaBuilder(gameLoop.getWidth() - 2 * INFO_SIZE,
                                gameLoop.getHeight() - 2 * INFO_SIZE).createArena()));
                else if (getModel().isSelectedExit()) gameLoop.stop();
        }
    }
}
