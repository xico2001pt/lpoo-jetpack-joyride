package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;

import java.util.List;

public class ElementsController extends GameController {
    private final long movementFrequency; // Milliseconds it takes to move an element
    private long counter;

    public ElementsController(Arena arena) {
        super(arena);

        this.movementFrequency = 100;
        this.counter = 0;
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        this.counter += elapsed;

        while (this.counter > movementFrequency) {
            moveElements(getModel().getObstacles());
            moveElements(getModel().getCoins());

            this.counter -= movementFrequency;
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
