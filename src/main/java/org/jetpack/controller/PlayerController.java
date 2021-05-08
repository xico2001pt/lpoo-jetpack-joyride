package org.jetpack.controller;

import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

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
        if (!canMove(position)) return;

        arena.getPlayer().setPosition(position);
    }

    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.UP) movePlayerUp();
        else if (action == GUI.ACTION.DOWN) movePlayerDown();
    }

    private boolean canMove(Position position) {
        return position.getY() >= 0 && position.getY() < arena.getHeight();
    }
}
