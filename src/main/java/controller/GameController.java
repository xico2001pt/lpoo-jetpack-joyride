package controller;

import model.arena.Arena;
import model.arena.ArenaBuilder;
import model.elements.Element;

import java.util.List;

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

    public abstract void updateArena();
}
