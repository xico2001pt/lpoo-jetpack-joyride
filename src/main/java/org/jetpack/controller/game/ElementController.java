package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;

public class ElementController extends GameController {
    private final long movementFrequency; // Milliseconds it takes to move an element
    private long elapsed;

    public ElementController(Arena arena) {
        super(arena);
        this.movementFrequency = 100;
        this.elapsed = 0;
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        this.elapsed += elapsed;

        while (this.elapsed > movementFrequency) {
            for (Obstacle obstacle: getModel().getObstacles())
                moveElement(obstacle, obstacle.getPosition().getLeft());

            for (Coin coin: getModel().getCoins())
                moveElement(coin, coin.getPosition().getLeft());

            this.elapsed -= movementFrequency;
        }
    }

    private void moveElement(Element element, Position position) {
        element.setPosition(position);
    }
}
