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

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ArenaController extends GameController {
    private final PlayerController playerController;
    private final ElementsController elementsController;

    public ArenaController(Arena arena) {
        super(arena);
        this.playerController = new PlayerController(getModel());
        this.elementsController = new ElementsController(getModel());
    }

    @Override
    public void update(GameLoop gameLoop, GUI.ACTION action, long elapsed) {
        if (getModel().getPlayer().getLives() <= 0 || action == GUI.ACTION.QUIT)
            gameLoop.setState(new MainMenuState(new Menu()));

        getModel().getArenaBuilder().incrementInstant(elapsed);
        getModel().addCoins(getModel().getArenaBuilder().getCoins());
        getModel().addObstacles(getModel().getArenaBuilder().getObstacles());

        playerController.update(gameLoop, action, elapsed);
        elementsController.update(gameLoop, action, elapsed);

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
            if (CollisionController.checkElementCollision(obstacle, getModel().getPlayer())) {
                getModel().getObstacles().remove(obstacle);
                getModel().getPlayer().setLives(getModel().getPlayer().getLives() - 1);
                break;
            }
        }

        for (Coin coin : getModel().getCoins()) {
            if (CollisionController.checkElementCollision(coin, getModel().getPlayer())) {
                getModel().getCoins().remove(coin);
                getModel().getPlayer().setCoins(getModel().getPlayer().getCoins() + 1);
                break;
            }
        }
    }

}
