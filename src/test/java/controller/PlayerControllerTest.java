package controller;

import gui.GUI;
import model.Position;
import model.arena.Arena;
import model.elements.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    @Test
    void doActionNone() {

        Arena arena_mock = Mockito.mock(Arena.class);
        PlayerController controller = new PlayerController(arena_mock);

        controller.doAction(GUI.ACTION.NONE);
        Mockito.verify(arena_mock, Mockito.times(0)).getPlayer();
    }


    @Test
    void doActionDown() {

        Player player = new Player(new Position(5, 5));
        Arena arena = new Arena(10, 10);
        arena.setPlayer(player);
        PlayerController controller = new PlayerController(arena);
        controller.doAction(GUI.ACTION.DOWN);

        assertEquals(new Position(5, 6), arena.getPlayer().getPosition());
    }

    @Test
    void doActionCannotMove() {

        Player player = new Player(new Position(5, 0));
        Arena arena = new Arena(10, 10);
        arena.setPlayer(player);
        PlayerController controller = new PlayerController(arena);
        controller.doAction(GUI.ACTION.UP);

        assertEquals(new Position(5, 0), arena.getPlayer().getPosition());
    }
}