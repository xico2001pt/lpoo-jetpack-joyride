package controller;

import gui.GUI;
import model.Position;
import model.arena.Arena;

public class PlayerController {
    private final Arena arena;

    public PlayerController(Arena arena) {
        this.arena = arena;
    }

    private void movePlayerDown() {
        movePlayer(arena.getPlayer().getPosition().getDown());
    }

    private void movePlayerUp() {
        movePlayer(arena.getPlayer().getPosition().getUp());
    }

    private void movePlayer(Position position) {
        // TODO: See position in arena
        // setPosition for player
        // - If coins, nCoins++
        // - If obstacle, lives--
        // - ...

    }

    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.UP) movePlayerUp();
        else if (action == GUI.ACTION.DOWN) movePlayerDown();
    }
}
