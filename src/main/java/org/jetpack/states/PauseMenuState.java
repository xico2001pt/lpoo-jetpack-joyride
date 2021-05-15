package org.jetpack.states;

import org.jetpack.controller.Controller;
import org.jetpack.controller.menu.MenuPauseController;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.viewer.menu.PauseMenuViewer;
import org.jetpack.viewer.Viewer;

public class PauseMenuState extends State<PauseMenu> {
    public PauseMenuState(PauseMenu menu, State state) {
        super(menu, new MenuPauseController(menu, state), new PauseMenuViewer(menu));
    }
}
