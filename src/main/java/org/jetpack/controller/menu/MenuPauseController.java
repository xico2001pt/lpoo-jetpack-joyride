package org.jetpack.controller.menu;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.states.State;

public class MenuPauseController extends Controller<PauseMenu>  {
    private State state;

    public MenuPauseController(PauseMenu menu, State state) {
        super(menu);
        this.state = state;
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
                if (getModel().isSelectedResume()) gameLoop.setState(state);
                else if (getModel().isSelectedExit()) gameLoop.stop();
        }
    }
}
