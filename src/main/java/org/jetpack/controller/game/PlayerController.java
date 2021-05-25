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
import org.jetpack.model.elements.player.playerStates.NormalState;
import org.jetpack.model.elements.player.playerStates.SlowDownState;

public class PlayerController extends GameController {
    private final long movementFrequency; // Milliseconds it takes to move an element
    private long movementCounter;
    private long powerUpCounter;
    private GUI.ACTION actionBefore;

    public PlayerController(Arena arena) {
        super(arena);
        this.movementFrequency = 150;
        this.movementCounter = 0;
        this.powerUpCounter = 0;
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {

        movementCounter += elapsed;
        powerUpCounter -= elapsed;
        Player player = getModel().getPlayer();

        if (powerUpCounter <= 0) player.setState(new NormalState());

        if (action == GUI.ACTION.NUMBER1 && player.buyPowerUp()) player.setState(new ImmortalState());
        else if (action == GUI.ACTION.NUMBER2 && player.buyPowerUp()) player.setState(new DoubleCoinsState());
        else if (action == GUI.ACTION.NUMBER3 && player.buyPowerUp()) player.setState(new SlowDownState());
        else if (action == GUI.ACTION.MOUSE_PRESSED && actionBefore == GUI.ACTION.NONE) {
            movementCounter = 0;
            player.setMovement(new UpMovement());
        }
        else if (action == GUI.ACTION.NONE && actionBefore == GUI.ACTION.MOUSE_PRESSED) {
            movementCounter = 0;
            player.setMovement(new DownMovement());
        }

        if (action == GUI.ACTION.NUMBER1 || action == GUI.ACTION.NUMBER2 || action == GUI.ACTION.NUMBER3)
            powerUpCounter = player.getState().getDuration();

        while (this.movementCounter > movementFrequency) {
            movePlayer(player.getMovement().move(player.getPosition(), getModel()));
            movementCounter -= movementFrequency;
        }

        if (action == GUI.ACTION.MOUSE_PRESSED || action == GUI.ACTION.NONE) actionBefore = action;
    }

    private void movePlayer(Position position) {
        if (!canMove(position)) return;
        getModel().getPlayer().setPosition(position);
    }

    private boolean canMove(Position position) {
        return position.getY() >= 0 && position.getY() < getModel().getHeight();
    }
}
