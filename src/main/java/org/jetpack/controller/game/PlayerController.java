package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Pace;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.player.Player;
import org.jetpack.model.elements.movements.DownMovement;
import org.jetpack.model.elements.movements.UpMovement;
import org.jetpack.model.elements.player.playerStrategies.DoubleCoinsStrategy;
import org.jetpack.model.elements.player.playerStrategies.ImmortalStrategy;
import org.jetpack.model.elements.player.playerStrategies.NormalStrategy;
import org.jetpack.model.elements.player.playerStrategies.SlowDownStrategy;

public class PlayerController extends GameController {
    private static final long MOVEMENT_PERIOD = 150;
    private final Pace movementPace;
    private long powerUpCounter;
    private GUI.ACTION actionBefore;

    public PlayerController(Arena arena) {
        super(arena);
        this.powerUpCounter = 0;
        this.movementPace = new Pace(MOVEMENT_PERIOD);
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {

        powerUpCounter -= elapsed;
        Player player = getModel().getPlayer();

        if (powerUpCounter <= 0) player.setStrategy(new NormalStrategy());

        if (action == GUI.ACTION.NUMBER1 && player.buyPowerUp()) player.setStrategy(new ImmortalStrategy());
        else if (action == GUI.ACTION.NUMBER2 && player.buyPowerUp()) player.setStrategy(new DoubleCoinsStrategy());
        else if (action == GUI.ACTION.NUMBER3 && player.buyPowerUp()) player.setStrategy(new SlowDownStrategy());
        else if (action == GUI.ACTION.MOUSE_PRESSED && actionBefore == GUI.ACTION.NONE) {
            movementPace.resetCounter();
            player.setMovement(new UpMovement());
        }
        else if (action == GUI.ACTION.NONE && actionBefore == GUI.ACTION.MOUSE_PRESSED) {
            movementPace.resetCounter();
            player.setMovement(new DownMovement());
        }

        if (action == GUI.ACTION.NUMBER1 || action == GUI.ACTION.NUMBER2 || action == GUI.ACTION.NUMBER3)
            powerUpCounter = player.getState().getDuration();


        for (long i = movementPace.update(elapsed); i > 0; --i)
            movePlayer(player.getMovement().move(player.getPosition(), getModel()));

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
