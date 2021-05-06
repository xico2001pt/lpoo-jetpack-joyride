package viewer;

import gui.GUI;
import model.Position;
import model.arena.Arena;
import model.elements.Coin;
import model.elements.Player;
import model.elements.obstacles.EnergyWall;
import model.elements.obstacles.Laser;
import model.elements.obstacles.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArenaViewerTest {
    private GUI gui;
    private ArenaViewer viewer;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = new Arena(10, 10);
        gui = Mockito.mock(GUI.class);
        viewer = new ArenaViewer(gui);

        arena.addObstacles(Arrays.asList(new Laser(new Position(4, 5)), new EnergyWall(new Position(5, 6))));
        arena.addCoins(Arrays.asList(new Coin(new Position(1, 2)), new Coin(new Position(2, 3)), new Coin(new Position(3, 4))));
        arena.setPlayer(new Player(new Position(5, 8)));
    }

    @Test
    void drawObstacles() throws IOException {
        viewer.draw(arena);

        Mockito.verify(gui, Mockito.times(1)).drawElement(new Laser(new Position(4, 5)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(new EnergyWall(new Position(5, 6)));
        Mockito.verify(gui, Mockito.times(2)).drawElement(Mockito.any(Obstacle.class));
    }

    @Test
    void drawCoins() throws IOException {
        viewer.draw(arena);

        Mockito.verify(gui, Mockito.times(1)).drawElement(new Coin(new Position(1, 2)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(new Coin(new Position(2, 3)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(new Coin(new Position(3, 4)));
        Mockito.verify(gui, Mockito.times(3)).drawElement(Mockito.any(Coin.class));
    }

    @Test
    void drawPlayer() throws IOException {
        viewer.draw(arena);

        Mockito.verify(gui, Mockito.times(1)).drawElement(new Player(new Position(5, 8)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(Mockito.any(Player.class));
    }
}