package org.jetpack.controller.menu;

import org.jetpack.controller.Controller;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.InstructionsMenu;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.states.MainMenuState;

public class InstructionsMenuController extends Controller<InstructionsMenu> {

    public InstructionsMenuController(InstructionsMenu menu) {super(menu);}

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        if (action == GUI.ACTION.ENTER) gameLoop.setState(new MainMenuState(new MainMenu()));
    }
}
