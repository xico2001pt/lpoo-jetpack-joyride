package model.arena;

import model.Position;
import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomArenaBuilder extends ArenaBuilder {
    private final Random rng;

    // Instants in milliseconds
    private long nextCoinInst;
    private long nextObstacleInst;

    public RandomArenaBuilder(int width, int height) {
        super(width, height);

        this.rng = new Random();

        this.nextCoinInst = generateInstant(0, generateInstant(100, 1000));
        this.nextObstacleInst = generateInstant(0, generateInstant(100, 1000));
    }

    @Override
    public List<Coin> getCoins(long instant) {
        // TODO: IMPROVE
        List<Coin> coins = new ArrayList<>();

        if (instant >= nextCoinInst) {
            coins.add(generateCoin());
            coins.add(generateCoin());
        }

        return coins;
    }

    @Override
    public List<Obstacle> getObstacles(long instant) {
        // TODO: IMPROVE
        List<Obstacle> obstacles = new ArrayList<>();

        if (instant >= nextObstacleInst) {
            obstacles.add(generateObstacle());
            obstacles.add(generateObstacle());
        }

        return obstacles;
    }

    @Override
    protected Player createPlayer() {
        return new Player(new Position(1, getHeight() / 2));
    }

    private long generateInstant(long lower, long upper) {
        // TODO
        return 0;
    }

    private Coin generateCoin() {
        // TODO
        return null;
    }

    private Obstacle generateObstacle() {
        // TODO
        return null;
    }
}
