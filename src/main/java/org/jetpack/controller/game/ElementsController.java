package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Pace;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.player.playerStates.SlowDownState;

import java.util.List;

public class ElementsController extends GameController {
    public static final long MIN_PERIOD = 100;         // Milliseconds per frame
    public static final long MAX_PERIOD = 200;         // Milliseconds per frame
    private static final long SPEED_UP_PERIOD = 1200;  // Milliseconds per frame

    private final Pace movementPace;
    private final Pace speedUpPace;
    private long movementPeriodBackup;
    private long slowDownCounter;

    public ElementsController(Arena arena) {
        super(arena);

        this.movementPace = new Pace(MAX_PERIOD);
        this.speedUpPace = new Pace(SPEED_UP_PERIOD);
        this.movementPeriodBackup = movementPace.getPeriod();
        this.slowDownCounter = 0;
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {

        // When it changes from slow down effect to no slow down effect it resets the movement frequency
        if (this.slowDownCounter * (this.slowDownCounter - elapsed) < 0) movementPace.setPeriod(this.movementPeriodBackup);

        this.slowDownCounter -= elapsed;

        if (action == GUI.ACTION.NUMBER3 && getModel().getPlayer().getState() instanceof SlowDownState) {
            slowDownCounter = getModel().getPlayer().getState().getDuration();
            this.movementPeriodBackup = movementPace.getPeriod();
            movementPace.setPeriod(MAX_PERIOD);
        }

        for (int i = movementPace.update(elapsed); i > 0; --i) {
            moveElements(getModel().getObstacles());
            moveElements(getModel().getCoins());
        }

        if (this.slowDownCounter <= 0) {
            for (int i = speedUpPace.update(elapsed); i > 0; --i)
                changeMovementFrequency();
        }
    }

    private void changeMovementFrequency() {
        if (movementPace.getPeriod() > MIN_PERIOD) {
            movementPace.setPeriod(movementPace.getPeriod() - 1);
            getModel().getArenaBuilder().setTimeCoefficient((float)(movementPace.getPeriod()) / 100);
        }
    }

    private void moveElements(List<? extends Element> elements) {
        for (Element element : elements)
            moveElement(element);
    }

    private void moveElement(Element element) {
        element.setPosition(element.getMovement().move(element.getPosition(), getModel()));
    }
}
