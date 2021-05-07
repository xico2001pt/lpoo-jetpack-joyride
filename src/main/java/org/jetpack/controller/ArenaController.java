package org.jetpack.controller;

import org.jetpack.gui.GUI;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;
import java.util.List;

public class ArenaController extends GameController {
    private final PlayerController playerController;
    private final ElementController elementController;

    public ArenaController(ArenaBuilder arenaBuilder) {
        super(arenaBuilder);
        this.playerController = new PlayerController(getArena());
        this.elementController = new ElementController(getArena());
    }

    @Override
    public void updateArena(GUI.ACTION action, long elapsed) {
        playerController.doAction(action);
        elementController.moveElements(elapsed);

        handleCollisions();
        removeOutOfBoundariesElements(getArena().getCoins());
        removeOutOfBoundariesElements(getArena().getObstacles());
    }

    private void removeOutOfBoundariesElements(List<? extends Element> elements) {
        for (int i = elements.size() - 1; i >= 0; --i) {
            if (elements.get(i).getPosition().getX() + elements.get(i).getImage().getNumberCol() < 0) {
                elements.remove(i);
            }
        }
    }

    private void handleCollisions() {
        // TODO: code smell. talvez criar obstacle cotroller e coin org.jetpack.controller?
        for (Obstacle obstacle: getArena().getObstacles()) {
            if (checkElementCollision(obstacle, getArena().getPlayer())) {
                getArena().getObstacles().remove(obstacle);
                getArena().getPlayer().setLives(getArena().getPlayer().getLives() - 1);
                break;
            }
        }

        for (Coin coin : getArena().getCoins()) {
            if (checkElementCollision(coin, getArena().getPlayer())) {
                getArena().getCoins().remove(coin);
                getArena().getPlayer().setCoins(getArena().getPlayer().getCoins() + 1);
                break;
            }
        }
    }

    private boolean checkElementCollision(Element a, Element b) {
        int x1 = a.getPosition().getX(), y1 = a.getPosition().getY();
        int width1 = a.getImage().getNumberCol(), height1 = a.getImage().getNumberRows();

        int x2 = b.getPosition().getX(), y2 = b.getPosition().getY();
        int width2 = b.getImage().getNumberCol(), height2 = b.getImage().getNumberRows();

        return checkBoxCollision(x1, y1, width1, height1, x2, y2, width2, height2) && checkImageCollision(a, b);
    }

    private boolean checkBoxCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        return x1 < x2 + width2 && x1 + width1 > x2 && y1 < y2 + height2 && y1 + height1 > y2;
    }

    private boolean checkImageCollision(Element a, Element b) {
        // TODO
        return true;
    }
}
