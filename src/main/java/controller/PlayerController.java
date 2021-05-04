package controller;

import gui.GUI;
import model.Position;
import model.arena.Arena;
import model.elements.Player;
import model.elements.Coin;
import model.elements.Element;
import model.elements.obstacles.Obstacle;

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
