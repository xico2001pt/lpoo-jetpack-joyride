package controller;

import model.Position;
import model.arena.Arena;
import model.elements.Coin;
import model.elements.Element;
import model.elements.obstacles.Obstacle;

public class ElementController {
    private final Arena arena;
    private long movementFrequency; // Milliseconds it takes to move an element
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
        // TODO:
        // Collisions
        // Coins and obstacles

        element.setPosition(position);
    }
}
