package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.movements.DownMovement;
import org.jetpack.model.elements.movements.UpMovement;

public class PlayerController extends GameController {
    private final long movementFrequency; // Milliseconds it takes to move an element
    private long counter;
    private GUI.ACTION actionBefore;

    public PlayerController(Arena arena) {
        super(arena);
        this.movementFrequency = 100;
        this.counter = 0;
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        counter += elapsed;

        //System.out.println(action);

        Player player = getModel().getPlayer();
        if (action == GUI.ACTION.MOUSE_PRESSED && actionBefore == GUI.ACTION.NONE) {
            counter = 0;
            player.setMovement(new UpMovement());
        }
        else if (action == GUI.ACTION.NONE && actionBefore == GUI.ACTION.MOUSE_PRESSED) {
            counter = 0;
            player.setMovement(new DownMovement());
        }

        while (this.counter > movementFrequency) {
            movePlayer(player.getMovement().move(player.getPosition(), getModel()));
            counter -= movementFrequency;
        }
        actionBefore = action;
    }

    private void movePlayer(Position position) {
        if (!canMove(position)) return;
        getModel().getPlayer().setPosition(position);
    }

    private boolean canMove(Position position) {
        return position.getY() >= 0 && position.getY() < getModel().getHeight();
    }
}
