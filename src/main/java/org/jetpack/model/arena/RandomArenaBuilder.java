package org.jetpack.model.arena;

import org.jetpack.model.Position;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.ImageLibrary;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.obstacles.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random rng;

    // Instants in milliseconds
    private long nextCoinInst;
    private long nextObstacleInst;

    public RandomArenaBuilder(int width, int height) {
        super(width, height);

        this.rng = new Random();

        this.nextCoinInst = generateInstant(20, generateInstant(200, 1000));
        this.nextObstacleInst = generateInstant(20, generateInstant(200, 1000));
    }

    @Override
    public List<Coin> getCoins() {
        // TODO: IMPROVE
        List<Coin> coins = new ArrayList<>();

        if (getInstant() >= nextCoinInst) {
            // Add coins
            coins.add(generateCoin());

            // Generate new instant
            nextCoinInst = generateInstant(getInstant() + 1000, getInstant() + generateInstant(1500, 2000));
        }

        return coins;
    }

    @Override
    public List<Obstacle> getObstacles() {
        // TODO: IMPROVE
        List<Obstacle> obstacles = new ArrayList<>();

        if (getInstant() >= nextObstacleInst) {
            // Add obstacle
            obstacles.add(generateObstacle());

            // Generate new instant
            nextObstacleInst = generateInstant(getInstant() + 500, getInstant() + generateInstant(1000, 2000));
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
        int type = rng.nextInt(7);

        switch (type) {
            case 0: case 1:
                return new Laser(new Position(getWidth(), rng.nextInt(getHeight() - ImageLibrary.getLaser2Image().getNumberRows())));
            case 2: case 3:
                return new EnergyWall(new Position(getWidth(), rng.nextInt(getHeight() - ImageLibrary.getEnergyWall1Image().getNumberRows())));
            case 4:
                return new Missile(new Position(getWidth(), rng.nextInt(getHeight() - ImageLibrary.getMissileImage().getNumberRows())));
            case 5:
                return new ZigZag(new Position(getWidth(), rng.nextInt(getHeight() - ImageLibrary.getZigZagImage().getNumberRows())));
            case 6:
                return new StaticLaser(new Position(getWidth(), rng.nextInt(getHeight() - ImageLibrary.getStaticLaserImage(new Dimension(getWidth(), 1)).getNumberRows())), new Dimension(getWidth(), 1));
        }

        return null;
    }
}
