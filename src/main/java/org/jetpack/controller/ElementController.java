package org.jetpack.controller;

import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;

public class ElementController {
    private final Arena arena;
    private final long movementFrequency; // Milliseconds it takes to move an element
    private long elapsed;

    public ElementController(Arena arena) {
        this.arena = arena;
        this.movementFrequency = 100;
        this.elapsed = 0;
    }

    public void moveElements(long elapsed) {
        this.elapsed += elapsed;

        while (this.elapsed > movementFrequency) {
            for (Obstacle obstacle: arena.getObstacles())
                moveElement(obstacle, obstacle.getPosition().getLeft());

            for (Coin coin: arena.getCoins())
                moveElement(coin, coin.getPosition().getLeft());

            this.elapsed -= movementFrequency;
        }
    }

    private void moveElement(Element element, Position position) {
        element.setPosition(position);
    }
}
