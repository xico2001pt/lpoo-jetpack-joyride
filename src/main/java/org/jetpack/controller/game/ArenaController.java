package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.Menu;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;
import org.jetpack.states.MainMenuState;

import java.util.List;

public class ArenaController extends GameController {
    private final PlayerController playerController;
    private final ElementController elementController;

    public ArenaController(Arena arena) {
        super(arena);
        this.playerController = new PlayerController(getModel());
        this.elementController = new ElementController(getModel());
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        if (getModel().getPlayer().getLives() <= 0 || action == GUI.ACTION.QUIT)
            gameLoop.setState(new MainMenuState(new Menu()));

        getModel().getArenaBuilder().incrementInstant(elapsed);
        getModel().addCoins(getModel().getArenaBuilder().getCoins());
        getModel().addObstacles(getModel().getArenaBuilder().getObstacles());

        playerController.update(gameLoop, action, elapsed);
        elementController.update(gameLoop, action, elapsed);

        handleCollisions();
        removeOutOfBoundariesElements(getModel().getCoins());
        removeOutOfBoundariesElements(getModel().getObstacles());
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
        for (Obstacle obstacle: getModel().getObstacles()) {
            if (checkElementCollision(obstacle, getModel().getPlayer())) {
                getModel().getObstacles().remove(obstacle);
                getModel().getPlayer().setLives(getModel().getPlayer().getLives() - 1);
                break;
            }
        }

        for (Coin coin : getModel().getCoins()) {
            if (checkElementCollision(coin, getModel().getPlayer())) {
                getModel().getCoins().remove(coin);
                getModel().getPlayer().setCoins(getModel().getPlayer().getCoins() + 1);
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