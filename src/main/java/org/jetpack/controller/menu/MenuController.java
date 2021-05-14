package org.jetpack.controller.menu;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Menu;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.states.ArenaState;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
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
                if (getModel().isSelectedExit()) gameLoop.stop();
                if (getModel().isSelectedStart()) gameLoop.setState(new ArenaState(new RandomArenaBuilder(28, 18).createArena()));
        }
    }
}
