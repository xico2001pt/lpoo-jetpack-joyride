package org.jetpack.controller.game;

import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.model.menu.PauseMenu;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;
import org.jetpack.states.GameOverMenuState;
import org.jetpack.states.PauseMenuState;

import java.util.List;

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
        if (getModel().getPlayer().getLives() <= 0)
            gameLoop.setState(new GameOverMenuState(new GameOverMenu(getModel().getArenaBuilder().getInstant()/1000)));
        else if (action == GUI.ACTION.QUIT)
            gameLoop.stop();
        else if (action == GUI.ACTION.PAUSE)
            gameLoop.setState(new PauseMenuState(new PauseMenu(), gameLoop.getState()));

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
        Obstacle obstacle = (Obstacle) CollisionController.checkCollision(getModel().getObstacles(), getModel().getPlayer());
        if (obstacle != null) {
            getModel().getPlayer().takeDamage();
            getModel().getObstacles().remove(obstacle);
        }

        Coin coin = (Coin) CollisionController.checkCollision(getModel().getCoins(), getModel().getPlayer());
        if (coin != null) {
            getModel().getPlayer().addCoin();
            getModel().getCoins().remove(coin);
        }
    }
}
