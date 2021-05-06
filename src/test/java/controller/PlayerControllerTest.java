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
}