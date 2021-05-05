package model.arena;

import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int width;
    private final int height;

    private Player player;

    private List<Obstacle> obstacles;
    private List<Coin> coins;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.obstacles = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addCoins(List<Coin> coins) {
        this.coins.addAll(coins);
    }

    public void addObstacles(List<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    public void removeObstacle() {
        // TODO
    }
}
