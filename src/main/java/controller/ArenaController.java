package controller;

import model.arena.Arena;

public class ArenaController extends GameController {
    private final PlayerController playerController;
    private final ElementController elementController;

    public ArenaController(Arena arena) {
        super(arena);

        this.playerController = new PlayerController(arena);
        this.elementController = new ElementController(arena);
    }
}
