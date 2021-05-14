package org.jetpack.states;

import org.jetpack.controller.Controller;
import org.jetpack.controller.menu.MainMenuController;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.viewer.Viewer;
import org.jetpack.viewer.menu.MainMenuViewer;

public class MainMenuState extends State<MainMenu> {
    public MainMenuState(MainMenu menu) {
        super(menu);
    }

    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MainMenuViewer(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel());
    }
}
