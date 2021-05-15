package org.jetpack.states;

import org.jetpack.controller.game.ArenaController;
import org.jetpack.controller.Controller;
import org.jetpack.model.arena.Arena;
import org.jetpack.viewer.game.ArenaViewer;
import org.jetpack.viewer.Viewer;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena arena) {
        super(arena, new ArenaController(arena), new ArenaViewer(arena));
    }
}
