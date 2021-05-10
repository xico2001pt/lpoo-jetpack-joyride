package org.jetpack.states;

import org.jetpack.controller.ArenaController;
import org.jetpack.controller.Controller;
import org.jetpack.model.arena.Arena;
import org.jetpack.viewer.Viewer;
import org.jetpack.viewer.WindowViewer;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena arena) {
        super(arena);
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new WindowViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }
}
