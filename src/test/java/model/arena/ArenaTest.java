package model.arena;

import model.Position;
import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.EnergyWall;
import model.elements.obstacles.Laser;
import model.elements.obstacles.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {

    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(20, 20);
    }

    @Test
    void setPlayer() {
        Player p = new Player(new Position(10, 10));
        arena.setPlayer(p);
        assertEquals(p, arena.getPlayer());
    }

    @Test
    void addCoins() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(new Position(5,5)));
        coins.add(new Coin(new Position(15,15)));
        arena.addCoins(coins);
        assertEquals(coins, arena.getCoins());
    }

    @Test
    void addObstacles() {
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new EnergyWall(new Position(5,5)));
        obstacles.add(new Laser(new Position(15,15)));
        arena.addObstacles(obstacles);
        assertEquals(obstacles, arena.getObstacles());
    }

    @Test
    void removeObstacle() {
        // TODO
    }
}