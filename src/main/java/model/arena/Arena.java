package model.arena;

import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.Obstacle;

import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private final ArenaBuilder supplier;

    private Player player;

    private List<Obstacle> obstacles;
    private List<Coin> coins;

    public Arena(int width, int height, ArenaBuilder supplier) {
        this.width = width;
        this.height = height;
        this.supplier = supplier;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public Player getPlayer() {
        return player;
    }

}
