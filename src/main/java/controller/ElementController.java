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

        element.setPosition(position);

        if (checkCollision(element, arena.getPlayer())) {
            if (element instanceof Coin) {
                arena.getCoins().remove(element);
                arena.getPlayer().setCoins(arena.getPlayer().getCoins() + 1);
            } else if (element instanceof Obstacle) {
                arena.getObstacles().remove(element);
                arena.getPlayer().setLives(arena.getPlayer().getLives() - 1);
            }
        }
    }

    private boolean checkCollision(Element a, Element b) {
        return checkBoxCollision(a, b) && checkImageCollision(a, b);
    }

    private boolean checkBoxCollision(Element a, Element b) {
        return !(a.getPosition().getX() + a.getImage().getNumberCol() < b.getPosition().getX() ||
                a.getPosition().getX() > b.getPosition().getX() + b.getImage().getNumberCol() ||
                a.getPosition().getY() < b.getPosition().getY() + b.getImage().getNumberRows() ||
                a.getPosition().getY() + a.getImage().getNumberRows() > b.getPosition().getY());
    }

    private boolean checkImageCollision(Element a, Element b) {
        // TODO
        return true;
    }
}
