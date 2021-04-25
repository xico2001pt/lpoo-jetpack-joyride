package controller;

import model.arena.Arena;

public abstract class GameController {
    private final Arena arena;

    public GameController(Arena arena) {
        this.arena = arena;
    }
}
