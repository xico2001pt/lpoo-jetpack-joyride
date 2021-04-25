package controller;

import model.Position;
import model.arena.Arena;
import model.elements.Coin;
import model.elements.Element;
import model.elements.obstacles.Obstacle;

public class ElementController {
    private final Arena arena;

    public ElementController(Arena arena) {
        this.arena = arena;
    }

    public void moveElements() {
        for (Obstacle obstacle: arena.getObstacles())
            moveElement(obstacle, obstacle.getPosition().getLeft());

        for (Coin coin: arena.getCoins())
            moveElement(coin, coin.getPosition().getLeft());
    }

    private void moveElement(Element element, Position position) {
        // TODO:
        // Collisions
        // Coins and obstacles
    }
}
