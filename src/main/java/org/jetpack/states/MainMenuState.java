package org.jetpack.states;

import org.jetpack.controller.MenuController;
import org.jetpack.model.Menu;

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
