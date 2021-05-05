package controller;

import model.arena.Arena;
import model.arena.ArenaBuilder;

public abstract class GameController {
    private final Arena arena;
    private final ArenaBuilder arenaBuilder;

    public GameController(ArenaBuilder arenaBuilder) {
        this.arenaBuilder = arenaBuilder;
        this.arena = arenaBuilder.createArena();
    }

    public Arena getArena() {
        return arena;
    }

    public ArenaBuilder getArenaBuilder() {
        return arenaBuilder;
    }

    public abstract void handleCollisions();
}
