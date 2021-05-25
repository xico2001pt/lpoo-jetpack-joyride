package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.player.playerStates.SlowDownState;

import java.util.List;

public class ElementsController extends GameController {
    private static final long SPEED_UP_FREQUENCY = 1200; // Milliseconds it takes to speed up the game
    private long movementFrequency;                      // Milliseconds it takes to move an element
    private long movementFrequencyBackup;
    private long movementCounter;
    private long speedUpCounter;
    private long slowDownCounter;

    public ElementsController(Arena arena) {
        super(arena);

        this.movementFrequency = 200;
        this.movementFrequencyBackup = 200;
        this.movementCounter = 0;
        this.speedUpCounter = 0;
        this.slowDownCounter = 0;
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        this.movementCounter += elapsed;

        // When it changes from slow down effect to no slow down effect it resets the movement frequency
        if (this.slowDownCounter * (this.slowDownCounter - elapsed) < 0) this.movementFrequency = this.movementFrequencyBackup;

        this.slowDownCounter -= elapsed;

        if (this.slowDownCounter <= 0) this.speedUpCounter += elapsed;

        if (action == GUI.ACTION.NUMBER3 && getModel().getPlayer().getState() instanceof SlowDownState) {
            slowDownCounter = getModel().getPlayer().getState().getDuration();
            this.movementFrequencyBackup = this.movementFrequency;
            this.movementFrequency = 200;
        }

        while (this.movementCounter > movementFrequency) {
            moveElements(getModel().getObstacles());
            moveElements(getModel().getCoins());

            this.movementCounter -= movementFrequency;
        }

        System.out.println(this.speedUpCounter);

        while (this.speedUpCounter > SPEED_UP_FREQUENCY) {
            changeMovementFrequency();
            this.speedUpCounter -= SPEED_UP_FREQUENCY;
        }
    }

    private void changeMovementFrequency() {
        if (this.movementFrequency > 100) {
            this.movementFrequency = this.movementFrequency - 1;
            getModel().getArenaBuilder().setTimeCoefficient((float)(this.movementFrequency) / 100);
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
