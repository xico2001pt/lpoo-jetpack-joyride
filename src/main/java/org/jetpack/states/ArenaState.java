package org.jetpack.states;

import org.jetpack.controller.game.ArenaController;
import org.jetpack.model.arena.Arena;
import org.jetpack.viewer.game.ArenaViewer;

public class ArenaState extends State<Arena> {
    public ArenaState(Arena arena) {
        super(arena, new ArenaController(arena), new ArenaViewer(arena));
    }
}
