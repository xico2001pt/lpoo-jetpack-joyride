package org.jetpack.controller.game;

import org.jetpack.controller.Controller;
import org.jetpack.model.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
