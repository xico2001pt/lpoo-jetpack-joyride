package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;

public class PlayerController extends GameController {
    public PlayerController(Arena arena) {
        super(arena);
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        if (action == GUI.ACTION.UP) movePlayerUp();
        else if (action == GUI.ACTION.DOWN) movePlayerDown();
    }

    private void movePlayerDown() {
        movePlayer(getModel().getPlayer().getPosition().getDown());
    }

    private void movePlayerUp() {
        movePlayer(getModel().getPlayer().getPosition().getUp());
    }

    private void movePlayer(Position position) {
        if (!canMove(position)) return;

        getModel().getPlayer().setPosition(position);
    }


    private boolean canMove(Position position) {
        return position.getY() >= 0 && position.getY() < getModel().getHeight();
    }
}
