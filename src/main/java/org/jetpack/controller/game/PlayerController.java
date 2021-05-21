package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.player.Player;
import org.jetpack.model.elements.movements.DownMovement;
import org.jetpack.model.elements.movements.UpMovement;
import org.jetpack.model.elements.player.playerStates.DoubleCoinsState;
import org.jetpack.model.elements.player.playerStates.ImmortalState;
import org.jetpack.model.elements.player.playerStates.SlowDownState;

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
        Player player = getModel().getPlayer();

        System.out.println(action);

        if (action == GUI.ACTION.POWER_UP1) player.setState(new ImmortalState());
        else if (action == GUI.ACTION.POWER_UP2) player.setState(new DoubleCoinsState());
        else if (action == GUI.ACTION.POWER_UP3) player.setState(new SlowDownState());
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
