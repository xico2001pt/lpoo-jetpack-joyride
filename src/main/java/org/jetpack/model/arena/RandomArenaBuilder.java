package org.jetpack.model.arena;

import org.jetpack.model.Position;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.player.Player;
import org.jetpack.model.elements.obstacles.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class RandomArenaBuilder extends ArenaBuilder {

    private static final int INITIAL_MIN_INST = 20;
    private static final int INITIAL_LOWER_BOUND_MAX_INST = 200;
    private static final int INITIAL_UPPER_BOUND_MAX_INST = 1000;
    private static final int MIN_INST = 1000;
    private static final int LOWER_BOUND_MAX_INST = 1500;
    private static final int UPPER_BOUND_MAX_INST = 2000;

    private final Random rng;

    // Instants in milliseconds
    private long nextCoinInst;
    private long nextObstacleInst;

    public RandomArenaBuilder(int width, int height) {
        super(width, height);

        this.rng = new Random();

        this.nextCoinInst = (long) (generateInstant(INITIAL_MIN_INST, generateInstant(INITIAL_LOWER_BOUND_MAX_INST,
                INITIAL_UPPER_BOUND_MAX_INST)) * this.timeCoefficient);
        this.nextObstacleInst = (long) (generateInstant(INITIAL_MIN_INST, generateInstant(INITIAL_LOWER_BOUND_MAX_INST,
                INITIAL_UPPER_BOUND_MAX_INST)) * this.timeCoefficient);
    }

    @Override
    public List<Coin> getCoins() {
        List<Coin> coins = new ArrayList<>();

        if (getInstant() >= nextCoinInst) {
            // Add coins
            coins.add(generateCoin());

            // Generate new instant
            nextCoinInst = (long) (generateInstant(MIN_INST, generateInstant(LOWER_BOUND_MAX_INST, UPPER_BOUND_MAX_INST))
                    * this.timeCoefficient) + getInstant();
        }

        return coins;
    }

    @Override
    public List<Obstacle> getObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();

        if (getInstant() >= nextObstacleInst) {
            // Add obstacle
            obstacles.add(generateObstacle());

            // Generate new instant
            nextObstacleInst = (long) (generateInstant(MIN_INST,
                    generateInstant(LOWER_BOUND_MAX_INST/2, UPPER_BOUND_MAX_INST)) * this.timeCoefficient) + getInstant();
        }

        return obstacles;
    }

    @Override
    protected Player createPlayer() {
        return new Player(new Position(1, getHeight() / 2));
    }

    private long generateInstant(long lower, long upper) {
        return abs(rng.nextLong()) % (upper - lower + 1) + lower;
    }

    private Coin generateCoin() {
        return new Coin(new Position(getWidth() - 1, rng.nextInt(getHeight())));
    }

    private Obstacle generateObstacle() {
        int type = rng.nextInt(10);

        switch (type) {
            case 0: case 1: case 2:
                return new Laser(new Position(getWidth(),
                        rng.nextInt(getHeight() - ImageLibrary.getLaserImage().getNumberRows())));
            case 3: case 4: case 5:
                return new EnergyWall(new Position(getWidth(),
                        rng.nextInt(getHeight() - ImageLibrary.getEnergyWallImage().getNumberRows())));
            case 6:
                return new Missile(new Position(getWidth(),
                        rng.nextInt(getHeight() - ImageLibrary.getMissileImage().getNumberRows())));
            case 7: case 8:
                return new ZigZag(new Position(getWidth(),
                        rng.nextInt(getHeight() - ImageLibrary.getZigZagImage().getNumberRows())));
            case 9:
                return new StaticLaser(new Position(getWidth(),
                        rng.nextInt(getHeight() - ImageLibrary.getStaticLaserImage(new Dimension(getWidth(), 1)).getNumberRows())),
                        new Dimension(getWidth(), 1));
        }

        return null;
    }
}
