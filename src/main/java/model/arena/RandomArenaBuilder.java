package model.arena;

import model.Position;
import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.EnergyWall;
import model.elements.obstacles.Laser;
import model.elements.obstacles.Obstacle;

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

        this.nextCoinInst = generateInstant(0, generateInstant(0, 10));
        this.nextObstacleInst = generateInstant(0, generateInstant(0, 10));
    }

    @Override
    public List<Coin> getCoins(long instant) {
        // TODO: IMPROVE
        List<Coin> coins = new ArrayList<>();

        if (instant >= nextCoinInst) {
            // Add coins
            coins.add(generateCoin());
            //coins.add(generateCoin());

            // Generate new instant
            nextCoinInst = generateInstant(instant + 2, instant + generateInstant(2, 10));
        }

        return coins;
    }

    @Override
    public List<Obstacle> getObstacles(long instant) {
        // TODO: IMPROVE
        List<Obstacle> obstacles = new ArrayList<>();

        if (instant >= nextObstacleInst) {
            // Add obstacle
            obstacles.add(generateObstacle());
            //obstacles.add(generateObstacle());

            // Generate new instant
            nextObstacleInst = generateInstant(instant + 20, instant + generateInstant(20, 30));
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
        int type = rng.nextInt(2);

        switch (type) {
            case 0:
                return new Laser(new Position(getWidth() - 1, rng.nextInt(getHeight())));  // TODO: GERAR HEIGHT COM MAIS CUIDADO
            case 1:
                return new EnergyWall(new Position(getWidth() - 1, rng.nextInt(getHeight()))); // TODO: same
        }

        return null;
    }
}
