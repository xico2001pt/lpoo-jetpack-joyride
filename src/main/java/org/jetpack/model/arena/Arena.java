package org.jetpack.model.arena;

import org.jetpack.controller.game.CollisionController;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.player.Player;
import org.jetpack.model.elements.obstacles.Obstacle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final Dimension dimension;
    private final ArenaBuilder arenaBuilder;

    private Player player;

    private final List<Obstacle> obstacles;
    private final List<Coin> coins;

    public Arena(int width, int height, ArenaBuilder arenaBuilder) {
        this.dimension = new Dimension(width, height);
        this.arenaBuilder = arenaBuilder;

        this.obstacles = new ArrayList<>();
        this.coins = new ArrayList<>();
    }

    public int getHeight() {
        return dimension.height;
    }

    public int getWidth() {
        return dimension.width;
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
        for (Coin c: coins) if (CollisionController.checkCollision(coins, c) == null) this.coins.add(c);
    }

    public void addObstacles(List<Obstacle> obstacles) {
        for (Obstacle o: obstacles) if (CollisionController.checkCollision(obstacles, o) == null) this.obstacles.add(o);
    }
}
