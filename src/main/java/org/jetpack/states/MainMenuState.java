package org.jetpack.states;

import org.jetpack.controller.Controller;
import org.jetpack.controller.menu.MenuController;
import org.jetpack.model.Menu;
import org.jetpack.viewer.menu.MenuViewer;
import org.jetpack.viewer.Viewer;

public class MainMenuState extends State<Menu> {
    public MainMenuState(Menu menu) {
        super(menu);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
