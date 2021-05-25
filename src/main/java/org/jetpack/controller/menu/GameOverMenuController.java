package org.jetpack.controller.menu;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.states.ArenaState;
import org.jetpack.states.MainMenuState;

public class GameOverMenuController extends Controller<GameOverMenu> {
    public GameOverMenuController(GameOverMenu menu) {
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
                        (new RandomArenaBuilder(28, 18).createArena()));
                else if (getModel().isSelectedExit()) gameLoop.setState(new MainMenuState(new MainMenu()));
        }
    }
}

