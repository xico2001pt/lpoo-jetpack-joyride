package org.jetpack.model.arena;

import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private final ArenaBuilder arenaBuilder;

    private Player player;

    private List<Obstacle> obstacles;
    private List<Coin> coins;

    public Arena(int width, int height, ArenaBuilder arenaBuilder) {
        this.width = width;
        this.height = height;
        this.arenaBuilder = arenaBuilder;

        this.obstacles = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArenaBuilder getArenaBuilder() {
        return arenaBuilder;
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
}
