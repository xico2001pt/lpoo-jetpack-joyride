package model.arena;

import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.Obstacle;

import java.util.List;

public abstract class ArenaBuilder {
    private final int width;
    private final int height;

    public ArenaBuilder(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Arena createArena() {
        return new Arena(width, height, this);
    }

    public abstract List<Coin> getCoins(long instant);

    public abstract List<Obstacle> getObstacles(long instant);

    protected int getWidth() {
        return this.width;
    }

    protected int getHeight() {
        return this.height;
    }

    protected abstract Player createPlayer();
}
