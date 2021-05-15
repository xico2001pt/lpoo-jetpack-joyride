package org.jetpack.model.arena;

import org.jetpack.model.Dimensions;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.obstacles.Obstacle;

import java.util.List;

public abstract class ArenaBuilder {
    private final Dimensions dimensions;

    private long instant;

    public ArenaBuilder(int width, int height) {
        this.dimensions = new Dimensions(width, height);

        this.instant = 0;
    }

    public Arena createArena() {
        Arena arena = new Arena(dimensions.getWidth(), dimensions.getHeight(), this);
        arena.setPlayer(createPlayer());

        return arena;
    }

    public void incrementInstant(long delta) {
        if (delta > 0) this.instant += delta;
    }

    public abstract List<Coin> getCoins();

    public abstract List<Obstacle> getObstacles();

    protected int getWidth() {
        return this.dimensions.getWidth();
    }

    protected int getHeight() {
        return this.dimensions.getHeight();
    }

    protected long getInstant() {
        return this.instant;
    }

    protected abstract Player createPlayer();
}
