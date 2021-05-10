package org.jetpack.controller;

import org.jetpack.gui.GUI;
import org.jetpack.model.Menu;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.states.ArenaState;
import org.jetpack.states.State;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) gameLoop.setState(null);
                if (getModel().isSelectedStart()) gameLoop.setState(new ArenaState(new RandomArenaBuilder(30, 30).createArena()));
        }
    }
}
