package org.jetpack.controller.menu;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.InstructionsMenu;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.states.ArenaState;
import org.jetpack.states.InstructionsMenuState;

public class MainMenuController extends Controller<MainMenu> {

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
                        (new RandomArenaBuilder(gameLoop.getWidth() - 2 * GameLoop.INFO_SIZE,
                                gameLoop.getHeight() - 2 * GameLoop.INFO_SIZE).createArena()));
                else if (getModel().isSelectedInstructions()) gameLoop.setState(
                        new InstructionsMenuState(new InstructionsMenu()));
                else if (getModel().isSelectedExit()) gameLoop.stop();
                break;
            case QUIT:
                gameLoop.stop();
        }
    }
}
