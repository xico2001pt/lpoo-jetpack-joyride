package controller;

import gui.GUI;
import gui.LanternaGUI;
import model.arena.RandomArenaBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.WindowViewer;

import java.io.IOException;

class GameLoopTest {

    @Test
    void run() throws IOException {
        LanternaGUI gui = Mockito.mock(LanternaGUI.class);
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.QUIT);
        GameLoop gameLoop = new GameLoop(10, new ArenaController(new RandomArenaBuilder(10, 10)),
                new WindowViewer(gui), gui);

        gameLoop.run();
        Mockito.verify(gui, Mockito.times(1)).getNextAction();
    }
}