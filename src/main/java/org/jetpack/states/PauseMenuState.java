package org.jetpack.states;

import org.jetpack.controller.Controller;
import org.jetpack.controller.menu.MenuPauseController;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.viewer.menu.PauseMenuViewer;
import org.jetpack.viewer.Viewer;

public class PauseMenuState extends State<PauseMenu> {
    private final State state;

    public PauseMenuState(PauseMenu menu, State state) {
        super(menu);
        // TODO
        this.state = state;
    }

    @Override
    protected Viewer<PauseMenu> getViewer() {
        return new PauseMenuViewer(getModel());
    }

    @Override
    protected Controller<PauseMenu> getController() {
        return new MenuPauseController(getModel(), state);
    }
}
