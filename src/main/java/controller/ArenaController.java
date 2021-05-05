package controller;

import gui.GUI;
import model.Position;
import model.arena.ArenaBuilder;
import model.elements.Coin;
import model.elements.Element;
import model.elements.obstacles.Obstacle;
import viewer.WindowViewer;

import java.io.IOException;

public class ArenaController extends GameController {
    private final ArenaBuilder arenaBuilder;
    private final PlayerController playerController;
    private final ElementController elementController;
    private final WindowViewer viewer;
    private final GUI gui;

    public ArenaController(ArenaBuilder arenaBuilder, WindowViewer viewer, GUI gui) {
        super(arenaBuilder.createArena());

        this.arenaBuilder = arenaBuilder;
        this.playerController = new PlayerController(getArena());
        this.elementController = new ElementController(getArena());
        this.viewer = viewer;
        this.gui = gui;
    }

    public void start(int fps) throws IOException {
        long millisecondsPerFrame = 1000 / fps;
        long lastInstant = System.currentTimeMillis(), currentInstant, elapsedInstants;

        while (getArena().getPlayer().getLives() > 0) {
            currentInstant = System.currentTimeMillis();
            elapsedInstants = currentInstant - lastInstant;

            GUI.ACTION action = processInput();
            if (action == GUI.ACTION.QUIT) break;
            update(action, elapsedInstants);
            render();

            lastInstant = currentInstant;
            try {
                Thread.sleep(Math.max(0, currentInstant + millisecondsPerFrame - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        viewer.close();
    }

    private GUI.ACTION processInput() throws IOException {
        return gui.getNextAction();
    }

    private void update(GUI.ACTION action, long elapsed) {
        arenaBuilder.incrementInstant(elapsed);
        getArena().addCoins(arenaBuilder.getCoins());
        getArena().addObstacles(arenaBuilder.getObstacles());

        playerController.doAction(action);
        elementController.moveElements();
        handleCollisions();
    }

    private void handleCollisions() {
        // TODO: code smell. talvez criar obstacle cotroller e coin controller?
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

    private void render() throws IOException {
        viewer.draw(getArena());
    }
}
