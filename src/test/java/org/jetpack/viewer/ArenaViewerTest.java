/*
package org.jetpack.viewer;

import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Coin;
import org.jetpack.model.elements.Player;
import org.jetpack.model.elements.obstacles.EnergyWall;
import org.jetpack.model.elements.obstacles.Laser;
import org.jetpack.model.elements.obstacles.Obstacle;
import org.jetpack.viewer.game.ArenaViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

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
    void drawObstacles() {
        viewer.draw(arena);

        Mockito.verify(gui, Mockito.times(1)).drawElement(new Laser(new Position(4, 5)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(new EnergyWall(new Position(5, 6)));
        Mockito.verify(gui, Mockito.times(2)).drawElement(Mockito.any(Obstacle.class));
    }

    @Test
    void drawCoins() {
        viewer.draw(arena);

        Mockito.verify(gui, Mockito.times(1)).drawElement(new Coin(new Position(1, 2)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(new Coin(new Position(2, 3)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(new Coin(new Position(3, 4)));
        Mockito.verify(gui, Mockito.times(3)).drawElement(Mockito.any(Coin.class));
    }

    @Test
    void drawPlayer() {
        viewer.draw(arena);

        Mockito.verify(gui, Mockito.times(1)).drawElement(new Player(new Position(5, 8)));
        Mockito.verify(gui, Mockito.times(1)).drawElement(Mockito.any(Player.class));
    }
}*/
