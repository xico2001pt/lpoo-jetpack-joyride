package org.jetpack.controller;

import org.jetpack.gui.GUI;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Obstacle;
import org.jetpack.viewer.WindowViewer;

import java.util.List;

public class ArenaController {
    private final Arena arena;
    private final ArenaBuilder arenaBuilder;
    private final PlayerController playerController;
    private final ElementController elementController;

    public ArenaController(ArenaBuilder arenaBuilder) {
        this.arenaBuilder = arenaBuilder;
        this.arena = arenaBuilder.createArena();
        this.playerController = new PlayerController(arena);
        this.elementController = new ElementController(arena);
    }

    public Arena getArena() {
        return this.arena;
    }

    public ArenaBuilder getArenaBuilder() {
        return this.arenaBuilder;
    }

    public void updateArena(GUI.ACTION action, long elapsed) {
        playerController.doAction(action);
        elementController.moveElements(elapsed);

        handleCollisions();
        removeOutOfBoundariesElements(arena.getCoins());
        removeOutOfBoundariesElements(arena.getObstacles());
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
        for (Obstacle obstacle: arena.getObstacles()) {
            if (checkElementCollision(obstacle, arena.getPlayer())) {
                arena.getObstacles().remove(obstacle);
                arena.getPlayer().setLives(arena.getPlayer().getLives() - 1);
                break;
            }
        }

        for (Coin coin : arena.getCoins()) {
            if (checkElementCollision(coin, arena.getPlayer())) {
                arena.getCoins().remove(coin);
                arena.getPlayer().setCoins(arena.getPlayer().getCoins() + 1);
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
